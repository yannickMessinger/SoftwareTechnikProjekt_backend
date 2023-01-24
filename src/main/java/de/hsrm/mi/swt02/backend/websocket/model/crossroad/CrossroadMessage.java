package de.hsrm.mi.swt02.backend.websocket.model.crossroad;

import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrossroadMessage {
    private long id;
    private Map<String, Light> trafficLights = new HashMap<>();

    public CrossroadMessage(long id, Map<String, Light> trafficLights){
        this.id = id;
        this.trafficLights = trafficLights;
    }
}
