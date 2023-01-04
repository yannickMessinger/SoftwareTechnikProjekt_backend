package de.hsrm.mi.swt02.backend.api.game.position;

import de.hsrm.mi.swt02.backend.api.game.position.dto.AddPlayerPositionDTO;
import de.hsrm.mi.swt02.backend.api.game.position.dto.GetPlayerPositionsDTO;
import de.hsrm.mi.swt02.backend.api.game.position.service.PositionServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectTypeServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerServiceImpl;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/game/position")
@Slf4j
public class PositionRestController {

    @Autowired
    PositionServiceImpl positionService;
    @Autowired
    PlayerServiceImpl playerService;
    @Autowired
    MapService mapService;
    @Autowired
    MapObjectTypeServiceImpl mapObjectTypeService;

    @Operation(summary = "Get all player positions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Position´s")})
    @GetMapping("")
    public ResponseEntity<List<GetPlayerPositionsDTO>> getPlayerPositions() {
        List<GetPlayerPositionsDTO> playerPositions = new ArrayList<>(
                positionService
                        .findAllPositions()
                        .stream()
                        .map(GetPlayerPositionsDTO::from)
                        .toList()
        );
        return new ResponseEntity<>(playerPositions, HttpStatus.OK);
    }

    @Operation(summary = "Get PlayerPosition by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Position")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerPosition(
            @PathVariable("id") long id) {
        positionService.deletePosition(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Post new Position by given X, Y coordinate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PlayerPosition ID")})
    @PostMapping("/{id}")
    public ResponseEntity<Long> postPlayerPosition(
            @RequestBody AddPlayerPositionDTO playerPositionDTO,
            @Schema(description = "Map ID")
            @PathVariable("id") long id) {
        int x = 0;
        int y = 0;
        if (playerPositionDTO.x() == 0 || playerPositionDTO.y() == 0) {
            MapObject mapObject = getRandomMapObject(id);
            if (mapObject != null) {
                x = mapObject.getX();
                y = mapObject.getY();
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            x = playerPositionDTO.x();
            y = playerPositionDTO.y();
        }
        var player = playerService.findPlayerById(playerPositionDTO.playerID());
        return new ResponseEntity<>(
                positionService.createPosition(
                        player,
                        x,
                        y
                ),
                HttpStatus.OK
        );
    }

    /**
     * Get all MapObjects
     *
     * @return all MapObjects of given Map ID
     */
    private MapObject getRandomMapObject(long id) {
        List<MapObject> mapObjects = mapService.getMapById(id).getMapObjects();
        if (mapObjects.isEmpty()) {
            log.debug("No mapObjects");
            return null;
        }
        mapObjects = filterStreetMapObjects(mapObjects);
        Random rand = new Random();
        return mapObjects.get(rand.nextInt(mapObjects.size()));
    }

    /**
     * Get all MapObjects with Type StreetObject
     *
     * @param mapObjects unfiltered
     * @return filtered mapObjects
     */
    private List<MapObject> filterStreetMapObjects(List<MapObject> mapObjects) {
        return mapObjects
                .stream()
                .filter(mapObject ->
                        mapObjectTypeService
                                .findMapObjectTypeById(
                                        mapObject.getObjectTypeId()
                                ).getGroupId() == 0
                ).toList();
    }
}
