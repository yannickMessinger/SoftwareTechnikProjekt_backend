package de.hsrm.mi.swt02.backend.api.map.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;

/**
 * Service methods that are  to operate on MapObject database.
 */
public interface MapObjectService {
    List<MapObject> findAllStreetObjects();
    MapObject getStreetObjectById(long id);
    void deleteStreetObjectById(long id);
    Long createStreetObject(AddMapObjectsRequestDTO streetObjects, long streetPlanId);
}
