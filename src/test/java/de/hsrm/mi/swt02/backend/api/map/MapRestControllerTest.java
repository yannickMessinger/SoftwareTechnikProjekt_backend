package de.hsrm.mi.swt02.backend.api.map;
// package de.hsrm.mi.swt02.backend.api.map.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @Autowired
    private PlayerRepository playerReposoitory;

    @Autowired
    private LobbyRepository lobbyRepository;

    @Test
    void PreTest () {
        assertThat(mapRestController).isNotNull();
        assertThat(mapService).isNotNull();
        assertThat(mapRepository).isNotNull();
    }

    @Test
    void postNewMapAndGetIDTest () throws Exception {
        Object map = new Object() {
            public final String mapName = "Testmap";
            public final LocalDate creationDate = LocalDate.of(2022, 12, 24);
            public final int sizeX = 30;
            public final int sizeY = 20;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMap = objectMapper.writeValueAsString(map);

        MvcResult resultMap = mockmvc.perform(
                        post("/api/map")
                                .contentType("application/json")
                                .content(jsonMap)
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        mapService.deleteMapById(Long.parseLong(resultMap.getResponse().getContentAsString()));

    }

    @Test
    void getMapById () throws Exception {

        // setup
        Player mapOwner = new Player("Testowner", "123");
        mapOwner.setId(1);
        long id = mapService.saveMap(new AddMapRequestDTO("Testmap", LocalDate.of(2022, 12, 24), 30, 20, 1));
        Map testMap = mapService.getMapById(id);
        testMap.setMapOwner(mapOwner);

        MvcResult mock = mockmvc.perform(
                        get("/api/map/" + id)
                                .contentType("application/json")
                                .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.mapName", is(testMap.getMapName())))
                // .andExpect(jsonPath("$.creationDate", is(testMap.getCreationDate())))
                .andExpect(jsonPath("$.sizeX", is(testMap.getSizeX())))
                .andExpect(jsonPath("$.sizeY", is(testMap.getSizeY())))
                // .andExpect(jsonPath("$.mapId", is(testMap.getId())))
                .andReturn();

        mapService.deleteMapById(id);

    }

    @Test
    void deleteMapByID () throws Exception {
        long id = mapService.saveMap(new AddMapRequestDTO("Testmap", LocalDate.of(2022, 12, 24), 30, 20, 1));
        Map testMap = mapService.getMapById(id);

        mockmvc.perform(
                        delete("/api/map/" + id)
                                .contentType("application/json")
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mapService.findAllMaps()).isEmpty();
    }

    @Test
    void assignMapToLobby () throws Exception {
        // setup
        Player testHost = new Player("testhost", "123");
        playerReposoitory.save(testHost);

        Lobby testLobby = new Lobby("Testlobby", 0, LobbyModeEnum.PLAY_MODE);
        testLobby.setHost(testHost);
        lobbyRepository.save(testLobby);

        Object map = new Object() {
            public final String mapName = "Testmap";
            public final LocalDate creationDate = null;
            public final int sizeX = 30;
            public final int sizeY = 20;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMap = objectMapper.writeValueAsString(map);

        MvcResult res = mockmvc.perform(
                        post("/api/map/" + testLobby.getId())
                                .contentType("application/json")
                                .content(jsonMap)
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())

                .andReturn();

        assertThat(mapService.getMapById(Long.parseLong(res.getResponse().getContentAsString())).getLobby()
                .equals(testLobby));
    }

}
