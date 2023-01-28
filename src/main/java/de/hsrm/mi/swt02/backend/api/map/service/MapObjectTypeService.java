package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;

import java.util.List;

public interface MapObjectTypeService {

    /**
     * Find all Map Object Type´s in the Database
     *
     * @return List of Map Object Type´s
     */
    List<MapObjectType> findAllMapObjectType ();

    /**
     * Find Map Object Type by given ID in the Database
     *
     * @param id ID of Map Object
     * @return found Map Object Type
     */
    MapObjectType findMapObjectTypeById (long id);
}
