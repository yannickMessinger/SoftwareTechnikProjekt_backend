package de.hsrm.mi.swt02.backend.api.map.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;


public interface MapObjectTypeService {
    List<MapObjectType> findAllStreetConstructionKits();
    Long createStreetConstructionKit( AddMapObjectsRequestDTO kit);
    MapObjectType getStreetConstructionKitById(long id);
    void deleteStreetConstructionKitById(long id);
}
