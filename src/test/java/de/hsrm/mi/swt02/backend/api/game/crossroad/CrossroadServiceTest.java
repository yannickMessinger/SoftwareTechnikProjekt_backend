package de.hsrm.mi.swt02.backend.api.game.crossroad;


import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import de.hsrm.mi.swt02.backend.api.game.crossroad.service.CrossroadService;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testable
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrossroadServiceTest {

    @Autowired
    private CrossroadService crossroadService;

    @Test
    public void startTest() throws InterruptedException {
        crossroadService.createTrafficLights(4);
        crossroadService.start();
        TimeUnit.SECONDS.sleep(1);
        crossroadService.stop();
    }

    @Test
    public void testCurrentState() throws InterruptedException {
        TrafficLight tl = crossroadService.createTrafficLights(1).get(0);
        crossroadService.start();
        TimeUnit.SECONDS.sleep(8);
        assertEquals(Light.YELLOW, tl.getCurrentState());
        TimeUnit.SECONDS.sleep(2);
        assertEquals(Light.RED, tl.getCurrentState());
        TimeUnit.SECONDS.sleep(10);
        assertEquals(Light.REDYELLOW, tl.getCurrentState());
        TimeUnit.SECONDS.sleep(2);
        assertEquals(Light.GREEN, tl.getCurrentState());
        
        List<Thread> tList = crossroadService.stop();
        for(Thread t : tList ){
            assertTrue(t.isInterrupted());
            t.getState().compareTo(Thread.State.TERMINATED); //Besser als isInterrupteed()?
        }
    }
}
