package de.hsrm.mi.swt02.backend.websocket.api.npc;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import de.hsrm.mi.swt02.backend.websocket.model.npc.NpcMessage;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NpcController {
    
    @MessageMapping("/npc.updatepos")
    @SendTo("/topic/npc/updatepos")
    public NpcMessage updatePos(NpcMessage npcMessage) {

        log.info("update Position message received");
       
        return npcMessage;
    }
}
