package de.hsrm.mi.swt02.backend.api.lobby.dtos;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;

public record GetLobbyResponseDTO(String lobbyName, int numOfPlayers, LobbyModeEnum lobbyModeEnum){
    

    public static GetLobbyResponseDTO from (Lobby lobby) {
        return new GetLobbyResponseDTO(
            lobby.getLobbyName(),
            lobby.getNumOfPlayers(),
            lobby.getLobbyMode()
             
        );
    }
}
