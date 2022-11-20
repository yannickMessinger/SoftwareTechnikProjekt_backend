package de.hsrm.mi.swt02.backend.api.streetgrid;

import de.hsrm.mi.swt02.backend.api.streetgrid.dtos.AddStreetGridRequestDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.hsrm.mi.swt02.backend.api.streetgrid.gridelements.StreetGrid;

@RestController
@RequestMapping("api/streetgrid")
public class StreetgridRestController {

   @Autowired
   private StreetGridServiceImpl gridService;
   private Logger logger = LoggerFactory.getLogger(StreetgridRestController.class);
   private String dto;
    @PostMapping("")
    public  ResponseEntity<HttpStatus> postNewStreetGrid(@RequestBody String dto){
        //ma schauen was vom frontend ankimmt
        logger.info(dto);
        gridService.saveStreetGrid(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String getStreetGrid(@PathVariable long id) {
        return gridService.getStreetGridById(id).getGridData();
    }
}
