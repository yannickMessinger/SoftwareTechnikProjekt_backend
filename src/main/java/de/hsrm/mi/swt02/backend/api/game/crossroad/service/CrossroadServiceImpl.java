package de.hsrm.mi.swt02.backend.api.game.crossroad.service;


import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightService;
import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightServiceImpl;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation of the {@link CrossroadService} interface. This service handles the creation and management 
 * of traffic lights at a crossroad.
*/
@Service
public class CrossroadServiceImpl implements CrossroadService {
    private List<TrafficLightService> tls;
    private Thread t;
    private boolean tlArrangement;
    private Logger logger = LoggerFactory.getLogger(CrossroadServiceImpl.class);

    /**
     * Constructor for creating an instance of the CrossroadServiceImpl.
     */
    public CrossroadServiceImpl(){
        tls = new ArrayList<>();
        tlArrangement = false;
    }

    /**
     * This method starts the thread that handles the change of states of the traffic lights at the crossroad.
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
     * This method stops the thread that handles the change of states of the traffic lights at the crossroad.
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
    public List<TrafficLightService> createTrafficLights(int numberOfTrafficLights) {
        for(int i = 0; i < numberOfTrafficLights; i++){
            TrafficLightService service = new TrafficLightServiceImpl(new TrafficLight());
            if(tlArrangement) service.changeCurrentState(Light.RED);
            tls.add(service);
            tlArrangement = !tlArrangement;
        } 
        return tls;   
    }

    /**
     * This method changes the state of all traffic lights at the crossroad.
     * 
     */
    @Override
    public void changeStates() {
        boolean toggle = false;
        while(!Thread.currentThread().isInterrupted()){
            try {
                if (!toggle) {
                    Thread.sleep(15000);
                } else {
                    Thread.sleep(3000);
                }
                toggle = !toggle;
                for(TrafficLightService service : tls){
                    service.changeCurrentState();
                }
            } catch (InterruptedException e) {
                logger.info("Thread interrupted. Ending changeStates() method");
                Thread.currentThread().interrupt();
                break;
            }
            
        }
    }

    /**
     * This method returns the thread that handles the change of states of the traffic lights at the crossroad.
     * 
     * @return the thread that handles the change of states of the traffic lights.
     */
    @Override
    public Thread getThread() {
        return t;
    }
}
