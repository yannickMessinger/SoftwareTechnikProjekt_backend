package de.hsrm.mi.swt02.backend.domain.lobby;

import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.when;

class LobbyTest {
    @Mock
    List<Player> playerList;
    @Mock
    Player host;
    @Mock
    Map map;
    @InjectMocks
    Lobby lobby;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPlayerToPlayerlist () {
        lobby.addPlayerToPlayerlist(new Player("userName", "password"));
    }

    @Test
    void testRemovePlayerFromPlayerList () {
        lobby.removePlayerFromPlayerList(new Player("userName", "password"));
    }

    @Test
    void testGetNumOfPlayersInLobby () {
        int result = lobby.getNumOfPlayersInLobby();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetHostId () {
        when(host.getId()).thenReturn(0L);

        long result = lobby.getHostId();
        Assertions.assertEquals(0L, result);
    }

    @Test
    void testIsHostedBy () {
        when(host.getId()).thenReturn(0L);

        boolean result = lobby.isHostedBy(0L);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testSetId () {
        lobby.setId(0L);
    }

    @Test
    void testSetVersion () {
        lobby.setVersion(0L);
    }

    @Test
    void testSetLobbyName () {
        lobby.setLobbyName("lobbyName");
    }

    @Test
    void testSetNumOfPlayers () {
        lobby.setNumOfPlayers(0);
    }

    @Test
    void testSetLobbyMode () {
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
    }

    @Test
    void testSetPlayerList () {
        lobby.setPlayerList(List.of(new Player("userName", "password")));
    }

    @Test
    void testSetMap () {
        lobby.setMap(new Map("mapName", LocalDate.of(2023, Month.JANUARY, 28), 0, 0));
    }
}

