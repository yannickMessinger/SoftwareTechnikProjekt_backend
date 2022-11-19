package de.hsrm.mi.swt02.backend.api.streetgrid;

import de.hsrm.mi.swt02.backend.api.streetgrid.dtos.AddStreetGridRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
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

   @Autowired
   private StreetGridServiceImpl gridService;
    
    @PostMapping("")
    public  ResponseEntity<HttpStatus> postNewStreetGrid(@RequestBody AddStreetGridRequestDTO dto){
        //ma schauen was vom frontend ankimmt
       
        StreetGrid grid = new StreetGrid();
        grid.setGridData(dto.playerGrid());
        
        gridService.createStreetGrid("test", dto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
