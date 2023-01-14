package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

public interface CrossroadService {
    public List<TrafficLight> createTrafficLights(int numberOfTrafficLights);
    public void start();
    public List<Thread> stop();
}
