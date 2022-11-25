package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.StreetObject;

/**
 * DTO to communicate from back to frontend.
 * @param object_ID id of the object from frontend
 * @param x X-Coordinate / Position in StreetGrid / MapEditor
 * @param y Y-Coordinate / Position in StreetGrid / MapEditor
 * @param rotation angle of rotation the element was set to.
 */
public record GetStreetObjectResponseDTO(long object_ID, int x, int y, int rotation){
    

    public static GetStreetObjectResponseDTO from (StreetObject s) {
        return new GetStreetObjectResponseDTO(
                s.getObject_ID(),
                s.getX(),
                s.getY(),
                s.getRotation()
        );
    }
}
