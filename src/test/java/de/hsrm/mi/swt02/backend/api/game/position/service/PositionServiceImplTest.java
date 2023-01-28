package de.hsrm.mi.swt02.backend.api.game.position.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hsrm.mi.swt02.backend.api.game.position.repository.PositionRepository;
import de.hsrm.mi.swt02.backend.api.lobby.service.LobbyServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith (SpringExtension.class)
class PositionServiceImplTest {
    @MockBean
    private LobbyServiceImpl lobbyServiceImpl;

    @MockBean
    private MapObjectServiceImpl mapObjectServiceImpl;

    @MockBean
    private MapService mapService;

    @MockBean
    private SqlDataSourceScriptDatabaseInitializer sqlDataSourceScriptDatabaseInitializer;

    @MockBean
    private PositionRepository positionRepository;

    @Autowired
    private PositionServiceImpl positionServiceImpl;

    /**
     * Method under test: {@link PositionServiceImpl#findAllPositions()}
     */
    @Test
    void testFindAllPositions () {
        assertTrue(positionServiceImpl.findAllPositions().isEmpty());
    }

    /**
     * Method under test: {@link PositionServiceImpl#deletePosition(long)}
     */
    @Test
    void testDeletePosition () {
        doNothing().when(positionRepository).deleteById((Long) any());
        positionServiceImpl.deletePosition(123L);
        verify(positionRepository).deleteById((Long) any());
        assertTrue(positionServiceImpl.findAllPositions().isEmpty());
    }

    /**
     * Method under test: {@link PositionServiceImpl#createPosition(long, double, double, double[])}
     */
    @Test
    void testCreatePosition () {
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

        ObjectPosition objectPosition3 = new ObjectPosition();
        objectPosition3.setId(123L);
        objectPosition3.setMapObjectId(123L);
        objectPosition3.setPlayer(player3);
        objectPosition3.setPosX(10.0d);
        objectPosition3.setPosY(10.0d);
        objectPosition3.setRotation(new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        objectPosition3.setVersion(1L);
        when(positionRepository.save((ObjectPosition) any())).thenReturn(objectPosition3);
        assertSame(objectPosition3,
                positionServiceImpl.createPosition(123L, 2.0d, 3.0d, new double[]{10.0d, 10.0d, 10.0d, 10.0d}));
        verify(positionRepository).save((ObjectPosition) any());
    }

    /**
     * Method under test: {@link PositionServiceImpl#updatePosition(long, double, double, double[])}
     */
    @Test
    void testUpdatePosition () {
        positionServiceImpl.updatePosition(123L, 2.0d, 3.0d, new double[]{10.0d, 10.0d, 10.0d, 10.0d});
        assertTrue(positionServiceImpl.findAllPositions().isEmpty());
    }
}

