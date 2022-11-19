package de.hsrm.mi.swt02.backend.api.streetgrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.streetgrid.dtos.AddStreetGridRequestDTO;

@Service
public class StreetGridServiceImpl implements StreetGridService {


    @Autowired
    private StreetGridRepository gridRepository;

    @Override
    public long createStreetGrid(String gridName, AddStreetGridRequestDTO dto) {
       
        return 0;
    }
    
}
