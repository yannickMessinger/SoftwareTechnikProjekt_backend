package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectTypeRepository;
import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to manage CRUD database operations on repository for
 * MapObjectType's.
 */
@Service
public class MapObjectTypeServiceImpl implements MapObjectTypeService {

    @Autowired
    private MapObjectTypeRepository mapConstRepo;

    @Override
    /*
     * returns a list with all available MapObjectTypes (placable Mapobjects)
     */
    public List<MapObjectType> findAllMapObjectType () {
        return mapConstRepo.findAll();
    }

    @Override
    /*
     * returns the MapObjectsType by objectId
     */
    public MapObjectType findMapObjectTypeById (long id) {
        return mapConstRepo.findById(id).get();
    }

}
