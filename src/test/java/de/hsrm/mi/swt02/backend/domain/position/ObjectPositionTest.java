package de.hsrm.mi.swt02.backend.domain.position;

import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ObjectPositionTest {
    //Field rotation of type double[] - was not mocked since Mockito doesn't mock arrays
    @Mock
    Player player;
    @InjectMocks
    ObjectPosition objectPosition;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId () {
        objectPosition.setId(0L);
    }

    @Test
    void testSetVersion () {
        objectPosition.setVersion(0L);
    }

    @Test
    void testSetMapObjectId () {
        objectPosition.setMapObjectId(0L);
    }

    @Test
    void testSetPosX () {
        objectPosition.setPosX(0d);
    }

    @Test
    void testSetPosY () {
        objectPosition.setPosY(0d);
    }

    @Test
    void testSetRotation () {
        objectPosition.setRotation(new double[]{0d});
    }

    @Test
    void testSetPlayer () {
        objectPosition.setPlayer(new Player("userName", "password"));
    }
}

