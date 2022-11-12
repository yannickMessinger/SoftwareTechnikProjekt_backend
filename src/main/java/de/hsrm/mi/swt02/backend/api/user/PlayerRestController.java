package de.hsrm.mi.swt02.backend.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    @Autowired
    PlayerServiceImpl userService;

    // GET localhost:8080/api/player | {}
    @GetMapping("/")
    public List<GetPlayerResponseDTO> getAllUsers() {
        List<GetPlayerResponseDTO> uDTOs = new ArrayList<GetPlayerResponseDTO>(
                userService.findAllUsers()
                    .stream()
                    .map(GetPlayerResponseDTO::from)
                    .toList());
        return uDTOs;
    }

    // POST localhost:8080/api/player | {"userName": "Hans"}
    @PostMapping("/")
    public long postNewUser(
            @RequestBody AddPlayerRequestDTO uDTO) {
        Player u = userService.createUser(uDTO.userName());

        return u.getId();
    }

    // GET localhost:8080/api/player?id=12 | {}
    @GetMapping("/{id}")
    public GetPlayerResponseDTO getUser(
            @PathVariable("id") long id) {
        Player player = userService.findUserById(id);
        return GetPlayerResponseDTO.from(player);
    }

    // DEL localhost:8080/api/player?id=12 | {}
    @DeleteMapping("/{id}")
    public void delUser(
            @PathVariable("id") long id) {

        userService.deleteUser(id);
    }
}
