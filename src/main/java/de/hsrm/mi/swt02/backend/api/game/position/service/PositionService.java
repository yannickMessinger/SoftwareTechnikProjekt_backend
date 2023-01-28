package de.hsrm.mi.swt02.backend.api.game.position.service;

import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {
    /**
     * Get all Positions of Objects
     *
     * @return List of all found ObjectPositions
     */
    List<ObjectPosition> findAllPositions ();

    /**
     * Delete the Object Position by given id
     *
     * @param id of Object to be deleted
     */
    void deletePosition (long id);

    /**
     * Persist an Object by given Attributes
     *
     * @param mapObjectId of ObjectType
     * @param x           position of Object on Map
     * @param y           position of Object on Map
     * @param rotation    of Object on Map
     * @return id of created ObjectPosition
     */
    ObjectPosition createPosition (long mapObjectId, double x, double y, double[] rotation);

    /**
     * Update the Object by given Attributes
     *
     * @param objectPositionId of ObjectPosition
     * @param x                position of Object on Map
     * @param y                position of Object on Map
     * @param rotation         of Object on Map
     */
    void updatePosition (long objectPositionId, double x, double y, double[] rotation);
}

