package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;

import java.time.LocalDate;

public record GetMapResponseDTO(
        String mapName,
        LocalDate creationDate,
        int sizeX,
        int sizeY
) {
    public static GetMapResponseDTO from(Map map) {
        return new GetMapResponseDTO(
                map.getMapName(),
                map.getCreationDate(),
                map.getSizeX(),
                map.getSizeY()
        );
    }
}
