package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos;

import java.util.List;

/**
 * DTO that hold multiple AddStreetObjectRequestDTO's to parse data from frontend correctly
 * @param streetobjects List of AddStreetObjectRequestDTO's  
 * AddStreetObjectRequestDTO represents tiles that very set in the StreetEditor. 
 */
public record AddMultipleStreetObjectsRequestDTO(List<AddStreetObjectRequestDTO> streetobjects) {
    
}
