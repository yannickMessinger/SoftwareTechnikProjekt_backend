package de.hsrm.mi.swt02.backend.api.map.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;

/**
 * Service Class to handle CRUD operations on the MapObjectRepository.
 */

@Service
public class MapObjectServiceImpl implements MapObjectService {

    @Autowired
    private MapObjectRepository mapObjRepo;

    @Autowired
    private MapService mapService;

    /**
     * @return list containing all MapObjects of repository.
     */
    @Override
    @Transactional
    public List<MapObject> findAllMapObjects() {

        Optional<List<MapObject>> allMaps = Optional.of(mapObjRepo.findAll());

        if (allMaps.isEmpty()) {
            // logger
        }

        return allMaps.get();
    }

    /**
     * gets single MapObject by the handed id
     * 
     * @param id id to look for the correct object
     * @return returns MapObject if found
     */
    @Override
    @Transactional
    public MapObject getMapObjectById(long id) {
        Optional<MapObject> foundMapObj = mapObjRepo.findById(id);

        if (foundMapObj.isEmpty()) {
            // logger
        }

        return foundMapObj.orElseThrow();
    }

    /**
     * deletes single MapObject by the given id and delete MapObject from
     * MapObjectList in Map
     * 
     * @param id id of the MapObject to be deleted.
     */
    @Override
    @Transactional
    public void deleteMapObjectById(long id) {
        this.getMapObjectById(id).getMap().getMapObjects().remove(this.getMapObjectById(id));
        mapObjRepo.deleteById(id);
    }

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
    @Override
    @Transactional
    public Long createMapObject(AddMapObjectsRequestDTO mapObjects, long mapId) {

        Map foundMap = mapService.getMapById(mapId);

        this.deleteAllMapObjectsFromMapById(mapId);

        if (!mapObjects.mapObjects().isEmpty()) {
            for (AddMapObjectRequestDTO ele : mapObjects.mapObjects()) {
                MapObject nMapObj = new MapObject(ele.objectTypeId(), ele.x(), ele.y(), ele.rotation());
                nMapObj.setMap(mapService.getMapById(mapId));
                foundMap.getMapObjects().add(nMapObj);
                mapObjRepo.save(nMapObj);

            }
        }

        mapService.saveEditedMap(foundMap);

        return 0L;
    }

    @Override
    @Transactional
    public void deleteAllMapObjectsFromMapById(long id) {
        Map foundMap = mapService.getMapById(id);

        for (MapObject ele : foundMap.getMapObjects()) {
            mapObjRepo.deleteById(ele.getId());
        }

        foundMap.getMapObjects().clear();

    }

}
