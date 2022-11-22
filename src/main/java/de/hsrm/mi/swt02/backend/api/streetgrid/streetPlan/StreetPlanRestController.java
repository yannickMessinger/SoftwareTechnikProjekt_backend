package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.GetStreetPlanResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/streetplan")
public class StreetPlanRestController {

    @Autowired
    StreetPlanService streetPlanService;

    @Operation(summary = "Posting a new StreetPlan to the DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "StreetPlan was created"),
            @ApiResponse(responseCode = "400", description = "User JSON wrong syntax")
    })
    @PostMapping("")
    public ResponseEntity<Long> postNewStreetPlan(
            @RequestBody AddStreetPlanRequestDTO addStreetPlanRequestDTO) {
        long id = streetPlanService.saveStreetPlan(addStreetPlanRequestDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(summary = "Get StreetPlan by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found StreetPlan")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetStreetPlanResponseDTO> getStreetPlanById(
            @Schema(description = "StreetPlan ID", defaultValue = "1")
            @PathVariable long id) {
        GetStreetPlanResponseDTO dto = GetStreetPlanResponseDTO.from(streetPlanService.getStreetPlanById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
