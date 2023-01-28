package de.hsrm.mi.swt02.backend.api.lobby;

import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.lobby.service.LobbyServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ContextConfiguration (classes = {LobbyRestController.class, LobbyServiceImpl.class})
@ExtendWith (SpringExtension.class)
class LobbyRestControllerTest {
    @MockBean
    private LobbyRepository lobbyRepository;

    @Autowired
    private LobbyRestController lobbyRestController;

    @MockBean
    private MapService mapService;

    @MockBean
    private PlayerService playerService;

    /**
     * Method under test: {@link LobbyRestController#deleteLobby(long)}
     */
    @Test
    void testDeleteLobby () throws Exception {
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
        Optional<Lobby> ofResult = Optional.of(lobby3);
        doNothing().when(lobbyRepository).deleteById((Long) any());
        when(lobbyRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/lobby/{id}", 123L);
        MockMvcBuilders.standaloneSetup(lobbyRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link LobbyRestController#getAllLobbys()}
     */
    @Test
    void testGetAllLobbys () throws Exception {
        when(lobbyRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lobby");
        MockMvcBuilders.standaloneSetup(lobbyRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LobbyRestController#getAllLobbys()}
     */
    @Test
    void testGetAllLobbys2 () throws Exception {
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

        Lobby lobby2 = new Lobby();
        lobby2.setHost(new Player());
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("?");
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
        map2.setMapName("?");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(player4);
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(player2);
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("?");
        lobby3.setMap(map2);
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(1L);

        ArrayList<Lobby> lobbyList = new ArrayList<>();
        lobbyList.add(lobby3);
        when(lobbyRepository.findAll()).thenReturn(lobbyList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lobby");
        MockMvcBuilders.standaloneSetup(lobbyRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"lobbyId\":123,\"hostId\":123,\"mapId\":123,\"lobbyName\":\"?\",\"numOfPlayers\":10,\"lobbyModeEnum\":\"BUILD"
                                        + "_MODE\"}]"));
    }

    /**
     * Method under test: {@link LobbyRestController#getAllLobbys()}
     */
    @Test
    void testGetAllLobbys3 () throws Exception {
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

        Lobby lobby2 = new Lobby();
        lobby2.setHost(new Player());
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("?");
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
        map2.setMapName("?");
        map2.setMapObjects(new ArrayList<>());
        map2.setMapOwner(player4);
        map2.setSizeX(3);
        map2.setSizeY(3);
        map2.setVersion(1L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(player2);
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("?");
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
        map3.setMapName("?");
        map3.setMapObjects(new ArrayList<>());
        map3.setMapOwner(new Player());
        map3.setSizeX(3);
        map3.setSizeY(3);
        map3.setVersion(1L);

        Lobby lobby4 = new Lobby();
        lobby4.setHost(player5);
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("?");
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

        Player player8 = new Player();
        player8.setActiveLobby(new Lobby());
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(new ObjectPosition());
        player8.setUserName("janedoe");
        player8.setVersion(1L);

        Map map4 = new Map();
        map4.setCreationDate(null);
        map4.setId(123L);
        map4.setLobby(new Lobby());
        map4.setMapName("?");
        map4.setMapObjects(new ArrayList<>());
        map4.setMapOwner(new Player());
        map4.setSizeX(3);
        map4.setSizeY(3);
        map4.setVersion(1L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(player8);
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("?");
        lobby5.setMap(map4);
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(1L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(new Player());
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("?");
        lobby6.setMap(new Map());
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(1L);

        ObjectPosition objectPosition3 = new ObjectPosition();
        objectPosition3.setId(123L);
        objectPosition3.setMapObjectId(123L);
        objectPosition3.setPlayer(new Player());
        objectPosition3.setPosX(10.0d);
        objectPosition3.setPosY(10.0d);
        objectPosition3.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition3.setVersion(1L);

        Player player9 = new Player();
        player9.setActiveLobby(lobby6);
        player9.setHostedLobbys(new ArrayList<>());
        player9.setId(123L);
        player9.setMapList(new ArrayList<>());
        player9.setPassword("iloveyou");
        player9.setPlayerPosition(objectPosition3);
        player9.setUserName("janedoe");
        player9.setVersion(1L);

        Map map5 = new Map();
        map5.setCreationDate(LocalDate.ofEpochDay(1L));
        map5.setId(123L);
        map5.setLobby(lobby5);
        map5.setMapName("?");
        map5.setMapObjects(new ArrayList<>());
        map5.setMapOwner(player9);
        map5.setSizeX(3);
        map5.setSizeY(3);
        map5.setVersion(1L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(player7);
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("?");
        lobby7.setMap(map5);
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(1L);

        ArrayList<Lobby> lobbyList = new ArrayList<>();
        lobbyList.add(lobby7);
        lobbyList.add(lobby3);
        when(lobbyRepository.findAll()).thenReturn(lobbyList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lobby");
        MockMvcBuilders.standaloneSetup(lobbyRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"lobbyId\":123,\"hostId\":123,\"mapId\":123,\"lobbyName\":\"?\",\"numOfPlayers\":10,\"lobbyModeEnum\":\"BUILD"
                                        + "_MODE\"},{\"lobbyId\":123,\"hostId\":123,\"mapId\":123,\"lobbyName\":\"?\",\"numOfPlayers\":10,\"lobbyModeEnum\":"
                                        + "\"BUILD_MODE\"}]"));
    }

    /**
     * Method under test: {@link LobbyRestController#getAllPlayersFromLobby(long)}
     */
    @Test
    void testGetAllPlayersFromLobby () throws Exception {
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
        Optional<Lobby> ofResult = Optional.of(lobby3);
        when(lobbyRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lobby/get_players/{lobby_id}",
                1L);
        MockMvcBuilders.standaloneSetup(lobbyRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LobbyRestController#getLobby(long)}
     */
    @Test
    void testGetLobby () throws Exception {
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
        Optional<Lobby> ofResult = Optional.of(lobby3);
        when(lobbyRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lobby/{id}", 123L);
        MockMvcBuilders.standaloneSetup(lobbyRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"lobbyId\":123,\"hostId\":123,\"mapId\":123,\"lobbyName\":\"Lobby Name\",\"numOfPlayers\":10,\"lobbyModeEnum\":"
                                        + "\"BUILD_MODE\"}"));
    }
}

