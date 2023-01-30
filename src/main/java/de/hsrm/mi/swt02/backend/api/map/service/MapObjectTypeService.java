package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;

import java.util.List;

public interface MapObjectTypeService {

    /**
     * Get all Map Object Types from the Database
     *
     * @return found MapObjectType´s
     */
    List<MapObjectType> findAllMapObjectType();

    /**
     * Get Map Object Type by ID from the Database
     *
     * @param id ID of MapObjectType
     * @return MapObjectType Object
     */
    MapObjectType findMapObjectTypeById(long id);
}
