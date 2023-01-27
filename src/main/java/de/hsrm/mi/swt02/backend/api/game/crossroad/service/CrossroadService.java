package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

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
    public List<TrafficLight> createTrafficLights(int numberOfTrafficLights, Long crId);

    /**
     * This method starts the thread that handles the change of states of the traffic lights at the crossroad.
     */
    public void start(Long crId);

    /**
     * This method stops the thread that handles the change of states of the traffic lights at the crossroad.
     * 
     * @return the thread that was interrupted.
     */
    public void stop(Long crId);

    public Crossroad createCrossroad();
    /**
     * This method changes the state of all traffic lights at the crossroad.
     */
    public void changeStates(Long crId);

    /**
     * This method returns the thread that handles the change of states of the traffic lights at the crossroad.
     * 
     * @return the thread that handles the change of states of the traffic lights.
     */
    public Thread getThread(Long crId);

    public Crossroad getCrossroad(Long crId);
    
}
