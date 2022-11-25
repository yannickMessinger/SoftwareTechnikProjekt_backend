package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;


import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreetPlanServiceImpl implements StreetPlanService {

    @Autowired
    StreetPlanRepository streetPlanRepository;

    @Override
    public long saveStreetPlan(AddStreetPlanRequestDTO dto) {
        StreetPlan streetPlan = new StreetPlan(dto.lobbyName(), dto.numOfPlayers(), dto.lobbyMode(), dto.sizeX(), dto.sizeY());
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

    @Override
    public void deleteStreetPlanById(long id) {
        streetPlanRepository.deleteById(id);
    }

    @Override
    public List<StreetPlan> findAllStreetPlans() {

        Optional<List<StreetPlan>> allStreetPlans = Optional.of(streetPlanRepository.findAll());

        if (allStreetPlans.isEmpty()) {
            //logger
        }

        return allStreetPlans.get();
    }


}
