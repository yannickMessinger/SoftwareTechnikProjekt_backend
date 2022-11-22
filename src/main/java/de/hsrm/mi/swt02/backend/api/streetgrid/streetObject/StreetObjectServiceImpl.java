package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.AddMultipleStreetObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.AddStreetObjectRequestDTO;


@Service
public class StreetObjectServiceImpl implements StreetObjectService {


    @Autowired
    private StreetObjectRepository streetObjRepo;


    public List<StreetObject> findAllStreetObjects() {
        
        Optional<List<StreetObject>> allStreets = Optional.of(streetObjRepo.findAll());
        
        if(allStreets.isEmpty()){
            //logger
        }

        return allStreets.get();
    }


    public StreetObject getStreetObjectById(long id) {
        Optional<StreetObject> foundStreetObj = streetObjRepo.findById(id);

        if(foundStreetObj.isEmpty()){
            //logger
        }

        return foundStreetObj.get();
    }


    @Override
    public void deleteStreetObjectById(long id) {
        streetObjRepo.deleteById(id);
    }


    @Override
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
