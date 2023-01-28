package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;

import java.util.List;

public interface MapObjectTypeService {
    List<MapObjectType> findAllMapObjectType ();

    MapObjectType findMapObjectTypeById (long id);
}
