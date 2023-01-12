package de.hsrm.mi.swt02.backend.websocket.model.npc;


import de.hsrm.mi.swt02.backend.npc.NpcInfoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcMessage {
   
    public NpcInfoDTO npcContent;
    private MessageType type;

    public enum MessageType {
        POSITION_UPDATE
    }

}
