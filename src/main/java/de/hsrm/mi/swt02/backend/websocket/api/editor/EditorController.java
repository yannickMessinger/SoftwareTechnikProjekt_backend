package de.hsrm.mi.swt02.backend.websocket.api.editor;

import de.hsrm.mi.swt02.backend.api.map.service.MapObjectService;
import de.hsrm.mi.swt02.backend.websocket.model.editor.EditorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class EditorController {

    Logger logger = LoggerFactory.getLogger(EditorController.class);

    @Autowired
    MapObjectService mapObjectService;

    @MessageMapping("/editor.sendMessage")
    @SendTo("/topic/public")
    public EditorMessage sendMessage(@Payload EditorMessage editorMessage) {
        return editorMessage;
    }

    @MessageMapping("/editor.create")
    @SendTo("/topic/public")
    public EditorMessage create(EditorMessage editorMessage) {
        logger.info("create message received");
        mapObjectService.addNewMapObjectFromBroker(editorMessage.content, editorMessage.getId());
        // headerAccessor.getSessionAttributes().put("username", editorMessage.getAuthor());
        return editorMessage;
    }

    @MessageMapping("/editor.delete")
    @SendTo("/topic/public")
    public EditorMessage delete(@Payload EditorMessage editorMessage,
                                      SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", editorMessage.getAuthor());
        return editorMessage;
    }

    @MessageMapping("/editor.update")
    @SendTo("/topic/public")
    public EditorMessage update(@Payload EditorMessage editorMessage,
                                      SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", editorMessage.getAuthor());
        return editorMessage;
    }
}
