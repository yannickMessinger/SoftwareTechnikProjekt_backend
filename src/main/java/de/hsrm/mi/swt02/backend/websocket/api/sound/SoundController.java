package de.hsrm.mi.swt02.backend.websocket.api.sound;

import de.hsrm.mi.swt02.backend.websocket.model.sound.SoundMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SoundController {

    /**
     * Broker method: forwards sound (horn sound) message.
     * @param soundMessage - includes posX and pox y to find out the distance for volume control.
     * @return - forwards soundMessage
     */
    @MessageMapping ("/sound.horn/{lobbyId}")
    @SendTo ("/topic/sound/{lobbyId}")
    public SoundMessage doHorn (SoundMessage soundMessage) {
        return soundMessage;
    }
}
