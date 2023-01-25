package de.hsrm.mi.swt02.backend.api.lobby.service;

import de.hsrm.mi.swt02.backend.api.game.position.service.PositionServiceImpl;
import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.actuate.endpoint.web.servlet.AdditionalHealthEndpointPathsWebMvcHandlerMapping;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.*;

class LobbyServiceImplTest {
    @Mock
    Logger logger;
    @Mock
    LobbyRepository lobbyRepository;
    @Mock
    PlayerService playerService;
    @Mock
    MapService mapService;
    @InjectMocks
    LobbyServiceImpl lobbyServiceImpl;
    @MockBean
    private AdditionalHealthEndpointPathsWebMvcHandlerMapping additionalHealthEndpointPathsWebMvcHandlerMapping;
    @MockBean
    private PositionServiceImpl positionServiceImpl;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLobby () {
        when(playerService.findPlayerById(anyLong())).thenReturn(new Player("userName", "password"));
        when(mapService.createNewMap()).thenReturn(new Map("mapName", LocalDate.of(2023, Month.JANUARY, 25), 0, 0));
        when(lobbyRepository.save(any(Lobby.class))).thenReturn(new Lobby("lobbyName", 0, LobbyModeEnum.BUILD_MODE));

        long result = lobbyServiceImpl.createLobby("lobbyName", LobbyModeEnum.BUILD_MODE, 0, 0L);
        Assertions.assertEquals(0L, result);
    }

    @Test
    void testUpdateLobby () {
        lobbyServiceImpl.updateLobby(0L);
    }

    @Test
    void testSaveEditedLobby () {
        lobbyServiceImpl.saveEditedLobby(new Lobby("lobbyName", 0, LobbyModeEnum.BUILD_MODE));
    }
}