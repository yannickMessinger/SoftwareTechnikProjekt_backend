package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapRepository;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    MapRepository mapRepository;

    @Autowired
    PlayerService playerService;

    /**
     * save map Plan
     * 
     * @param dto
     * @return id
     */
    @Override
    public long saveMap(AddMapRequestDTO dto) {
        Map map = new Map(dto.mapName(), dto.creationDate(), dto.sizeX(), dto.sizeY());
        Player mapOwner = playerService.findPlayerById(dto.mapOwnerID());
        mapOwner.addMapToMapList(map);
        map.setMapOwner(mapOwner);
        map = mapRepository.save(map);

        return map.getId();
    }

    /**
     * get map by id
     * 
     * @param id
     * @return map
     */
    @Override
    public Map getMapById(long id) {
        Optional<Map> mapOpt = mapRepository.findById(id);
        if (mapOpt.isEmpty()) {
            // logger
        }
        return mapOpt.orElseThrow();
    }

    /**
     * delete map by id
     * 
     * @param id
     * @return map
     */
    @Override
    public void deleteMapById(long id) {
        //Map delMap = this.getMapById(id);
        //Player PlayertoDelMapFrom = delMap.getMapOwner();
        //PlayertoDelMapFrom.removeMapFromMapList(delMap);

        mapRepository.deleteById(id);
    }

    /**
     * get all Maps
     * 
     * @return Maps
     */
    @Override
    public List<Map> findAllMaps() {

        Optional<List<Map>> allMaps = Optional.of(mapRepository.findAll());

        if (allMaps.isEmpty()) {
            // logger
        }

        return allMaps.get();
    }

}
