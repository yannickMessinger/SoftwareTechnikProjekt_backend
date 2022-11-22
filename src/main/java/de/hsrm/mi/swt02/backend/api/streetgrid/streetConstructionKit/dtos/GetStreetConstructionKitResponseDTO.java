package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.StreetConstructionKit;

public record GetStreetConstructionKitResponseDTO(String object_ID, String object_Name, String img, String type, String rotatable){
    
    public static GetStreetConstructionKitResponseDTO from (StreetConstructionKit kit) {
        return new GetStreetConstructionKitResponseDTO(
           
           kit.getObject_ID(),
           kit.getObject_Name(),
           kit.getImg(),
           kit.getType(),
           kit.getRotatable()
        );
    }
}
