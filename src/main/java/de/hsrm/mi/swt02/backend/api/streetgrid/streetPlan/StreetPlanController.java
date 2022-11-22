package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.GetStreetPlanResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/streetplan")
public class StreetPlanController {

    @Autowired
    StreetPlanService streetPlanService;

    @PostMapping("")
    public ResponseEntity<Long> postNewStreetPlan(@RequestBody AddStreetPlanRequestDTO addStreetPlanRequestDTO) {
        long id = streetPlanService.saveStreetPlan(addStreetPlanRequestDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetStreetPlanResponseDTO> getStreetPlanById(@PathVariable long id) {
        GetStreetPlanResponseDTO dto = GetStreetPlanResponseDTO.from(streetPlanService.getStreetPlanById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
