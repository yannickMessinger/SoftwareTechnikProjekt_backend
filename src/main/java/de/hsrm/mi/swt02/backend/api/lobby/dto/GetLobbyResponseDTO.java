package de.hsrm.mi.swt02.backend.api.lobby.dto;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
/**
 * DTO to transfer lobby info to frontend 
 * @param lobbyId id of the lobby that was created
 * @param hostId id of the host that created the lobby
 * @param lobbyName Name of the Lobby that was assigned by User
 * @param numOfPlayers amount of players that are currently in lobby
 * @param lobbyModeEnum Lobby mode the lobby was set to, either Playmode (currently driving) oder buildmode(user construct map together)
 */
public record GetLobbyResponseDTO(long lobbyId, long hostId, long mapId, String lobbyName, int numOfPlayers,
                                  LobbyModeEnum lobbyModeEnum) {


    public static GetLobbyResponseDTO from (Lobby lobby) {
        return new GetLobbyResponseDTO(
                lobby.getId(),
                lobby.getHostId(),
                lobby.getMap().getId(),
                lobby.getLobbyName(),
                lobby.getNumOfPlayers(),
                lobby.getLobbyMode()
        );
    }
}
