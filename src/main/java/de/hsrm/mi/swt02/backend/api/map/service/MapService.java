package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {

    long saveMap (AddMapRequestDTO dto);

    Map getMapById (long id);

    void deleteMapById (long id);

    List<Map> findAllMaps ();

    void assignLobbyToMap (long mapId, long lobbyId);

    Map createNewMap ();

    void saveEditedMap (Map map);

}
