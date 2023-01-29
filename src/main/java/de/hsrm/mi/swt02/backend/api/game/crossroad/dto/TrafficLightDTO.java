package de.hsrm.mi.swt02.backend.api.game.crossroad.dto;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;


public record TrafficLightDTO(long tlId, Light currentState, long crId) {
    public static TrafficLightDTO from (TrafficLight tl) {
        return new TrafficLightDTO(
                tl.getId(), tl.getCurrentState(), tl.getCr().getId()
        );
    }
}
