package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;

public record GetMapObjectResponseDTO(
        long objectId,
        long objectTypeId,
        int x,
        int y,
        int rotation
){
    public static GetMapObjectResponseDTO from (MapObject s) {
        return new GetMapObjectResponseDTO(
                s.getId(),
                s.getObjectTypeId(),
                s.getX(),
                s.getY(),
                s.getRotation()
        );
    }
}
