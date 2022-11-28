package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.AddMultipleStreetObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.AddStreetObjectRequestDTO;

/**
 * Service Class to handle CRUD operations on the StreetObjectRepository.
 */

@Service
public class StreetObjectServiceImpl implements StreetObjectService {


    @Autowired
    private StreetObjectRepository streetObjRepo;



     /**
     * @return  list containing all StreetObjects of repository.
     */
    @Override
    @Transactional
    public List<StreetObject> findAllStreetObjects() {
        
        Optional<List<StreetObject>> allStreets = Optional.of(streetObjRepo.findAll());
        
        if(allStreets.isEmpty()){
            //logger
        }

        return allStreets.get();
    }

    /**
     * gets single StreetObject by the handed id
     * @param id id to look for the correct object
     * @return returns StreetObject if found
     */
    @Override
    @Transactional
    public StreetObject getStreetObjectById(long id) {
        Optional<StreetObject> foundStreetObj = streetObjRepo.findById(id);

        if(foundStreetObj.isEmpty()){
            //logger
        }
        

        return foundStreetObj.orElseThrow();
    }


    /**
     * deletes single StreetObject by the given id
     * @param id id of the StreetObject to be deleted.
     */
    @Override
    @Transactional
    public void deleteStreetObjectById(long id) {
        streetObjRepo.deleteById(id);
    }



     /**
     * adds incoming StreetObjects from frontend to the repository. 
     * @param streetObjects  initial converting from JSON to regular java object from incoming Request Body in corresponding REST Controller,
     * every Entity is saved individually.
     * 
     * @return returns the id of the Entity that was saved last. 
     */
    @Override
    @Transactional
    public Long createStreetObject(AddMultipleStreetObjectsRequestDTO streetObjects) {
        List <StreetObject> street_list = new ArrayList<>();

        for(AddStreetObjectRequestDTO ele : streetObjects.streetobjects()){
            street_list.add(new StreetObject(ele.object_ID(), ele.x(), ele.y(), ele.rotation()));
        }

        for (StreetObject ele : street_list){
            streetObjRepo.save(ele);
        }
        
        return street_list.get(street_list.size() - 1).getId();
    }


   
    
}
