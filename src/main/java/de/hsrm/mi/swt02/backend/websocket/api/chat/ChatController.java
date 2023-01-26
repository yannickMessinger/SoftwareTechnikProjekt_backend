package de.hsrm.mi.swt02.backend.websocket.api.chat;

import de.hsrm.mi.swt02.backend.websocket.model.chat.ChatMessage;
import de.hsrm.mi.swt02.backend.websocket.model.chat.ChatMessage.MessageType;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/chat.globalChat")
    @SendTo("/topic/chat")
    public ChatMessage sendMessageGlobalChat(@Payload ChatMessage chatMessage) {
        log.info("send message received");
        return chatMessage;
    }

    @MessageMapping("/chat.lobbyChat/{lobbyId}")
    @SendTo("/topic/chat/lobby/{lobbyId}")
    public ChatMessage sendMessageLobbyChat(@DestinationVariable("lobbyId") String lobbyId ,@Payload ChatMessage lobbyChatMessage) {
        log.info("send lobby-message received");
        return lobbyChatMessage;
    }
}
