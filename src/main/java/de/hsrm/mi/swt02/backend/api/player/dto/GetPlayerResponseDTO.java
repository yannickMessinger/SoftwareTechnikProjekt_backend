package de.hsrm.mi.swt02.backend.api.player.dto;

import de.hsrm.mi.swt02.backend.domain.player.Player;

public record GetPlayerResponseDTO(
        Long userId,
        String userName) {

    public static GetPlayerResponseDTO from (Player u) {
        return new GetPlayerResponseDTO(
                u.getId(),
                u.getUserName()
        );
    }
}
