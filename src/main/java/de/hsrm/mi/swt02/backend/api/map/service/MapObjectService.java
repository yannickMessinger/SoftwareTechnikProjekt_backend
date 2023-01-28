package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;

import java.util.List;

/**
 * Service methods that are  to operate on MapObject database.
 */
public interface MapObjectService {

    /**
     * @return list containing all MapObjects of repository.
     */
    List<MapObject> findAllMapObjects ();

    /**
     * gets single MapObject by the handed id
     *
     * @param id id to look for the correct object
     * @return returns MapObject if found
     */
    MapObject getMapObjectById (long id);

    /**
     * deletes single MapObject by the given id and delete MapObject from
     * MapObjectList in Map
     *
     * @param id id of the MapObject to be deleted.
     */
    void deleteMapObjectById (long id);

    /**
     * adds incoming MapObjects from frontend to the repository.
     *
     * @param mapObjects initial converting from JSON to regular java object from
     *                   incoming Request Body in corresponding REST Controller,
     *                   every Entity is saved individually.
     * @param mapId      id to add the MapObjects to the right Map (in MapObjects
     *                   list) and add Map field to the right MapObject
     * @return returns the id of the Entity that was saved last.
     */
    Long createMapObject (AddMapObjectsRequestDTO mapObjects, long mapId);

    /**
     * Delete all Map Objects on a Map
     *
     * @param id ID of Map
     */
    void deleteAllMapObjectsFromMapById (long id);

    /**
     * Get all Map Objects of a Map
     *
     * @param id ID of Map
     * @return All found Map Objects on Map
     */
    List<MapObject> getAllMapObjectsFromMap (long id);

    /**
     * Add a new Map Object to the right Map and save in database that comes from Message Broker.
     * If MapObject has a field that already exist, it will be deleted.
     *
     * @param mapObjectDTO - came from Broker (create) channel
     */
    void addNewMapObjectFromBroker (AddMapObjectRequestDTO mapObjectDTO, long mapId);

    /**
     * Delete a MapObject that comes from Message Broker.
     *
     * @param mapObjectDTO - came from Broker (delete) channel
     */
    void deleteMapObjectFromBroker (AddMapObjectRequestDTO mapObjectDTO, long mapId);

    /**
     * Update a new MapObjekt that comes from Message Broker (rotation).
     *
     * @param mapObjectDTO - came from Broker (update) channel
     */
    void updateMapObjectFromBroker (AddMapObjectRequestDTO mapObjectDTO, long mapId);

}
