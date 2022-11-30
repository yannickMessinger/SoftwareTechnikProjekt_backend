package de.hsrm.mi.swt02.backend.api.map.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectTypeRepository;
import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectTypeRequestDTO;

/**
 * Service class to manage CRUD database operations on repository for MapObjectType's.
 */
@Service
public class MapObjectTypeServiceImpl implements MapObjectTypeService {

    @Autowired
    private MapObjectTypeRepository streetConstRepo;


    /**
     * @return  list containing all elements of repository
     */
    @Override
    @Transactional
    public List<MapObjectType> findAllStreetConstructionKits() {
        
        List<MapObjectType> allConstructionKits = streetConstRepo.findAll();
        
        if(allConstructionKits.isEmpty()){
            //logger
        }

        return allConstructionKits;

    }

    /**
     * adds incoming StreetConstructionKits from frontend to the repository. 
     * @param kit  initial converting from JSON to regular java object from incoming Request Body in corresponding REST Controller,
     * every Entity is saved individually.
     * 
     * @return returns the id of the Entity that was saved last. 
     */
    @Override
    @Transactional
    public Long createStreetConstructionKit(AddMapObjectsRequestDTO kit) {
        List <MapObjectType> kit_list = new ArrayList<>();
        //welche ID zurück geben?

        for(AddMapObjectTypeRequestDTO ele : kit.streetkits()){
            kit_list.add(new MapObjectType(ele.object_ID(), ele.object_Name(), ele.img(), ele.type(), ele.rotatable()));
        }

        for (MapObjectType ele : kit_list){
            streetConstRepo.save(ele);
        }
        
        return kit_list.get(kit_list.size() - 1).getId();
    }

    /**
     * gets single MapObjectType by the handed id
     * @param id id to look for the correct object
     * @return returns MapObjectType if found
     */
    @Override
    @Transactional
    public MapObjectType getStreetConstructionKitById(long id) {
        Optional<MapObjectType> foundKit = streetConstRepo.findById(id);

        if(foundKit.isEmpty()){
            //logger
        }

       return foundKit.orElseThrow();
    }

    /**
     * deletes single MapObjectType by the given id
     * @param id id of the MapObjectType to be deleted.
     */
    @Override
    @Transactional
    public void deleteStreetConstructionKitById(long id) {
       streetConstRepo.deleteById(id);
    
    }
    
}
