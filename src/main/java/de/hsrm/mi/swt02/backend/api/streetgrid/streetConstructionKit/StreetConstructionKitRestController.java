package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.GetStreetConstructionKitResponseDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.AddStreetConstructionKitRequestDTO;

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

    @GetMapping("")
    public ResponseEntity<List<GetStreetConstructionKitResponseDTO>> getAllStreetConstructionKits() {
        List<GetStreetConstructionKitResponseDTO> streetConstructionKitDTOs = new ArrayList<GetStreetConstructionKitResponseDTO>(
            streetService.findAllStreetConstructionKits()
                        .stream()
                       .map(GetStreetConstructionKitResponseDTO::from)
                       .toList());
            return new ResponseEntity <>(streetConstructionKitDTOs, HttpStatus.OK);
     }


     @GetMapping("/{id}")
     public ResponseEntity<GetStreetConstructionKitResponseDTO> getSingleStreetConstructionKit(@PathVariable("id") long id){
        GetStreetConstructionKitResponseDTO constructionKit = GetStreetConstructionKitResponseDTO.from(streetService.getStreetConstructionKitById(id));
        return new ResponseEntity<>(constructionKit, HttpStatus.OK);
    }



    @PostMapping("")
    public ResponseEntity<Long> postNewLobby(@RequestBody AddStreetConstructionKitRequestDTO kit){

        return new ResponseEntity<>(streetService.createStreetConstructionKit(), HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLobby(@PathVariable("id") long id){
        streetService.deleteStreetConstructionKitById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
        
        
        
        
        
        
       

    



}
