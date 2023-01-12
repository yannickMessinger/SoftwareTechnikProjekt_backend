package de.hsrm.mi.swt02.backend.websocket.api.editor;

import de.hsrm.mi.swt02.backend.api.map.service.MapObjectService;
import de.hsrm.mi.swt02.backend.websocket.model.editor.EditorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class EditorController {


    @Autowired
    MapObjectService mapObjectService;

    @MessageMapping("/editor.create")
    @SendTo("/topic/editor")
    public EditorMessage create(EditorMessage editorMessage) {
        log.info("create message received");
        mapObjectService.addNewMapObjectFromBroker(editorMessage.content, editorMessage.getId());
        return editorMessage;
    }

    @MessageMapping("/editor.delete")
    @SendTo("/topic/editor")
    public EditorMessage delete(EditorMessage editorMessage) {

        log.info("delete message received");
        mapObjectService.deleteMapObjectFromBroker(editorMessage.content, editorMessage.getId());
        return editorMessage;
    }

    @MessageMapping("/editor.update")
    @SendTo("/topic/editor")
    public EditorMessage update(EditorMessage editorMessage) {

        log.info("update message received");
        mapObjectService.updateMapObjectFromBroker(editorMessage.content, editorMessage.getId());
        return editorMessage;
    }

    @MessageMapping("/editor.reset")
    @SendTo("/topic/editor")
    public EditorMessage reset(EditorMessage editorMessage) {
        log.info("reset message received");
        mapObjectService.deleteAllMapObjectsFromMapById(editorMessage.getId());
        return editorMessage;
    }
}
