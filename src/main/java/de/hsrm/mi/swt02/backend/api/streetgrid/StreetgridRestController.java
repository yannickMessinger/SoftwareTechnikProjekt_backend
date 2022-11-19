package de.hsrm.mi.swt02.backend.api.streetgrid;

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
    

    @PostMapping("")
    public  ResponseEntity<HttpStatus> postNewStreetGrid(@RequestBody StreetGrid streetGrid){
        //ma schauen was vom frontend ankimmt
        return new ResponseEntity<>(HttpStatus.OK);

    }




}
