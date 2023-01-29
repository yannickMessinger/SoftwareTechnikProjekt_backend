package de.hsrm.mi.swt02.backend.api.game.crossroad.dto;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

/**
 * A record class to represent a {@link TrafficLight} Data Transfer Object.
 */
public record TrafficLightDTO(long tlId, Light currentState, long crId) {
    /**
     * Converts a {@link TrafficLight} object to TrafficLightDTO object.
     * 
     * @param tl the {@link TrafficLight} object to convert
     * @return a new TrafficLightDTO object
     */
    public static TrafficLightDTO from (TrafficLight tl) {
        return new TrafficLightDTO(
                tl.getId(), tl.getCurrentState(), tl.getCr().getId()
        );
    }
}
