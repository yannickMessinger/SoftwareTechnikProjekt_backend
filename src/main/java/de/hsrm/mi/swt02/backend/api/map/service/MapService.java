package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.npc.NpcNavInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {

    /**
     * Save a Map by Map DTO into the Database
     *
     * @param dto DTO of the Map
     * @return ID of saved Map
     */
    long saveMap(AddMapRequestDTO dto);

    /**
     * Get Map by Id from the Database
     *
     * @param id ID of Map
     * @return Map from the Database
     */
    Map getMapById(long id);

    /**
     * Delete Map by ID in the Database
     *
     * @param id ID of Map
     */
    void deleteMapById(long id);

    /**
     * Get all Maps from the Database
     *
     * @return found Maps
     */
    List<Map> findAllMaps();

    /**
     * Assign Relations of Map by ID and Lobby by ID in the Database
     *
     * @param mapId   ID of Map
     * @param lobbyId ID of Lobby
     */
    void assignLobbyToMap(long mapId, long lobbyId);

    /**
     * Create empty Map
     *
     * @return created Map Object
     */
    Map createNewMap();

    /**
     * Save Map into the Database
     *
     * @param map Map Object
     */
    void saveEditedMap(Map map);

    //Triggers Python Script

    /**
     * Get NPC Direction from the Python Script by given Parameters
     *
     * @param mapId   ID of Map
     * @param npcId   ID of NPC
     * @param npcPosX X-Position of NPC
     * @param npcPosY Y-Position of NPC
     * @param npcRot  Rotation of NPC
     * @return NpcNavInfo Object
     */
    NpcNavInfo getNpcDirections(long mapId, long npcId, int npcPosX, int npcPosY, int npcRot);

    /**
     * Get all Maps of an Player by given ID from the Database
     *
     * @param playerId ID of Player
     * @return found Maps of Player
     */
    List<Map> findAllMapsFromPlayer(long playerId);
}
