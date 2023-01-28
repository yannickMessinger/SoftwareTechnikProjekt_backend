package de.hsrm.mi.swt02.backend.websocket.model.position;

import de.hsrm.mi.swt02.backend.api.game.position.dto.AddObjectPositionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PositionMessageTest {
    @Mock
    AddObjectPositionDTO content;
    //Field type of type MessageType - was not mocked since Mockito doesn't mock enums
    @InjectMocks
    PositionMessage positionMessage;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }
}

