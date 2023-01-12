package de.hsrm.mi.swt02.backend.websocket.api.npc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.npc.NpcInfo;
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

        log.info("update Position message received");
        log.info(String.valueOf(npcMessage.npcContent.npcId()));

        //mapid noch hardcoded
        NpcInfo info = mapService.initNpc(3, npcMessage.npcContent.npcId(), npcMessage.npcContent.npcPosX(), npcMessage.npcContent.npcPosZ(), npcMessage.npcContent.npcRotation());
        
        return npcMessage;
    }
}
