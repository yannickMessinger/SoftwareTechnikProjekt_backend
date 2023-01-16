package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;

/**
    The interface for a service that handles the management of a traffic light.
    This interface defines methods for changing the current state of a traffic light and getting the current state.
*/
public interface TrafficLightService {
    /**
     * This method changes the current state of the traffic light to the next state in the following order: 
     * GREEN -> YELLOW -> RED -> REDYELLOW -> GREEN.
     */
    public void changeCurrentState();

    /**
     * This method changes the current state of the traffic light to the given state.
     * 
     * @param l the new state of the traffic light.
     */
    public void changeCurrentState(Light l);

    /**
     * This method returns the current state of the traffic light.
     * 
     * @return the current state of the traffic light.
     */
    public Light getCurrentState();
}
