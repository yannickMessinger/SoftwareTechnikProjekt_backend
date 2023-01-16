package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

@Service
public class TrafficLightServiceImpl implements TrafficLightService{
    private TrafficLight tl;
    Logger logger = LoggerFactory.getLogger(TrafficLightServiceImpl.class);
    
    @Override
    public void start(){
        while (!Thread.currentThread().isInterrupted()) {
            switch (tl.getCurrentState()) {
                case GREEN:
                    logger.info(tl.toString());
                    try {
                        //Thread.currentThread(); //Vlt den Thread zum schlafen legen
                        Thread.sleep(8000);
                        //TimeUnit.SECONDS.sleep(8);
                        tl.setCurrentState(Light.YELLOW);
                    } catch (InterruptedException e) {
                        /* e.printStackTrace(); */
                        Thread.currentThread().interrupt();
                    }
                    break;
                case YELLOW:
                    logger.info(tl.toString());
                    try {
                        Thread.sleep(2000);
                        //TimeUnit.SECONDS.sleep(2);
                        tl.setCurrentState(Light.RED);
                    } catch (InterruptedException e) {
                        /* e.printStackTrace(); */
                        Thread.currentThread().interrupt();
                    }
                    break;
                case RED:
                    logger.info(tl.toString());
                    try {
                        Thread.sleep(10000);
                        //TimeUnit.SECONDS.sleep(10);
                        tl.setCurrentState(Light.REDYELLOW);
                    } catch (InterruptedException e) {
                        /* e.printStackTrace(); */
                        Thread.currentThread().interrupt();
                    }
                    break;
                case REDYELLOW:
                    logger.info(tl.toString());
                    try {
                        Thread.sleep(2000);
                        //TimeUnit.SECONDS.sleep(2);
                        tl.setCurrentState(Light.GREEN);
                    } catch (InterruptedException e) {
                        /* e.printStackTrace(); */
                        Thread.currentThread().interrupt();
                    }
                    break;
            }
        }
    }

    @Override
    public TrafficLight createTrafficLight(TrafficLight tl) {
        this.tl = tl;
        return this.tl;
    }
}
