package de.hsrm.mi.swt02.backend.api.lobby;

import de.hsrm.mi.swt02.backend.api.player.Player;

import java.util.List;

public interface LobbyService {
    List<Lobby> findAllLobbys();
    Lobby findLobbyById(long id);
    void deleteLobby(long id);
    long createLobby(String lobbyName, LobbyMode lobbyMode, int numOfPlayers, long hostID);
    void updateLobby(long id);
    void addPlayerToLobby(long lobbyId, long playerId);
    List<Player> findAllPlayersFromLobby(long lobbyId);
}
