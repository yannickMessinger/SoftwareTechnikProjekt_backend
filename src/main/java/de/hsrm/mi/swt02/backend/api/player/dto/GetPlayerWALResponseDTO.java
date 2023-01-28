package de.hsrm.mi.swt02.backend.api.player.dto;

import de.hsrm.mi.swt02.backend.domain.player.Player;

public record GetPlayerWALResponseDTO(
        Long userId,
        String userName,
        Long activeLobbyId) {

    public static GetPlayerWALResponseDTO from (Player u) {
        Long activeLobbyId = -1L;
        if (u.getActiveLobby() != null) {
            activeLobbyId = u.getActiveLobby().getId();
        }
        return new GetPlayerWALResponseDTO(
                u.getId(),
                u.getUserName(),
                activeLobbyId
        );
    }
}
