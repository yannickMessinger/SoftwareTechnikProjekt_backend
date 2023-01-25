package de.hsrm.mi.swt02.backend.websocket.model.editor;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditorMessage {
    public AddMapObjectRequestDTO content;
    private long id;
    private MessageType type;
    private String author;

    public enum MessageType {
        CREATE,
        DELETE,
        UPDATE,
        RESET
    }
}
