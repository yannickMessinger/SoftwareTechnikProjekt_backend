package de.hsrm.mi.swt02.backend.api.game.position.dto;

import de.hsrm.mi.swt02.backend.domain.player.Player;

public record AddPlayerPositionDTO(
        long playerID,
        double x,
        double y,
        double rotation
) {
}
