package de.hsrm.mi.swt02.backend.api.game.position.dto;

import de.hsrm.mi.swt02.backend.api.lobby.dto.GetLobbyResponseDTO;
import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.player.Player;

public record GetPlayerPositionsDTO(
        long id,
        double posX,
        double posY,
        Player player
) {
    public static GetPlayerPositionsDTO from (PlayerPosition playerPosition) {
        return new GetPlayerPositionsDTO(
                playerPosition.getId(),
                playerPosition.getPosX(),
                playerPosition.getPosY(),
                playerPosition.getPlayer()
        );
    }
}
