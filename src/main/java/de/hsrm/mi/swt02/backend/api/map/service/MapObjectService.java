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
     * Find all Map Object in the Database
     *
     * @return result Maps
     */
    List<MapObject> findAllMapObjects();

    /**
     * gets single MapObject by the handed id
     *
     * @param id id to look for the correct object
     * @return returns MapObject if found
     */
    MapObject getMapObjectById(long id);

    /**
     * Delete a Map Object by given ID in the Database
     *
     * @param id ID of Map
     */
    void deleteMapObjectById(long id);

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
    Long createMapObject(AddMapObjectsRequestDTO mapObjects, long mapId);

    /**
     * Delete a Map Object by ID in the Database
     *
     * @param id ID of Map Object
     */
    void deleteAllMapObjectsFromMapById(long id);

    /**
     * Add a new MapObjekt to the right Map and save in database that comes from Message Broker.
     * If MapObject has a field that already exist, it will be deleted.
     *
     * @param mapObjectDTO - came from Broker (create) channel
     */
    void addNewMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId);

    /**
     * Delete a Map Object by given DTO and Map ID in the Database
     *
     * @param mapObjectDTO DTO of MapObject
     * @param mapId        ID of Map
     */
    void deleteMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId);

    /**
     * Update a Map Object by given DTO and Map ID in the Database
     *
     * @param mapObjectDTO DTO of MapObject
     * @param mapId        ID of Map
     */
    void updateMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId);

}
