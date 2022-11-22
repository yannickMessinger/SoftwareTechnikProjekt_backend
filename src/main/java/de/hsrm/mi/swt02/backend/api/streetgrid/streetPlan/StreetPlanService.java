package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface StreetPlanService {

    long saveStreetPlan(AddStreetPlanRequestDTO dto);

    StreetPlan getStreetPlanById(long id);

}
