package de.hsrm.mi.swt02.backend.api.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.GameAssetDTO;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectTypeServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration (classes = {MapObjectRestController.class})
@ExtendWith (SpringExtension.class)
class MapObjectRestControllerTest {
    @Autowired
    private MapObjectRestController mapObjectRestController;

    @MockBean
    private MapObjectServiceImpl mapObjectServiceImpl;

    @MockBean
    private MapObjectTypeServiceImpl mapObjectTypeServiceImpl;

    /**
     * Method under test: {@link MapObjectRestController#deleteMapObject(long)}
     */
    @Test
    void testDeleteMapObject () throws Exception {
        doNothing().when(mapObjectServiceImpl).deleteMapObjectById(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/mapobject/{id}", 123L);
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link MapObjectRestController#deleteMapObject(long)}
     */
    @Test
    void testDeleteMapObject2 () throws Exception {
        doNothing().when(mapObjectServiceImpl).deleteMapObjectById(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/mapobject/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link MapObjectRestController#getAllMapObjects()}
     */
    @Test
    void testGetAllMapObjects () throws Exception {
        when(mapObjectServiceImpl.findAllMapObjects()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getAllMapObjects()}
     */
    @Test
    void testGetAllMapObjects2 () throws Exception {
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
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(new Player());
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby = new Lobby();
        lobby.setHost(player);
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
        lobby.setMap(map);
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("?");
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
        map1.setMapName("?");
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

        ArrayList<MapObject> mapObjectList = new ArrayList<>();
        mapObjectList.add(mapObject);
        when(mapObjectServiceImpl.findAllMapObjects()).thenReturn(mapObjectList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"objectTypeId\":123,\"x\":2,\"y\":3,\"rotation\":1,\"game_assets\":[]}]"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getAllMapObjects()}
     */
    @Test
    void testGetAllMapObjects3 () throws Exception {
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
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(new Player());
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby = new Lobby();
        lobby.setHost(player);
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
        lobby.setMap(map);
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("?");
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
        map1.setMapName("?");
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
        map2.setMapName("?");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(new Player());
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player2);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("?");
        lobby2.setMap(map2);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("?");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(new Player());
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby3);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(playerPosition1);
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(LocalDate.ofEpochDay(1L));
        map3.setId(123L);
        map3.setLobby(lobby2);
        map3.setMapName("?");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(player3);
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        MapObject mapObject1 = new MapObject();
        mapObject1.setGameAssets(new ArrayList<>());
        mapObject1.setId(123L);
        mapObject1.setMap(map3);
        mapObject1.setObjectTypeId(123L);
        mapObject1.setRotation(1);
        mapObject1.setVersion(1L);
        mapObject1.setX(2);
        mapObject1.setY(3);

        ArrayList<MapObject> mapObjectList = new ArrayList<>();
        mapObjectList.add(mapObject1);
        mapObjectList.add(mapObject);
        when(mapObjectServiceImpl.findAllMapObjects()).thenReturn(mapObjectList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"objectTypeId\":123,\"x\":2,\"y\":3,\"rotation\":1,\"game_assets\":[]},{\"objectTypeId\":123,\"x\":2,\"y\":3,"
                                        + "\"rotation\":1,\"game_assets\":[]}]"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getAllMapObjects()}
     */
    @Test
    void testGetAllMapObjects4 () throws Exception {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

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
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby);
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        MapObject mapObject = new MapObject();
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);

        GameAsset gameAsset = new GameAsset();
        gameAsset.setId(123L);
        gameAsset.setMapObject(mapObject);
        gameAsset.setObjectTypeId(123);
        gameAsset.setRotation(1);
        gameAsset.setTexture("?");
        gameAsset.setVersion(1L);
        gameAsset.setX(2.0d);
        gameAsset.setY(3.0d);

        ArrayList<GameAsset> gameAssetList = new ArrayList<>();
        gameAssetList.add(gameAsset);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(null);
        map1.setId(123L);
        map1.setLobby(new Lobby());
        map1.setMapName("?");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(new Player());
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(player1);
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("?");
        lobby1.setMap(map1);
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(new Player());
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("?");
        lobby2.setMap(new Map());
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby2);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Map map2 = new Map();
        map2.setCreationDate(LocalDate.ofEpochDay(1L));
        map2.setId(123L);
        map2.setLobby(lobby1);
        map2.setMapName("?");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(player2);
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        MapObject mapObject1 = new MapObject();
        mapObject1.setGameAssets(gameAssetList);
        mapObject1.setId(123L);
        mapObject1.setMap(map2);
        mapObject1.setObjectTypeId(123L);
        mapObject1.setRotation(1);
        mapObject1.setVersion(1L);
        mapObject1.setX(2);
        mapObject1.setY(3);

        ArrayList<MapObject> mapObjectList = new ArrayList<>();
        mapObjectList.add(mapObject1);
        when(mapObjectServiceImpl.findAllMapObjects()).thenReturn(mapObjectList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"objectTypeId\":123,\"x\":2,\"y\":3,\"rotation\":1,\"game_assets\":[{\"objectTypeId\":123,\"x\":2.0,\"y\":3.0,"
                                        + "\"rotation\":1,\"texture\":\"?\"}]}]"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getAllMapObjects()}
     */
    @Test
    void testGetAllMapObjects5 () throws Exception {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

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
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby);
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        MapObject mapObject = new MapObject();
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);

        GameAsset gameAsset = new GameAsset();
        gameAsset.setId(123L);
        gameAsset.setMapObject(mapObject);
        gameAsset.setObjectTypeId(123);
        gameAsset.setRotation(1);
        gameAsset.setTexture("?");
        gameAsset.setVersion(1L);
        gameAsset.setX(2.0d);
        gameAsset.setY(3.0d);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("?");
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
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(1L));
        map1.setId(123L);
        map1.setLobby(lobby1);
        map1.setMapName("?");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player1);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        MapObject mapObject1 = new MapObject();
        mapObject1.setGameAssets(new ArrayList<>());
        mapObject1.setId(123L);
        mapObject1.setMap(map1);
        mapObject1.setObjectTypeId(123L);
        mapObject1.setRotation(1);
        mapObject1.setVersion(1L);
        mapObject1.setX(2);
        mapObject1.setY(3);

