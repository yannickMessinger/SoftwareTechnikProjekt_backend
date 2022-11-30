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
    private MapObjectRepository streetObjRepo;

    @Autowired
    private MapService mapService;


     /**
     * @return  list containing all StreetObjects of repository.
     */
    @Override
    @Transactional
    public List<MapObject> findAllStreetObjects() {
        
        Optional<List<MapObject>> allStreets = Optional.of(streetObjRepo.findAll());
        
        if(allStreets.isEmpty()){
            //logger
        }

        return allStreets.get();
    }

    /**
     * gets single MapObject by the handed id
     * @param id id to look for the correct object
     * @return returns MapObject if found
     */
    @Override
    @Transactional
    public MapObject getStreetObjectById(long id) {
        Optional<MapObject> foundStreetObj = streetObjRepo.findById(id);

        if(foundStreetObj.isEmpty()){
            //logger
        }
        

        return foundStreetObj.orElseThrow();
    }


    /**
     * deletes single MapObject by the given id and delete MapObject from StreetObjectList in Map
     * @param id id of the MapObject to be deleted.
     */
    @Override
    @Transactional
    public void deleteStreetObjectById(long id) {
        this.getStreetObjectById(id).getMap().getMapObjects().remove(this.getStreetObjectById(id));
        streetObjRepo.deleteById(id);
    }



     /**
     * adds incoming StreetObjects from frontend to the repository. 
     * @param streetObjects  initial converting from JSON to regular java object from incoming Request Body in corresponding REST Controller,
     * every Entity is saved individually.
     * @param streetPlanId id to add the StreetObjects to the right Map (in StreetObjects list) and add Map field to the right MapObject
     * @return returns the id of the Entity that was saved last. 
     */
    @Override
    @Transactional
    public Long createStreetObject(AddMapObjectsRequestDTO streetObjects, long streetPlanId) {
        List <MapObject> street_list = new ArrayList<>();

        for(AddMapObjectRequestDTO ele : streetObjects.streetobjects()){
            street_list.add(new MapObject(ele.object_ID(), ele.x(), ele.y(), ele.rotation()));
        }

        for (MapObject ele : street_list){
            streetObjRepo.save(ele);

            ele.setMap(mapService.getStreetPlanById(streetPlanId));
            mapService.getStreetPlanById(streetPlanId).getMapObjects().add(ele);
        }

        return street_list.get(street_list.size() - 1).getId();
    }


   
    
}
