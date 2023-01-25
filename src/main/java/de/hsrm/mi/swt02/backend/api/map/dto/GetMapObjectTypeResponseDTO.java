package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import de.hsrm.mi.swt02.backend.domain.map.ObjectTypeEnum;

public record GetMapObjectTypeResponseDTO(
        long objectTypeId,
        long groupId,
        ObjectTypeEnum type,
        int rotation,
        String name,
        String texture,
        String model3d
) {
    public static GetMapObjectTypeResponseDTO from (MapObjectType mapObjectType) {
        return new GetMapObjectTypeResponseDTO(
                mapObjectType.getObjectTypeId(),
                mapObjectType.getGroupId(),
                mapObjectType.getType(),
                mapObjectType.getRotation(),
                mapObjectType.getName(),
                mapObjectType.getTexture(),
                mapObjectType.getModel3d()
        );
    }
}
