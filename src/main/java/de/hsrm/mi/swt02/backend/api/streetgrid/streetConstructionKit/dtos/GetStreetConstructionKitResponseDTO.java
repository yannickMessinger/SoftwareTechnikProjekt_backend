package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.StreetConstructionKit;

public record GetStreetConstructionKitResponseDTO(long kit_id, String getObject_Name){
    
    public static GetStreetConstructionKitResponseDTO from (StreetConstructionKit kit) {
        return new GetStreetConstructionKitResponseDTO(
            kit.getId(),
            kit.getObject_Name()
        );
    }
}
