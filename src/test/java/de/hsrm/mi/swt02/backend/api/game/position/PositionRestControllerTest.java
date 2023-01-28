package de.hsrm.mi.swt02.backend.api.game.position;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swt02.backend.api.game.position.dto.AddObjectPositionDTO;
import de.hsrm.mi.swt02.backend.api.game.position.repository.PositionRepository;
import de.hsrm.mi.swt02.backend.api.game.position.service.PositionServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.repository.GameAssetRepository;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectTypeRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectTypeServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerServiceImpl;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import de.hsrm.mi.swt02.backend.domain.map.ObjectTypeEnum;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
import de.hsrm.mi.swt02.backend.domain.position.Rotation;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;

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

@ContextConfiguration (classes = {PositionRestController.class, MapObjectServiceImpl.class,
        MapObjectTypeServiceImpl.class, PlayerServiceImpl.class, PositionServiceImpl.class})
@ExtendWith (SpringExtension.class)
class PositionRestControllerTest {
    @MockBean
    private GameAssetRepository gameAssetRepository;

    @MockBean
    private MapObjectRepository mapObjectRepository;

    @MockBean
    private MapObjectTypeRepository mapObjectTypeRepository;

    @MockBean
    private MapService mapService;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private PositionRepository positionRepository;

    @Autowired
    private PositionRestController positionRestController;

    /**
     * Method under test: {@link PositionRestController#deletePlayerPosition(long)}
     */
    @Test
    void testDeletePlayerPosition () throws Exception {
        doNothing().when(positionRepository).deleteById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/position/{id}", 123L);
        MockMvcBuilders.standaloneSetup(positionRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PositionRestController#deletePlayerPosition(long)}
     */
    @Test
    void testDeletePlayerPosition2 () throws Exception {
        doNothing().when(positionRepository).deleteById((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/position/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(positionRestController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PositionRestController#getPlayerPositions()}
     */
    @Test
    void testGetPlayerPositions () throws Exception {
        when(positionRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/position");
        MockMvcBuilders.standaloneSetup(positionRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PositionRestController#getPlayerPositions()}
     */
    @Test
    void testGetPlayerPositions2 () throws Exception {
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

        ObjectPosition objectPosition1 = new ObjectPosition();
        objectPosition1.setId(123L);
        objectPosition1.setMapObjectId(123L);
        objectPosition1.setPlayer(player2);
        objectPosition1.setPosX(10.0d);
        objectPosition1.setPosY(10.0d);
        objectPosition1.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition1.setVersion(1L);

        ArrayList<ObjectPosition> objectPositionList = new ArrayList<>();
        objectPositionList.add(objectPosition1);
        when(positionRepository.findAll()).thenReturn(objectPositionList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/position");
        MockMvcBuilders.standaloneSetup(positionRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"positionId\":123,\"objectTypeId\":123,\"posX\":10.0,\"posZ\":10.0,\"posRotation\":[10.0,10.0,10.0,10.0]}]"));
    }

    /**
     * Method under test: {@link PositionRestController#getPlayerPositions()}
     */
    @Test
    void testGetPlayerPositions3 () throws Exception {
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

        ObjectPosition objectPosition1 = new ObjectPosition();
        objectPosition1.setId(123L);
        objectPosition1.setMapObjectId(123L);
        objectPosition1.setPlayer(player2);
        objectPosition1.setPosX(10.0d);
        objectPosition1.setPosY(10.0d);
        objectPosition1.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition1.setVersion(1L);

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
        map1.setMapName("?");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(new Player());
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(player3);
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("?");
        lobby1.setMap(map1);
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(new Lobby());
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(new ObjectPosition());
        player4.setUserName("janedoe");
        player4.setVersion(1L);

        ObjectPosition objectPosition2 = new ObjectPosition();
        objectPosition2.setId(123L);
        objectPosition2.setMapObjectId(123L);
        objectPosition2.setPlayer(player4);
        objectPosition2.setPosX(10.0d);
        objectPosition2.setPosY(10.0d);
        objectPosition2.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition2.setVersion(1L);

        Player player5 = new Player();
        player5.setActiveLobby(lobby1);
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(objectPosition2);
        player5.setUserName("janedoe");
        player5.setVersion(1L);

        ObjectPosition objectPosition3 = new ObjectPosition();
        objectPosition3.setId(123L);
        objectPosition3.setMapObjectId(123L);
        objectPosition3.setPlayer(player5);
        objectPosition3.setPosX(10.0d);
        objectPosition3.setPosY(10.0d);
        objectPosition3.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition3.setVersion(1L);

        ArrayList<ObjectPosition> objectPositionList = new ArrayList<>();
        objectPositionList.add(objectPosition3);
        objectPositionList.add(objectPosition1);
        when(positionRepository.findAll()).thenReturn(objectPositionList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/position");
        MockMvcBuilders.standaloneSetup(positionRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"positionId\":123,\"objectTypeId\":123,\"posX\":10.0,\"posZ\":10.0,\"posRotation\":[10.0,10.0,10.0,10.0]},{"
                                        + "\"positionId\":123,\"objectTypeId\":123,\"posX\":10.0,\"posZ\":10.0,\"posRotation\":[10.0,10.0,10.0,10.0]}]"));
    }

    /**
     * Method under test: {@link PositionRestController#postObjectPosition(AddObjectPositionDTO, long)}
     */
    @Test
    void testPostObjectPosition () throws Exception {
        MapObjectType mapObjectType = new MapObjectType();
        mapObjectType.setGroupId(123L);
        mapObjectType.setModel3d("Model3d");
        mapObjectType.setName("Name");
        mapObjectType.setObjectTypeId(123L);
        mapObjectType.setRotation(1);
        mapObjectType.setTexture("Texture");
        mapObjectType.setType(ObjectTypeEnum.STREET);
        mapObjectType.setVersion(1L);
        Optional<MapObjectType> ofResult = Optional.of(mapObjectType);
        when(mapObjectTypeRepository.findById((Long) any())).thenReturn(ofResult);

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

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(1L);

        ObjectPosition objectPosition = new ObjectPosition();
        objectPosition.setId(123L);
        objectPosition.setMapObjectId(123L);
        objectPosition.setPlayer(new Player());
        objectPosition.setPosX(10.0d);
        objectPosition.setPosY(10.0d);
        objectPosition.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition.setVersion(1L);

        Player player1 = new Player();
        player1.setActiveLobby(lobby1);
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(objectPosition);
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
        mapObject.setCenterX3d(1);
        mapObject.setCenterZ3d(1);
        mapObject.setGameAssets(new ArrayList<>());
        mapObject.setId(123L);
        mapObject.setMap(map1);
        mapObject.setObjectTypeId(123L);
        mapObject.setRotation(1);
        mapObject.setVersion(1L);
        mapObject.setX(2);
        mapObject.setY(3);
        Optional<MapObject> ofResult1 = Optional.of(mapObject);
        when(mapObjectRepository.findById((Long) any())).thenReturn(ofResult1);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/position/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new AddObjectPositionDTO(123L, 2.0d, 10.0d, new Rotation(10.0d, 10.0d, 10.0d, " order"))));
        MockMvcBuilders.standaloneSetup(positionRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

