package de.hsrm.mi.swt02.backend.api.player.service;

import de.hsrm.mi.swt02.backend.domain.player.Player;

import java.util.List;

public interface PlayerService {

    /**
     * Get all Players from the Database
     *
     * @return List of found Players
     */
    List<Player> findAllPlayers();

    /**
     * Find Player by given ID from the Database
     *
     * @param id ID of Player
     * @return found Player
     */
    Player findPlayerById(long id);

    /**
     * Delete Player by given ID from the Database
     *
     * @param id ID of Player
     */
    void deletePlayer(long id);

    /**
     * Create new Player and save into the Database
     *
     * @param userName User Name of Player
     * @param password Password of Player
     * @return Player Object of Player
     */
    Player createPlayer(String userName, String password);

    /**
     * Find a Player by given Username and Password from the Database
     *
     * @param username User Name of Player
     * @param password Password of Player
     * @return found Player
     */
    Player findPlayerByUsernameAndPassword(String username, String password);
}
