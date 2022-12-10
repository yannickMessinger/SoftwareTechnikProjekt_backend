package de.hsrm.mi.swt02.backend.websocket.api.editor;

import de.hsrm.mi.swt02.backend.websocket.model.editor.EditorMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class EditorController {

    @MessageMapping("/editor.sendMessage")
    @SendTo("/topic/public")
    public EditorMessage sendMessage(@Payload EditorMessage editorMessage) {
        return editorMessage;
    }

    @MessageMapping("/editor.create")
    @SendTo("/topic/public")
    public EditorMessage create(@Payload EditorMessage editorMessage,
                                      SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", editorMessage.getAuthor());
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
