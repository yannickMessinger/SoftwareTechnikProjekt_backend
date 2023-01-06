package de.hsrm.mi.swt02.backend.api.map;

import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectResponseDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.GetMapResponseDTO;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.npc.NpcVehicle;
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
@RequestMapping("api/map")
public class MapRestController {

    @Autowired
    MapService mapService;

    @Operation(summary = "Posting a new Map")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map was created"),
            @ApiResponse(responseCode = "400", description = "User JSON wrong syntax")
    })
    @PostMapping("")
    public ResponseEntity<Long> postNewMap(
            @RequestBody AddMapRequestDTO addMapRequestDTO) {
        long id = mapService.saveMap(addMapRequestDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(summary = "Get Map by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Map")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetMapResponseDTO> getMapById(
            @Schema(description = "Map ID", defaultValue = "1")
            @PathVariable long id) {
        GetMapResponseDTO dto = GetMapResponseDTO.from(mapService.getMapById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Get all Maps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Maps")
    })
    @GetMapping("")
    public ResponseEntity<List<GetMapResponseDTO>> getAllMaps() {
        List<GetMapResponseDTO> allMapsDTOs = new ArrayList<>(
                mapService.findAllMaps()
                        .stream()
                        .map(GetMapResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(allMapsDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Delete Map by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Map")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMaps(@PathVariable("id") long id) {
        mapService.deleteMapById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get all MapObjects from a specific Map (by Map id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all MapObjects from Map")
    })
    @GetMapping("/objects/{id}")
    public ResponseEntity<List<GetMapObjectResponseDTO>> getAllMapObjectsFromMap(@PathVariable("id") long id) {
        List<GetMapObjectResponseDTO> allMapObjectDTOs = new ArrayList<>(
                mapService.getMapById(id).getMapObjects()
                        .stream()
                        .map(GetMapObjectResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(allMapObjectDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Tests npc script")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "init npc test")
    })
    @GetMapping("/npc/{id}")
    public ResponseEntity<HttpStatus> testNPCMap(@PathVariable("id") long id) {
        mapService.initNpc(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Posting a new Map and directly assign to Lobby")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map was created and assigned to Lobby"),
            @ApiResponse(responseCode = "400", description = "User JSON wrong syntax")
    })
    @PostMapping("/{lobby_id}")
    public ResponseEntity<Long> postNewMapAndAssignToLobby(
            @PathVariable("lobby_id") long lobbyId,
            @RequestBody AddMapRequestDTO addMapRequestDTO) {
        long mapId = mapService.saveMap(addMapRequestDTO);
        mapService.assignLobbyToMap(mapId, lobbyId);

        return new ResponseEntity<>(mapId, HttpStatus.OK);
    }
}
