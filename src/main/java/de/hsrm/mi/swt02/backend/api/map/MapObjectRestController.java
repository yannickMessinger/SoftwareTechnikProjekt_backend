package de.hsrm.mi.swt02.backend.api.map;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.swt02.backend.api.map.service.MapObjectServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectResponseDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectTypeResponseDTO;

@RestController
@RequestMapping("api/mapobject")
public class MapObjectRestController {
    

    @Autowired
    private MapObjectServiceImpl mapObjectService;

    @Operation(summary = "Get all mapObjects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got all mapObjects")
    })
    @GetMapping("")
    public ResponseEntity<List<GetMapObjectResponseDTO>> getAllMapObjects() {
        List<GetMapObjectResponseDTO> allMapObjectDTOs = new ArrayList<GetMapObjectResponseDTO>(
            mapObjectService.findAllMapObjects()
                        .stream()
                       .map(GetMapObjectResponseDTO::from)
                       .toList());
            return new ResponseEntity <>(allMapObjectDTOs, HttpStatus.OK);
     }

    @Operation(summary = "Get MapObject by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got MapObject")
    })
     @GetMapping("/{id}")
     public ResponseEntity<GetMapObjectResponseDTO> getSingleMapObject(@PathVariable("id") long id){
        GetMapObjectResponseDTO mapObjectDTO = GetMapObjectResponseDTO.from(mapObjectService.getMapObjectById(id));
        return new ResponseEntity<>(mapObjectDTO, HttpStatus.OK);
    }

    @Operation(summary = "Post new MapObjects to Map (by id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "new MapObjects are created")
    })
    @PostMapping("/{map_id}")
    public ResponseEntity<Long> postMapObject(
            @Schema(description = "MapDto",
                    implementation = AddMapObjectsRequestDTO.class,
                    required = true)
            @RequestBody AddMapObjectsRequestDTO mapObjects,
            @PathVariable("map_id") long mapId){

        return new ResponseEntity<>(mapObjectService.createMapObject(mapObjects, mapId), HttpStatus.OK);
    }


    @Operation(summary = "Delete MapObject by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MapObject has been deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMapObject(@PathVariable("id") long id){
        mapObjectService.deleteMapObjectById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get list of all placeable MapObjects")
    @GetMapping("/list")
    public ResponseEntity<List<GetMapObjectTypeResponseDTO>> getMapObjectList() {
        return null;
    }
}
