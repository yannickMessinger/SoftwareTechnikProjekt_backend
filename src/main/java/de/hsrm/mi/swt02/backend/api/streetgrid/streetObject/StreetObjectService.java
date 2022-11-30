package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;

import java.util.List;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.AddMultipleStreetObjectsRequestDTO;

/**
 * Service methods that are  to operate on StreetObject database.
 */
public interface StreetObjectService {
    List<StreetObject> findAllStreetObjects();
    StreetObject getStreetObjectById(long id);
    void deleteStreetObjectById(long id);
    Long createStreetObject(AddMultipleStreetObjectsRequestDTO streetObjects, long streetPlanId);
}
