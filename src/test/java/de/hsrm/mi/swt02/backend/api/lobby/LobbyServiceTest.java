package de.hsrm.mi.swt02.backend.api.lobby;

import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.lobby.service.LobbyService;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testable
public class LobbyServiceTest {

    @Autowired
    private LobbyService lobbyService;

    @Autowired
    private LobbyRepository lobbyRepository;

    @Test
    void preTest() {
        assertThat(lobbyService).isNotNull();
        assertThat(lobbyRepository).isNotNull();
    }

    @BeforeEach
    void setup() {
        lobbyRepository.deleteAll();
    }

    @Test
    void CreateLobbyTest() {

        // setup
        Lobby lobby1 = new Lobby(
                "Lobby1",
                1,
                LobbyModeEnum.PLAY_MODE);
        Lobby lobby2 = new Lobby(
                "Lobby2",
                2,
                LobbyModeEnum.BUILD_MODE);

        // exercise
        long lobby1ID = lobbyService.createLobby("Lobby1", LobbyModeEnum.PLAY_MODE,1,1);
        long lobby2ID = lobbyService.createLobby( "Lobby2", LobbyModeEnum.BUILD_MODE,2,1);

        // validate
        assertThat(lobbyRepository.count()).isEqualTo(2);
        assertThat(lobbyRepository
                .findById(lobby1ID)
                .orElseThrow()
                .toString())
                    .isEqualTo(lobby1.toString());
        assertThat(lobbyRepository
                .findById(lobby2ID)
                .orElseThrow()
                .toString())
                    .isEqualTo(lobby2.toString());
    }

    @Test
    void DeleteLobbyTest() {

        // setup
        List<Lobby> lobbyList = initDB();

        // exercise
        for (var lobby: lobbyList) {
            lobbyService.deleteLobby(lobby.getId());

        // verify
            assertThat(lobbyRepository
                    .findById(lobby.getId())
                    .isEmpty())
                    .isTrue();
        }
        assertThat(lobbyRepository.count()).isZero();
    }

    @Test
    void FindAllPlayers() {

        // setup
        List<Lobby> lobbyList = initDB();
        List<Lobby> lobbiesFromService = lobbyService.findAllLobbys();

        Iterator<Lobby> iterator = lobbyList.iterator();

        // exercise
        for (var lobbyFromService: lobbiesFromService) {
            Lobby lobby = iterator.next();

        // validate
            assertThat(lobbyFromService.getLobbyName()).isEqualTo(lobby.getLobbyName());
            assertThat(lobbyFromService.getNumOfPlayers()).isEqualTo(lobby.getNumOfPlayers());
            assertThat(lobbyFromService.getLobbyMode()).isEqualTo(lobby.getLobbyMode());
        }
    }

    @Test
    void FindPlayersByIDTest() {

        // setup
        List<Lobby> lobbies = initDB();

        // exercise
        for (var lobby: lobbies) {

        // validate
            assertThat(lobbyService
                    .findLobbyById(
                        lobby
                            .getId())
                            .toString())
                    .isEqualTo(lobby.toString());
        }
    }

    private List<Lobby> initDB() {

        List<Lobby> lobbyList = new ArrayList<>();
        int i = 1;

        for (var name: List.of("Lobby1, Lobby2")) {
            Lobby lobby = new Lobby(
                    name, i++, LobbyModeEnum.BUILD_MODE);
            lobbyList.add(lobbyRepository.save(lobby));
        }
        return lobbyList;
    }
}
