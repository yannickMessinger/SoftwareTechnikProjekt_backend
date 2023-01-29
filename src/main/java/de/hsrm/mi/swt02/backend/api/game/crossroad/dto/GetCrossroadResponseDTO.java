package de.hsrm.mi.swt02.backend.api.game.crossroad.dto;

import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;

import java.util.List;

/**
 * GetCrossroadResponseDTO is a data transfer object (DTO) class that contains
 * the information of a {@link Crossroad} and its {@link TrafficLight}.
 */
public record GetCrossroadResponseDTO(long crId, List<TrafficLightDTO> tl) {

    /**
     * A static method that converts a {@link Crossroad} object to a GetCrossroadResponseDTO object.
     * @param cr the {@link Crossroad} object to be converted.
     * @return a GetCrossroadResponseDTO object that represents the input
     */
    public static GetCrossroadResponseDTO from(Crossroad cr) {
        return new GetCrossroadResponseDTO(
                cr.getId(),
                cr.getTrafficLights()
                        .stream()
                        .map(TrafficLightDTO::from)
                        .toList());
    }
}