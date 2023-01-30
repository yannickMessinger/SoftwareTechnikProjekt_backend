package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.Map;

import java.time.LocalDate;

/**
 * DTO to transfer new map from backend to frontend
 * @param mapId id of map that is supposed to be transferred
 * @param mapName name of the map that was assigned by user
 * @param creationDate date when map was created
 * @param sizeX size in x direction 
 * @param sizeY size in y direction
 */
public record GetMapResponseDTO(
        long mapId,
        String mapName,
        LocalDate creationDate,
        int sizeX,
        int sizeY

) {
    public static GetMapResponseDTO from (Map map) {
        return new GetMapResponseDTO(
                map.getId(),
                map.getMapName(),
                map.getCreationDate(),
                map.getSizeX(),
                map.getSizeY()

        );
    }
}
