package de.hsrm.mi.swt02.backend.domain.npc;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class NpcNavInfoTest {
    @Mock
    MapObject nextUpperMapObject;
    @Mock
    MapObject nextnextUpperMapObject;
    @InjectMocks
    NpcNavInfo npcNavInfo;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetNpcId () {
        npcNavInfo.setNpcId(0L);
    }

    @Test
    void testSetNewGameAssetRotation () {
        npcNavInfo.setNewGameAssetRotation(0);
    }

    @Test
    void testSetNextUpperMapObject () {
        npcNavInfo.setNextUpperMapObject(new MapObject(0L, 0, 0, 0));
    }

    @Test
    void testSetNextnextUpperMapObject () {
        npcNavInfo.setNextnextUpperMapObject(new MapObject(0L, 0, 0, 0));
    }
}

