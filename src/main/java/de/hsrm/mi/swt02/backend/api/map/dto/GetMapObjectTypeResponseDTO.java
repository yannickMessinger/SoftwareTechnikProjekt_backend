package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import de.hsrm.mi.swt02.backend.domain.map.ObjectTypeEnum;

public record GetMapObjectTypeResponseDTO(
        long objectTypeID,
        String objectName,
        String img,
        ObjectTypeEnum type
){
    public static GetMapObjectTypeResponseDTO from (MapObjectType mapObjectType) {
        return new GetMapObjectTypeResponseDTO(
                mapObjectType.getObjectTypeID(),
                mapObjectType.getObjectName(),
                mapObjectType.getImg(),
                mapObjectType.getType()
        );
    }
}
