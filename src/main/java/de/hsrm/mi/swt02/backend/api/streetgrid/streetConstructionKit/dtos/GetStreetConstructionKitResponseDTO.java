package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.StreetConstructionKit;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.enums.KitType;

/**
 * DTO to be communicate from back to frontend
 * @param object_ID id of the object from frontend
 * @param object_Name name of the object, e.g. straight road tile 
 * @param img corresponding image of the tile
 * @param type to specifiy type of element, e.g. road, building etc.
 * @param rotatable angle of rotation the element is in (heading).
 */
public record GetStreetConstructionKitResponseDTO(long object_ID, String object_Name, String img, KitType type, boolean rotatable){
    
    public static GetStreetConstructionKitResponseDTO from (StreetConstructionKit kit) {
        return new GetStreetConstructionKitResponseDTO(
           
           kit.getObject_ID(),
           kit.getObject_Name(),
           kit.getImg(),
           kit.getKitType(),
           kit.isRotatable()
        );
    }
}
