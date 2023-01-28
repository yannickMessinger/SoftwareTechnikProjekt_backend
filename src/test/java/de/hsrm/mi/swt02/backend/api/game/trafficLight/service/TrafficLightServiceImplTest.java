package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrafficLightServiceImplTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link TrafficLightServiceImpl#TrafficLightServiceImpl()}
     *   <li>{@link TrafficLightServiceImpl#setTrafficLight(TrafficLight)}
     * </ul>
     */
    @Test
    void testConstructor () {
        TrafficLightServiceImpl actualTrafficLightServiceImpl = new TrafficLightServiceImpl();
        actualTrafficLightServiceImpl.setTrafficLight(new TrafficLight());
        assertEquals(Light.GREEN, actualTrafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState()}
     */
    @Test
    void testChangeCurrentState2 () {
        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(new TrafficLight());
        trafficLightServiceImpl.changeCurrentState();
        assertEquals(Light.YELLOW, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState()}
     */
    @Test
    void testChangeCurrentState3 () {

        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setCurrentState(Light.YELLOW);

        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(trafficLight);
        trafficLightServiceImpl.changeCurrentState();
        assertEquals(Light.RED, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState()}
     */
    @Test
    void testChangeCurrentState4 () {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setCurrentState(Light.RED);

        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(trafficLight);
        trafficLightServiceImpl.changeCurrentState();
        assertEquals(Light.REDYELLOW, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState()}
     */
    @Test
    void testChangeCurrentState5 () {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setCurrentState(Light.REDYELLOW);

        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(trafficLight);
        trafficLightServiceImpl.changeCurrentState();
        assertEquals(Light.GREEN, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState(Light)}
     */
    @Test
    void testChangeCurrentState7 () {
        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl(new TrafficLight());
        trafficLightServiceImpl.changeCurrentState(Light.GREEN);
        assertEquals(Light.GREEN, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState(Light)}
     */
    @Test
    void testChangeCurrentState8 () {
        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl(new TrafficLight());
        trafficLightServiceImpl.changeCurrentState(Light.YELLOW);
        assertEquals(Light.YELLOW, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState(Light)}
     */
    @Test
    void testChangeCurrentState9 () {
        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl(new TrafficLight());
        trafficLightServiceImpl.changeCurrentState(Light.RED);
        assertEquals(Light.RED, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#changeCurrentState(Light)}
     */
    @Test
    void testChangeCurrentState10 () {
        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl(new TrafficLight());
        trafficLightServiceImpl.changeCurrentState(Light.REDYELLOW);
        assertEquals(Light.REDYELLOW, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#getCurrentState()}
     */
    @Test
    void testGetCurrentState2 () {
        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(new TrafficLight());
        assertEquals(Light.GREEN, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#getCurrentState()}
     */
    @Test
    void testGetCurrentState3 () {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setCurrentState(Light.YELLOW);

        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(trafficLight);
        assertEquals(Light.YELLOW, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#getCurrentState()}
     */
    @Test
    void testGetCurrentState4 () {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setCurrentState(Light.RED);

        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(trafficLight);
        assertEquals(Light.RED, trafficLightServiceImpl.getCurrentState());
    }

    /**
     * Method under test: {@link TrafficLightServiceImpl#getCurrentState()}
     */
    @Test
    void testGetCurrentState5 () {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setCurrentState(Light.REDYELLOW);

        TrafficLightServiceImpl trafficLightServiceImpl = new TrafficLightServiceImpl();
        trafficLightServiceImpl.setTrafficLight(trafficLight);
        assertEquals(Light.REDYELLOW, trafficLightServiceImpl.getCurrentState());
    }
}

