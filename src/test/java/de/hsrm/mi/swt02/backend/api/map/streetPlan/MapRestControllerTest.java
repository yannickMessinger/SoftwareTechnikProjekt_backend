// package de.hsrm.mi.swt02.backend.api.map.map;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import de.hsrm.mi.swt02.backend.api.map.MapRestController;
// import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
// import de.hsrm.mi.swt02.backend.api.map.repository.MapRepository;
// import de.hsrm.mi.swt02.backend.api.map.service.MapService;
// import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
// import de.hsrm.mi.swt02.backend.domain.map.Map;
// import org.junit.jupiter.api.Test;
// import org.junit.platform.commons.annotation.Testable;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.hamcrest.Matchers.is;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Testable
// @AutoConfigureMockMvc
// public class MapRestControllerTest {

//     @Autowired
//     private MockMvc mockmvc;

//     @Autowired
//     private MapRestController mapRestController;

//     @Autowired
//     private MapService mapService;

//     @Autowired
//     private MapRepository mapRepository;

//     @Test
//     void PreTest() {
//         assertThat(mapRestController).isNotNull();
//         assertThat(mapService).isNotNull();
//         assertThat(mapRepository).isNotNull();
//     }

//     @Test
//     void postNewMapAndGetIDTest() throws Exception {
//         Object map = new Object() {
//             public final String lobbyName = "TestLobby";
//             public final int numOfPlayers = 3;
//             public final LobbyModeEnum lobbyMode = LobbyModeEnum.BUILD_MODE;
//             public final int sizeX = 30;
//             public final int sizeY = 20;
//         };

//         ObjectMapper objectMapper = new ObjectMapper();
//         String jsonMap = objectMapper.writeValueAsString(map);

//         MvcResult resultMap = mockmvc.perform(
//                 post("/api/map")
//                         .contentType("application/json")
//                         .content(jsonMap)
//                         .characterEncoding("utf-8"))
//                 .andExpect(status().isOk())
//                 .andReturn();

//         mapService.deleteMapById(Long.parseLong(resultMap.getResponse().getContentAsString()));

//     }

//     @Test
//     void getMapById() throws Exception {
//         long id = mapService.saveMap(new AddMapRequestDTO("TestLobby", 3, LobbyModeEnum.BUILD_MODE, 30, 20));
//         Map testMap = mapService.getMapById(id);

//         MvcResult mock = mockmvc.perform(
//                 get("/api/map/" + id)
//                         .contentType("application/json")
//                         .characterEncoding("utf-8"))
//                 .andExpect(jsonPath("$.mapName", is(testMap.getLobbyName())))
//                 .andExpect(jsonPath("$.numOfPlayers", is(testMap.getNumOfPlayers())))
//                 .andExpect(jsonPath("$.lobbyModeEnum", is(testMap.getLobbyMode().toString())))
//                 .andExpect(jsonPath("$.sizeX", is(testMap.getSizeX())))
//                 .andExpect(jsonPath("$.sizeY", is(testMap.getSizeY())))
//                 .andReturn();

//         mapService.deleteMapById(id);

//     }

//     @Test
//     void getAllLobbiesTest() throws Exception {
//         long id0 = mapService.saveMap(new AddMapRequestDTO("TestLobby", 3, LobbyModeEnum.BUILD_MODE, 30, 20));
//         long id1 = mapService.saveMap(new AddMapRequestDTO("TestLobby2", 4, LobbyModeEnum.BUILD_MODE, 40, 20));

//         Map testMap0 = mapService.getMapById(id0);
//         Map testMap1 = mapService.getMapById(id1);

//         MvcResult mock = mockmvc.perform(
//                 get("/api/map/")
//                         .contentType("application/json")
//                         .characterEncoding("utf-8"))
//                 .andExpect(jsonPath("$.[0].mapName", is(testMap0.getLobbyName())))
//                 .andExpect(jsonPath("$.[0].numOfPlayers", is(testMap0.getNumOfPlayers())))
//                 .andExpect(jsonPath("$.[0].lobbyModeEnum", is(testMap0.getLobbyMode().toString())))
//                 .andExpect(jsonPath("$.[0].sizeX", is(testMap0.getSizeX())))
//                 .andExpect(jsonPath("$.[0].sizeY", is(testMap0.getSizeY())))
//                 .andExpect(jsonPath("$.[1].mapName", is(testMap1.getLobbyName())))
//                 .andExpect(jsonPath("$.[1].numOfPlayers", is(testMap1.getNumOfPlayers())))
//                 .andExpect(jsonPath("$.[1].lobbyModeEnum", is(testMap1.getLobbyMode().toString())))
//                 .andExpect(jsonPath("$.[1].sizeX", is(testMap1.getSizeX())))
//                 .andExpect(jsonPath("$.[1].sizeY", is(testMap1.getSizeY())))
//                 .andReturn();

//         mapService.deleteMapById(id0);
//         mapService.deleteMapById(id1);
//     }

//     @Test
//     void deleteMapByID() throws Exception {
//         long id = mapService.saveMap(new AddMapRequestDTO("TestLobby", 3, LobbyModeEnum.BUILD_MODE, 30, 20));
//         Map testMap = mapService.getMapById(id);

//         mockmvc.perform(
//                 delete("/api/map/" + id)
//                         .contentType("application/json")
//                         .characterEncoding("utf-8"))
//                 .andExpect(status().isOk())
//                 .andReturn();

//         assertThat(mapService.findAllMaps()).isEmpty();
//     }
// }
