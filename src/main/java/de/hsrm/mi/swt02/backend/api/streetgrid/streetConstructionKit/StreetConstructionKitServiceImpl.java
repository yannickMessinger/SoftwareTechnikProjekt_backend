package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.AddMultipleStreetConstructionKitsRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.AddStreetConstructionKitRequestDTO;

/**
 * Service class to manage CRUD database operations on repository for StreetConstructionKit's.
 */
@Service
public class StreetConstructionKitServiceImpl implements StreetConstructionKitService{

    @Autowired
    private StreetConstructionKitRepository streetConstRepo;


    /**
     * @return  list containing all elements of repository
     */
    @Override
    @Transactional
    public List<StreetConstructionKit> findAllStreetConstructionKits() {
        
        List<StreetConstructionKit> allConstructionKits = streetConstRepo.findAll();
        
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
    public Long createStreetConstructionKit(AddMultipleStreetConstructionKitsRequestDTO kit) {
        List <StreetConstructionKit> kit_list = new ArrayList<>();
        //welche ID zurück geben?

        for(AddStreetConstructionKitRequestDTO ele : kit.streetkits()){
            kit_list.add(new StreetConstructionKit(ele.object_ID(), ele.object_Name(), ele.img(), ele.type(), ele.rotatable()));
        }

        for (StreetConstructionKit ele : kit_list){
            streetConstRepo.save(ele);
        }
        
        return kit_list.get(kit_list.size() - 1).getId();
    }

    /**
     * gets single StreetConstructionKit by the handed id
     * @param id id to look for the correct object
     * @return returns StreetConstructionKit if found
     */
    @Override
    @Transactional
    public StreetConstructionKit getStreetConstructionKitById(long id) {
        Optional<StreetConstructionKit> foundKit = streetConstRepo.findById(id);

        if(foundKit.isEmpty()){
            //logger
        }

       return foundKit.orElseThrow();
    }

    /**
     * deletes single StreetConstructionKit by the given id
     * @param id id of the StreetConstructionKit to be deleted.
     */
    @Override
    @Transactional
    public void deleteStreetConstructionKitById(long id) {
       streetConstRepo.deleteById(id);
    
    }
    
}
