package de.hsrm.mi.swt02.backend.api.lobby.service;

import de.hsrm.mi.swt02.backend.domain.player.Player;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface LobbyService {
    List<Lobby> findAllLobbys();
    Lobby findLobbyById(long id);
    void deleteLobby(long id);
    long createLobby(String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostID);
    void updateLobby(long id);
    void addPlayerToLobby(long lobbyId, long playerId);
    void removePlayerFromLobby(long lobbyId, long playerId);
    List<Player> findAllPlayersFromLobby(long lobbyId);

}
