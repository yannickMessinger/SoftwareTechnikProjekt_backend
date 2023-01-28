package de.hsrm.mi.swt02.backend.domain.npc;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;

import java.util.List;

import static org.mockito.Mockito.when;

class NpcNavigationSystemTest {
    @Mock
    Logger logger;
    @Mock
    PythonInterpreter pyInterp;
    @Mock
    MapObject currentMapObject;
    @Mock
    MapObject nextUpperMapObj;
    @Mock
    MapObject nextnextMapObject;
    @Mock
    List<MapObject> list;
    @Mock
    NpcNavInfo info;
    @InjectMocks
    NpcNavigationSystem npcNavigationSystem;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetNpcNavigationParams () {
        when(currentMapObject.getObjectTypeId()).thenReturn(0L);
        when(currentMapObject.getX()).thenReturn(0);
        when(currentMapObject.getY()).thenReturn(0);
        when(currentMapObject.getRotation()).thenReturn(0);
        when(nextUpperMapObj.getObjectTypeId()).thenReturn(0L);
        when(nextUpperMapObj.getX()).thenReturn(0);
        when(nextUpperMapObj.getY()).thenReturn(0);
        when(nextUpperMapObj.getRotation()).thenReturn(0);
        when(nextnextMapObject.getObjectTypeId()).thenReturn(0L);
        when(nextnextMapObject.getX()).thenReturn(0);
        when(nextnextMapObject.getY()).thenReturn(0);
        when(nextnextMapObject.getRotation()).thenReturn(0);

        npcNavigationSystem.setNpcNavigationParams(List.of(new MapObject(0L, 0, 0, 0)), 0, 0, 0, 0L);
    }

    @Test
    void testSetNavigationScriptParams () {
        npcNavigationSystem.setNavigationScriptParams(0, 0, 0, 0, 0L);
    }

    @Test
    void testSetLogger () {
        npcNavigationSystem.setLogger(null);
    }

    @Test
    void testSetPyInterp () {
        npcNavigationSystem.setPyInterp(null);
    }

    @Test
    void testSetCurrentMapObject () {
        npcNavigationSystem.setCurrentMapObject(new MapObject(0L, 0, 0, 0));
    }

    @Test
    void testSetNextUpperMapObj () {
        npcNavigationSystem.setNextUpperMapObj(new MapObject(0L, 0, 0, 0));
    }

    @Test
    void testSetNextnextMapObject () {
        npcNavigationSystem.setNextnextMapObject(new MapObject(0L, 0, 0, 0));
    }

    @Test
    void testSetList () {
        npcNavigationSystem.setList(List.of(new MapObject(0L, 0, 0, 0)));
    }

    @Test
    void testSetNpcRot () {
        npcNavigationSystem.setNpcRot(0);
    }

    @Test
    void testSetInfo () {
        npcNavigationSystem.setInfo(new NpcNavInfo(0, new MapObject(0L, 0, 0, 0), new MapObject(0L, 0, 0, 0)));
    }

    @Test
    void testSetNpcId () {
        npcNavigationSystem.setNpcId(0L);
    }
}

