package de.hsrm.mi.swt02.backend.api.player.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration (classes = {PlayerServiceImpl.class})
@ExtendWith (SpringExtension.class)
class PlayerServiceImplTest {
    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerServiceImpl playerServiceImpl;

    /**
     * Method under test: {@link PlayerServiceImpl#findAllPlayers()}
     */
    @Test
    void testFindAllPlayers () {
        ArrayList<Player> playerList = new ArrayList<>();
        when(playerRepository.findAll()).thenReturn(playerList);
        List<Player> actualFindAllPlayersResult = playerServiceImpl.findAllPlayers();
        assertSame(playerList, actualFindAllPlayersResult);
        assertTrue(actualFindAllPlayersResult.isEmpty());
        verify(playerRepository).findAll();
    }

    /**
     * Method under test: {@link PlayerServiceImpl#findAllPlayers()}
     */
    @Test
    void testFindAllPlayers2 () {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("DB is empty.. no Players were found");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        ObjectPosition objectPosition = new ObjectPosition();
        objectPosition.setId(123L);
        objectPosition.setMapObjectId(123L);
        objectPosition.setPlayer(new Player());
        objectPosition.setPosX(10.0d);
        objectPosition.setPosY(10.0d);
        objectPosition.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition.setVersion(1L);

        Player player = new Player();
        player.setActiveLobby(lobby);
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(objectPosition);
        player.setUserName("janedoe");
        player.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("DB is empty.. no Players were found");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new ObjectPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("DB is empty.. no Players were found");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("DB is empty.. no Players were found");
        lobby2.setMap(map);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("DB is empty.. no Players were found");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        ObjectPosition objectPosition1 = new ObjectPosition();
        objectPosition1.setId(123L);
        objectPosition1.setMapObjectId(123L);
        objectPosition1.setPlayer(new Player());
        objectPosition1.setPosX(10.0d);
        objectPosition1.setPosY(10.0d);
        objectPosition1.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition1.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(objectPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        ObjectPosition objectPosition2 = new ObjectPosition();
        objectPosition2.setId(123L);
        objectPosition2.setMapObjectId(123L);
        objectPosition2.setPlayer(player2);
        objectPosition2.setPosX(10.0d);
        objectPosition2.setPosY(10.0d);
        objectPosition2.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(objectPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player3);
        when(playerRepository.findAll()).thenReturn(playerList);
        List<Player> actualFindAllPlayersResult = playerServiceImpl.findAllPlayers();
        assertSame(playerList, actualFindAllPlayersResult);
        assertEquals(1, actualFindAllPlayersResult.size());
        verify(playerRepository).findAll();
    }

    /**
     * Method under test: {@link PlayerServiceImpl#findPlayerById(long)}
     */
    @Test
    void testFindPlayerById () {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("Lobby Name");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        ObjectPosition objectPosition = new ObjectPosition();
        objectPosition.setId(123L);
        objectPosition.setMapObjectId(123L);
        objectPosition.setPlayer(new Player());
        objectPosition.setPosX(10.0d);
        objectPosition.setPosY(10.0d);
        objectPosition.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition.setVersion(1L);

        Player player = new Player();
        player.setActiveLobby(lobby);
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(objectPosition);
        player.setUserName("janedoe");
        player.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new ObjectPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("Map Name");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(map);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        ObjectPosition objectPosition1 = new ObjectPosition();
        objectPosition1.setId(123L);
        objectPosition1.setMapObjectId(123L);
        objectPosition1.setPlayer(new Player());
        objectPosition1.setPosX(10.0d);
        objectPosition1.setPosY(10.0d);
        objectPosition1.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition1.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(objectPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        ObjectPosition objectPosition2 = new ObjectPosition();
        objectPosition2.setId(123L);
        objectPosition2.setMapObjectId(123L);
        objectPosition2.setPlayer(player2);
        objectPosition2.setPosX(10.0d);
        objectPosition2.setPosY(10.0d);
        objectPosition2.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(objectPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(1L);
        Optional<Player> ofResult = Optional.of(player3);
        when(playerRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(player3, playerServiceImpl.findPlayerById(123L));
        verify(playerRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PlayerServiceImpl#deletePlayer(long)}
     */
    @Test
    void testDeletePlayer () {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("Lobby Name");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        ObjectPosition objectPosition = new ObjectPosition();
        objectPosition.setId(123L);
        objectPosition.setMapObjectId(123L);
        objectPosition.setPlayer(new Player());
        objectPosition.setPosX(10.0d);
        objectPosition.setPosY(10.0d);
        objectPosition.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition.setVersion(1L);

        Player player = new Player();
        player.setActiveLobby(lobby);
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(objectPosition);
        player.setUserName("janedoe");
        player.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new ObjectPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("Map Name");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(map);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        ObjectPosition objectPosition1 = new ObjectPosition();
        objectPosition1.setId(123L);
        objectPosition1.setMapObjectId(123L);
        objectPosition1.setPlayer(new Player());
        objectPosition1.setPosX(10.0d);
        objectPosition1.setPosY(10.0d);
        objectPosition1.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition1.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(objectPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        ObjectPosition objectPosition2 = new ObjectPosition();
        objectPosition2.setId(123L);
        objectPosition2.setMapObjectId(123L);
        objectPosition2.setPlayer(player2);
        objectPosition2.setPosX(10.0d);
        objectPosition2.setPosY(10.0d);
        objectPosition2.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(objectPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(1L);
        Optional<Player> ofResult = Optional.of(player3);
        doNothing().when(playerRepository).deleteById((Long) any());
        when(playerRepository.findById((Long) any())).thenReturn(ofResult);
        playerServiceImpl.deletePlayer(123L);
        verify(playerRepository, atLeast(1)).findById((Long) any());
        verify(playerRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link PlayerServiceImpl#findPlayerByUsernameAndPassword(String, String)}
     */
    @Test
    void testFindPlayerByUsernameAndPassword () {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("Lobby Name");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        ObjectPosition objectPosition = new ObjectPosition();
        objectPosition.setId(123L);
        objectPosition.setMapObjectId(123L);
        objectPosition.setPlayer(new Player());
        objectPosition.setPosX(10.0d);
        objectPosition.setPosY(10.0d);
        objectPosition.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition.setVersion(1L);

        Player player = new Player();
        player.setActiveLobby(lobby);
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(objectPosition);
        player.setUserName("janedoe");
        player.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new ObjectPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("Map Name");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(map);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        ObjectPosition objectPosition1 = new ObjectPosition();
        objectPosition1.setId(123L);
        objectPosition1.setMapObjectId(123L);
        objectPosition1.setPlayer(new Player());
        objectPosition1.setPosX(10.0d);
        objectPosition1.setPosY(10.0d);
        objectPosition1.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition1.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(objectPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        ObjectPosition objectPosition2 = new ObjectPosition();
        objectPosition2.setId(123L);
        objectPosition2.setMapObjectId(123L);
        objectPosition2.setPlayer(player2);
        objectPosition2.setPosX(10.0d);
        objectPosition2.setPosY(10.0d);
        objectPosition2.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(objectPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(1L);
        Optional<Player> ofResult = Optional.of(player3);
        when(playerRepository.findPlayerByUsernameAndPassword((String) any(), (String) any())).thenReturn(ofResult);
        assertSame(player3, playerServiceImpl.findPlayerByUsernameAndPassword("janedoe", "iloveyou"));
        verify(playerRepository).findPlayerByUsernameAndPassword((String) any(), (String) any());
    }

    /**
     * Method under test: {@link PlayerServiceImpl#createPlayer(String, String)}
     */
    @Test
    void testCreatePlayer () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new ObjectPosition());
        player.setUserName("janedoe");
        player.setVersion(1L);

        Map map = new Map();
        map.setCreationDate(null);
        map.setId(123L);
        map.setLobby(new Lobby());
        map.setMapName("Map Name");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(new Player());
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby = new Lobby();
        lobby.setHost(player);
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("Lobby Name");
        lobby.setMap(map);
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new ObjectPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        ObjectPosition objectPosition = new ObjectPosition();
        objectPosition.setId(123L);
        objectPosition.setMapObjectId(123L);
        objectPosition.setPlayer(player1);
        objectPosition.setPosX(10.0d);
        objectPosition.setPosY(10.0d);
        objectPosition.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(objectPosition);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new ObjectPosition());
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(null);
        map1.setId(123L);
        map1.setLobby(new Lobby());
        map1.setMapName("Map Name");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(new Player());
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(player3);
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(map1);
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(new Player());
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(new Map());
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        ObjectPosition objectPosition1 = new ObjectPosition();
        objectPosition1.setId(123L);
        objectPosition1.setMapObjectId(123L);
        objectPosition1.setPlayer(new Player());
        objectPosition1.setPosX(10.0d);
        objectPosition1.setPosY(10.0d);
        objectPosition1.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition1.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby2);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(objectPosition1);
        player4.setUserName("janedoe");
        player4.setVersion(1L);

        Map map2 = new Map();
        map2.setCreationDate(LocalDate.ofEpochDay(1L));
        map2.setId(123L);
        map2.setLobby(lobby1);
        map2.setMapName("Map Name");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(player4);
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(player2);
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(map2);
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(new Lobby());
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(new ObjectPosition());
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(null);
        map3.setId(123L);
        map3.setLobby(new Lobby());
        map3.setMapName("Map Name");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(new Player());
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        Lobby lobby4 = new Lobby();
        lobby4.setHost(player5);
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(map3);
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(new Lobby());
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(new ObjectPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        ObjectPosition objectPosition2 = new ObjectPosition();
        objectPosition2.setId(123L);
        objectPosition2.setMapObjectId(123L);
        objectPosition2.setPlayer(player6);
        objectPosition2.setPosX(10.0d);
        objectPosition2.setPosY(10.0d);
        objectPosition2.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition2.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby4);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(objectPosition2);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        ObjectPosition objectPosition3 = new ObjectPosition();
        objectPosition3.setId(123L);
        objectPosition3.setMapObjectId(123L);
        objectPosition3.setPlayer(player7);
        objectPosition3.setPosX(10.0d);
        objectPosition3.setPosY(10.0d);
        objectPosition3.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition3.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby3);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(objectPosition3);
        player8.setUserName("janedoe");
        player8.setVersion(1L);
        when(playerRepository.save((Player) any())).thenReturn(player8);
        assertSame(player8, playerServiceImpl.createPlayer("janedoe", "iloveyou"));
        verify(playerRepository).save((Player) any());
    }
}

