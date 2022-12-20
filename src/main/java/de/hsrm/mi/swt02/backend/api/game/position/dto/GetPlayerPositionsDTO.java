package de.hsrm.mi.swt02.backend.api.game.position.dto;

import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.player.Player;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

public record GetPlayerPositionsDTO(
        double posX,
        double posY,
        Player player
) {}
