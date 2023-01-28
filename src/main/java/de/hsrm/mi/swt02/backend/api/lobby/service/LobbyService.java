package de.hsrm.mi.swt02.backend.api.lobby.service;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LobbyService {

    /**
     * Find all Lobbies in the Database
     *
     * @return return all found Lobbies
     */
    List<Lobby> findAllLobbys ();

    /**
     * Find a Lobby in the Database by given ID
     *
     * @param id of Lobby
     * @return found Lobby
     */
    Lobby findLobbyById (long id);

    /**
     * Delete a Lobby in the Database by given ID
     *
     * @param id of Lobby
     */
    void deleteLobby (long id);

    /**
     * Create a new Lobby by given Parameters
     *
     * @param lobbyName    Name of new Lobby
     * @param lobbyMode    Mode of new Mode
     * @param numOfPlayers amount of Players
     * @param hostId       ID of the Host
     * @return created new Lobby
     */
    long createLobby (String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId);

    /**
     * Update a Lobby if found by ID in the Database
     *
     * @param id ID of the Lobby
     */
    void updateLobby (long id);

    /**
     * Find Player and Lobby by ID and maintain the relations.
     *
     * @param lobbyId  ID of Lobby
     * @param playerId ID of Player
     */
    void addPlayerToLobby (long lobbyId, long playerId);

    /**
     * Find Player and Lobby by ID and remove its relations.
     *
     * @param lobbyId  ID of Lobby
     * @param playerId ID of Player
     */
    void removePlayerFromLobby (long lobbyId, long playerId);

    /**
     * Find all Players in a Lobby by it´s id in the Database
     *
     * @param lobbyId from Lobby
     * @return list of found Players in the Lobby
     */
    List<Player> findAllPlayersFromLobby (long lobbyId);

    /**
     * Add a Map to a Lobby
     *
     * @param id    ID of lobby
     * @param mapId ID of map
     * @return ID of the updated Lobby
     */
    long addMap (long id, long mapId);

    /**
     * Update a Lobby Mode in the Database
     *
     * @param id        ID of Lobby
     * @param lobbyMode Mode of Lobby
     */
    void updateLobbyModeBroker (long id, LobbyModeEnum lobbyMode);

    /**
     * Save a Lobby directly into the Database
     *
     * @param lobby Lobby Object
     */
    void saveEditedLobby (Lobby lobby);
}
