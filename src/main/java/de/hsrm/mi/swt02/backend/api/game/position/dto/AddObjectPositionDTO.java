package de.hsrm.mi.swt02.backend.api.game.position.dto;

public record AddObjectPositionDTO(
        long objectId,
        double x,
        double y,
        double rotation
) {
}
