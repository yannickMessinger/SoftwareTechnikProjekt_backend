package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to manage CRUD database operations on repository for
 * MapObjectType's.
 */
@Service
public class MapObjectTypeServiceImpl implements MapObjectTypeService {

    @Autowired
    private MapObjectTypeRepository mapConstRepo;

}
