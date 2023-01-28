package de.hsrm.mi.swt02.backend.websocket.model.chat;

import org.junit.jupiter.api.Test;

class ChatMessageTest {
    //Field type of type MessageType - was not mocked since Mockito doesn't mock enums
    ChatMessage chatMessage = new ChatMessage();

    @Test
    void testSetContent () {
        chatMessage.setContent("content");
    }

    @Test
    void testSetAuthor () {
        chatMessage.setAuthor("author");
    }

    @Test
    void testSetLobbyId () {
        chatMessage.setLobbyId(0L);
    }
}

