package de.hsrm.mi.swt02.backend.api.game.position.dto;

public record AddPlayerPositionDTO(
        long playerID,
        double x,
        double y,
        double rotation
) {
}
