package de.hsrm.mi.swt02.backend.api.lobby.service;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * interface to manage CRUD operations on lobby repository
 */
@Service
public interface LobbyService {
    List<Lobby> findAllLobbys ();

    Lobby findLobbyById (long id);

    void deleteLobby (long id);

    long createLobby (String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId);

    long createLobbyWithMap (String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId, long mapId);

    void addPlayerToLobby (long lobbyId, long playerId);

    void removePlayerFromLobby (long lobbyId, long playerId);

    List<Player> findAllPlayersFromLobby (long lobbyId);

    long addMap (long id, long mapId);

    void updateLobbyModeBroker (long id, LobbyModeEnum lobbyMode);

    void saveEditedLobby (Lobby lobby);
}
