package de.hsrm.mi.swt02.backend.api.lobby.dtos;

import de.hsrm.mi.swt02.backend.api.lobby.LobbyState;

public record AddLobbyRequestDTO(String lobbyName, LobbyState lobbyState, int numOfActivePlayers) {
    
}
