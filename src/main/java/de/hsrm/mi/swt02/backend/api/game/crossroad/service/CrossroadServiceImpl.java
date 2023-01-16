package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightService;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CrossroadServiceImpl implements CrossroadService {
    @Autowired
    private TrafficLightService tls;
    private List<TrafficLight> trafficLights;
    private List<Thread> threads;
    Logger logger = LoggerFactory.getLogger(CrossroadServiceImpl.class);

    public CrossroadServiceImpl(){
        threads = new ArrayList<>();
        trafficLights = new ArrayList<>();
    }

    
    @Override
    public void start() {
        logger.info("Crossroad is started");
        for(TrafficLight tl : trafficLights){
            Thread t = new Thread(() -> {
                tls.start();
            });
            t.start();
            threads.add(t);    
        }
    }

    @Override
    public List<Thread> stop() {
        for(Thread t : threads){
            t.interrupt();
        }
        return threads;
    }

    @Override
    public List<TrafficLight> createTrafficLights(int numberOfTrafficLights) {
        for(int i = 0; i < numberOfTrafficLights; i++){
            trafficLights.add(tls.createTrafficLight(new TrafficLight()));
        }
        return trafficLights;
        
    }
}
