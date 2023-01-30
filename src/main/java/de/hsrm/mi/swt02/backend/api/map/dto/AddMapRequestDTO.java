package de.hsrm.mi.swt02.backend.api.map.dto;

import java.time.LocalDate;
/**
 * DTO to transfer new map from frontend to backend
 * @param mapName of the map that is supposed to be added to backend
 * @param creationDate date when map was created
 * @param sizeX size in x direction 
 * @param sizeY size in y direction
 * @param mapOwnerId id of the creator / owner of the map
 */
public record AddMapRequestDTO(
        String mapName,
        LocalDate creationDate,
        int sizeX,
        int sizeY,
        long mapOwnerId
) {
}
