package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.GameAssetRepository;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.GameAsset;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration (classes = {MapObjectServiceImpl.class})
@ExtendWith (SpringExtension.class)
class MapObjectServiceImplTest {
    @MockBean
    private GameAssetRepository gameAssetRepository;

    @MockBean
    private MapObjectRepository mapObjectRepository;

    @Autowired
    private MapObjectServiceImpl mapObjectServiceImpl;

    @MockBean
    private MapService mapService;

    /**
     * Method under test: {@link MapObjectServiceImpl#findAllMapObjects()}
     */
    @Test
    void testFindAllMapObjects () {
        ArrayList<MapObject> mapObjectList = new ArrayList<>();
        when(mapObjectRepository.findAll()).thenReturn(mapObjectList);
        List<MapObject> actualFindAllMapObjectsResult = mapObjectServiceImpl.findAllMapObjects();
        assertSame(mapObjectList, actualFindAllMapObjectsResult);
        assertTrue(actualFindAllMapObjectsResult.isEmpty());
        verify(mapObjectRepository).findAll();
    }

    /**
     * Method under test: {@link MapObjectServiceImpl#getMapObjectById(long)}
     */
    @Test
    void testGetMapObjectById () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new PlayerPosition());
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

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(lobby1);
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(playerPosition);
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(1L));
        map1.setId(123L);
        map1.setLobby(lobby);
        map1.setMapName("Map Name");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player1);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        MapObject mapObject = new MapObject();
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map1);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);
        Optional<MapObject> ofResult = Optional.of(mapObject);
        when(mapObjectRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(mapObject, mapObjectServiceImpl.getMapObjectById(123L));
        verify(mapObjectRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MapObjectServiceImpl#deleteMapObjectById(long)}
     */
    @Test
    void testDeleteMapObjectById () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new PlayerPosition());
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

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(lobby1);
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(playerPosition);
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(1L));
        map1.setId(123L);
        map1.setLobby(lobby);
        map1.setMapName("Map Name");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player1);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        MapObject mapObject = new MapObject();
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map1);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);
        Optional<MapObject> ofResult = Optional.of(mapObject);
        doNothing().when(mapObjectRepository).deleteById((Long) any());
        when(mapObjectRepository.findById((Long) any())).thenReturn(ofResult);
        mapObjectServiceImpl.deleteMapObjectById(123L);
        verify(mapObjectRepository, atLeast(1)).findById((Long) any());
        verify(mapObjectRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link MapObjectServiceImpl#deleteMapObjectById(long)}
     */
    @Test
    void testDeleteMapObjectById2 () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new PlayerPosition());
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

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(lobby1);
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(playerPosition);
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(1L));
        map1.setId(123L);
        map1.setLobby(lobby);
        map1.setMapName("Map Name");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player1);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(new Lobby());
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(new PlayerPosition());
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Map map2 = new Map();
        map2.setCreationDate(null);
        map2.setId(123L);
        map2.setLobby(new Lobby());
        map2.setMapName("Map Name");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(new Player());
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player2);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(map2);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new PlayerPosition());
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(player3);
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby2);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(playerPosition1);
        player4.setUserName("janedoe");
        player4.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(new Lobby());
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(new PlayerPosition());
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

        Lobby lobby3 = new Lobby();
        lobby3.setHost(player5);
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(map3);
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        Lobby lobby4 = new Lobby();
        lobby4.setHost(new Player());
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(new Map());
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(new Player());
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(lobby4);
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(playerPosition2);
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        Map map4 = new Map();
        map4.setCreationDate(LocalDate.ofEpochDay(1L));
        map4.setId(123L);
        map4.setLobby(lobby3);
        map4.setMapName("Map Name");
        map4.setMapObjects(new ArrayList<>());
        map4.setMapOwner(player6);
        map4.setSizeX(3);
        map4.setSizeY(3);
        map4.setVersion(1L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(player4);
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("Lobby Name");
        lobby5.setMap(map4);
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(1L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(new Player());
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("Lobby Name");
        lobby6.setMap(new Map());
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(new Player());
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby6);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition3);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(new Player());
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("Lobby Name");
        lobby7.setMap(new Map());
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(new Lobby());
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(new PlayerPosition());
        player8.setUserName("janedoe");
        player8.setVersion(1L);

        Map map5 = new Map();
        map5.setCreationDate(LocalDate.ofEpochDay(1L));
        map5.setId(123L);
        map5.setLobby(lobby7);
        map5.setMapName("Map Name");
        map5.setMapObjects(new ArrayList<>());
        map5.setMapOwner(player8);
        map5.setSizeX(3);
        map5.setSizeY(3);
        map5.setVersion(1L);

        Lobby lobby8 = new Lobby();
        lobby8.setHost(player7);
        lobby8.setId(123L);
        lobby8.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby8.setLobbyName("Lobby Name");
        lobby8.setMap(map5);
        lobby8.setNumOfPlayers(10);
        lobby8.setPlayerList(new ArrayList<>());
        lobby8.setVersion(1L);

        Lobby lobby9 = new Lobby();
        lobby9.setHost(new Player());
        lobby9.setId(123L);
        lobby9.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby9.setLobbyName("Lobby Name");
        lobby9.setMap(new Map());
        lobby9.setNumOfPlayers(10);
        lobby9.setPlayerList(new ArrayList<>());
        lobby9.setVersion(1L);

        PlayerPosition playerPosition4 = new PlayerPosition();
        playerPosition4.setId(123L);
        playerPosition4.setPlayer(new Player());
        playerPosition4.setPosX(10.0d);
        playerPosition4.setPosY(10.0d);
        playerPosition4.setRotation(10.0d);
        playerPosition4.setVersion(1L);

        Player player9 = new Player();
        player9.setActiveLobby(lobby9);
        player9.setHostedLobbys(new ArrayList<>());
        player9.setId(123L);
        player9.setMapList(new ArrayList<>());
        player9.setPassword("iloveyou");
        player9.setPlayerPosition(playerPosition4);
        player9.setUserName("janedoe");
        player9.setVersion(1L);

        PlayerPosition playerPosition5 = new PlayerPosition();
        playerPosition5.setId(123L);
        playerPosition5.setPlayer(player9);
        playerPosition5.setPosX(10.0d);
        playerPosition5.setPosY(10.0d);
        playerPosition5.setRotation(10.0d);
        playerPosition5.setVersion(1L);

        Player player10 = new Player();
        player10.setActiveLobby(lobby8);
        player10.setHostedLobbys(new ArrayList<>());
        player10.setId(123L);
        player10.setMapList(new ArrayList<>());
        player10.setPassword("iloveyou");
        player10.setPlayerPosition(playerPosition5);
        player10.setUserName("janedoe");
        player10.setVersion(1L);

        Map map6 = new Map();
        map6.setCreationDate(LocalDate.ofEpochDay(1L));
        map6.setId(123L);
        map6.setLobby(lobby5);
        map6.setMapName("Map Name");
        map6.setMapObjects(new ArrayList<>());
        map6.setMapOwner(player10);
        map6.setSizeX(3);
        map6.setSizeY(3);
        map6.setVersion(1L);
        MapObject mapObject = mock(MapObject.class);
        when(mapObject.getMap()).thenReturn(map6);
        doNothing().when(mapObject).setGameAssets((List<GameAsset>) any());
        doNothing().when(mapObject).setId(anyLong());
        doNothing().when(mapObject).setMap((Map) any());
        doNothing().when(mapObject).setObjectTypeId(anyLong());
        doNothing().when(mapObject).setRotation(anyInt());
        doNothing().when(mapObject).setVersion(anyLong());
        doNothing().when(mapObject).setX(anyInt());
        doNothing().when(mapObject).setY(anyInt());
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map1);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);
        Optional<MapObject> ofResult = Optional.of(mapObject);
        doNothing().when(mapObjectRepository).deleteById((Long) any());
        when(mapObjectRepository.findById((Long) any())).thenReturn(ofResult);
        mapObjectServiceImpl.deleteMapObjectById(123L);
        verify(mapObjectRepository, atLeast(1)).findById((Long) any());
        verify(mapObjectRepository).deleteById((Long) any());
        verify(mapObject).getMap();
        verify(mapObject).setGameAssets((List<GameAsset>) any());
        verify(mapObject).setId(anyLong());
        verify(mapObject).setMap((Map) any());
        verify(mapObject).setObjectTypeId(anyLong());
        verify(mapObject).setRotation(anyInt());
        verify(mapObject).setVersion(anyLong());
        verify(mapObject).setX(anyInt());
        verify(mapObject).setY(anyInt());
    }

    /**
     * Method under test: {@link MapObjectServiceImpl#deleteAllMapObjectsFromMapById(long)}
     */
    @Test
    void testDeleteAllMapObjectsFromMapById () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new PlayerPosition());
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
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(player1);
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new PlayerPosition());
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

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(new Player());
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby2);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(playerPosition1);
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

        Lobby lobby4 = new Lobby();
        lobby4.setHost(new Player());
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(new Map());
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(new Player());
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(lobby4);
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(playerPosition2);
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(new Player());
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("Lobby Name");
        lobby5.setMap(new Map());
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(new Lobby());
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(new PlayerPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(LocalDate.ofEpochDay(1L));
        map3.setId(123L);
        map3.setLobby(lobby5);
        map3.setMapName("Map Name");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(player6);
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(player5);
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("Lobby Name");
        lobby6.setMap(map3);
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(1L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(new Player());
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("Lobby Name");
        lobby7.setMap(new Map());
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(new Player());
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby7);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition3);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        PlayerPosition playerPosition4 = new PlayerPosition();
        playerPosition4.setId(123L);
        playerPosition4.setPlayer(player7);
        playerPosition4.setPosX(10.0d);
        playerPosition4.setPosY(10.0d);
        playerPosition4.setRotation(10.0d);
        playerPosition4.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby6);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(playerPosition4);
        player8.setUserName("janedoe");
        player8.setVersion(1L);

        Map map4 = new Map();
        map4.setCreationDate(LocalDate.ofEpochDay(1L));
        map4.setId(123L);
        map4.setLobby(lobby3);
        map4.setMapName("Map Name");
        map4.setMapObjects(new ArrayList<>());
        map4.setMapOwner(player8);
        map4.setSizeX(3);
        map4.setSizeY(3);
        map4.setVersion(1L);
        when(mapService.getMapById(anyLong())).thenReturn(map4);
        mapObjectServiceImpl.deleteAllMapObjectsFromMapById(123L);
        verify(mapService).getMapById(anyLong());
    }

    /**
     * Method under test: {@link MapObjectServiceImpl#deleteMapObjectFromBroker(AddMapObjectRequestDTO, long)}
     */
    @Test
    void testDeleteMapObjectFromBroker () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new PlayerPosition());
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
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(player1);
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new PlayerPosition());
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

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(new Player());
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby2);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(playerPosition1);
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

        Lobby lobby4 = new Lobby();
        lobby4.setHost(new Player());
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(new Map());
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(new Player());
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(lobby4);
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(playerPosition2);
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(new Player());
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("Lobby Name");
        lobby5.setMap(new Map());
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(new Lobby());
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(new PlayerPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(LocalDate.ofEpochDay(1L));
        map3.setId(123L);
        map3.setLobby(lobby5);
        map3.setMapName("Map Name");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(player6);
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(player5);
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("Lobby Name");
        lobby6.setMap(map3);
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(1L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(new Player());
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("Lobby Name");
        lobby7.setMap(new Map());
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(new Player());
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby7);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition3);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        PlayerPosition playerPosition4 = new PlayerPosition();
        playerPosition4.setId(123L);
        playerPosition4.setPlayer(player7);
        playerPosition4.setPosX(10.0d);
        playerPosition4.setPosY(10.0d);
        playerPosition4.setRotation(10.0d);
        playerPosition4.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby6);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(playerPosition4);
        player8.setUserName("janedoe");
        player8.setVersion(1L);

        Map map4 = new Map();
        map4.setCreationDate(LocalDate.ofEpochDay(1L));
        map4.setId(123L);
        map4.setLobby(lobby3);
        map4.setMapName("Map Name");
        map4.setMapObjects(new ArrayList<>());
        map4.setMapOwner(player8);
        map4.setSizeX(3);
        map4.setSizeY(3);
        map4.setVersion(1L);
        when(mapService.getMapById(anyLong())).thenReturn(map4);
        mapObjectServiceImpl.deleteMapObjectFromBroker(new AddMapObjectRequestDTO(123L, 2, 3, 1, new ArrayList<>()),
                123L);
        verify(mapService).getMapById(anyLong());
    }

    /**
     * Method under test: {@link MapObjectServiceImpl#updateMapObjectFromBroker(AddMapObjectRequestDTO, long)}
     */
    @Test
    void testUpdateMapObjectFromBroker () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new PlayerPosition());
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
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(player1);
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new PlayerPosition());
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

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(new Player());
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby2);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(playerPosition1);
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

        Lobby lobby4 = new Lobby();
        lobby4.setHost(new Player());
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(new Map());
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(new Player());
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(lobby4);
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(playerPosition2);
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(new Player());
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("Lobby Name");
        lobby5.setMap(new Map());
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(new Lobby());
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(new PlayerPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(LocalDate.ofEpochDay(1L));
        map3.setId(123L);
        map3.setLobby(lobby5);
        map3.setMapName("Map Name");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(player6);
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(player5);
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("Lobby Name");
        lobby6.setMap(map3);
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(1L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(new Player());
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("Lobby Name");
        lobby7.setMap(new Map());
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(new Player());
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby7);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition3);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        PlayerPosition playerPosition4 = new PlayerPosition();
        playerPosition4.setId(123L);
        playerPosition4.setPlayer(player7);
        playerPosition4.setPosX(10.0d);
        playerPosition4.setPosY(10.0d);
        playerPosition4.setRotation(10.0d);
        playerPosition4.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby6);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(playerPosition4);
        player8.setUserName("janedoe");
        player8.setVersion(1L);

        Map map4 = new Map();
        map4.setCreationDate(LocalDate.ofEpochDay(1L));
        map4.setId(123L);
        map4.setLobby(lobby3);
        map4.setMapName("Map Name");
        map4.setMapObjects(new ArrayList<>());
        map4.setMapOwner(player8);
        map4.setSizeX(3);
        map4.setSizeY(3);
        map4.setVersion(1L);
        when(mapService.getMapById(anyLong())).thenReturn(map4);
        mapObjectServiceImpl.updateMapObjectFromBroker(new AddMapObjectRequestDTO(123L, 2, 3, 1, new ArrayList<>()),
                123L);
        verify(mapService).getMapById(anyLong());
    }

    /**
     * Method under test: {@link MapObjectServiceImpl#getAllMapObjectsFromMap(long)}
     */
    @Test
    void testGetAllMapObjectsFromMap () {
        Player player = new Player();
        player.setActiveLobby(new Lobby());
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(new PlayerPosition());
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
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(player1);
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new PlayerPosition());
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

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(new Player());
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby2);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(playerPosition1);
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

        Lobby lobby4 = new Lobby();
        lobby4.setHost(new Player());
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(new Map());
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(new Player());
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(lobby4);
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(playerPosition2);
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(new Player());
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("Lobby Name");
        lobby5.setMap(new Map());
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(new Lobby());
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(new PlayerPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(LocalDate.ofEpochDay(1L));
        map3.setId(123L);
        map3.setLobby(lobby5);
        map3.setMapName("Map Name");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(player6);
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(player5);
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("Lobby Name");
        lobby6.setMap(map3);
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(1L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(new Player());
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("Lobby Name");
        lobby7.setMap(new Map());
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(new Player());
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby7);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition3);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        PlayerPosition playerPosition4 = new PlayerPosition();
        playerPosition4.setId(123L);
        playerPosition4.setPlayer(player7);
        playerPosition4.setPosX(10.0d);
        playerPosition4.setPosY(10.0d);
        playerPosition4.setRotation(10.0d);
        playerPosition4.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby6);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(playerPosition4);
        player8.setUserName("janedoe");
        player8.setVersion(1L);

        Map map4 = new Map();
        map4.setCreationDate(LocalDate.ofEpochDay(1L));
        map4.setId(123L);
        map4.setLobby(lobby3);
        map4.setMapName("Map Name");
        ArrayList<MapObject> mapObjectList = new ArrayList<>();
        map4.setMapObjects(mapObjectList);
        map4.setMapOwner(player8);
        map4.setSizeX(3);
        map4.setSizeY(3);
        map4.setVersion(1L);
        when(mapService.getMapById(anyLong())).thenReturn(map4);
        List<MapObject> actualAllMapObjectsFromMap = mapObjectServiceImpl.getAllMapObjectsFromMap(123L);
        assertSame(mapObjectList, actualAllMapObjectsFromMap);
        assertTrue(actualAllMapObjectsFromMap.isEmpty());
        verify(mapService).getMapById(anyLong());
    }
}

