package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos;

import java.util.List;

public record AddMultipleStreetObjectsRequestDTO(List<AddStreetObjectRequestDTO> streetobjects) {
    
}
