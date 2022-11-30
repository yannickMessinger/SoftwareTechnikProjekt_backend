package de.hsrm.mi.swt02.backend.api.map;

import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectResponseDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.GetMapResponseDTO;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
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
@RequestMapping("api/streetplan")
public class MapRestController {

    @Autowired
    MapService mapService;

    @Operation(summary = "Posting a new Map to the DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map was created"),
            @ApiResponse(responseCode = "400", description = "User JSON wrong syntax")
    })
    @PostMapping("")
    public ResponseEntity<Long> postNewStreetPlan(
            @RequestBody AddMapRequestDTO addMapRequestDTO) {
        long id = mapService.saveStreetPlan(addMapRequestDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(summary = "Get Map by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Map")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetMapResponseDTO> getStreetPlanById(
            @Schema(description = "Map ID", defaultValue = "1")
            @PathVariable long id) {
        GetMapResponseDTO dto = GetMapResponseDTO.from(mapService.getStreetPlanById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Get all Streetplans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found StreetPlans")
    })
    @GetMapping("")
    public ResponseEntity<List<GetMapResponseDTO>> getAllStreetPlans() {
        List<GetMapResponseDTO> allStreetPlansDTOs = new ArrayList<>(
                mapService.findAllStreetPlans()
                        .stream()
                        .map(GetMapResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(allStreetPlansDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Delete Map by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Map")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStreetPlans(@PathVariable("id") long id) {
        mapService.deleteStreetPlanById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get all StreetObjects from a specific Streetplan (by Streetplan id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all StreetObjects from Map")
    })
    @GetMapping("/objects/{id}")
    public ResponseEntity<List<GetMapObjectResponseDTO>> getAllStreetObjectsFromStreetPlan(@PathVariable("id") long id) {
        List<GetMapObjectResponseDTO> allStreetObjectDTOs = new ArrayList<GetMapObjectResponseDTO>(
                mapService.getStreetPlanById(id).getMapObjects()
                        .stream()
                        .map(GetMapObjectResponseDTO::from)
                        .toList());
        return new ResponseEntity<>(allStreetObjectDTOs, HttpStatus.OK);
    }
}
