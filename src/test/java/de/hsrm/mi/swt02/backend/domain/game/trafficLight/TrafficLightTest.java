package de.hsrm.mi.swt02.backend.domain.game.trafficLight;

import org.junit.jupiter.api.Test;

class TrafficLightTest {
    TrafficLight trafficLight = new TrafficLight();

    @Test
    void testSetId () {
        trafficLight.setId("id");
    }

    @Test
    void testSetCurrentState () {
        trafficLight.setCurrentState(Light.GREEN);
    }
}

