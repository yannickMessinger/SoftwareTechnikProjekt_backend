package de.hsrm.mi.swt02.backend.api.map.streetPlan;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsrm.mi.swt02.backend.api.map.MapRestController;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testable
@AutoConfigureMockMvc
public class MapRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private MapRestController mapRestController;

    @Autowired
    private MapService mapService;

    @Autowired
    private MapRepository mapRepository;

    @Test
    void PreTest() {
        assertThat(mapRestController).isNotNull();
        assertThat(mapService).isNotNull();
        assertThat(mapRepository).isNotNull();
    }

    @Test
    void postNewStreetPlanAndGetIDTest() throws Exception {
        Object streetPlan = new Object() {
            public final String lobbyName = "TestLobby";
            public final int numOfPlayers = 3;
            public final LobbyModeEnum lobbyMode = LobbyModeEnum.BUILD_MODE;
            public final int sizeX = 30;
            public final int sizeY = 20;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStreetPlan = objectMapper.writeValueAsString(streetPlan);

        MvcResult resultStreetPlan = mockmvc.perform(
                post("/api/streetplan")
                        .contentType("application/json")
                        .content(jsonStreetPlan)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        mapService.deleteStreetPlanById(Long.parseLong(resultStreetPlan.getResponse().getContentAsString()));

    }

    @Test
    void getStreetPlanById() throws Exception {
        long id = mapService.saveStreetPlan(new AddMapRequestDTO("TestLobby", 3, LobbyModeEnum.BUILD_MODE, 30, 20));
        Map testStreetPlan = mapService.getStreetPlanById(id);

        MvcResult mock = mockmvc.perform(
                get("/api/streetplan/" + id)
                        .contentType("application/json")
                        .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.mapName", is(testStreetPlan.getLobbyName())))
                .andExpect(jsonPath("$.numOfPlayers", is(testStreetPlan.getNumOfPlayers())))
                .andExpect(jsonPath("$.lobbyModeEnum", is(testStreetPlan.getLobbyMode().toString())))
                .andExpect(jsonPath("$.sizeX", is(testStreetPlan.getSizeX())))
                .andExpect(jsonPath("$.sizeY", is(testStreetPlan.getSizeY())))
                .andReturn();

        mapService.deleteStreetPlanById(id);

    }

    @Test
    void getAllLobbiesTest() throws Exception {
        long id0 = mapService.saveStreetPlan(new AddMapRequestDTO("TestLobby", 3, LobbyModeEnum.BUILD_MODE, 30, 20));
        long id1 = mapService.saveStreetPlan(new AddMapRequestDTO("TestLobby2", 4, LobbyModeEnum.BUILD_MODE, 40, 20));

        Map testStreetPlan0 = mapService.getStreetPlanById(id0);
        Map testStreetPlan1 = mapService.getStreetPlanById(id1);

        MvcResult mock = mockmvc.perform(
                get("/api/streetplan/")
                        .contentType("application/json")
                        .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.[0].mapName", is(testStreetPlan0.getLobbyName())))
                .andExpect(jsonPath("$.[0].numOfPlayers", is(testStreetPlan0.getNumOfPlayers())))
                .andExpect(jsonPath("$.[0].lobbyModeEnum", is(testStreetPlan0.getLobbyMode().toString())))
                .andExpect(jsonPath("$.[0].sizeX", is(testStreetPlan0.getSizeX())))
                .andExpect(jsonPath("$.[0].sizeY", is(testStreetPlan0.getSizeY())))
                .andExpect(jsonPath("$.[1].mapName", is(testStreetPlan1.getLobbyName())))
                .andExpect(jsonPath("$.[1].numOfPlayers", is(testStreetPlan1.getNumOfPlayers())))
                .andExpect(jsonPath("$.[1].lobbyModeEnum", is(testStreetPlan1.getLobbyMode().toString())))
                .andExpect(jsonPath("$.[1].sizeX", is(testStreetPlan1.getSizeX())))
                .andExpect(jsonPath("$.[1].sizeY", is(testStreetPlan1.getSizeY())))
                .andReturn();

        mapService.deleteStreetPlanById(id0);
        mapService.deleteStreetPlanById(id1);
    }

    @Test
    void deleteStreetPlanByID() throws Exception {
        long id = mapService.saveStreetPlan(new AddMapRequestDTO("TestLobby", 3, LobbyModeEnum.BUILD_MODE, 30, 20));
        Map testStreetPlan = mapService.getStreetPlanById(id);

        mockmvc.perform(
                delete("/api/streetplan/" + id)
                        .contentType("application/json")
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mapService.findAllStreetPlans()).isEmpty();
    }
}
