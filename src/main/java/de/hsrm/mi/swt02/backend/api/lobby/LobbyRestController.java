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
        public ResponseEntity<List<GetLobbyResponseDTO>> getAllLobbys() {
        List<GetLobbyResponseDTO> lobbyDTOs = new ArrayList<GetLobbyResponseDTO>(
            lobbyService.findAllLobbys()
                        .stream()
                       .map(GetLobbyResponseDTO::from)
                       .toList());
            return new ResponseEntity <>(lobbyDTOs, HttpStatus.OK);
     }


    @GetMapping("/{id}")
    public ResponseEntity<GetLobbyResponseDTO> getSingleLobby(@PathVariable("id") long id){
        GetLobbyResponseDTO lobby = GetLobbyResponseDTO.from(lobbyService.findLobbyById(id));
        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> postNewLobby(@RequestBody Lobby lobby){

        return new ResponseEntity<>(lobbyService.createLobby(lobby.getLobbyName()), HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLobby(@PathVariable("id") long id){
        lobbyService.deleteLobby(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Todo: match DTO's with DTO's from frontend!
    @PutMapping("/{id}")
    public void updateLobby(

        @PathVariable("id") long id) {
        
    }
    
}
