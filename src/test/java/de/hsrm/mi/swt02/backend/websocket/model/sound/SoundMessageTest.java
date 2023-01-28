package de.hsrm.mi.swt02.backend.websocket.model.sound;

import org.junit.jupiter.api.Test;

class SoundMessageTest {
    SoundMessage soundMessage = new SoundMessage();

    @Test
    void testSetPosX () {
        soundMessage.setPosX(0);
    }

    @Test
    void testSetPosY () {
        soundMessage.setPosY(0);
    }
}

