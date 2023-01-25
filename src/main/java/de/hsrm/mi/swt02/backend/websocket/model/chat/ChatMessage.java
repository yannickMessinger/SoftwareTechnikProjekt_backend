package de.hsrm.mi.swt02.backend.websocket.model.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private MessageType type;
    public String content;
    private String author;
    private long lobbyId;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        LOBBYMSG
    }
}
