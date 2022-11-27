package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos;

import java.util.List;
 /**
  *List of DTO's that match the Frontend DTO's for Elements of the StreetEditor
  *@param streetkits List of Elements from the Street Editor Menu
  */

public record AddMultipleStreetConstructionKitsRequestDTO(List<AddStreetConstructionKitRequestDTO> streetkits) {
    
}
