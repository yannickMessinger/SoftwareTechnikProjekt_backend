package de.hsrm.mi.swt02.backend.api.lobby.dtos;

import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;

public record AddLobbyRequestDTO(String lobbyName, LobbyModeEnum lobbyModeEnum, int numOfPlayers , long hostID) {
    
}
