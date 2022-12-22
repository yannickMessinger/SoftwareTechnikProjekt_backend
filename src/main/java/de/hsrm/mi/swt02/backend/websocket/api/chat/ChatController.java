package de.hsrm.mi.swt02.backend.websocket.api.chat;

import de.hsrm.mi.swt02.backend.websocket.model.chat.ChatMessage;
import de.hsrm.mi.swt02.backend.websocket.model.chat.ChatMessage.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chat")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        log.info("send message received");
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/chat")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getAuthor());
        return chatMessage;
    }

    @MessageMapping("/chat.lobbyChat")
    @SendTo("/topic/chat")
    public ChatMessage lobbyChat(@Payload ChatMessage chatMessage) {
        log.info("lobby specific message received");
        chatMessage.setType(MessageType.LOBBYMSG);
        return chatMessage;
    }

    
}
