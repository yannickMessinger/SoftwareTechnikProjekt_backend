package de.hsrm.mi.swt02.backend.api.lobby;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.lobby.dtos.GetLobbyResponseDTO;

@RestController
@RequestMapping("api/lobby")
public class LobbyRestController {
    
    @Autowired 
    private LobbyServiceImpl lobbyService;



    @GetMapping("")
    public List<GetLobbyResponseDTO> getAllLobbys() {
        List<GetLobbyResponseDTO> lobbyDTOs = new ArrayList<GetLobbyResponseDTO>(
            lobbyService.findAllLobbys()
                        .stream()
                        .map(GetLobbyResponseDTO::from)
                        .toList());
        return lobbyDTOs;
    }
}
