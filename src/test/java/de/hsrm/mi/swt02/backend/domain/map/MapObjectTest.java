package de.hsrm.mi.swt02.backend.domain.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class MapObjectTest {
    @Mock
    Map map;
    @Mock
    List<GameAsset> gameAssets;
    @InjectMocks
    MapObject mapObject;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId () {
        mapObject.setId(0L);
    }

    @Test
    void testSetVersion () {
        mapObject.setVersion(0L);
    }

    @Test
    void testSetObjectTypeId () {
        mapObject.setObjectTypeId(0L);
    }

    @Test
    void testSetX () {
        mapObject.setX(0);
    }

    @Test
    void testSetY () {
        mapObject.setY(0);
    }

    @Test
    void testSetCenterX3d () {
        mapObject.setCenterX3d(0);
    }

    @Test
    void testSetCenterZ3d () {
        mapObject.setCenterZ3d(0);
    }

    @Test
    void testSetRotation () {
        mapObject.setRotation(0);
    }

    @Test
    void testSetMap () {
        mapObject.setMap(new Map("mapName", LocalDate.of(2023, Month.JANUARY, 28), 0, 0));
    }

    @Test
    void testSetGameAssets () {
        mapObject.setGameAssets(List.of(new GameAsset(0, 0d, 0d, 0, "texture", 0)));
    }
}

