package de.hsrm.mi.swt02.backend.api.game.crossroad;

import de.hsrm.mi.swt02.backend.api.game.crossroad.dto.GetCrossroadResponseDTO;
import de.hsrm.mi.swt02.backend.api.game.crossroad.service.CrossroadServiceImpl;
import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping ("/api/crossroad")
public class CrossroadRestController {

    @Autowired
    private CrossroadServiceImpl crs;

    @GetMapping ("/get/{id}")
    public ResponseEntity<GetCrossroadResponseDTO> getCrossroad (@PathVariable ("id") Long id) {
        // Funktioniert nicht. content wird nicht richtig übermittlet. Nach dem 3. Mal
        // laden hängt sich das FE auf
        try {
            Crossroad cr = crs.getCrossroad(id);
            /* GetCrossroadResponseDTO content = GetCrossroadResponseDTO.from(cr);
            log.info(content.toString()); */
            return new ResponseEntity<>(
                    GetCrossroadResponseDTO.from(cr),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/add")
    public ResponseEntity<Long> addCrossroad (@RequestParam int tlAmount) {
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

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteCrossroad (@PathVariable ("id") Long id) {
        try {
            crs.deleteCrossroad(id);
            return new ResponseEntity<>("Crossroad deleted with id: " + id,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Crossroad with the id " + id + "is null",HttpStatus.BAD_REQUEST);
        }
    }
}
