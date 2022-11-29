package de.hsrm.mi.swt02.backend.api.lobby.dtos;

import de.hsrm.mi.swt02.backend.api.lobby.Lobby;
import de.hsrm.mi.swt02.backend.api.lobby.LobbyMode;

public record GetLobbyResponseDTO(String lobbyName, int numOfPlayers, LobbyMode lobbyMode, String hostName){
    

    public static GetLobbyResponseDTO from (Lobby lobby) {
        return new GetLobbyResponseDTO(
            lobby.getLobbyName(),
            lobby.getNumOfPlayers(),
            lobby.getLobbyMode(),
            lobby.getHost().getUserName()
             
        );
    }
}
