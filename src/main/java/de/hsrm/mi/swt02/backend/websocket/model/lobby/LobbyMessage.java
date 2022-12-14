package de.hsrm.mi.swt02.backend.websocket.model.lobby;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LobbyMessage {
    public long lobbyId;
    public long playerId;
    private MessageType type;

    public enum MessageType {
        JOIN,
        SWITCH_MODE
    }
}
