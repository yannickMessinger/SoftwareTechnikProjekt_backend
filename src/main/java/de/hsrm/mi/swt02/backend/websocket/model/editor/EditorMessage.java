package de.hsrm.mi.swt02.backend.websocket.model.editor;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditorMessage {
    private long id;
    private MessageType type;
    public MapObject content;
    private String author;

    public enum MessageType {
        CREATE,
        DELETE,
        UPDATE
    }
}
