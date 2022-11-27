package de.hsrm.mi.swt02.backend.api.lobby;

import de.hsrm.mi.swt02.backend.api.lobby.dtos.GetLobbyResponseDTO;
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
@RequestMapping("api/lobby")
public class LobbyRestController {

    @Autowired
    private LobbyServiceImpl lobbyService;
    
    @Operation(summary = "Get all available Lobby´s")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Lobby´s")})
    @GetMapping("")
    public ResponseEntity<List<GetLobbyResponseDTO>> getAllLobbys() {
        List<GetLobbyResponseDTO> lobbyDTOs = new ArrayList<>(
                lobbyService
                        .findAllLobbys()
                        .stream()
                        .map(GetLobbyResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(lobbyDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get Lobby by Lobby ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Lobby")})
    @GetMapping("/{id}")
    public ResponseEntity<GetLobbyResponseDTO> getLobby(
            @Schema(description = "Lobby ID", defaultValue = "1", required = true)
            @PathVariable("id") long id) {
        GetLobbyResponseDTO lobby = GetLobbyResponseDTO.from(lobbyService.findLobbyById(id));
        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

    @Operation(summary = "Post new Lobby")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posted Lobby successfully")})
    @PostMapping("")
    public ResponseEntity<Long> postNewLobby(
            @Schema(description = "Lobby Dto",
                    defaultValue =  "{ " +
                                    "  lobbyName: Default Lobby," +
                                    "  numOfPlayers: 1," +
                                    "  lobbyState: BUILD_MODE" +
                                    " }",
                    required = true)
            @RequestBody Lobby lobby) {

        return new ResponseEntity<>(lobbyService.createLobby(lobby), HttpStatus.OK);

    }

    @Operation(description = "Delete Lobby by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Lobby")})
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLobby(
            @Schema(description = "Lobby ID", defaultValue = "1", required = true)
            @PathVariable("id") long id) {
        lobbyService.deleteLobby(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Todo: match DTO's with DTO's from frontend!
    @Operation(description = "Update Lobby with given JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Lobby")})
    @PutMapping("/{id}")
    public void updateLobby(
            @Schema(description = "Lobby ID", defaultValue = "1", required = true)
            @PathVariable("id") long id) {

    }

}
