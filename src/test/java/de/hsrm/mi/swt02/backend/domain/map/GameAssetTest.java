package de.hsrm.mi.swt02.backend.domain.map;

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
    void testSetX3d () {
        gameAsset.setX3d(0d);
    }

    @Test
    void testSetZ3d () {
        gameAsset.setZ3d(0d);
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

    @Test
    void testSetUserId () {
        gameAsset.setUserId(0);
    }
}

