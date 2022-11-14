package de.hsrm.mi.swt02.backend.api.player;

import java.util.List;

public interface PlayerService {

    List<Player> findAllPlayers();
    Player findPlayerById(long id);
    void deletePlayer(long id);
    Player createPlayer(String userName);
}
