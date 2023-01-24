package de.hsrm.mi.swt02.backend.websocket.model.npc;


import de.hsrm.mi.swt02.backend.npc.NpcInfoRequestDTO;
import de.hsrm.mi.swt02.backend.npc.NpcInfoResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcMessage {
   
    
    public NpcInfoRequestDTO npcInfoRequestDTO;
    public NpcInfoResponseDTO npcInfoResponseDTO;
    private MessageType type;

    public enum MessageType {
        POSITION_UPDATE, NEW_POSITION_RECEIVED,INIT_NEXT_MAP_ELE
    }

}
