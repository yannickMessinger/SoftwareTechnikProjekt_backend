package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

public interface TrafficLightService {
    public void start();
    public TrafficLight createTrafficLight(TrafficLight tl);
}
