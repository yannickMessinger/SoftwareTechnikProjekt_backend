package de.hsrm.mi.swt02.backend.api.map.dto;

import java.util.List;

public record AddMapObjectsRequestDTO(
        List<AddMapObjectRequestDTO> mapObjects
) {
}
