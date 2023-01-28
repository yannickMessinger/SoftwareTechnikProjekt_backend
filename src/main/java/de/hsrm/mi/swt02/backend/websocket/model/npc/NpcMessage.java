package de.hsrm.mi.swt02.backend.websocket.model.npc;


import de.hsrm.mi.swt02.backend.api.npc.dto.NpcNavInfoRequestDTO;
import de.hsrm.mi.swt02.backend.api.npc.dto.NpcNavInfoResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcMessage {


    public NpcNavInfoRequestDTO npcInfoRequestDTO;
    public NpcNavInfoResponseDTO npcInfoResponseDTO;
    public INpcPositionDTO npcPositionContent;
    private MessageType type;

    public enum MessageType {
        POSITION_UPDATE, NEW_POSITION_RECEIVED, SET_CLIENT_POS
    }

}
