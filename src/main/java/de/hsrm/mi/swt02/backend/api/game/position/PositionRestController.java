package de.hsrm.mi.swt02.backend.api.game.position;

import de.hsrm.mi.swt02.backend.api.game.position.dto.AddObjectPositionDTO;
import de.hsrm.mi.swt02.backend.api.game.position.dto.GetObjectPositionDTO;
import de.hsrm.mi.swt02.backend.api.game.position.service.PositionServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectTypeServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerServiceImpl;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
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
@RequestMapping("/api/position")
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
    @Autowired
    MapObjectServiceImpl mapObjectService;
    @Autowired
    MapObjectRepository mapObjectRepository;

    @Operation(summary = "Get all object positions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Position´s")})
    @GetMapping("")
    public ResponseEntity<List<GetObjectPositionDTO>> getPlayerPositions() {
        List<GetObjectPositionDTO> playerPositions = new ArrayList<>(
                positionService
                        .findAllPositions()
                        .stream()
                        .map(GetObjectPositionDTO::from)
                        .toList()
        );
        return new ResponseEntity<>(playerPositions, HttpStatus.OK);
    }

    @Operation(summary = "Delete ObjectPosition by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Position")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerPosition(
            @PathVariable("id") long id) {
        positionService.deletePosition(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Post new Position by given X, Y coordinate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Position ID")})
    @PostMapping("/{id}")
    public ResponseEntity<GetObjectPositionDTO> postObjectPosition(
            @RequestBody AddObjectPositionDTO objectPositionDTO,
            @Schema(description = "Map ID")
            @PathVariable("id") long id) {

        var mapObject = mapObjectService.getMapObjectById(objectPositionDTO.objectId());

        // only can be null if no object was created beforehand
        if (objectPositionDTO.objectId() == -1) {
            mapObject = mapObjectRepository.save(new MapObject(7, 0, 0, 0));
        }
        var mapObjectType = mapObjectTypeService.findMapObjectTypeById(mapObject.getObjectTypeId());
        var mapObjectGroupId = mapObjectType.getGroupId();

        if (mapObject.getX() == 0 && mapObject.getY() == 0) {

            // can be placed on a street-element (mapObjectTypeGroupId == 0)
            if (mapObjectGroupId == 3 || mapObjectGroupId == 4) {
                mapObject = getRandomMapObject(id, 0);

                // can be placed on a train rail (mapObjectTypeGroupId == 6)
            } else if (mapObjectGroupId == 5) {
                mapObject = getRandomMapObject(id, 6);
            }
            assert mapObject != null;
        }

        var objectPosition = positionService.createPosition(mapObjectType.getObjectTypeId(), mapObject.getX(), mapObject.getY(), mapObject.getRotation());

        return new ResponseEntity<>(
                GetObjectPositionDTO.from(objectPosition),
                HttpStatus.OK);
    }

    /**
     * Get all MapObjects
     * @return all MapObjects of given Map ID
     */
    private MapObject getRandomMapObject(long id, long mapObjectTypeGroupId) {
        List<MapObject> mapObjects = mapService.getMapById(id).getMapObjects();
        if (mapObjects.isEmpty()) {
            log.debug("No mapObjects");
            return null;
        }
        mapObjects = mapObjects
                .stream()
                .filter(mapObject -> mapObjectTypeService.findMapObjectTypeById(mapObject.getObjectTypeId()).getGroupId() == mapObjectTypeGroupId)
                .toList();
        Random rand = new Random();
        return mapObjects.get(rand.nextInt(mapObjects.size()));
    }
}