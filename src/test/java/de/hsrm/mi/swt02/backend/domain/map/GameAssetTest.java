package de.hsrm.mi.swt02.backend.domain.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GameAssetTest {
    @Mock
    MapObject mapObject;
    @InjectMocks
    GameAsset gameAsset;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEquals () {
        boolean result = gameAsset.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString () {
        String result = gameAsset.toString();
        Assertions.assertEquals("GameAsset{id=0, version=0, objectTypeId=0, x=0.0, y=0.0, rotation=0, texture='null'}", result);
    }

    @Test
    void testSetId () {
        gameAsset.setId(0L);
    }

    @Test
    void testSetVersion () {
        gameAsset.setVersion(0L);
    }

    @Test
    void testSetObjectTypeId () {
        gameAsset.setObjectTypeId(0);
    }

    @Test
    void testSetX () {
        gameAsset.setX(0d);
    }

    @Test
    void testSetY () {
        gameAsset.setY(0d);
    }

    @Test
    void testSetRotation () {
        gameAsset.setRotation(0);
    }

    @Test
    void testSetTexture () {
        gameAsset.setTexture("texture");
    }

    @Test
    void testSetMapObject () {
        gameAsset.setMapObject(new MapObject(0L, 0, 0, 0));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme