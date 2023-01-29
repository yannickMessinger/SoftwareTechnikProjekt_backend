package de.hsrm.mi.swt02.backend.api.game.crossroad.dto;

import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;

import java.util.List;

public record GetCrossroadResponseDTO(long crId, List<TrafficLightDTO> tl) {

    public static GetCrossroadResponseDTO from (Crossroad cr) {
        return new GetCrossroadResponseDTO(
                cr.getId(),
                cr.getTrafficLights()
                        .stream()
                        .map(TrafficLightDTO::from)
                        .toList()
        );
    }
}