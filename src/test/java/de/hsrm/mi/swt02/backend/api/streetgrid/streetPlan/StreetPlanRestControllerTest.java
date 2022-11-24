package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
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
public class StreetPlanRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private StreetPlanRestController streetPlanRestController;

    @Autowired
    private StreetPlanService streetPlanService;

    @Autowired
    private StreetPlanRepository streetPlanRepository;

    @Test
    void PreTest() {
        assertThat(streetPlanRestController).isNotNull();
        assertThat(streetPlanService).isNotNull();
        assertThat(streetPlanRepository).isNotNull();
    }

    @Test
    void postNewStreetPlanAndGetIDTest() throws Exception {
        Object streetPlan = new Object() {
            public final String lobbyName = "TestLobby";
            public final String numOfPlayers = "3";
            public final String lobbyMode = "BUILD_MODE";
            public final String sizeX = "30";
            public final String sizeY = "20";
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

        streetPlanService.deleteStreetPlanById(Long.parseLong(resultStreetPlan.getResponse().getContentAsString()));

    }

    @Test
    void getStreetPlanById() throws Exception {
        long id = streetPlanService.saveStreetPlan(new AddStreetPlanRequestDTO("TestLobby", "3", "BUILD_MODE", "30", "20"));
        StreetPlan testStreetPlan = streetPlanService.getStreetPlanById(id);

        MvcResult mock = mockmvc.perform(
                get("/api/streetplan/" + id)
                        .contentType("application/json")
                        .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.lobbyName", is(testStreetPlan.getLobbyName())))
                .andExpect(jsonPath("$.numOfPlayers", is(testStreetPlan.getNumOfPlayers())))
                .andExpect(jsonPath("$.lobbyMode", is(testStreetPlan.getLobbyMode())))
                .andExpect(jsonPath("$.sizeX", is(testStreetPlan.getSizeX())))
                .andExpect(jsonPath("$.sizeY", is(testStreetPlan.getSizeY())))
                .andReturn();

        streetPlanService.deleteStreetPlanById(id);

    }

    @Test
    void getAllLobbiesTest() throws Exception {
        long id0 = streetPlanService.saveStreetPlan(new AddStreetPlanRequestDTO("TestLobby", "3", "BUILD_MODE", "30", "20"));
        long id1 = streetPlanService.saveStreetPlan(new AddStreetPlanRequestDTO("TestLobby2", "4", "BUILD_MODE", "40", "20"));

        StreetPlan testStreetPlan0 = streetPlanService.getStreetPlanById(id0);
        StreetPlan testStreetPlan1 = streetPlanService.getStreetPlanById(id1);

        MvcResult mock = mockmvc.perform(
                get("/api/streetplan/")
                        .contentType("application/json")
                        .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.[0].lobbyName", is(testStreetPlan0.getLobbyName())))
                .andExpect(jsonPath("$.[0].numOfPlayers", is(testStreetPlan0.getNumOfPlayers())))
                .andExpect(jsonPath("$.[0].lobbyMode", is(testStreetPlan0.getLobbyMode())))
                .andExpect(jsonPath("$.[0].sizeX", is(testStreetPlan0.getSizeX())))
                .andExpect(jsonPath("$.[0].sizeY", is(testStreetPlan0.getSizeY())))
                .andExpect(jsonPath("$.[1].lobbyName", is(testStreetPlan1.getLobbyName())))
                .andExpect(jsonPath("$.[1].numOfPlayers", is(testStreetPlan1.getNumOfPlayers())))
                .andExpect(jsonPath("$.[1].lobbyMode", is(testStreetPlan1.getLobbyMode())))
                .andExpect(jsonPath("$.[1].sizeX", is(testStreetPlan1.getSizeX())))
                .andExpect(jsonPath("$.[1].sizeY", is(testStreetPlan1.getSizeY())))
                .andReturn();

        streetPlanService.deleteStreetPlanById(id0);
        streetPlanService.deleteStreetPlanById(id1);
    }

    @Test
    void deleteStreetPlanByID() throws Exception {
        long id = streetPlanService.saveStreetPlan(new AddStreetPlanRequestDTO("TestLobby", "3", "BUILD_MODE", "30", "20"));
        StreetPlan testStreetPlan = streetPlanService.getStreetPlanById(id);

        mockmvc.perform(
                delete("/api/streetplan/" + id)
                        .contentType("application/json")
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(streetPlanService.findAllStreetPlans()).isEmpty();
    }
}
