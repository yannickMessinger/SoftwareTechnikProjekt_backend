package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import java.util.List;

public interface StreetConstructionKitService {
    List<StreetConstructionKit> findAllStreetConstructionKits();
    Long createStreetConstructionKit();
    StreetConstructionKit getStreetConstructionKitById(long id);
    void deleteStreetConstructionKitById(long id);
}
