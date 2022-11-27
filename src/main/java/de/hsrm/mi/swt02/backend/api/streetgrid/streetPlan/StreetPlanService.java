package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.StreetObject;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StreetPlanService {

    long saveStreetPlan(AddStreetPlanRequestDTO dto);
    StreetPlan getStreetPlanById(long id);
    void deleteStreetPlanById(long id);
    List<StreetPlan> findAllStreetPlans();

}
