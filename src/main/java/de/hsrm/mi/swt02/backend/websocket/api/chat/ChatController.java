package de.hsrm.mi.swt02.backend.websocket.api.chat;

import de.hsrm.mi.swt02.backend.websocket.model.chat.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {


    /**
     * Broker method: forwards chatMessage for global chat.
     * @param chatMessage - get chatMessage
     * @return forwards chatMessage
     */
    @MessageMapping ("/chat.globalChat")
    @SendTo ("/topic/chat")
    public ChatMessage sendMessageGlobalChat (@Payload ChatMessage chatMessage) {
        log.info("send message received");
        return chatMessage;
    }

    /**
     * Broker method: forwards chatMessage for lobby chat.
     * @param lobbyId - For each lobby there is a channel which is identified by the lobbyId
     * @param lobbyChatMessage - get lobbyChatMessage
     * @return  forwards lobbyChatMessage
     */
    @MessageMapping ("/chat.lobbyChat/{lobbyId}")
    @SendTo ("/topic/chat/lobby/{lobbyId}")
    public ChatMessage sendMessageLobbyChat (@DestinationVariable ("lobbyId") String lobbyId, @Payload ChatMessage lobbyChatMessage) {
        log.info("send lobby-message received");
        return lobbyChatMessage;
    }
}
