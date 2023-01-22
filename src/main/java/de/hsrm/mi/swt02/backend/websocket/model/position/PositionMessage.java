package de.hsrm.mi.swt02.backend.websocket.model.position;

import de.hsrm.mi.swt02.backend.api.game.position.dto.AddObjectPositionDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionMessage{
    private long id;
    private MessageType type;
    private String author;
    public AddObjectPositionDTO content;


    public enum MessageType {
        CREATE,
        DELETE,
        UPDATE
    
    }
}