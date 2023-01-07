package de.hsrm.mi.swt02.backend.websocket.model.npc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcMessage {
   

    private MessageType type;

    public enum MessageType {
        POSITION_UPDATE
    }

}
