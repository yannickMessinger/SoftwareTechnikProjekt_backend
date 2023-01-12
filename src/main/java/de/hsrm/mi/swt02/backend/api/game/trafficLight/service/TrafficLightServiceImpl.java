package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrafficLightServiceImpl implements TrafficLightService{
    @Autowired
    private TrafficLight tl;
    private Logger logger = LoggerFactory.getLogger(TrafficLightServiceImpl.class);

    public void start(){
        Light currentState = tl.currentState();
        while (true) {
            switch (currentState) {
                case GREEN:
                    logger.info("Now I am green");
                    try {
                        TimeUnit.SECONDS.sleep(8);
                        currentState = Light.GREENYELLOW;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case GREENYELLOW:
                    logger.info("Now I am green yellow");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        currentState = Light.RED;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case RED:
                    logger.info("Now I am red");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        currentState = Light.REDYELLOW;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case REDYELLOW:
                    logger.info("Now I am red yellow");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        currentState = Light.GREEN;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
