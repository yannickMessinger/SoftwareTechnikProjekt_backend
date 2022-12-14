package de.hsrm.mi.swt02.backend.api.map.service;


import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
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

    @Autowired
    LobbyRepository lobbyRepository;

    /**
     * save map Plan
     * @param dto
     * @return id
     */
    @Override
    public long saveMap(AddMapRequestDTO dto) {
        Map map = new Map(dto.mapName(), dto.creationDate(), dto.sizeX(), dto.sizeY());
        map = mapRepository.save(map);

        return map.getId();
    }

    @Override
    public Map createNewMap(){
        Map map = new Map();

        return mapRepository.save(map);
    }

    /**
     * assign new Lobby to map and cut old relations
     * 
     */
    public void assignLobbyToMap(long mapId, long lobbyId) {
        Map map =  this.getMapById(mapId);

        lobbyRepository.findById(lobbyId).ifPresent(lobby -> {
            if(lobby.getMap() != null) {
                lobby.getMap().setLobby(null);

            }
            if(map.getLobby() != null) {
                map.getLobby().setMap(null);
            }

            lobby.setMap(map);
            map.setLobby(lobby);
            mapRepository.save(map);
        });
    }

    /**
     * get map by id
     * @param id
     * @return map
     */
    @Override
    public Map getMapById(long id) {
        Optional<Map> mapOpt = mapRepository.findById(id);
        if (mapOpt.isEmpty()) {
            //logger
        }
        return mapOpt.orElseThrow();
    }

    /**
     * delete map by id
     * @param id
     * @return map
     */
    @Override
    public void deleteMapById(long id) {
        mapRepository.deleteById(id);
    }

    /**
     * get all Maps
     * @return Maps
     */
    @Override
    public List<Map> findAllMaps() {

        Optional<List<Map>> allMaps = Optional.of(mapRepository.findAll());

        if (allMaps.isEmpty()) {
            //logger
        }

        return allMaps.get();
    }


}
