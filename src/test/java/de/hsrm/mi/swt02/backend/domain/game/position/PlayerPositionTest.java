package de.hsrm.mi.swt02.backend.domain.game.position;

import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PlayerPositionTest {
    @Mock
    Player player;
    @InjectMocks
    PlayerPosition playerPosition;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToString () {
        String result = playerPosition.toString();
        Assertions.assertEquals("PlayerPosition{id=0, version=0, posX=0.0, posY=0.0, rotation=0.0, player=player}", result);
    }

    @Test
    void testSetId () {
        playerPosition.setId(0L);
    }

    @Test
    void testSetVersion () {
        playerPosition.setVersion(0L);
    }

    @Test
    void testSetPosX () {
        playerPosition.setPosX(0d);
    }

    @Test
    void testSetPosY () {
        playerPosition.setPosY(0d);
    }

    @Test
    void testSetRotation () {
        playerPosition.setRotation(0d);
    }

    @Test
    void testSetPlayer () {
        playerPosition.setPlayer(new Player("userName", "password"));
    }
}