        GameAsset gameAsset1 = new GameAsset();
        gameAsset1.setId(123L);
        gameAsset1.setMapObject(mapObject1);
        gameAsset1.setObjectTypeId(123);
        gameAsset1.setRotation(1);
        gameAsset1.setTexture("?");
        gameAsset1.setVersion(1L);
        gameAsset1.setX(2.0d);
        gameAsset1.setY(3.0d);

        ArrayList<GameAsset> gameAssetList = new ArrayList<>();
        gameAssetList.add(gameAsset1);
        gameAssetList.add(gameAsset);

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
        map2.setMapName("?");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(new Player());
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player2);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("?");
        lobby2.setMap(map2);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("?");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby3);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(playerPosition);
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(LocalDate.ofEpochDay(1L));
        map3.setId(123L);
        map3.setLobby(lobby2);
        map3.setMapName("?");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(player3);
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        MapObject mapObject2 = new MapObject();
        mapObject2.setGameAssets(gameAssetList);
        mapObject2.setId(123L);
        mapObject2.setMap(map3);
        mapObject2.setObjectTypeId(123L);
        mapObject2.setRotation(1);
        mapObject2.setVersion(1L);
        mapObject2.setX(2);
        mapObject2.setY(3);

        ArrayList<MapObject> mapObjectList = new ArrayList<>();
        mapObjectList.add(mapObject2);
        when(mapObjectServiceImpl.findAllMapObjects()).thenReturn(mapObjectList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"objectTypeId\":123,\"x\":2,\"y\":3,\"rotation\":1,\"game_assets\":[{\"objectTypeId\":123,\"x\":2.0,\"y\":3.0,"
                                        + "\"rotation\":1,\"texture\":\"?\"},{\"objectTypeId\":123,\"x\":2.0,\"y\":3.0,\"rotation\":1,\"texture\":\"?\"}]}]"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getMapObjectList()}
     */
    @Test
    void testGetMapObjectList () throws Exception {
        when(mapObjectTypeServiceImpl.findAllMapObjectType()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject/list");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getMapObjectList()}
     */
    @Test
    void testGetMapObjectList2 () throws Exception {
        when(mapObjectTypeServiceImpl.findAllMapObjectType()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/mapobject/list");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getSingleMapObject(long)}
     */
    @Test
    void testGetSingleMapObject () throws Exception {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("Lobby Name");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player = new Player();
        player.setActiveLobby(lobby);
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(playerPosition);
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
        player1.setPlayerPosition(new PlayerPosition());
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

        Player player2 = new Player();
        player2.setActiveLobby(new Lobby());
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(new PlayerPosition());
        player2.setUserName("janedoe");
        player2.setVersion(1L);

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

        Lobby lobby3 = new Lobby();
        lobby3.setHost(player2);
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(map1);
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

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
        player4.setActiveLobby(lobby3);
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
        map2.setLobby(lobby2);
        map2.setMapName("Map Name");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(player4);
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        MapObject mapObject = new MapObject();
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map2);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);
        when(mapObjectServiceImpl.getMapObjectById(anyLong())).thenReturn(mapObject);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject/{id}", 123L);
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"objectTypeId\":123,\"x\":2,\"y\":3,\"rotation\":1,\"game_assets\":[]}"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getSingleMapObject(long)}
     */
    @Test
    void testGetSingleMapObject2 () throws Exception {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

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
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby);
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        MapObject mapObject = new MapObject();
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);

        GameAsset gameAsset = new GameAsset();
        gameAsset.setId(123L);
        gameAsset.setMapObject(mapObject);
        gameAsset.setObjectTypeId(123);
        gameAsset.setRotation(1);
        gameAsset.setTexture("?");
        gameAsset.setVersion(1L);
        gameAsset.setX(2.0d);
        gameAsset.setY(3.0d);

        ArrayList<GameAsset> gameAssetList = new ArrayList<>();
        gameAssetList.add(gameAsset);

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

        Lobby lobby2 = new Lobby();
        lobby2.setHost(new Player());
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(new Map());
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(new Lobby());
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(new PlayerPosition());
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(1L));
        map1.setId(123L);
        map1.setLobby(lobby2);
        map1.setMapName("Map Name");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player2);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(player1);
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(map1);
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new PlayerPosition());
        player3.setUserName("janedoe");
        player3.setVersion(1L);

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

        Lobby lobby4 = new Lobby();
        lobby4.setHost(player3);
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(map2);
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(new Lobby());
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(new PlayerPosition());
        player4.setUserName("janedoe");
        player4.setVersion(1L);

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(player4);
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(lobby4);
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(playerPosition1);
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        Map map3 = new Map();
        map3.setCreationDate(LocalDate.ofEpochDay(1L));
        map3.setId(123L);
        map3.setLobby(lobby3);
        map3.setMapName("Map Name");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(player5);
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        MapObject mapObject1 = new MapObject();
        mapObject1.setGameAssets(gameAssetList);
        mapObject1.setId(123L);
        mapObject1.setMap(map3);
        mapObject1.setObjectTypeId(123L);
        mapObject1.setRotation(1);
        mapObject1.setVersion(1L);
        mapObject1.setX(2);
        mapObject1.setY(3);
        when(mapObjectServiceImpl.getMapObjectById(anyLong())).thenReturn(mapObject1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject/{id}", 123L);
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"objectTypeId\":123,\"x\":2,\"y\":3,\"rotation\":1,\"game_assets\":[{\"objectTypeId\":123,\"x\":2.0,\"y\":3.0,"
                                        + "\"rotation\":1,\"texture\":\"?\"}]}"));
    }

    /**
     * Method under test: {@link MapObjectRestController#getSingleMapObject(long)}
     */
    @Test
    void testGetSingleMapObject3 () throws Exception {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(1L);

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
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby);
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        MapObject mapObject = new MapObject();
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);

        GameAsset gameAsset = new GameAsset();
        gameAsset.setId(123L);
        gameAsset.setMapObject(mapObject);
        gameAsset.setObjectTypeId(123);
        gameAsset.setRotation(1);
        gameAsset.setTexture("?");
        gameAsset.setVersion(1L);
        gameAsset.setX(2.0d);
        gameAsset.setY(3.0d);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("?");
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
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(1L);

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(1L));
        map1.setId(123L);
        map1.setLobby(lobby1);
        map1.setMapName("?");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player1);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        MapObject mapObject1 = new MapObject();
        mapObject1.setGameAssets(new ArrayList<>());
        mapObject1.setId(123L);
        mapObject1.setMap(map1);
        mapObject1.setObjectTypeId(123L);
        mapObject1.setRotation(1);
        mapObject1.setVersion(1L);
        mapObject1.setX(2);
        mapObject1.setY(3);

        GameAsset gameAsset1 = new GameAsset();
        gameAsset1.setId(123L);
        gameAsset1.setMapObject(mapObject1);
        gameAsset1.setObjectTypeId(123);
        gameAsset1.setRotation(1);
        gameAsset1.setTexture("?");
        gameAsset1.setVersion(1L);
        gameAsset1.setX(2.0d);
        gameAsset1.setY(3.0d);

        ArrayList<GameAsset> gameAssetList = new ArrayList<>();
        gameAssetList.add(gameAsset1);
        gameAssetList.add(gameAsset);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(new Player());
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(new Map());
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(1L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(1L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby2);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(new Lobby());
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(new PlayerPosition());
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        Map map2 = new Map();
        map2.setCreationDate(LocalDate.ofEpochDay(1L));
        map2.setId(123L);
        map2.setLobby(lobby3);
        map2.setMapName("Map Name");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(player3);
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        Lobby lobby4 = new Lobby();
        lobby4.setHost(player2);
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("Lobby Name");
        lobby4.setMap(map2);
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(new Lobby());
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(new PlayerPosition());
        player4.setUserName("janedoe");
        player4.setVersion(1L);

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

        Lobby lobby5 = new Lobby();
        lobby5.setHost(player4);
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("Lobby Name");
        lobby5.setMap(map3);
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(new Lobby());
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(new PlayerPosition());
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(player5);
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(lobby5);
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(playerPosition1);
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        Map map4 = new Map();
        map4.setCreationDate(LocalDate.ofEpochDay(1L));
        map4.setId(123L);
        map4.setLobby(lobby4);
        map4.setMapName("Map Name");
        map4.setMapObjects(new ArrayList<>());
        map4.setMapOwner(player6);
        map4.setSizeX(3);
        map4.setSizeY(3);
        map4.setVersion(1L);

        MapObject mapObject2 = new MapObject();
        mapObject2.setGameAssets(gameAssetList);
        mapObject2.setId(123L);
        mapObject2.setMap(map4);
        mapObject2.setObjectTypeId(123L);
        mapObject2.setRotation(1);
        mapObject2.setVersion(1L);
        mapObject2.setX(2);
        mapObject2.setY(3);
        when(mapObjectServiceImpl.getMapObjectById(anyLong())).thenReturn(mapObject2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mapobject/{id}", 123L);
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"objectTypeId\":123,\"x\":2,\"y\":3,\"rotation\":1,\"game_assets\":[{\"objectTypeId\":123,\"x\":2.0,\"y\":3.0,"
                                        + "\"rotation\":1,\"texture\":\"?\"},{\"objectTypeId\":123,\"x\":2.0,\"y\":3.0,\"rotation\":1,\"texture\":\"?\"}]}"));
    }

    /**
     * Method under test: {@link MapObjectRestController#postMapObject(AddMapObjectsRequestDTO, long)}
     */
    @Test
    void testPostMapObject () throws Exception {
        when(mapObjectServiceImpl.createMapObject((AddMapObjectsRequestDTO) any(), anyLong())).thenReturn(1L);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/mapobject/{map_id}", 1L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new AddMapObjectsRequestDTO(new ArrayList<>())));
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link MapObjectRestController#postMapObject(AddMapObjectsRequestDTO, long)}
     */
    @Test
    void testPostMapObject2 () throws Exception {
        when(mapObjectServiceImpl.createMapObject((AddMapObjectsRequestDTO) any(), anyLong())).thenReturn(1L);

        ArrayList<GameAssetDTO> gameAssetDTOList = new ArrayList<>();
        gameAssetDTOList.add(new GameAssetDTO(123, 2.0d, 3.0d, 1, "Texture"));
        AddMapObjectRequestDTO e = new AddMapObjectRequestDTO(123L, 2, 3, 2, gameAssetDTOList);

        ArrayList<AddMapObjectRequestDTO> addMapObjectRequestDTOList = new ArrayList<>();
        addMapObjectRequestDTOList.add(e);
        AddMapObjectsRequestDTO value = new AddMapObjectsRequestDTO(addMapObjectRequestDTOList);
        String content = (new ObjectMapper()).writeValueAsString(value);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/mapobject/{map_id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link MapObjectRestController#postMapObject(AddMapObjectsRequestDTO, long)}
     */
    @Test
    void testPostMapObject3 () throws Exception {
        when(mapObjectServiceImpl.createMapObject((AddMapObjectsRequestDTO) any(), anyLong())).thenReturn(1L);

        ArrayList<GameAssetDTO> gameAssetDTOList = new ArrayList<>();
        gameAssetDTOList.add(new GameAssetDTO(123, 2.0d, 3.0d, 2, "?"));
        gameAssetDTOList.add(new GameAssetDTO(123, 2.0d, 3.0d, 1, "Texture"));
        AddMapObjectRequestDTO e = new AddMapObjectRequestDTO(123L, 2, 3, 2, gameAssetDTOList);

        ArrayList<AddMapObjectRequestDTO> addMapObjectRequestDTOList = new ArrayList<>();
        addMapObjectRequestDTOList.add(e);
        AddMapObjectsRequestDTO value = new AddMapObjectsRequestDTO(addMapObjectRequestDTOList);
        String content = (new ObjectMapper()).writeValueAsString(value);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/mapobject/{map_id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mapObjectRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }
}

