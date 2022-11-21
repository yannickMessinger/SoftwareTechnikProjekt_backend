package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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
    public Long createStreetConstructionKit() {
        
        StreetConstructionKit constructionKit = new StreetConstructionKit();

        return streetConstRepo.save(constructionKit).getId();
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
