package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.AddMultipleStreetObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetObject.dtos.GetStreetObjectResponseDTO;

@RestController
@RequestMapping("api/streetobject")
public class StreetObjectRestController {
    

    @Autowired
    private StreetObjectServiceImpl streeObjectService;

    @GetMapping("")
    public ResponseEntity<List<GetStreetObjectResponseDTO>> getAllStreetObjects() {
        List<GetStreetObjectResponseDTO> allStreetObjectDTOs = new ArrayList<GetStreetObjectResponseDTO>(
            streeObjectService.findAllStreetObjects()
                        .stream()
                       .map(GetStreetObjectResponseDTO::from)
                       .toList());
            return new ResponseEntity <>(allStreetObjectDTOs, HttpStatus.OK);
     }


     @GetMapping("/{id}")
     public ResponseEntity<GetStreetObjectResponseDTO> getSingleStreetObject(@PathVariable("id") long id){
        GetStreetObjectResponseDTO streetObjectDTO = GetStreetObjectResponseDTO.from(streeObjectService.getStreetObjectById(id));
        return new ResponseEntity<>(streetObjectDTO, HttpStatus.OK);
    }



    @PostMapping("")
    public ResponseEntity<Long> postNewStreetObject(@RequestBody AddMultipleStreetObjectsRequestDTO streetObjects){

        return new ResponseEntity<>(streeObjectService.createStreetObject(streetObjects), HttpStatus.OK);

    }

    



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStreetObject(@PathVariable("id") long id){
        streeObjectService.deleteStreetObjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    



}
