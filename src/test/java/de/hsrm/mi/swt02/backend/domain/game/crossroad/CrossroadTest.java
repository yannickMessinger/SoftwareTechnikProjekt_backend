package de.hsrm.mi.swt02.backend.domain.game.crossroad;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class CrossroadTest {
    @Mock
    List<TrafficLight> trafficLights;
    @InjectMocks
    Crossroad crossroad;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId () {
        crossroad.setId("id");
    }

    @Test
    void testSetTrafficLights () {
        crossroad.setTrafficLights(List.of(new TrafficLight()));
    }

    @Test
    void testSetTlArrangement () {
        crossroad.setTlArrangement(true);
    }
}

