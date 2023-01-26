package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.game.trafficLight.repository.TrafficLightRepository;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

/**
The implementation of the {@link TrafficLightService} interface. This service handles the management of a traffic light.
*/
@Service
public class TrafficLightServiceImpl implements TrafficLightService{
    @Autowired
    private TrafficLightRepository tlRepo;
    private Logger logger = LoggerFactory.getLogger(TrafficLightServiceImpl.class);
    
    /**
     * Constructor for creating an instance of the TrafficLightServiceImpl.
     */
    public TrafficLightServiceImpl(){}


    /**
     * This method changes the current state of the traffic light managed by this service to the next state in the following order: 
     * GREEN -> YELLOW -> RED -> REDYELLOW -> GREEN.
     * 
     */
    @Override
    @Transactional
    public void changeCurrentState(Long tlId){
        TrafficLight tl = tlRepo.findById(tlId).get();
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

    /**
     * This method changes the current state of the traffic light managed by this service to the given state.
     * 
     * @param l the new state of the traffic light.
     */
    @Override
    public void changeCurrentState(Long tlId, Light l) {
        tlRepo.findById(tlId).get().setCurrentState(l);
    }

    /**
     * This method returns the current state of the traffic light managed by this service.
     * 
     * @return the current state of the traffic light.
     */
    @Override
    public Light getCurrentState(Long tlId) {
        return tlRepo.findById(tlId).get().getCurrentState();
    }

    @Override
    public TrafficLight createTrafficLight(){
        return tlRepo.save(new TrafficLight());
    }

    @Override
    public TrafficLight getTrafficLight(Long tlId) {
        return tlRepo.findById(tlId).get();
    }
}
