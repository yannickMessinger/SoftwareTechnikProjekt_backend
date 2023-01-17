package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightService;
import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightServiceImpl;
import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;
import de.hsrm.mi.swt02.backend.websocket.model.crossroad.CrossroadMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation of the {@link CrossroadService} interface. This service
 * handles the creation and management
 * of traffic lights at a crossroad.
 */
@Service
public class CrossroadServiceImpl implements CrossroadService {

    private Crossroad cr;
    private TrafficLightService tls;
    private Thread t;
    @Autowired
    private SimpMessagingTemplate messaging;
    private Logger logger = LoggerFactory.getLogger(CrossroadServiceImpl.class);

    /**
     * Constructor for creating an instance of the CrossroadServiceImpl.
     */
    public CrossroadServiceImpl() {
        cr = new Crossroad();
        tls = new TrafficLightServiceImpl();
    }

    /**
     * Constructor for creating an instance of the CrossroadServiceImpl.
     * 
     * @param cr the new crossroad.
     */
    public CrossroadServiceImpl(Crossroad cr) {
        this.cr = cr;
        tls = new TrafficLightServiceImpl();
    }

    /**
     * This method starts the thread that handles the change of states of the
     * traffic lights at the crossroad.
     * 
     */
    @Override
    public void start() {
        logger.info("Crossroad is started");
        t = new Thread(() -> {
            changeStates();
        });
        t.start();
    }

    /**
     * This method stops the thread that handles the change of states of the traffic
     * lights at the crossroad.
     * 
     * @return the thread that was interrupted.
     */
    @Override
    public Thread stop() {
        t.interrupt();
        return t;
    }

    /**
     * This method creates a number of traffic lights at the crossroad.
     * 
     * @param numberOfTrafficLights the number of traffic lights to be created.
     * 
     * @return a list of the created traffic light services.
     */
    @Override
    public List<TrafficLight> createTrafficLights(int numberOfTrafficLights) {
        for (int i = 0; i < numberOfTrafficLights; i++) {
            TrafficLight tl = new TrafficLight();
            if (cr.isTlArrangement()) {
                tls.setTrafficLight(tl);
                tls.changeCurrentState(Light.RED);
            }
            cr.getTrafficLights().add(tl);
            cr.setTlArrangement(!cr.isTlArrangement());
        }
        return cr.getTrafficLights();
    }

    /**
     * This method changes the state of all traffic lights at the crossroad.
     * 
     */
    @Override
    public void changeStates() {
        boolean toggle = false;
        Map<String, Light> tlMap = new HashMap<>();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!toggle) {
                    Thread.sleep(15000);
                } else {
                    Thread.sleep(3000);
                }
                toggle = !toggle;
                for (TrafficLight tl : cr.getTrafficLights()) {
                    tls.setTrafficLight(tl);
                    tls.changeCurrentState();
                    tlMap.put(tl.getId(), tl.getCurrentState())
                }
                messaging.convertAndSend("/", new CrossroadMessage(0, tlMap));
            } catch (InterruptedException e) {
                logger.info("Thread interrupted. Ending changeStates() method");
                Thread.currentThread().interrupt();
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
    public Thread getThread() {
        return t;
    }

    /**
     * Retrieves a TrafficLight object by its ID.
     *
     * @param id the ID of the TrafficLight to retrieve
     * @return the TrafficLight with the specified ID, or null if no TrafficLight
     * with that ID exists in the list
     */
    @Override
    public TrafficLight getTrafficLightById(String id) {
        for(TrafficLight tl : cr.getTrafficLights()){
            if(tl.getId().equals(id)) return tl;
        }
        return null;
    }
}
