package de.hsrm.mi.swt02.backend.api.player;

import de.hsrm.mi.swt02.backend.api.player.dto.AddPlayerRequestDTO;
import de.hsrm.mi.swt02.backend.api.player.dto.GetPlayerResponseDTO;
import de.hsrm.mi.swt02.backend.api.player.dto.GetPlayerWALResponseDTO;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerServiceImpl;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    @Autowired
    PlayerServiceImpl playerService;

    @Operation(summary = "Get all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found users")})
    @GetMapping("")
    public ResponseEntity<List<GetPlayerResponseDTO>> getAllPlayers() {
        List<GetPlayerResponseDTO> uDTOs = new ArrayList<>(
                playerService.findAllPlayers()
                        .stream()
                        .map(GetPlayerResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(uDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Posting a new user to the DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was created"),
            @ApiResponse(responseCode = "400", description = "User JSON wrong syntax")})
    @PostMapping("")
    public ResponseEntity<Long> postNewPlayer(
            @Schema(
                    description = "User Dto (userName: '')",
                    implementation = AddPlayerRequestDTO.class)
            @RequestBody AddPlayerRequestDTO uDTO) {
        if (isBlankString(uDTO.userName()) || isBlankString(uDTO.password())) {
            return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
        }
        if (containsUsername(playerService.findAllPlayers(), uDTO.userName())) {
            return new ResponseEntity<>(-2L, HttpStatus.BAD_REQUEST);
        }

        Player u = playerService.createPlayer(uDTO.userName(), uDTO.password());

        return new ResponseEntity<>(u.getId(), HttpStatus.OK);
    }

    @Operation(summary = "Get user by username and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user found"),
        @ApiResponse(responseCode = "400", description = "user not found")})
    @PostMapping("/login")
    public ResponseEntity<GetPlayerResponseDTO> getUser(
        @Schema(
            description = "Userlogin", 
            implementation = AddPlayerRequestDTO.class)
        @RequestBody AddPlayerRequestDTO uDto) {
            Player p = playerService.findPlayerByUsernameAndPassword(uDto.userName(), uDto.password());
            if (p != null){
                return new ResponseEntity<>(GetPlayerResponseDTO.from(p), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was created"),
            @ApiResponse(responseCode = "500", description = "User was not found and threw Exception internally")})
    @GetMapping("/{id}")
    public ResponseEntity<GetPlayerResponseDTO> getPlayer(
            @Schema(description = "User ID", defaultValue = "1")
            @PathVariable("id") long id) {
        Player player = playerService.findPlayerById(id);
        return new ResponseEntity<>(GetPlayerResponseDTO.from(player), HttpStatus.OK);
    }

    @Operation(summary = "Get user by ID with active Lobby ID included")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information delivered"),
            @ApiResponse(responseCode = "500", description = "User was not found and threw Exception internally")})
    @GetMapping("/wal/{id}")
    public ResponseEntity<GetPlayerWALResponseDTO> getPlayerWithActiveLobby(
            @Schema(description = "User ID", defaultValue = "1")
            @PathVariable("id") long id) {
        Player player = playerService.findPlayerById(id);
        return new ResponseEntity<>(GetPlayerWALResponseDTO.from(player), HttpStatus.OK);
    }

    @Operation(summary = "Delete user by given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was deleted"),
            @ApiResponse(responseCode = "500", description = "User was not found and threw Exception internally")})
    @DeleteMapping("/{id}")
    public void delPlayer(
            @Schema(description = "User ID", defaultValue = "1")
            @PathVariable("id") long id) {

        playerService.deletePlayer(id);
    }

    private boolean isBlankString (String string) {
        return string == null || string.isBlank() || string.isEmpty();
    }

    public boolean containsUsername(final List<Player> list, final String name){
        return list.stream().anyMatch(o -> o.getUserName().equals(name));
    }
}
