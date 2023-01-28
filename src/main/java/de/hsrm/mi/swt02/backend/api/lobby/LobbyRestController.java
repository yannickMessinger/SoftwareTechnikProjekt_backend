package de.hsrm.mi.swt02.backend.api.lobby;

import de.hsrm.mi.swt02.backend.api.lobby.dto.AddLobbyRequestDTO;
import de.hsrm.mi.swt02.backend.api.lobby.dto.GetLobbyResponseDTO;
import de.hsrm.mi.swt02.backend.api.lobby.service.LobbyServiceImpl;
import de.hsrm.mi.swt02.backend.api.player.dto.GetPlayerResponseDTO;
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
@RequestMapping ("api/lobby")
public class LobbyRestController {

    @Autowired
    private LobbyServiceImpl lobbyService;

    @Operation (summary = "Get all available Lobby´s")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Found Lobby´s")})
    @GetMapping ("")
    public ResponseEntity<List<GetLobbyResponseDTO>> getAllLobbys () {
        List<GetLobbyResponseDTO> lobbyDTOs = new ArrayList<>(
                lobbyService
                        .findAllLobbys()
                        .stream()
                        .map(GetLobbyResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(lobbyDTOs, HttpStatus.OK);
    }

    @Operation (summary = "Get Lobby by Lobby ID")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Found Lobby")})
    @GetMapping ("/{id}")
    public ResponseEntity<GetLobbyResponseDTO> getLobby (
            @Schema (description = "Lobby ID", defaultValue = "1", required = true)
            @PathVariable ("id") long id) {
        GetLobbyResponseDTO lobby = GetLobbyResponseDTO.from(lobbyService.findLobbyById(id));
        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

    @Operation (summary = "Post new Lobby")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Posted Lobby successfully")})
    @PostMapping ("")
    public ResponseEntity<Long> postNewLobby (
            @Schema (description = "Lobby Dto",
                    implementation = AddLobbyRequestDTO.class,
                    required = true)
            @RequestBody AddLobbyRequestDTO lobbyDTO) {

        return new ResponseEntity<>(lobbyService.createLobby(lobbyDTO.lobbyName(), lobbyDTO.lobbyModeEnum(), lobbyDTO.numOfPlayers(), lobbyDTO.hostId()), HttpStatus.OK);
    }

    @Operation (summary = "Delete Lobby by ID")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Successfully deleted Lobby")})
    @DeleteMapping ("/{id}")
    public ResponseEntity<HttpStatus> deleteLobby (
            @Schema (description = "Lobby ID", defaultValue = "1", required = true)
            @PathVariable ("id") long id) {
        lobbyService.deleteLobby(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Todo: match DTO's with DTO's from frontend!
    @Operation (summary = "Update Lobby with given JSON")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Successfully updated Lobby")})
    @PutMapping ("/{id}")
    public void updateLobby (
            @Schema (description = "Lobby ID", defaultValue = "1", required = true)
            @PathVariable ("id") long id) {

    }

    @Operation (summary = "Add Player to Lobby ")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Successfully added Player to Lobby")})
    @PostMapping (value = "/get_players/{id}", params = "player_id")
    public ResponseEntity<HttpStatus> addPlayerToLobby (@PathVariable ("id") long id, @RequestParam long player_id) {
        lobbyService.addPlayerToLobby(id, player_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation (summary = "Remove Player from Lobby ")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Successfully removed Player from Lobby")})
    @DeleteMapping (value = "/get_players/{id}", params = "player_id")
    public ResponseEntity<HttpStatus> removePlayerToLobby (@PathVariable ("id") long id, @RequestParam long player_id) {
        lobbyService.removePlayerFromLobby(id, player_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation (summary = "Get all Player from Lobby")
    @GetMapping ("/get_players/{lobby_id}")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Successfully got all Players from Lobby")})
    public ResponseEntity<List<GetPlayerResponseDTO>> getAllPlayersFromLobby (@PathVariable ("lobby_id") long id) {
        List<GetPlayerResponseDTO> playerDTOS = new ArrayList<>(
                lobbyService
                        .findAllPlayersFromLobby(id)
                        .stream()
                        .map(GetPlayerResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(playerDTOS, HttpStatus.OK);
    }

    @Operation (summary = "Assign present map to lobby")
    @PostMapping ("/{lobby_id}/{map_id}")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "Assign new map to lobby successfully"
            )
    })
    public ResponseEntity<Long> assignMapToLobby (
            @PathVariable ("lobby_id") long lobbyId,
            @PathVariable ("map_id") long mapId
    ) {
        lobbyService.addMap(lobbyId, mapId);
        return new ResponseEntity<>(mapId, HttpStatus.OK);
    }

    @Operation (summary = "Post new Lobby and assign Map to the posted Lobby")
    @PostMapping ("/map/{map_id}")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "New Lobby posted and successfully assigned to map"
            )
    })
    public ResponseEntity<Long> postNewLobbyAndAssignToMap (
            @Schema (description = "Lobby Dto",
                    implementation = AddLobbyRequestDTO.class,
                    required = true)
            @RequestBody AddLobbyRequestDTO lobbyDTO,
            @PathVariable ("map_id") long mapId) {

        long lobbyId = lobbyService.createLobbyWithMap(lobbyDTO.lobbyName(), lobbyDTO.lobbyModeEnum(), lobbyDTO.numOfPlayers(), lobbyDTO.hostId(), mapId);
        return new ResponseEntity<>(lobbyId, HttpStatus.OK);
    }


}
