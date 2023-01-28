package de.hsrm.mi.swt02.backend.domain.player;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class PlayerTest {
    @Mock
    ObjectPosition playerPosition;
    @Mock
    List<Lobby> hostedLobbys;
    @Mock
    List<Map> mapList;
    @InjectMocks
    Player player;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddHostToHostedLobbyList () {
        player.AddHostToHostedLobbyList(new Lobby("lobbyName", 0, LobbyModeEnum.BUILD_MODE));
    }

    @Test
    void testRemoveLobbyFromHostedLobbyList () {
        player.removeLobbyFromHostedLobbyList(new Lobby("lobbyName", 0, LobbyModeEnum.BUILD_MODE));
    }

    @Test
    void testSetPlayerPosition () {
        player.setPlayerPosition(new ObjectPosition(0L, 0d, 0d, new double[]{0d}));
    }

    @Test
    void testSetId () {
        player.setId(0L);
    }

    @Test
    void testSetVersion () {
        player.setVersion(0L);
    }

    @Test
    void testSetUserName () {
        player.setUserName("userName");
    }

    @Test
    void testSetPassword () {
        player.setPassword("password");
    }

    @Test
    void testSetActiveLobby () {
        player.setActiveLobby(new Lobby("lobbyName", 0, LobbyModeEnum.BUILD_MODE));
    }

    @Test
    void testSetHostedLobbys () {
        player.setHostedLobbys(List.of(new Lobby("lobbyName", 0, LobbyModeEnum.BUILD_MODE)));
    }

    @Test
    void testSetMapList () {
        player.setMapList(List.of(new Map("mapName", LocalDate.of(2023, Month.JANUARY, 28), 0, 0)));
    }
}

