package de.hsrm.mi.swt02.backend.api.player.service;

import de.hsrm.mi.swt02.backend.domain.player.Player;

import java.util.List;

public interface PlayerService {

    List<Player> findAllPlayers ();

    Player findPlayerById (long id);

    void deletePlayer (long id);

    Player createPlayer (String userName, String password);

    Player findPlayerByUsernameAndPassword (String username, String password);
}
