package de.hsrm.mi.swt02.backend.api.game.crossroad;

import de.hsrm.mi.swt02.backend.api.game.crossroad.dto.GetCrossroadResponseDTO;
import de.hsrm.mi.swt02.backend.api.game.crossroad.service.CrossroadServiceImpl;
import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is a REST Controller class that handles the CRUD operations for crossroads. 
 * The class maps the URLs with the specified HTTP methods to perform the operations.
 * It utilizes the service implementation for crossroads to perform the operations.
 */
@Slf4j
@RestController
@RequestMapping("/api/crossroad")
public class CrossroadRestController {

    @Autowired
    private CrossroadServiceImpl crs;

    /**
     * This method maps the "/api/crossroad/{id}" URL with the GET method. 
     * It retrieves a {@link Crossroad} with the specified id.
     * @param id ID of the crossroad to retrieve
     * @return ResponseEntity with the GetCrossroadResponseDTO and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetCrossroadResponseDTO> getCrossroad(@PathVariable("id") Long id) {
        try {
            Crossroad cr = crs.getCrossroad(id);
            return new ResponseEntity<>(
                    GetCrossroadResponseDTO.from(cr),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method maps the "/api/crossroad" URL with the POST method.
     * It creates a new crossroad and starts it.
     * @param tlAmount Number of {@link} TrafficLight} to be created for the {@link Crossroad}
     * @return ResponseEntity with the created {@link Crossroad} ID and HTTP status code
     */
    @PostMapping("")
    public ResponseEntity<Long> addCrossroad(@RequestParam int tlAmount) {
        try {
            Long crId = crs.createCrossroad().getId();
            crs.createTrafficLights(tlAmount, crId);
            crs.start(crId);
            log.info("Add & start crossroad with the id: " + crId);
            return new ResponseEntity<>(
                    crId,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * This method maps the "/api/crossroad/{id}" URL with the DELETE method. 
     * It deletes the {@link Crossroad} with the specified id.
     * @param id ID of the {@link Crossroad} to delete
     * @return ResponseEntity with the deletion message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCrossroad(@PathVariable("id") Long id) {
        try {
            crs.deleteCrossroad(id);
            return new ResponseEntity<>("Crossroad deleted with id: " + id, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Crossroad with the id " + id + "is null", HttpStatus.BAD_REQUEST);
        }
    }
}
