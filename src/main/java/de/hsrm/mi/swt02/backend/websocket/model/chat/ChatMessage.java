package de.hsrm.mi.swt02.backend.websocket.model.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public String content;
    private MessageType type;
    private String author;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
