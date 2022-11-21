package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreetPlanServiceImpl implements StreetPlanService {

    @Autowired
    StreetPlanRepository streetPlanRepository;

    @Override
    public long saveStreetPlan(AddStreetPlanRequestDTO dto) {
        StreetPlan streetPlan = new StreetPlan(dto.lobbyName(), dto.numOfPlayers(), dto.lobbyMode(), dto.size());
        streetPlan = streetPlanRepository.save(streetPlan);

        return streetPlan.getId();
    }

    @Override
    public StreetPlan getStreetPlanById(long id) {
        Optional<StreetPlan> streetPlanOpt = streetPlanRepository.findById(id);
        if (streetPlanOpt.isEmpty()) {
            //logger
        }
        return streetPlanOpt.orElseThrow();
    }


}
