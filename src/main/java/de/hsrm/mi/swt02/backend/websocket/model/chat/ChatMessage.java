package de.hsrm.mi.swt02.backend.websocket.model.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public String content;
    private MessageType type;
    private String author;
    private long lobbyId;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        LOBBYMSG
    }
}
