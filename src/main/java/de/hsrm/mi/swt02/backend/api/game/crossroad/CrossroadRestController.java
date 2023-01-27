package de.hsrm.mi.swt02.backend.api.game.crossroad;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.game.crossroad.dto.GetCrossroadResponseDTO;
import de.hsrm.mi.swt02.backend.api.game.crossroad.service.CrossroadService;
import de.hsrm.mi.swt02.backend.api.game.crossroad.service.CrossroadServiceImpl;
import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/crossroad")
public class CrossroadRestController {

    @Autowired
    private CrossroadServiceImpl crs;

    @GetMapping("/get/{id}")
    public ResponseEntity<GetCrossroadResponseDTO> getCrossroad(@PathVariable("id") Long id) {
        // Funktioniert nicht. content wird nicht richtig übermittlet. Nach dem 3. Mal
        // laden hängt sich das FE auf
        try {
            Crossroad cr = crs.getCrossroad(id);
            /* GetCrossroadResponseDTO content = GetCrossroadResponseDTO.from(cr);
            log.info(content.toString()); */
            return new ResponseEntity<>(
                    new GetCrossroadResponseDTO(1, new ArrayList<TrafficLight>()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCrossroad(@PathVariable("id") Long id) {
        // TODO: Delete operation is missing in the service class
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
