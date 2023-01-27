package de.hsrm.mi.swt02.backend.api.game.crossroad.dto;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;


public record TrafficLightDTO(long id, Light lightState, long crossRoadId) {
    public static TrafficLightDTO from (TrafficLight trafficLight) {
        return new TrafficLightDTO(
                trafficLight.getId(), trafficLight.getCurrentState(), trafficLight.getCr().getId()
        );
    }
}
