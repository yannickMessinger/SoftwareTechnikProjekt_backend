package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;

public interface TrafficLightService {
    public void changeCurrentState();
    public void changeCurrentState(Light l);
    public Light getCurrentState();
}
