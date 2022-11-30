package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.GetStreetConstructionKitResponseDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.AddMultipleStreetConstructionKitsRequestDTO;


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
public class StreetConstructionKitRestController {
    
    @Autowired
    private StreetConstructionKitServiceImpl streetService;

    @Operation(summary = "Get all StreetConstructionKits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got all StreetConstructionKits")
    })
    @GetMapping("")
    public ResponseEntity<List<GetStreetConstructionKitResponseDTO>> getAllStreetConstructionKits() {
        List<GetStreetConstructionKitResponseDTO> streetConstructionKitDTOs = new ArrayList<GetStreetConstructionKitResponseDTO>(
            streetService.findAllStreetConstructionKits()
                        .stream()
                       .map(GetStreetConstructionKitResponseDTO::from)
                       .toList());
            return new ResponseEntity <>(streetConstructionKitDTOs, HttpStatus.OK);
     }

    @Operation(summary = "Get StreetConstructionKit by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got StreetConstructionKits")
    })
     @GetMapping("/{id}")
     public ResponseEntity<GetStreetConstructionKitResponseDTO> getSingleStreetConstructionKit(@PathVariable("id") long id){
        GetStreetConstructionKitResponseDTO constructionKit = GetStreetConstructionKitResponseDTO.from(streetService.getStreetConstructionKitById(id));
        return new ResponseEntity<>(constructionKit, HttpStatus.OK);
    }


    @Operation(summary = "Post new StreetConstructionKit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "new StreetConstructionKit are created")
    })
    @PostMapping("")
    public ResponseEntity<Long> postNewConstructionKit(@RequestBody AddMultipleStreetConstructionKitsRequestDTO kit){

    return new ResponseEntity<>(streetService.createStreetConstructionKit(kit), HttpStatus.OK);
    }

    @Operation(summary = "Delete StreetConstructionKit by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "StreetConstructionKit has been deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStreetConstructionKit(@PathVariable("id") long id){
        streetService.deleteStreetConstructionKitById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
