package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.AddMultipleStreetConstructionKitsRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.AddStreetConstructionKitRequestDTO;

@Service
public class StreetConstructionKitServiceImpl implements StreetConstructionKitService{

    @Autowired
    private StreetConstructionKitRepository streetConstRepo;


    @Override
    public List<StreetConstructionKit> findAllStreetConstructionKits() {
        
        Optional<List<StreetConstructionKit>> allConstructionKits = Optional.of(streetConstRepo.findAll());
        
        if(allConstructionKits.isEmpty()){
            //logger
        }

        return allConstructionKits.get();

    }

    @Override
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

    @Override
    public StreetConstructionKit getStreetConstructionKitById(long id) {
        Optional<StreetConstructionKit> foundKit = streetConstRepo.findById(id);

        if(foundKit.isEmpty()){
            //logger
        }

        return foundKit.get();
    }

    @Override
    public void deleteStreetConstructionKitById(long id) {
       streetConstRepo.deleteById(id);
    
    }
    
}
