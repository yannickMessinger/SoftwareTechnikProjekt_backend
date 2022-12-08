package de.hsrm.mi.swt02.backend.api.map.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
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
     * @return  list containing all MapObjects of repository.
     */
    @Override
    @Transactional
    public List<MapObject> findAllMapObjects() {
        
        Optional<List<MapObject>> allMaps = Optional.of(mapObjRepo.findAll());
        
        if(allMaps.isEmpty()){
            //logger
        }

        return allMaps.get();
    }

    /**
     * gets single MapObject by the handed id
     * @param id id to look for the correct object
     * @return returns MapObject if found
     */
    @Override
    @Transactional
    public MapObject getMapObjectById(long id) {
        Optional<MapObject> foundMapObj = mapObjRepo.findById(id);

        if(foundMapObj.isEmpty()){
            //logger
        }
        

        return foundMapObj.orElseThrow();
    }


    /**
     * deletes single MapObject by the given id and delete MapObject from MapObjectList in Map
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
     * @param mapObjects  initial converting from JSON to regular java object from incoming Request Body in corresponding REST Controller,
     * every Entity is saved individually.
     * @param mapId id to add the MapObjects to the right Map (in MapObjects list) and add Map field to the right MapObject
     * @return returns the id of the Entity that was saved last. 
     */
    @Override
    @Transactional
    public Long createMapObject(AddMapObjectsRequestDTO mapObjects, long mapId) {
        List <MapObject> map_list = new ArrayList<>();

        for(AddMapObjectRequestDTO ele : mapObjects.mapObjects()){
            map_list.add(new MapObject(ele.objectTypeId(), ele.x(), ele.y(), ele.rotation()));
        }

        for (MapObject ele : map_list){
            mapObjRepo.save(ele);

            ele.setMap(mapService.getMapById(mapId));
            mapService.getMapById(mapId).getMapObjects().add(ele);
        }

        return map_list.get(map_list.size() - 1).getId();
    }


   
    
}
