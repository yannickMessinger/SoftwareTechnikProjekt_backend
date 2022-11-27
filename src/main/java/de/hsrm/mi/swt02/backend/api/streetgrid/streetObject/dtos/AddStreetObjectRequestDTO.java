package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos;

/**
 * DTO to match DTO's from frontend representing a StreetObject that is used to construct a map
 * in StreetEditor.
 * @param object_ID id of the object from frontend
 * @param x X-Coordinate / Position in StreetGrid / MapEditor
 * @param y Y-Coordinate / Position in StreetGrid / MapEditor
 * @param rotation angle of rotation the element was set to.
 */
public record AddStreetObjectRequestDTO(long object_ID, int x, int y, int rotation){
    
}
