package de.hsrm.mi.swt02.backend.api.lobby.dto;

import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;

public record AddLobbyRequestDTO(String lobbyName, LobbyModeEnum lobbyModeEnum, int numOfPlayers , long hostId) {
    
}
