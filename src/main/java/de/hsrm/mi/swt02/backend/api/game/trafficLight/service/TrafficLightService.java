package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

/**
 * The TrafficLightService interface defines a set of methods for managing traffic lights.
 */
public interface TrafficLightService {
    /**
     * Changes the current state of a traffic light identified by its ID.
     * 
     * @param tlId the ID of the traffic light
     */
    public void changeCurrentState(Long tlId);

    /**
     * Changes the current state of a traffic light identified by its ID to the specified state.
     * 
     * @param tlId the ID of the traffic light
     * @param l the state to set the traffic light to
     */
    public void changeCurrentState(Long tlId, Light l);

    /**
     * Returns the current state of a traffic light identified by its ID.
     * 
     * @param tlId the ID of the traffic light
     * @return the current state of the traffic light
     */
    public Light getCurrentState(Long tlId);

    /**
     * Creates a new traffic light.
     * 
     * @return the newly created traffic light
     */
    public TrafficLight createTrafficLight();

    /**
     * Returns a traffic light identified by its ID.
     * 
     * @param tlId the ID of the traffic light
     * @return the traffic light
     */
    public TrafficLight getTrafficLight(Long tlId);
}
