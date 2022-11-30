package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
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

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.AddMultipleStreetObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.GetStreetObjectResponseDTO;

@RestController
@RequestMapping("api/streetobject")
public class StreetObjectRestController {
    

    @Autowired
    private StreetObjectServiceImpl streeObjectService;

    @Operation(summary = "Get all StreetObjects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got all StreetObjects")
    })
    @GetMapping("")
    public ResponseEntity<List<GetStreetObjectResponseDTO>> getAllStreetObjects() {
        List<GetStreetObjectResponseDTO> allStreetObjectDTOs = new ArrayList<GetStreetObjectResponseDTO>(
            streeObjectService.findAllStreetObjects()
                        .stream()
                       .map(GetStreetObjectResponseDTO::from)
                       .toList());
            return new ResponseEntity <>(allStreetObjectDTOs, HttpStatus.OK);
     }

    @Operation(summary = "Get StreetObject by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got StreetObject")
    })
     @GetMapping("/{id}")
     public ResponseEntity<GetStreetObjectResponseDTO> getSingleStreetObject(@PathVariable("id") long id){
        GetStreetObjectResponseDTO streetObjectDTO = GetStreetObjectResponseDTO.from(streeObjectService.getStreetObjectById(id));
        return new ResponseEntity<>(streetObjectDTO, HttpStatus.OK);
    }

    @Operation(summary = "Post new StreetObjects to StreetPlan (by id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "new StreetObjects are created")
    })
    @PostMapping("/{streetplan_id}")
    public ResponseEntity<Long> postNewStreetObject(@RequestBody AddMultipleStreetObjectsRequestDTO streetObjects, @PathVariable("streetplan_id") long streetPlanId){

        return new ResponseEntity<>(streeObjectService.createStreetObject(streetObjects, streetPlanId), HttpStatus.OK);
    }


    @Operation(summary = "Delete StreetObject by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "StreetObject has been deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStreetObject(@PathVariable("id") long id){
        streeObjectService.deleteStreetObjectById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
