package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {

    /**
     * Create a new Map by given DTO
     *
     * @param dto DTO of Map
     * @return ID of created Map
     */
    long saveMap (AddMapRequestDTO dto);

    /**
     * Get a Map by given ID from the Database
     *
     * @param id ID of Map
     * @return found Map
     */
    Map getMapById (long id);

    /**
     * Delete Map by given ID from the Database
     *
     * @param id ID of Map
     */
    void deleteMapById (long id);

    /**
     * Find all Map´s in the Database
     *
     * @return found Map´s
     */
    List<Map> findAllMaps ();

    /**
     * Assign Map <=> Lobby Relation of given Map and Lobby
     *
     * @param mapId   ID of Map
     * @param lobbyId ID of Lobby
     */
    void assignLobbyToMap (long mapId, long lobbyId);

    /**
     * Create a new empty Map
     *
     * @return created Map
     */
    Map createNewMap ();

    /**
     * Save given Map into the Database
     *
     * @param map Map Object
     */
    void saveEditedMap (Map map);
}
