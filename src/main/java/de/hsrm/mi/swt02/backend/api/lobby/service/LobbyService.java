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
     * @return
     */
    List<Lobby> findAllLobbys ();

    /**
     * Find Lobby by ID in the Database
     * @param id ID of Lobby
     * @return result Lobby
     */
    Lobby findLobbyById (long id);

    /**
     * Delete Lobby by ID in the Database
     * @param id ID of Lobby
     */
    void deleteLobby (long id);

    /**
     * Create a Lobby by given Parameters in the Database
     * @param lobbyName Lobby Name of the Lobby
     * @param lobbyMode Lobby Mode of the Lobby
     * @param numOfPlayers Number of Players in the Lobby
     * @param hostId ID of the Host
     * @return ID of created Lobby
     */
    long createLobby (String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId);

    /**
     * Create a Lobby by given Parameters and Map ID in the Database
     * @param lobbyName Lobby Name of the Lobby
     * @param lobbyMode Lobby Mode of the Lobby
     * @param numOfPlayers Number of Players in the Lobby
     * @param hostId ID of the Host
     * @param mapId ID of the Map
     * @return ID of created Lobby
     */
    long createLobbyWithMap (String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId, long mapId);

    /**
     * Find Player and Lobby by id and maintain the relations.
     *
     * @param lobbyId  from Lobby
     * @param playerId from Player
     */
    void addPlayerToLobby (long lobbyId, long playerId);

    /**
     * Find Player and Lobby by id and remove the relations.
     *
     * @param lobbyId  from Lobby
     * @param playerId from Player
     */
    void removePlayerFromLobby (long lobbyId, long playerId);

    /**
     * Find Lobby by lobbyId and get all Players from Lobby
     *
     * @param lobbyId from Lobby
     * @return list of Players
     */
    List<Player> findAllPlayersFromLobby (long lobbyId);

    /**
     * adds an available map to a lobby, both found by the id, in the Database
     *
     * @param id    id of lobby
     * @param mapId id of map
     */
    void addMap (long id, long mapId);

    /**
     * Update the Lobby Mode of given ID, by the Information of the Message Broker, in the Database
     * @param id ID of Lobby
     * @param lobbyMode Lobby Mode of the Lobby
     */
    void updateLobbyModeBroker (long id, LobbyModeEnum lobbyMode);

    /**
     * Save a Lobby in the Database
     * @param lobby Lobby Object of Lobby
     */
    void saveEditedLobby (Lobby lobby);
}
