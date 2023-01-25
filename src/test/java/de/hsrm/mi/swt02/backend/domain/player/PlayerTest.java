package de.hsrm.mi.swt02.backend.domain.player;

import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import org.junit.jupiter.api.Assertions;
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
    PlayerPosition playerPosition;
    @Mock
    Lobby activeLobby;
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
    void testToString () {
        String result = player.toString();
        Assertions.assertEquals("User{id=0, version=0, userName='null'}", result);
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
    void testSetPlayerPosition () {
        player.setPlayerPosition(new PlayerPosition(new Player("userName", "password"), 0d, 0d, 0d));
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
        player.setMapList(List.of(new Map("mapName", LocalDate.of(2023, Month.JANUARY, 25), 0, 0)));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme