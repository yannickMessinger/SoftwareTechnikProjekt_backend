package de.hsrm.mi.swt02.backend.websocket.api.npc;

import de.hsrm.mi.swt02.backend.npc.NpcInfoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.npc.NpcInfo;
import de.hsrm.mi.swt02.backend.npc.NpcInfoResponseDTO;
import de.hsrm.mi.swt02.backend.websocket.model.npc.NpcMessage;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NpcController {

    @Autowired
    private MapService mapService;

    
    @MessageMapping("/npc.updatepos")
    @SendTo("/topic/npc")
    public NpcMessage updatePos(NpcMessage npcMessage) {
        NpcMessage npcMessageResponse = new NpcMessage();
        log.info("update Position NpcID -> " + String.valueOf(npcMessage.npcInfoRequestDTO.npcId()));
        log.info("mapId -> " + String.valueOf(npcMessage.npcInfoRequestDTO.mapId()));

        
        NpcInfo info = mapService.initNpc(npcMessage.npcInfoRequestDTO.mapId(), npcMessage.npcInfoRequestDTO.npcId(), npcMessage.npcInfoRequestDTO.currentMapObject().getX(), npcMessage.npcInfoRequestDTO.currentMapObject().getY(), npcMessage.npcInfoRequestDTO.npcRotation());
        NpcInfoResponseDTO infoDTO = NpcInfoResponseDTO.from(info);
        npcMessageResponse.setNpcInfoResponseDTO(infoDTO);
        npcMessageResponse.setType(NpcMessage.MessageType.NEW_POSITION_RECEIVED);
       
        return npcMessageResponse;
    }

}
