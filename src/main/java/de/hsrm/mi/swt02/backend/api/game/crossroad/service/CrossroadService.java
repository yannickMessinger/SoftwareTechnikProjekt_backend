package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightService;

/**

The interface for a service that handles the management of a crossroad.
This interface defines methods for creating traffic lights at a crossroad, starting and stopping the
thread that handles the change of states of the traffic lights, and obtaining the thread.

**/
public interface CrossroadService {
    /*
    * This method creates a number of traffic lights at the crossroad.
    * @param numberOfTrafficLights the number of traffic lights to be created.
    * @return a list of the created traffic light services.
    */
    public List<TrafficLightService> createTrafficLights(int numberOfTrafficLights);
    /**
     * This method starts the thread that handles the change of states of the traffic lights at the crossroad.
     */
    public void start();
    /**
     * This method stops the thread that handles the change of states of the traffic lights at the crossroad.
     * 
     * @return the thread that was interrupted.
     */
    public Thread stop();
    /**
     * This method changes the state of all traffic lights at the crossroad.
     */
    public void changeStates();
    /**
     * This method returns the thread that handles the change of states of the traffic lights at the crossroad.
     * 
     * @return the thread that handles the change of states of the traffic lights.
     */
    public Thread getThread();
}
