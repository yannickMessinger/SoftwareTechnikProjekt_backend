package de.hsrm.mi.swt02.backend.api.game.position.dto;

import de.hsrm.mi.swt02.backend.domain.position.Rotation;

public record AddObjectPositionDTO(
        long id,
        double x,
        double z,
        Rotation rotation
) {
}
