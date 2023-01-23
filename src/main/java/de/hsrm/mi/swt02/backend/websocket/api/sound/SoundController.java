package de.hsrm.mi.swt02.backend.websocket.api.sound;

import de.hsrm.mi.swt02.backend.websocket.model.sound.SoundMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SoundController {

    @MessageMapping("/sound.horn")
    @SendTo("/topic/sound")
   public SoundMessage doHorn(SoundMessage soundMessage) {
        return soundMessage;
    }

    @MessageMapping("/sound.engine")
    @SendTo("/topic/sound")
    public SoundMessage doEngine(SoundMessage soundMessage) {
        return soundMessage;
    }
}
