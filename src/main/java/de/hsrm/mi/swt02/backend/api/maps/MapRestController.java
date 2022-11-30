package de.hsrm.mi.swt02.backend.api.maps;

import de.hsrm.mi.swt02.backend.api.maps.dtos.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.maps.dtos.GetMapResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/map")
public class MapRestController {

    @Autowired
    private MapServiceImpl mapService;

    @Operation(summary = "Get all created maps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found maps")})
    @GetMapping("")
    public ResponseEntity<List<GetMapResponseDTO>> getAllMaps() {
        List<GetMapResponseDTO> mDTOs = new ArrayList<>(
                mapService.findAllMaps()
                    .stream()
                    .map(GetMapResponseDTO::from)
                    .toList());
        return new ResponseEntity<>(mDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get map by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map was found"),
            @ApiResponse(responseCode = "400", description = "Map was not found by given ID")})
    @GetMapping("/{id}")
    public ResponseEntity<GetMapResponseDTO> getMap(
            @Schema(description = "Map ID", defaultValue = "1", required = true)
            @PathVariable("id") long id) {
        Map map = mapService.findMapByID(id);
        return new ResponseEntity<>(GetMapResponseDTO.from(map), HttpStatus.OK);
    }

    @Operation(summary = "Create map and return ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map was created"),
            @ApiResponse(responseCode = "400", description = "Map couldn´t be created")})
    @PostMapping("/")
    public ResponseEntity<Long> createMap(
            @Schema(
                    implementation = Map.class,
                    description = "Map JSON",
                    required = true)
            @RequestBody @NotNull AddMapRequestDTO mDTO) {
        long mapID =  mapService.createMap(new Map(
                mDTO.mapName(),
                mDTO.date(),
                mDTO.owner()));
        return new ResponseEntity<>(mapID, HttpStatus.OK);
    }

    @Operation(summary = "Delete map by given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map was deleted")})
    @DeleteMapping("/{id}")
    public void deleteMap(
            @Schema(description = "Map ID", defaultValue = "1", required = true)
            @PathVariable("id") long id) {

        mapService.deleteMap(id);
    }
}
