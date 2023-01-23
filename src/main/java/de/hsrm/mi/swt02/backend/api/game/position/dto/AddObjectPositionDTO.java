package de.hsrm.mi.swt02.backend.api.game.position.dto;

public record AddObjectPositionDTO(
        long id,
        double x,
        double z,
        double rotation
) {
}
