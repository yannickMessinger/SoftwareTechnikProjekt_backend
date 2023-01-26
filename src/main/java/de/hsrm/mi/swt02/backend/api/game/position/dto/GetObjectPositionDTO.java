package de.hsrm.mi.swt02.backend.api.game.position.dto;

import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;

public record GetObjectPositionDTO(
    long positionId,
    long objectTypeId,
    double posX,
    double posZ,
    double [] posRotation
) {
    public static GetObjectPositionDTO from (ObjectPosition objectPosition) {
        return new GetObjectPositionDTO(
                objectPosition.getId(),
                objectPosition.getMapObjectId(),
                objectPosition.getPosX(),
                objectPosition.getPosY(),
                objectPosition.getRotation()
        );
    }
}
