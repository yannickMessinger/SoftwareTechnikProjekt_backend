package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.StreetObject;

public record GetStreetObjectResponseDTO(String object_ID, String x, String y, String rotation){
    

    public static GetStreetObjectResponseDTO from (StreetObject s) {
        return new GetStreetObjectResponseDTO(
                s.getObject_ID(),
                s.getX(),
                s.getY(),
                s.getRotation()
        );
    }
}
