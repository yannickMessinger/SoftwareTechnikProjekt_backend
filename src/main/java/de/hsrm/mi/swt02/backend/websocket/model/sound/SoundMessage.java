package de.hsrm.mi.swt02.backend.websocket.model.sound;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoundMessage {
    private SoundMessageType type;
    private int posX;
    private int posY;

    public enum SoundMessageType {
        HORN,
        ENGIN
    }
}
