package de.hsrm.mi.swt02.backend.api.map.dto;

import java.util.List;

/**
 * DTO to add multiple MapObjects in one request.
 */
public record AddMapObjectsRequestDTO(
        List<AddMapObjectRequestDTO> mapObjects
) {
}
