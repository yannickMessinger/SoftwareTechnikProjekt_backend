package de.hsrm.mi.swt02.backend.websocket.api.npc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


import de.hsrm.mi.swt02.backend.api.map.service.MapService;

import de.hsrm.mi.swt02.backend.api.npc.dto.NpcNavInfoResponseDTO;
import de.hsrm.mi.swt02.backend.domain.npc.NpcNavInfo;

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
        //log.info("update Position NpcID -> " + String.valueOf(npcMessage.npcInfoRequestDTO.npcId()));
        //log.info("mapId -> " + String.valueOf(npcMessage.npcInfoRequestDTO.mapId()));

        
        NpcNavInfo info = mapService.getNpcDirections(npcMessage.npcInfoRequestDTO.mapId(), npcMessage.npcInfoRequestDTO.npcId(), npcMessage.npcInfoRequestDTO.currentMapObject().getX(), npcMessage.npcInfoRequestDTO.currentMapObject().getY(), npcMessage.npcInfoRequestDTO.npcRotation());
        NpcNavInfoResponseDTO infoDTO = NpcNavInfoResponseDTO.from(info);
        npcMessageResponse.setNpcInfoResponseDTO(infoDTO);
        npcMessageResponse.setType(NpcMessage.MessageType.NEW_POSITION_RECEIVED);
       
        return npcMessageResponse;
    }



    @MessageMapping("/npc.setclientpos")
    @SendTo("/topic/npc/setclientpos")
    public NpcMessage setClientPos(NpcMessage npcMessage) {
        //NpcMessage npcMessageResponse = new NpcMessage();
        //log.info("__x:" + String.valueOf(npcMessage.npcPositionContent.npcPosX()));
        //log.info("__y:" + String.valueOf(npcMessage.npcPositionContent.npcPosZ()));
        //log.info("___NPC POS UPDATE");
        

        return npcMessage;
    }

}
