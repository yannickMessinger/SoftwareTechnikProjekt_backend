package de.hsrm.mi.swt02.backend.websocket.api.lobby;

import de.hsrm.mi.swt02.backend.api.lobby.service.LobbyService;
import de.hsrm.mi.swt02.backend.websocket.model.lobby.LobbyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LobbyController {

    Logger logger = LoggerFactory.getLogger(LobbyController.class);

    @Autowired
    LobbyService lobbyService;

    @MessageMapping("lobby.sendMessage")
    @SendTo("/topic/public")
    public LobbyMessage sendMessage(@Payload LobbyMessage lobbyMessage) {
        return lobbyMessage;
    }

    @MessageMapping("/lobby.join")
    @SendTo("/topic/public")
    public LobbyMessage join(LobbyMessage lobbyMessage) {
        logger.info("create message received", lobbyMessage);
        lobbyService.addPlayerToLobby(lobbyMessage.lobbyId, lobbyMessage.playerId);
        return lobbyMessage;
    }

    @MessageMapping("/lobby.switchMode")
    @SendTo("/topic/public")
    public LobbyMessage switchMode(LobbyMessage lobbyMessage) {
        // handle mode switch here
        return lobbyMessage;
    }
}
