package de.hsrm.mi.swt02.backend.websocket.model.crossroad;

import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrossroadMessage {
    private MessageType type;
    private long id;
    private Map<String, Light> trafficLights = new HashMap<>();
    
    private enum MessageType {
        CREATE,
        DELETE
    }
}
