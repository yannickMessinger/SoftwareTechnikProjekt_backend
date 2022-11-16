package de.hsrm.mi.swt02.backend.api.lobby.dtos;

import de.hsrm.mi.swt02.backend.api.lobby.Lobby;

public record GetLobbyResponseDTO(long lobbyID, String lobbyName){
    

    public static GetLobbyResponseDTO from (Lobby lobby) {
        return new GetLobbyResponseDTO(
            lobby.getId(),
             lobby.getLobbyName()
        );
    }
}
