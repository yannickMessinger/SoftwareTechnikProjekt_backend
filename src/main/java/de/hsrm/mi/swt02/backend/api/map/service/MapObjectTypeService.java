package de.hsrm.mi.swt02.backend.api.map.service;

import java.util.List;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;

public interface MapObjectTypeService {
    List<MapObjectType> findAllMapObjectType();
    MapObjectType findMapObjectTypeById(long id);
}
