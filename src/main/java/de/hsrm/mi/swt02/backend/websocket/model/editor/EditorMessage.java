package de.hsrm.mi.swt02.backend.websocket.model.editor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditorMessage {
    private MessageType type;
    public String content;
    private String author;

    public enum MessageType {
        CREATE,
        DELETE,
        UPDATE
    }
}
