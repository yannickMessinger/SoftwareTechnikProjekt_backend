package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

@Service
public class TrafficLightServiceImpl implements TrafficLightService{
    private TrafficLight tl;
    private Logger logger = LoggerFactory.getLogger(TrafficLightServiceImpl.class);
    
    public TrafficLightServiceImpl(TrafficLight tl){
        this.tl = tl;
    }
    @Override
    public void changeCurrentState(){
        switch (tl.getCurrentState()) {
            case GREEN:
                tl.setCurrentState(Light.YELLOW);
                break;
            case YELLOW:
                tl.setCurrentState(Light.RED);
                break;
            case RED:
                tl.setCurrentState(Light.REDYELLOW);
                break;
            case REDYELLOW:
                tl.setCurrentState(Light.GREEN);
                break;
        }
        logger.info(tl.toString());
    }

    @Override
    public void changeCurrentState(Light l) {
        tl.setCurrentState(l);   
    }

    @Override
    public Light getCurrentState() {
        return tl.getCurrentState();
    }
}
