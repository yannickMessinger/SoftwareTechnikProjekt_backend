package de.hsrm.mi.swt02.backend.websocket.model.position;

import de.hsrm.mi.swt02.backend.api.game.position.dto.AddObjectPositionDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionMessage {
    public AddObjectPositionDTO content;
    private long id;
    private MessageType type;
    private String author;


    public enum MessageType {
        CREATE,
        DELETE,
        UPDATE

    }
}