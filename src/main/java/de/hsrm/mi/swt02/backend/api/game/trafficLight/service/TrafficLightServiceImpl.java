package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The implementation of the {@link TrafficLightService} interface. This service handles the management of a traffic light.
 */
@Service
public class TrafficLightServiceImpl implements TrafficLightService {
    private TrafficLight tl;
    private Logger logger = LoggerFactory.getLogger(TrafficLightServiceImpl.class);


    /**
     * Constructor for creating an instance of the TrafficLightServiceImpl.
     */
    public TrafficLightServiceImpl () {
    }

    /**
     * Constructor for creating an instance of the TrafficLightServiceImpl.
     *
     * @param tl the traffic light that this service will manage.
     */
    public TrafficLightServiceImpl (TrafficLight tl) {
        this.tl = tl;
    }

    /**
     * This method changes the current state of the traffic light managed by this service to the next state in the following order:
     * GREEN -> YELLOW -> RED -> REDYELLOW -> GREEN.
     */
    @Override
    public void changeCurrentState () {
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
    public void changeCurrentState (Light l) {
        tl.setCurrentState(l);
    }

    /**
     * This method returns the current state of the traffic light managed by this service.
     *
     * @return the current state of the traffic light.
     */
    @Override
    public Light getCurrentState () {
        return tl.getCurrentState();
    }

    /**
     * Sets the TrafficLight object in the Crossroad
     *
     * @param tl the TrafficLight object to be set in the Crossroad
     */
    @Override
    public void setTrafficLight (TrafficLight tl) {
        this.tl = tl;
    }
}
