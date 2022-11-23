package de.hsrm.mi.swt02.backend.api.maps.dtos;

import de.hsrm.mi.swt02.backend.api.maps.Map;
import de.hsrm.mi.swt02.backend.api.player.Player;

import java.time.LocalDate;
import java.util.Date;

public record GetMapResponseDTO(
        String mapName,
        LocalDate creationDate,
        Player owner) {

    public static GetMapResponseDTO from (Map map) {
        return new GetMapResponseDTO(
                map.getMapName(),
                map.getCreationDate(),
                map.getOwner());
    }
}
