package de.hsrm.mi.swt02.backend.websocket.api.lobby;

import de.hsrm.mi.swt02.backend.api.lobby.service.LobbyService;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.websocket.model.lobby.LobbyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class LobbyController {

    @Autowired
    LobbyService lobbyService;

    @Autowired
    PlayerService playerService;

    @MessageMapping("lobby.sendMessage")
    @SendTo("/topic/public")
    public LobbyMessage sendMessage(@Payload LobbyMessage lobbyMessage) {
        return lobbyMessage;
    }

    @MessageMapping("/lobby.join")
    @SendTo("/topic/lobby")
    public LobbyMessage join(LobbyMessage lobbyMessage) {
        log.info("create message received");
        lobbyService.addPlayerToLobby(lobbyMessage.lobbyContent.lobbyId(), lobbyMessage.playerContent.userId());
        return lobbyMessage;
    }

    @MessageMapping("/lobby.leave")
    @SendTo("/topic/lobby")
    public LobbyMessage leaveMessage(LobbyMessage lobbyMessage) {
        return lobbyMessage;
    }

    @MessageMapping("/lobby.close")
    @SendTo("/topic/lobby")
    public LobbyMessage closeMessage(LobbyMessage lobbyMessage) {
        return lobbyMessage;
    }

    @MessageMapping("/lobby.create")
    @SendTo("/topic/lobby")
    public LobbyMessage crateMessage(LobbyMessage lobbyMessage) {
        return lobbyMessage;
    }

    @MessageMapping("/lobby.switchMode")
    @SendTo("/topic/lobby")
    public LobbyMessage switchMode(LobbyMessage lobbyMessage) {
        // TODO: Validierung ob User host ist und überhaupt berechtigung hat um mode zu ändern!
        lobbyService.updateLobbyModeBroker(lobbyMessage.lobbyContent.lobbyId(), lobbyMessage.lobbyContent.lobbyModeEnum());
        return lobbyMessage;
    }

    @MessageMapping("/lobby.switchMap")
    @SendTo("/topic/lobby")
    public LobbyMessage switchMap(LobbyMessage lobbyMessage) {
        log.debug("switch map message recieved");
        return lobbyMessage;
    }
}
