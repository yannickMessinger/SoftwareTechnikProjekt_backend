package de.hsrm.mi.swt02.backend.api.game.position.service;

import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {

    /**
     * Find all PlayerPositions´s in the Database
     *
     * @return list of all found PlayerPosition´s
     */
    List<PlayerPosition> findAllPositions ();

    /**
     * Delete a PlayerPositions´s in the Database by given reference ID
     *
     * @param id of PlayerPosition
     */
    void deletePosition (long id);

    /**
     * Create a new PlayerPositions´s in the Database
     *
     * @param player Player to be persisted
     * @param x      Coordinate of Player in 3D world
     * @param y      Coordinate of Player in 3D world
     * @return id of created Position
     */
    long createPosition (Player player, double x, double y, double rotation);

    /**
     * Update a PlayerPositions´s in the Backend
     * <p><ul>
     * <li>This will search for the Position
     * <li>if found update it´s values
     * <li>else log the unsuccessful outcome
     * </ul></p>
     *
     * @param playerPositionId Player ID to be updated
     * @param x                Coordinate of Player in 3D world
     * @param y                Coordinate of Player in 3D world
     * @param rotation         Rotation of Player in 3D world
     */
    void updatePosition (long playerPositionId, double x, double y, double rotation);
}
