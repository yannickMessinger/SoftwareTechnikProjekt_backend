package de.hsrm.mi.swt02.backend.websocket.api.crossroad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swt02.backend.api.game.crossroad.service.CrossroadService;
import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;

@RestController
@RequestMapping("/api/crossroad")
public class CrossroadController {

    @Autowired
    private CrossroadService crService;

    @PostMapping("/addCrossroad")
    public ResponseEntity<String> addCrossroad(int tlAmount /**als Requeest Param oder ein DTO anlegen(?) mitgeben */ ){
        Crossroad cr = crService.createCrossroad();
        crService.createTrafficLights(tlAmount, cr.getId());
        return new ResponseEntity<>("Crossroad was added",HttpStatus.OK);
    }
    
}
