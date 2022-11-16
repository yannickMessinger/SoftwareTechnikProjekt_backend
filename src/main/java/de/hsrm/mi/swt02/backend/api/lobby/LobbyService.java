package de.hsrm.mi.swt02.backend.api.lobby;

import java.util.List;

public interface LobbyService {
    List<Lobby> findAllLobbys();
    Lobby findLobbyById(long id);
    void deleteLobby(long id);
}
