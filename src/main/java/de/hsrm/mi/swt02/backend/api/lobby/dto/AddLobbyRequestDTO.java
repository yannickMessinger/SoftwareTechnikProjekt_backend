package de.hsrm.mi.swt02.backend.api.lobby.dto;

import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
/**
 * @param lobbyName Name of the Lobby that was assigned by User
 * @param lobbyModeEnum Lobby mode the lobby was set to, either Playmode (currently driving) oder buildmode(user construct map together)
 * @param numOfPlayers amount of players that are currently in lobby
 * @param hostId id of the host that created the lobby
 */
public record AddLobbyRequestDTO(String lobbyName, LobbyModeEnum lobbyModeEnum, int numOfPlayers, long hostId) {

}
