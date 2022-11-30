package de.hsrm.mi.swt02.backend.api.map.service;


import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapRepository;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    MapRepository mapRepository;

    /**
     * save street Plan
     * @param dto
     * @return id
     */
    @Override
    public long saveStreetPlan(AddMapRequestDTO dto) {
        Map map = new Map(dto.lobbyName(), dto.numOfPlayers(), dto.lobbyModeEnum(), dto.sizeX(), dto.sizeY());
        map = mapRepository.save(map);

        return map.getId();
    }

    /**
     * get streetPlan by id
     * @param id
     * @return streetPlan
     */
    @Override
    public Map getStreetPlanById(long id) {
        Optional<Map> streetPlanOpt = mapRepository.findById(id);
        if (streetPlanOpt.isEmpty()) {
            //logger
        }
        return streetPlanOpt.orElseThrow();
    }

    /**
     * delete streetPlan by id
     * @param id
     * @return streetPlan
     */
    @Override
    public void deleteStreetPlanById(long id) {
        mapRepository.deleteById(id);
    }

    /**
     * get all StreetPlans
     * @return StreetPlans
     */
    @Override
    public List<Map> findAllStreetPlans() {

        Optional<List<Map>> allStreetPlans = Optional.of(mapRepository.findAll());

        if (allStreetPlans.isEmpty()) {
            //logger
        }

        return allStreetPlans.get();
    }


}
