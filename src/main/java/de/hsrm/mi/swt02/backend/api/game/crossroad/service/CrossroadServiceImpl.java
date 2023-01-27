package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.game.crossroad.repository.CrossroadRepository;
import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightService;
import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation of the {@link CrossroadService} interface. This service
 * handles the creation and management
 * of traffic lights at a crossroad.
 */
@Service
public class CrossroadServiceImpl implements CrossroadService {

    @Autowired
    private TrafficLightService tls;
    @Autowired
    private CrossroadRepository crRepo;
    @Autowired
    private SimpMessagingTemplate messaging; // Autowired funktioniert nicht
    private Map<Long, Thread> crTMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(CrossroadServiceImpl.class);

    /**
     * Constructor for creating an instance of the CrossroadServiceImpl.
     */
    public CrossroadServiceImpl() {}

    public Crossroad createCrossroad() {
        Crossroad crossroad = crRepo.save(new Crossroad());
        return crossroad;
    }

    /**
     * This method starts the thread that handles the change of states of the
     * traffic lights at the crossroad.
     * 
     */
    @Override
    public void start(Long crId) {
        Crossroad cr = crRepo.findById(crId).get();
        Thread thread = new Thread(() -> {
            changeStates(crId);
        });
        crTMap.put(crId, thread);
        thread.start();
        cr.setRunning(true);
    }

    /**
     * This method stops the thread that handles the change of states of the traffic
     * lights at the crossroad.
     * 
     * @return the thread that was interrupted.
     */
    @Override
    public void stop(Long crId) {
        Thread thread = crTMap.get(crId);
        if (thread != null) {
            thread.interrupt();
            crRepo.findById(crId).get().setRunning(false);
        }
    }

    /**
     * This method creates a number of traffic lights at the crossroad.
     * 
     * @param numberOfTrafficLights the number of traffic lights to be created.
     * 
     * @return a list of the created traffic light services.
     */
    @Override
    @Transactional
    public List<TrafficLight> createTrafficLights(int numberOfTrafficLights, Long crId) {
        Crossroad cr = crRepo.findById(crId).get();
        for (int i = 0; i < numberOfTrafficLights; i++) {
            Long tlId = tls.createTrafficLight().getId();
            tls.getTrafficLight(tlId).setCr(cr);
            if (cr.isTlArrangement()) {
                tls.changeCurrentState(tlId, Light.RED);
            }
            cr.getTrafficLights().add(tls.getTrafficLight(tlId));
            cr.setTlArrangement(!cr.isTlArrangement());
        }
        return cr.getTrafficLights();
    }

    /**
     * This method changes the state of all traffic lights at the crossroad.
     * 
     */
    @Override
    public void changeStates(Long crId) {
        Crossroad cr = crRepo.findById(crId).get();
        boolean toggle = false;
        Map<Long, Light> tlMap = new HashMap<>();

        while (!crTMap.get(crId).isInterrupted()) {
            logger.info("Bin drin");
            try {
                if (!toggle) {
                    Thread.sleep(15000);
                } else {
                    Thread.sleep(3000);
                }
                toggle = !toggle;
                for (TrafficLight tl : cr.getTrafficLights()) {
                    tls.changeCurrentState(tl.getId());
                    tlMap.put(tl.getId(), tls.getCurrentState(tl.getId()));
                }
                messaging.convertAndSend("/topic/crossroad", tlMap);
            } catch (InterruptedException e) {
                logger.info("Thread interrupted. Ending changeStates() method");
                crTMap.get(crId).interrupt();
                cr.setRunning(false);
                break;
            }
        }
    }

    /**
     * This method returns the thread that handles the change of states of the
     * traffic lights at the crossroad.
     * 
     * @return the thread that handles the change of states of the traffic lights.
     */
    @Override
    public Thread getThread(Long crId) {
        return crTMap.get(crId);
    }

    @Override
    public Crossroad getCrossroad(Long crId){
        return crRepo.findById(crId).get();
    }

    @Override
    public void deleteCrossroad(Long crId){
        stop(crId);
        crRepo.deleteById(crId);
    }
}
