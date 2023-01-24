package de.hsrm.mi.swt02.backend.websocket.api.position;

import de.hsrm.mi.swt02.backend.api.game.position.service.PositionService;
import de.hsrm.mi.swt02.backend.websocket.model.position.PositionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class PositionController{

    @Autowired
     PositionService positionService;

    @MessageMapping("/position.create") 
    @SendTo("/topic/position") 
    public PositionMessage create(PositionMessage positionMessage) {
        log.info("create message received");
        //positionService.createPosition( positionMessage.getId(),positionMessage.content.x(),positionMessage.content.z(),positionMessage.content.rotation());
        return positionMessage;
    }

    @MessageMapping("/position.delete")
    @SendTo("/topic/position")
    public PositionMessage delete(PositionMessage positionMessage) {

        log.info("delete message received");
        positionService.deletePosition(positionMessage.getId());
        //headerAccessor.getSessionAttributes().put("username", editorMessage.getAuthor());
        return positionMessage;
    }

    @MessageMapping("/position.update")
    @SendTo("/topic/position")
    public PositionMessage update(@Payload PositionMessage positionMessage) {

        log.info("update message received");
        //positionService.updatePosition(positionMessage.getId(),positionMessage.content.x(), positionMessage.content.z(), positionMessage.content.rotation()  );
        //headerAccessor.getSessionAttributes().put("username", editorMessage.getAuthor());
        return positionMessage;
    }

}