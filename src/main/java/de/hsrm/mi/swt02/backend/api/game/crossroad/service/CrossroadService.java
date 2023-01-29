package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

/**
 * The interface for a service that handles the management of a
 * {@link Crossroad}.
 * This interface defines methods for creating several {@link TrafficLight} at a
 * crossroad, starting and stopping the thread that handles the change of states
 * of all {@link TrafficLight} at a crossroad, and obtaining the thread.
 */
public interface CrossroadService {
    /**
     * This method creates a number of {@link TrafficLight} at the
     * {@link Crossroad}.
     * 
     * @param numberOfTrafficLights the number of {@link TrafficLight} to be
     *                              created.
     * @param crId                  the id of the {@link Crossroad}.
     * @return a list of the created traffic light services.
     */
    public List<TrafficLight> createTrafficLights(int numberOfTrafficLights, Long crId);

    /**
     * This method starts the thread that handles the change of states of all
     * {@link TrafficLight}
     * at the {@link Crossroad}.
     * 
     * @param crId the id of the {@link Crossroad}.
     */
    public void start(Long crId);

    /**
     * This method stops the thread that handles the change of states of the traffic
     * lights at the {@link Crossroad}.
     * 
     * @return the thread that was interrupted.
     */
    public void stop(Long crId);

    public Crossroad createCrossroad();

    /**
     * This method changes the state of all traffic lights at the {@link Crossroad}.
     * 
     * @param crId the id of the {@link Crossroad}.
     */
    public void changeStates(Long crId);

    /**
     * This method returns the thread that handles the change of states of the
     * traffic lights at the {@link Crossroad}.
     * 
     * @param crId the id of the {@link Crossroad}.
     * @return the thread that handles the change of states of the traffic lights.
     */
    public Thread getThread(Long crId);

    /**
     * This method returns the {@link Crossroad} with the given id.
     * 
     * @param crId the id of the {@link Crossroad}.
     * @return the {@link Crossroad} with the given id.
     */
    public Crossroad getCrossroad(Long crId);

    /**
     * This method deletes the {@link Crossroad} with the given id.
     * 
     * @param crId the id of the {@link Crossroad}.
     */
    public void deleteCrossroad(Long crId);

}
