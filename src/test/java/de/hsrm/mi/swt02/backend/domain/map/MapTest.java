package de.hsrm.mi.swt02.backend.domain.map;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class MapTest {
    //Field creationDate of type LocalDate - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    List<MapObject> mapObjects;
    @Mock
    Player mapOwner;
    @Mock
    Lobby lobby;
    @InjectMocks
    Map map;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId () {
        map.setId(0L);
    }

    @Test
    void testSetVersion () {
        map.setVersion(0L);
    }

    @Test
    void testSetMapName () {
        map.setMapName("mapName");
    }

    @Test
    void testSetCreationDate () {
        map.setCreationDate(LocalDate.of(2023, Month.JANUARY, 25));
    }

    @Test
    void testSetMapObjects () {
        map.setMapObjects(List.of(new MapObject(0L, 0, 0, 0)));
    }

    @Test
    void testSetMapOwner () {
        map.setMapOwner(new Player("userName", "password"));
    }

    @Test
    void testSetSizeX () {
        map.setSizeX(0);
    }

    @Test
    void testSetSizeY () {
        map.setSizeY(0);
    }

    @Test
    void testSetLobby () {
        map.setLobby(new Lobby("lobbyName", 0, LobbyModeEnum.BUILD_MODE));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme