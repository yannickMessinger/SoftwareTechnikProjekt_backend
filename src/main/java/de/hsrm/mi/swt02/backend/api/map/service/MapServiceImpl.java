package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapRepository;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MapServiceImpl implements MapService {

    @Autowired
    MapRepository mapRepository;

    @Autowired
    LobbyRepository lobbyRepository;

    @Override
    @Transactional
    public long saveMap (AddMapRequestDTO dto) {
        Map map = new Map(dto.mapName(), dto.creationDate(), dto.sizeX(), dto.sizeY());
        map = mapRepository.save(map);

        return map.getId();
    }

    @Override
    @Transactional
    public Map createNewMap () {
        Map map = new Map();

        return mapRepository.save(map);
    }

    @Override
    @Transactional
    public void assignLobbyToMap (long mapId, long lobbyId) {
        Map map = this.getMapById(mapId);

        lobbyRepository.findById(lobbyId).ifPresent(lobby -> {
            if (lobby.getMap() != null) {
                lobby.getMap().setLobby(null);

            }
            if (map.getLobby() != null) {
                map.getLobby().setMap(null);
            }

            lobby.setMap(map);
            map.setLobby(lobby);
            mapRepository.save(map);
        });
    }

    @Override
    @Transactional
    public Map getMapById (long id) {
        Optional<Map> mapOpt = mapRepository.findById(id);
        if (mapOpt.isEmpty()) {
            log.warn("No Map was found");
        }
        return mapOpt.orElseThrow();
    }

    @Override
    @Transactional
    public void deleteMapById (long id) {
        mapRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Map> findAllMaps () {

        Optional<List<Map>> allMaps = Optional.of(mapRepository.findAll());

        return allMaps.get();
    }

    @Override
    @Transactional
    public void saveEditedMap (Map map) {
        mapRepository.save(map);
    }
}
