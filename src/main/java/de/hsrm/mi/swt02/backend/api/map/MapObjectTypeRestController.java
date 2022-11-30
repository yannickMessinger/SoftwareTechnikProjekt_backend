package de.hsrm.mi.swt02.backend.api.map;

import de.hsrm.mi.swt02.backend.api.map.service.MapObjectTypeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectTypeResponseDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/streetconstructionkit")
public class MapObjectTypeRestController {
    
    @Autowired
    private MapObjectTypeServiceImpl streetService;

    @Operation(summary = "Get all StreetConstructionKits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got all StreetConstructionKits")
    })
    @GetMapping("")
    public ResponseEntity<List<GetMapObjectTypeResponseDTO>> getAllStreetConstructionKits() {
        List<GetMapObjectTypeResponseDTO> streetConstructionKitDTOs = new ArrayList<GetMapObjectTypeResponseDTO>(
            streetService.findAllStreetConstructionKits()
                        .stream()
                       .map(GetMapObjectTypeResponseDTO::from)
                       .toList());
            return new ResponseEntity <>(streetConstructionKitDTOs, HttpStatus.OK);
     }

    @Operation(summary = "Get MapObjectType by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got StreetConstructionKits")
    })
     @GetMapping("/{id}")
     public ResponseEntity<GetMapObjectTypeResponseDTO> getSingleStreetConstructionKit(@PathVariable("id") long id){
        GetMapObjectTypeResponseDTO constructionKit = GetMapObjectTypeResponseDTO.from(streetService.getStreetConstructionKitById(id));
        return new ResponseEntity<>(constructionKit, HttpStatus.OK);
    }


    @Operation(summary = "Post new MapObjectType")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "new MapObjectType are created")
    })
    @PostMapping("")
    public ResponseEntity<Long> postNewConstructionKit(@RequestBody AddMapObjectsRequestDTO kit){

    return new ResponseEntity<>(streetService.createStreetConstructionKit(kit), HttpStatus.OK);
    }

    @Operation(summary = "Delete MapObjectType by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MapObjectType has been deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStreetConstructionKit(@PathVariable("id") long id){
        streetService.deleteStreetConstructionKitById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
