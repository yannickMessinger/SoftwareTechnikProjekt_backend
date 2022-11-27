package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import java.util.List;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.AddMultipleStreetConstructionKitsRequestDTO;


public interface StreetConstructionKitService {
    List<StreetConstructionKit> findAllStreetConstructionKits();
    Long createStreetConstructionKit( AddMultipleStreetConstructionKitsRequestDTO kit);
    StreetConstructionKit getStreetConstructionKitById(long id);
    void deleteStreetConstructionKitById(long id);
}
