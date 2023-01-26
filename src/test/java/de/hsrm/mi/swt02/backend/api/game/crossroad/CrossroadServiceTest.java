package de.hsrm.mi.swt02.backend.api.game.crossroad;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.hsrm.mi.swt02.backend.api.game.crossroad.repository.CrossroadRepository;
import de.hsrm.mi.swt02.backend.api.game.crossroad.service.CrossroadService;
import de.hsrm.mi.swt02.backend.api.game.trafficLight.repository.TrafficLightRepository;
import de.hsrm.mi.swt02.backend.api.game.trafficLight.service.TrafficLightService;
import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

/**
    Test class for the {@link CrossroadService} class.
    This class tests the functionality of the {@link CrossroadService} class by creating instances of the class,
    creating and manipulating traffic lights, and asserting the expected behavior.
*/
@Testable
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrossroadServiceTest {

    @Autowired
    private CrossroadService crs;
    @Autowired
    private CrossroadRepository crRepo;
    @Autowired
    private TrafficLightRepository tlRepo;
    @Autowired
    private TrafficLightService tls;


    /**
     * Test method for the traffic light state.
     * 
     * This method creates a traffic light, starts the crossroad service, and asserts that the state of the
     * traffic light changes as expected.
     *
     * @throws InterruptedException
     */
    @Test
    public void TrafficLightState() throws InterruptedException {
        
        Crossroad cr = crs.createCrossroad();
        crs.createTrafficLights(1, cr.getId());

        cr = crRepo.findById(cr.getId()).get();
        List<TrafficLight> tlList = cr.getTrafficLights();
        TrafficLight tl = tlList.get(0);
        
        crs.start(cr.getId());
        TimeUnit.SECONDS.sleep(16);
        assertEquals(Light.YELLOW, tlRepo.findById(tl.getId()).get().getCurrentState());
        TimeUnit.SECONDS.sleep(3);
        assertEquals(Light.RED, tlRepo.findById(tl.getId()).get().getCurrentState());
        TimeUnit.SECONDS.sleep(15);
        assertEquals(Light.REDYELLOW, tlRepo.findById(tl.getId()).get().getCurrentState());
        TimeUnit.SECONDS.sleep(3);
        assertEquals(Light.GREEN, tlRepo.findById(tl.getId()).get().getCurrentState());
        crs.stop(cr.getId());
    }

    /**
     * This method tests if the thread is closed after calling the stop method.
     * @throws InterruptedException
    */
    @Test
    public void CloseThreadAfterStop() throws InterruptedException {
        Long crId = crs.createCrossroad().getId();
        crs.createTrafficLights(1, crId);
        crs.start(crId);
        crs.stop(crId);
        Thread t = crs.getThread(crId);
        assertTrue(t.isInterrupted());
    }

    /**
     * This method tests the case where the traffic light list is empty.
     * @throws InterruptedException
    */
    @Test
    public void EmptyTrafficLightList() throws InterruptedException {
        Long crId = crs.createCrossroad().getId();
        crs.start(crId);
        crs.stop(crId);
        Thread t = crs.getThread(crId);
        assertTrue(t.isInterrupted());
    }

    /**
     * This method tests the functionality of multiple traffic lights on one crossroad.
     * @throws InterruptedException
    */
    @Test
    public void MultipleTrafficLightState() throws InterruptedException {
        Long crId = crs.createCrossroad().getId();
        List<TrafficLight> tlList = crs.createTrafficLights(2, crId);
        Long tl1Id = tlList.get(0).getId();
        Long tl2Id = tlList.get(1).getId();

        crs.start(crId);
        assertEquals(Light.GREEN, tls.getCurrentState(tl1Id) );
        assertEquals(Light.RED, tls.getCurrentState(tl2Id));
        TimeUnit.SECONDS.sleep(16);
        assertEquals(Light.YELLOW, tls.getCurrentState(tl1Id) );
        assertEquals(Light.REDYELLOW, tls.getCurrentState(tl2Id));
        TimeUnit.SECONDS.sleep(3);
        assertEquals(Light.RED, tls.getCurrentState(tl1Id) );
        assertEquals(Light.GREEN, tls.getCurrentState(tl2Id));
        TimeUnit.SECONDS.sleep(15);
        assertEquals(Light.REDYELLOW, tls.getCurrentState(tl1Id) );
        assertEquals(Light.YELLOW, tls.getCurrentState(tl2Id));
        TimeUnit.SECONDS.sleep(3);
        assertEquals(Light.GREEN, tls.getCurrentState(tl1Id) );
        assertEquals(Light.RED, tls.getCurrentState(tl2Id));
        crs.stop(crId);
    }

    /**
     * This method tests the functionality of multiple crossroads.
     * @throws InterruptedException
    */
    @Test
    public void MultipleCrossroads() throws InterruptedException {
        Long crId1 = crs.createCrossroad().getId();
        Long crId2 = crs.createCrossroad().getId();
        Long crId3 = crs.createCrossroad().getId();
        crs.createTrafficLights(4, crId1);
        crs.createTrafficLights(4, crId2);
        crs.createTrafficLights(4, crId3);

        crs.start(crId1);
        crs.start(crId2);
        crs.start(crId3);
        
        crs.stop(crId1);
    
        TimeUnit.SECONDS.sleep(5);
        assertTrue(crs.getThread(crId1).isInterrupted());
        assertFalse(crs.getThread(crId2).isInterrupted());
        assertFalse(crs.getThread(crId3).isInterrupted());
    }
}