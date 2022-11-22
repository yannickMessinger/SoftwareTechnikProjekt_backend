package de.hsrm.mi.swt02.backend.api.lobby;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testable
@AutoConfigureMockMvc
public class LobbyRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LobbyRestController lobbyRestController;

    @Autowired
    private LobbyService lobbyService;

    @Autowired
    private LobbyRepository lobbyRepository;

    @Test
    void PreTest() {
        assertNotNull(lobbyRestController);
        assertNotNull(lobbyService);
        assertNotNull(lobbyRepository);
    }

    @Test
    void postNewLobbyAndGetIDTest() throws Exception {

        // setup
        var testObject = new Object() {
            public final String lobbyName = "TestLobby";
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String testObjectJSON = objectMapper.writeValueAsString(testObject);

        // exercise
        MvcResult result = mockMvc.perform(
                        post("/api/lobby")
                                .contentType("application/json")
                                .content(testObjectJSON))
                .andExpect(status().isOk()).andReturn();

        // validate
        assertNotNull(result);
        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).containsOnlyDigits();

        // cleanup
        lobbyService.deleteLobby(Long.parseLong(resultString));
    }

    @Test
    void getAllLobbies() throws Exception {

        // setup
        ObjectMapper mapper = new ObjectMapper();
        Lobby lobby1 = new Lobby("Lobby 1", 1, LobbyMode.BUILD_MODE);
        Lobby lobby2 = new Lobby("Lobby 2", 3, LobbyMode.PLAY_MODE);

        long lobby1ID = lobbyService.createLobby(
                lobby1.getLobbyName(),
                lobby1.getNumOfPlayers(),
                lobby1.getLobbyMode());
        long lobby2ID = lobbyService.createLobby(
                lobby2.getLobbyName(),
                lobby2.getNumOfPlayers(),
                lobby2.getLobbyMode()
        );

        // exercise
        MvcResult mock = mockMvc.perform(
                        get("/api/lobby")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        Lobby[] lobbies = mapper.readValue(
                mock.getResponse().getContentAsString(),
                Lobby[].class);

        // validate
        assertThat(lobbies[0].getLobbyName()).isEqualTo(lobby1.getLobbyName());
        assertThat(lobbies[0].getNumOfPlayers()).isEqualTo(lobby1.getNumOfPlayers());
        assertThat(lobbies[0].getLobbyMode()).isEqualTo(lobby1.getLobbyMode());
        assertThat(lobbies[1].getLobbyName()).isEqualTo(lobby2.getLobbyName());
        assertThat(lobbies[1].getNumOfPlayers()).isEqualTo(lobby2.getNumOfPlayers());
        assertThat(lobbies[1].getLobbyMode()).isEqualTo(lobby2.getLobbyMode());

        // cleanup
        lobbyService.deleteLobby(lobby1ID);
        lobbyService.deleteLobby(lobby2ID);
    }

    @Test
    void getLobbyWithID() throws Exception {

        // setup
        ObjectMapper mapper = new ObjectMapper();
        Lobby testLobby = new Lobby("TestLobby", 3, LobbyMode.PLAY_MODE);
        long testLobbyID = lobbyService.createLobby(
                testLobby.getLobbyName(),
                testLobby.getNumOfPlayers(),
                testLobby.getLobbyMode());

        // exercise
        MvcResult mock = mockMvc.perform(
                        get("/api/lobby")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        Lobby[] lobby = mapper.readValue(
                mock.getResponse().getContentAsString(),
                Lobby[].class);

        // validate
        assertThat(lobby[0].getLobbyName()).isEqualTo(testLobby.getLobbyName());
        assertThat(lobby[0].getNumOfPlayers()).isEqualTo(testLobby.getNumOfPlayers());
        assertThat(lobby[0].getLobbyMode()).isEqualTo(testLobby.getLobbyMode());

        // cleanup
        lobbyService.deleteLobby(testLobbyID);
    }

    @Test
    void deleteLobbyWithId() throws Exception {

        // setup
        Lobby testLobby = new Lobby("TestLobby", 3, LobbyMode.PLAY_MODE);
        long testLobbyID = lobbyService.createLobby(
                testLobby.getLobbyName(),
                testLobby.getNumOfPlayers(),
                testLobby.getLobbyMode());

        //delete user with ID
        mockMvc.perform(
                        delete("/api/lobby/" + testLobbyID)
                                .contentType("application/json")
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        //test
        assertThat(lobbyService.findAllLobbys()).isEmpty();
    }
}
