package de.hsrm.mi.swt02.backend.websocket.model.crossroad;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

class CrossroadMessageTest {
    @Mock
    Map<String, Light> trafficLights;
    @InjectMocks
    CrossroadMessage crossroadMessage;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }
}

