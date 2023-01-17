package de.hsrm.mi.swt02.backend.websocket.model.npc;


import de.hsrm.mi.swt02.backend.npc.NpcInfoDTO;
import de.hsrm.mi.swt02.backend.npc.NpcInfoResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcMessage {
   
    public NpcInfoDTO npcContent;
    public NpcInfoResponseDTO nextMapEleInfo;
    private MessageType type;

    public enum MessageType {
        POSITION_UPDATE, NEW_POSITION_RECEIVED
    }

}
