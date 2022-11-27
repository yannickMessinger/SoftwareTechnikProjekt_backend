package de.hsrm.mi.swt02.backend.api.lobby.dtos;

import de.hsrm.mi.swt02.backend.api.lobby.LobbyMode;

public record AddLobbyRequestDTO(String lobbyName, LobbyMode lobbyState, int numOfActivePlayers, long hostID) {
    
}
