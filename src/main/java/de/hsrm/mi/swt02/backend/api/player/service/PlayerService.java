package de.hsrm.mi.swt02.backend.api.player.service;

import de.hsrm.mi.swt02.backend.domain.player.Player;

import java.util.List;

public interface PlayerService {

    /**
     * Find all Players in the Database
     *
     * @return List of all found Players
     */
    List<Player> findAllPlayers ();

    /**
     * Find Player by ID in the Database
     *
     * @param id ID of Player
     * @return found Player
     */
    Player findPlayerById (long id);

    /**
     * Delete Player by ID in the Database
     *
     * @param id ID of Player
     */
    void deletePlayer (long id);

    /**
     * Create a Player by given login Information
     *
     * @param userName Username of Player
     * @param password Password of Player
     * @return created Player
     */
    Player createPlayer (String userName, String password);

    /**
     * Find a Player by Username Password combination in the Database
     *
     * @param username Username of Player
     * @param password Password of Player
     * @return found Player else 0L
     */
    Player findPlayerByUsernameAndPassword (String username, String password);
}
