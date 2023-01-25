package de.hsrm.mi.swt02.backend.api.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swt02.backend.api.player.dto.AddPlayerRequestDTO;
import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerServiceImpl;
import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;


@ExtendWith (SpringExtension.class)
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@Testable
@AutoConfigureMockMvc
public class PlayerRestControllerTest {

    @MockBean
    private PlayerServiceImpl playerServiceImpl;

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private PlayerRestController playerRestController;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Method under test: {@link PlayerRestController#containsUsername(List, String)}
     */
    @Test
    void testContainsUsername () {
        assertFalse(playerRestController.containsUsername(new ArrayList<>(), "Name"));
    }

    /**
     * Method under test: {@link PlayerRestController#containsUsername(List, String)}
     */
    @Test
    void testContainsUsername2 () {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("Lobby Name");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(6L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(6L);

        Player player = new Player();
        player.setActiveLobby(lobby);
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(playerPosition);
        player.setUserName("janedoe");
        player.setVersion(6L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(6L);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(6L);

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(6L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("Map Name");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(6L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(map);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(6L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(6L);

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(new Player());
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(6L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(6L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(player2);
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(6L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(playerPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(6L);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player3);
        assertFalse(playerRestController.containsUsername(playerList, "Name"));
    }

    /**
     * Method under test: {@link PlayerRestController#containsUsername(List, String)}
     */
    @Test
    void testContainsUsername3 () {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("Lobby Name");
        lobby.setMap(new Map());
        lobby.setNumOfPlayers(10);
        lobby.setPlayerList(new ArrayList<>());
        lobby.setVersion(6L);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setId(123L);
        playerPosition.setPlayer(new Player());
        playerPosition.setPosX(10.0d);
        playerPosition.setPosY(10.0d);
        playerPosition.setRotation(10.0d);
        playerPosition.setVersion(6L);

        Player player = new Player();
        player.setActiveLobby(lobby);
        player.setHostedLobbys(new ArrayList<>());
        player.setId(123L);
        player.setMapList(new ArrayList<>());
        player.setPassword("iloveyou");
        player.setPlayerPosition(playerPosition);
        player.setUserName("janedoe");
        player.setVersion(6L);

        Lobby lobby1 = new Lobby();
        lobby1.setHost(new Player());
        lobby1.setId(123L);
        lobby1.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby1.setLobbyName("Lobby Name");
        lobby1.setMap(new Map());
        lobby1.setNumOfPlayers(10);
        lobby1.setPlayerList(new ArrayList<>());
        lobby1.setVersion(6L);

        Player player1 = new Player();
        player1.setActiveLobby(new Lobby());
        player1.setHostedLobbys(new ArrayList<>());
        player1.setId(123L);
        player1.setMapList(new ArrayList<>());
        player1.setPassword("iloveyou");
        player1.setPlayerPosition(new PlayerPosition());
        player1.setUserName("janedoe");
        player1.setVersion(6L);

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(6L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("Map Name");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(6L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("Lobby Name");
        lobby2.setMap(map);
        lobby2.setNumOfPlayers(10);
        lobby2.setPlayerList(new ArrayList<>());
        lobby2.setVersion(6L);

        Lobby lobby3 = new Lobby();
        lobby3.setHost(new Player());
        lobby3.setId(123L);
        lobby3.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby3.setLobbyName("Lobby Name");
        lobby3.setMap(new Map());
        lobby3.setNumOfPlayers(10);
        lobby3.setPlayerList(new ArrayList<>());
        lobby3.setVersion(6L);

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setId(123L);
        playerPosition1.setPlayer(new Player());
        playerPosition1.setPosX(10.0d);
        playerPosition1.setPosY(10.0d);
        playerPosition1.setRotation(10.0d);
        playerPosition1.setVersion(6L);

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(6L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(player2);
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(6L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(playerPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(6L);

        Lobby lobby4 = new Lobby();
        lobby4.setHost(new Player());
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("janedoe");
        lobby4.setMap(new Map());
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(5L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(new Player());
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(5L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby4);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(playerPosition3);
        player4.setUserName("janedoe");
        player4.setVersion(5L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(new Player());
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("janedoe");
        lobby5.setMap(new Map());
        lobby5.setNumOfPlayers(10);
        lobby5.setPlayerList(new ArrayList<>());
        lobby5.setVersion(5L);

        Player player5 = new Player();
        player5.setActiveLobby(new Lobby());
        player5.setHostedLobbys(new ArrayList<>());
        player5.setId(123L);
        player5.setMapList(new ArrayList<>());
        player5.setPassword("iloveyou");
        player5.setPlayerPosition(new PlayerPosition());
        player5.setUserName("janedoe");
        player5.setVersion(5L);

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(5L));
        map1.setId(123L);
        map1.setLobby(lobby5);
        map1.setMapName("janedoe");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player5);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(5L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(player4);
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("janedoe");
        lobby6.setMap(map1);
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(5L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(new Player());
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("janedoe");
        lobby7.setMap(new Map());
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(5L);

        PlayerPosition playerPosition4 = new PlayerPosition();
        playerPosition4.setId(123L);
        playerPosition4.setPlayer(new Player());
        playerPosition4.setPosX(10.0d);
        playerPosition4.setPosY(10.0d);
        playerPosition4.setRotation(10.0d);
        playerPosition4.setVersion(5L);

        Player player6 = new Player();
        player6.setActiveLobby(lobby7);
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(playerPosition4);
        player6.setUserName("janedoe");
        player6.setVersion(5L);

        PlayerPosition playerPosition5 = new PlayerPosition();
        playerPosition5.setId(123L);
        playerPosition5.setPlayer(player6);
        playerPosition5.setPosX(10.0d);
        playerPosition5.setPosY(10.0d);
        playerPosition5.setRotation(10.0d);
        playerPosition5.setVersion(5L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby6);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition5);
        player7.setUserName("janedoe");
        player7.setVersion(5L);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player7);
        playerList.add(player3);
        assertFalse(playerRestController.containsUsername(playerList, "Name"));
    }

    /**
     * Method under test: {@link PlayerRestController#getAllPlayers()}
     */
    @Test
    void testGetAllPlayers2 () throws Exception {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
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

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("?");
        lobby2.setMap(map);
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

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(player2);
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(playerPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player3);
        when(playerServiceImpl.findAllPlayers()).thenReturn(playerList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/player");
        MockMvcBuilders.standaloneSetup(playerRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"userId\":123,\"userName\":\"janedoe\"}]"));
    }

    /**
     * Method under test: {@link PlayerRestController#getAllPlayers()}
     */
    @Test
    void testGetAllPlayers3 () throws Exception {
        Lobby lobby = new Lobby();
        lobby.setHost(new Player());
        lobby.setId(123L);
        lobby.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby.setLobbyName("?");
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

        Map map = new Map();
        map.setCreationDate(LocalDate.ofEpochDay(1L));
        map.setId(123L);
        map.setLobby(lobby1);
        map.setMapName("?");
        map.setMapObjects(new ArrayList<>());
        map.setMapOwner(player1);
        map.setSizeX(3);
        map.setSizeY(3);
        map.setVersion(1L);

        Lobby lobby2 = new Lobby();
        lobby2.setHost(player);
        lobby2.setId(123L);
        lobby2.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby2.setLobbyName("?");
        lobby2.setMap(map);
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

        Player player2 = new Player();
        player2.setActiveLobby(lobby3);
        player2.setHostedLobbys(new ArrayList<>());
        player2.setId(123L);
        player2.setMapList(new ArrayList<>());
        player2.setPassword("iloveyou");
        player2.setPlayerPosition(playerPosition1);
        player2.setUserName("janedoe");
        player2.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(player2);
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player3 = new Player();
        player3.setActiveLobby(lobby2);
        player3.setHostedLobbys(new ArrayList<>());
        player3.setId(123L);
        player3.setMapList(new ArrayList<>());
        player3.setPassword("iloveyou");
        player3.setPlayerPosition(playerPosition2);
        player3.setUserName("janedoe");
        player3.setVersion(1L);

        Lobby lobby4 = new Lobby();
        lobby4.setHost(new Player());
        lobby4.setId(123L);
        lobby4.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby4.setLobbyName("?");
        lobby4.setMap(new Map());
        lobby4.setNumOfPlayers(10);
        lobby4.setPlayerList(new ArrayList<>());
        lobby4.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(new Player());
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player4 = new Player();
        player4.setActiveLobby(lobby4);
        player4.setHostedLobbys(new ArrayList<>());
        player4.setId(123L);
        player4.setMapList(new ArrayList<>());
        player4.setPassword("iloveyou");
        player4.setPlayerPosition(playerPosition3);
        player4.setUserName("janedoe");
        player4.setVersion(1L);

        Lobby lobby5 = new Lobby();
        lobby5.setHost(new Player());
        lobby5.setId(123L);
        lobby5.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby5.setLobbyName("?");
        lobby5.setMap(new Map());
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

        Map map1 = new Map();
        map1.setCreationDate(LocalDate.ofEpochDay(1L));
        map1.setId(123L);
        map1.setLobby(lobby5);
        map1.setMapName("?");
        map1.setMapObjects(new ArrayList<>());
        map1.setMapOwner(player5);
        map1.setSizeX(3);
        map1.setSizeY(3);
        map1.setVersion(1L);

        Lobby lobby6 = new Lobby();
        lobby6.setHost(player4);
        lobby6.setId(123L);
        lobby6.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby6.setLobbyName("?");
        lobby6.setMap(map1);
        lobby6.setNumOfPlayers(10);
        lobby6.setPlayerList(new ArrayList<>());
        lobby6.setVersion(1L);

        Lobby lobby7 = new Lobby();
        lobby7.setHost(new Player());
        lobby7.setId(123L);
        lobby7.setLobbyMode(LobbyModeEnum.BUILD_MODE);
        lobby7.setLobbyName("?");
        lobby7.setMap(new Map());
        lobby7.setNumOfPlayers(10);
        lobby7.setPlayerList(new ArrayList<>());
        lobby7.setVersion(1L);

        PlayerPosition playerPosition4 = new PlayerPosition();
        playerPosition4.setId(123L);
        playerPosition4.setPlayer(new Player());
        playerPosition4.setPosX(10.0d);
        playerPosition4.setPosY(10.0d);
        playerPosition4.setRotation(10.0d);
        playerPosition4.setVersion(1L);

        Player player6 = new Player();
        player6.setActiveLobby(lobby7);
        player6.setHostedLobbys(new ArrayList<>());
        player6.setId(123L);
        player6.setMapList(new ArrayList<>());
        player6.setPassword("iloveyou");
        player6.setPlayerPosition(playerPosition4);
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        PlayerPosition playerPosition5 = new PlayerPosition();
        playerPosition5.setId(123L);
        playerPosition5.setPlayer(player6);
        playerPosition5.setPosX(10.0d);
        playerPosition5.setPosY(10.0d);
        playerPosition5.setRotation(10.0d);
        playerPosition5.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby6);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition5);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player7);
        playerList.add(player3);
        when(playerServiceImpl.findAllPlayers()).thenReturn(playerList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/player");
        MockMvcBuilders.standaloneSetup(playerRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"userId\":123,\"userName\":\"janedoe\"},{\"userId\":123,\"userName\":\"janedoe\"}]"));
    }

    /**
     * Method under test: {@link PlayerRestController#getPlayer(long)}
     */
    @Test
    void testGetPlayer () throws Exception {
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
        player6.setPlayerPosition(new PlayerPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(player6);
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby4);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition2);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(player7);
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby3);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(playerPosition3);
        player8.setUserName("janedoe");
        player8.setVersion(1L);
        when(playerServiceImpl.findPlayerById(anyLong())).thenReturn(player8);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/player/{id}", 123L);
        MockMvcBuilders.standaloneSetup(playerRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userId\":123,\"userName\":\"janedoe\"}"));
    }

    /**
     * Method under test: {@link PlayerRestController#getUser(AddPlayerRequestDTO)}
     */
    @Test
    void testGetUser () throws Exception {
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
        player6.setPlayerPosition(new PlayerPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(player6);
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby4);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition2);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(player7);
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby3);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(playerPosition3);
        player8.setUserName("janedoe");
        player8.setVersion(1L);
        when(playerServiceImpl.findPlayerByUsernameAndPassword((String) any(), (String) any())).thenReturn(player8);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/player/login")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new AddPlayerRequestDTO("janedoe", "iloveyou")));
        MockMvcBuilders.standaloneSetup(playerRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userId\":123,\"userName\":\"janedoe\"}"));
    }

    /**
     * Method under test: {@link PlayerRestController#postNewPlayer(AddPlayerRequestDTO)}
     */
    @Test
    void testPostNewPlayer () throws Exception {
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
        player6.setPlayerPosition(new PlayerPosition());
        player6.setUserName("janedoe");
        player6.setVersion(1L);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setId(123L);
        playerPosition2.setPlayer(player6);
        playerPosition2.setPosX(10.0d);
        playerPosition2.setPosY(10.0d);
        playerPosition2.setRotation(10.0d);
        playerPosition2.setVersion(1L);

        Player player7 = new Player();
        player7.setActiveLobby(lobby4);
        player7.setHostedLobbys(new ArrayList<>());
        player7.setId(123L);
        player7.setMapList(new ArrayList<>());
        player7.setPassword("iloveyou");
        player7.setPlayerPosition(playerPosition2);
        player7.setUserName("janedoe");
        player7.setVersion(1L);

        PlayerPosition playerPosition3 = new PlayerPosition();
        playerPosition3.setId(123L);
        playerPosition3.setPlayer(player7);
        playerPosition3.setPosX(10.0d);
        playerPosition3.setPosY(10.0d);
        playerPosition3.setRotation(10.0d);
        playerPosition3.setVersion(1L);

        Player player8 = new Player();
        player8.setActiveLobby(lobby3);
        player8.setHostedLobbys(new ArrayList<>());
        player8.setId(123L);
        player8.setMapList(new ArrayList<>());
        player8.setPassword("iloveyou");
        player8.setPlayerPosition(playerPosition3);
        player8.setUserName("janedoe");
        player8.setVersion(1L);
        when(playerServiceImpl.createPlayer((String) any(), (String) any())).thenReturn(player8);
        when(playerServiceImpl.findAllPlayers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/player")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new AddPlayerRequestDTO("janedoe", "iloveyou")));
        MockMvcBuilders.standaloneSetup(playerRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("123"));
    }
}
