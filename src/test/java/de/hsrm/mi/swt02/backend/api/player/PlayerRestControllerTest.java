package de.hsrm.mi.swt02.backend.api.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@Testable
@AutoConfigureMockMvc
public class PlayerRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private PlayerRestController playerRestController;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void PreTest () {
        assertThat(playerRestController).isNotNull();
        assertThat(playerService).isNotNull();
        assertThat(playerRepository).isNotNull();
    }

    @Test
    void postNewPlayerAndGetIDTest () throws Exception {
        //setup Json Objects
        Object Yannick = new Object() {
            public final String userName = "Yannick";
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonYannick = objectMapper.writeValueAsString(Yannick);

        //Post userNames and get userID back
        MvcResult resultUserYannick = mockmvc.perform(
                        post("/api/player")
                                .contentType("application/json")
                                .content(jsonYannick)
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        String yannickID = resultUserYannick.getResponse().getContentAsString();

        //test that ID are a number
        assertThat(yannickID).containsOnlyDigits();

        playerService.deletePlayer(Long.parseLong(yannickID));
    }

    @Test
    void getAllPlayersTest () throws Exception {
        //Setup
        Player marc = playerService.createPlayer("Marc", "password");
        Player yannick = playerService.createPlayer("Yannick", "password");

        //get all Users
        mockmvc.perform(
                        get("/api/player")
                                .contentType("application/json")
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].userName", is(marc.getUserName())))
                .andExpect(jsonPath("$.[0].userId", is((int) marc.getId())))
                .andExpect(jsonPath("$.[1].userName", is(yannick.getUserName())))
                .andExpect(jsonPath("$.[1].userId", is((int) yannick.getId())))
                .andReturn();

        playerService.deletePlayer(marc.getId());
        playerService.deletePlayer(yannick.getId());
    }

    @Test
    void getPlayerWithId () throws Exception {
        //Setup
        Player marc = playerService.createPlayer("marc", "password");

        //get user with ID
        mockmvc.perform(
                        get("/api/player/" + marc.getId())
                                .contentType("application/json")
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(marc.getUserName())))
                .andExpect(jsonPath("$.userId", is((int) marc.getId())))
                .andReturn();

        //test
        playerService.deletePlayer(marc.getId());
    }

    @Test
    void deletePlayerWithId () throws Exception {
        //Setup
        Player marc = playerService.createPlayer("marc", "password");

        //delete user with ID
        mockmvc.perform(
                        delete("/api/player/" + marc.getId())
                                .contentType("application/json")
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        //test
        assertThat(playerService.findAllPlayers()).isEmpty();

    }
}
