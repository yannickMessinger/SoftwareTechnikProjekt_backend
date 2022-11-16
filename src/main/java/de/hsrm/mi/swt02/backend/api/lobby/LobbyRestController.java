package de.hsrm.mi.swt02.backend.api.lobby;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.lobby.dtos.AddLobbyRequestDTO;
import de.hsrm.mi.swt02.backend.api.lobby.dtos.GetLobbyResponseDTO;

@RestController
@RequestMapping("api/lobby")
public class LobbyRestController {
    
    @Autowired 
    private LobbyServiceImpl lobbyService;


    @GetMapping("")
    public ResponseEntity<GetLobbyResponseDTO> getID() {
        GetLobbyResponseDTO test = GetLobbyResponseDTO.from(new Lobby());
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    // @GetMapping("")
    // public List<GetLobbyResponseDTO> getAllLobbys() {
    //     List<GetLobbyResponseDTO> lobbyDTOs = new ArrayList<GetLobbyResponseDTO>(
    //         lobbyService.findAllLobbys()
    //                     .stream()
    //                     .map(GetLobbyResponseDTO::from)
    //                     .toList());
    //     return lobbyDTOs;
    // }


    @GetMapping("/{id}")
    public GetLobbyResponseDTO getSingleLobby(@PathVariable("id") long id){
        return GetLobbyResponseDTO.from(lobbyService.findLobbyById(id));
    }

    @PostMapping("")
    public long postNewLobby(@RequestBody Lobby lobby){

        return lobbyService.createLobby(lobby.getLobbyName());

    }


    @DeleteMapping("/{id}")
    public void deleteLobby(@PathVariable("id") long id){
        lobbyService.deleteLobby(id);
    }


    @PutMapping("/{id}")
    public void updateLobby(

        @PathVariable("id") long id) {
        
    }
    
}
