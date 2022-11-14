package de.hsrm.mi.swt02.backend.api.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    @Autowired
    PlayerServiceImpl playerService;

    // GET localhost:8080/api/player | {}
    @GetMapping("")
    public List<GetPlayerResponseDTO> getAllPlayers() {
        List<GetPlayerResponseDTO> uDTOs = new ArrayList<GetPlayerResponseDTO>(
                playerService.findAllPlayers()
                        .stream()
                        .map(GetPlayerResponseDTO::from)
                        .toList());
        return uDTOs;
    }

    // POST localhost:8080/api/player | {"userName": "Hans"}
    @PostMapping("")
    public long postNewPlayer(
            @RequestBody AddPlayerRequestDTO uDTO) {
        Player u = playerService.createPlayer(uDTO.userName());

        return u.getId();
    }

    // GET localhost:8080/api/player?id=12 | {}
    @GetMapping("/{id}")
    public GetPlayerResponseDTO getPlayer(
            @PathVariable("id") long id) {
        Player player = playerService.findPlayerById(id);
        return GetPlayerResponseDTO.from(player);
    }

    // DEL localhost:8080/api/player?id=12 | {}
    @DeleteMapping("/{id}")
    public void delPlayer(
            @PathVariable("id") long id) {

        playerService.deletePlayer(id);
    }
}
