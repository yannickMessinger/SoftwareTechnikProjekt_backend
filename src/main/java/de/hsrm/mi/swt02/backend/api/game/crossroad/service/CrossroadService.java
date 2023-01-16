package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightService;

public interface CrossroadService {
    public List<TrafficLightService> createTrafficLights(int numberOfTrafficLights);
    public void start();
    public Thread stop();
    public void changeStates();
    public Thread getThread();
}
