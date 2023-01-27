package de.hsrm.mi.swt02.backend.api.game.crossroad.dto;

import java.util.List;

import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

public record GetCrossroadResponseDTO(long crId, List<TrafficLight> tl){
    

    public GetCrossroadResponseDTO(long crId, List<TrafficLight> tl){
        this.crId = crId;
        this.tl = tl;
    }
    
    public static GetCrossroadResponseDTO from (Crossroad cr) {
        return new GetCrossroadResponseDTO(
            cr.getId(),
            cr.getTrafficLights()
        );
    }
}