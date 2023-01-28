package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.Map;

import java.time.LocalDate;

public record GetMapObjectByPlayerIdDTO(
        long mapId,
        String mapName,
        LocalDate creationDate
) {

    public static GetMapObjectByPlayerIdDTO from (Map s) {
        return new GetMapObjectByPlayerIdDTO(
                s.getId(),
                s.getMapName(),
                s.getCreationDate()
        );
    }
}
