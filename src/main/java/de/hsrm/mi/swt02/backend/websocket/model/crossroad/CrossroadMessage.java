package de.hsrm.mi.swt02.backend.websocket.model.crossroad;

import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt02.backend.api.game.crossroad.dto.AddCrossroadRequestDTO;
import de.hsrm.mi.swt02.backend.api.game.crossroad.dto.GetCrossroadResponseDTO;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrossroadMessage {
    private MessageType type;
    private Long id;
    private Map<Long, Light> tl = new HashMap<>();
    
    public CrossroadMessage(long id, Map<Long, Light> tl, MessageType type){
        this.id = id;
        this.tl = tl;
        this. type = type;
    }

    public enum MessageType {
        CREATE,
        UPDATE,
        DELETE
    }
}
