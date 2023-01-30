package de.hsrm.mi.swt02.backend.websocket.api.npc;

import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.npc.dto.NpcNavInfoResponseDTO;
import de.hsrm.mi.swt02.backend.domain.npc.NpcNavInfo;
import de.hsrm.mi.swt02.backend.websocket.model.npc.NpcMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
/**
 * Controller that listens to incoming messages from npc's.Method @updatePos triggers calculation from
 * npc NavigationSystem object and returns DTO with new infos, further described in the NpcVavigationClass itself.
 * 
 * Method @setClientPos contains method to distribute npc position infos to every client. 
 */
public class NpcController {

    @Autowired
    private MapService mapService;


    @MessageMapping ("/npc.updatepos")
    @SendTo ("/topic/npc")
    public NpcMessage updatePos(NpcMessage npcMessage) {
        NpcMessage npcMessageResponse = new NpcMessage();

        NpcNavInfo info = mapService.getNpcDirections(npcMessage.npcInfoRequestDTO.mapId(), npcMessage.npcInfoRequestDTO.npcId(), npcMessage.npcInfoRequestDTO.currentMapObject().getX(), npcMessage.npcInfoRequestDTO.currentMapObject().getY(), npcMessage.npcInfoRequestDTO.npcRotation());
        NpcNavInfoResponseDTO infoDTO = NpcNavInfoResponseDTO.from(info);
        npcMessageResponse.setNpcInfoResponseDTO(infoDTO);
        npcMessageResponse.setType(NpcMessage.MessageType.NEW_POSITION_RECEIVED);

        return npcMessageResponse;
    }


    @MessageMapping ("/npc.setclientpos")
    @SendTo ("/topic/npc/setclientpos")
    public NpcMessage setClientPos(NpcMessage npcMessage) {
        return npcMessage;
    }

}
