package de.hsrm.mi.swt02.backend.api.streetgrid;

import de.hsrm.mi.swt02.backend.api.streetgrid.dtos.AddStreetGridRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.streetgrid.gridelements.StreetGrid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/streetgrid")
public class StreetgridRestController {

    private StreetGrid grid;

    @PostMapping("")
    public  ResponseEntity<HttpStatus> postNewStreetGrid(@RequestBody Object dto){
        //ma schauen was vom frontend ankimmt
        this.grid = new StreetGrid();
        grid.parseStreetData(dto);
        
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
