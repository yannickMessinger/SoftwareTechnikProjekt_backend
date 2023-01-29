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
 * The TrafficLightServiceImpl class implements the {@link TrafficLightService} interface
 * and provides services to manipulate TrafficLight objects.
 * The class is annotated with @Service to indicate it is a Spring service bean.
 */
@Service
public class TrafficLightServiceImpl implements TrafficLightService{
    /* The TrafficLightRepository instance for accessing the database. Autowired by Spring framework. */
    @Autowired
    private TrafficLightRepository tlRepo;
    /* The Logger instance for logging purpose. */
    private Logger logger = LoggerFactory.getLogger(TrafficLightServiceImpl.class);
    
    /**
     * Default constructor
     */
    public TrafficLightServiceImpl(){}


    /**
     * Changes the current state of the traffic light with the given id.
     * Transactional annotation is used to ensure that the method is executed in a transaction.
     * @param tlId the id of the traffic light
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
     * Returns the current state of the traffic light with the given id.
     * @param tlId the id of the traffic light
     * @return the current state of the traffic light
     */
    @Override
    public Light getCurrentState(Long tlId) {
        return tlRepo.findById(tlId).get().getCurrentState();
    }

    /**
     * Creates and returns a new traffic light object.
     * @return the created traffic light object
     */
    @Override
    public TrafficLight createTrafficLight(){
        return tlRepo.save(new TrafficLight());
    }

    /**
     * Returns the traffic light object with the given id.
     * @param tlId the id of the traffic light
     * @return the traffic light object with the given id
     */
    @Override
    public TrafficLight getTrafficLight(Long tlId) {
        return tlRepo.findById(tlId).get();
    }
}
