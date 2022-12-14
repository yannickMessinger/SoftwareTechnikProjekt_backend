// package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;
//
// import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
// import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
// import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddMapRequestDTO;
// import org.junit.jupiter.api.Test;
// import org.junit.platform.commons.annotation.Testable;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;
//
// import static org.assertj.core.api.Assertions.assertThat;
//
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Testable
// public class StreetPlanServiceTest {
//
//     @Autowired
//     private MapService streetPlanService;
//
//     @Autowired
//     private MapRepository streetPlanRepository;
//
//     private List<Map> initDB() {
//         streetPlanRepository.deleteAll();
//         List<Map> streePlanList = new ArrayList<>();
//
//         streePlanList.add(streetPlanRepository.save(new Map("TestLobby", 3, LobbyModeEnum.BUILD_MODE, 30, 20)));
//         streePlanList.add(streetPlanRepository.save(new Map("TestLobby2", 2, LobbyModeEnum.BUILD_MODE, 30, 60)));
//         streePlanList.add(streetPlanRepository.save(new Map("TestLobby3", 4, LobbyModeEnum.BUILD_MODE, 20, 10)));
//
//         return streePlanList;
//     }
//
//     @Test
//     void PreTest() {
//         assertThat(streetPlanService).isNotNull();
//         assertThat(streetPlanRepository).isNotNull();
//     }
//
//     @Test
//     void createStreetPlanTest() {
//         //Setup
//         streetPlanRepository.deleteAll();
//         long id0 = streetPlanService.saveStreetPlan(new AddMapRequestDTO("TestLobby0", 3, LobbyModeEnum.BUILD_MODE, 70, 20));
//         long id1 = streetPlanService.saveStreetPlan(new AddMapRequestDTO("TestLobby1", 3, LobbyModeEnum.BUILD_MODE, 20, 20));
//
//         //Test
//         assertThat(streetPlanRepository.count()).isEqualTo(2);
//
//         assertThat(streetPlanRepository.findById(id0).orElseThrow()).isEqualTo(streetPlanService.getStreetPlanById(id0));
//         assertThat(streetPlanRepository.findById(id1).orElseThrow()).isEqualTo(streetPlanService.getStreetPlanById(id1));
//     }
//
//     @Test
//     void deleteStreetPlanTest() {
//         //Setup
//         List<Map> streetPlanList = initDB();
//
//         //Test
//         for (var streetPlan : streetPlanList) {
//             streetPlanService.deleteStreetPlanById(streetPlan.getId());
//             assertThat(streetPlanRepository.findById(streetPlan.getId()).isPresent()).isFalse();
//         }
//         assertThat(streetPlanRepository.count()).isZero();
//     }
//
//     @Test
//     void findAllStreetPlansTest() {
//         //Setup
//         List<Map> streetPlanList = initDB();
//         List<Map> streetPlanListFromService = streetPlanService.findAllStreetPlans();
//
//         Iterator<Map> iterator = streetPlanList.iterator();
//
//         //Test
//         for (var streetPlanFromService : streetPlanListFromService) {
//             Map streetPlan = iterator.next();
//
//             assertThat(streetPlanFromService.getNumOfPlayers()).isEqualTo(streetPlan.getNumOfPlayers());
//             assertThat(streetPlanFromService.getId()).isEqualTo(streetPlan.getId());
//             assertThat(streetPlanFromService.getLobbyMode()).isEqualTo(streetPlan.getLobbyMode());
//             assertThat(streetPlanFromService.getLobbyName()).isEqualTo(streetPlan.getLobbyName());
//             assertThat(streetPlanFromService.getSizeX()).isEqualTo(streetPlan.getSizeX());
//             assertThat(streetPlanFromService.getSizeY()).isEqualTo(streetPlan.getSizeY());
//
//         }
//     }
//
//     @Test
//     void findStreetPlanByIdTest() {
//         //Setup
//         List<Map> streetPlanList = initDB();
//
//         //Test
//         for (var streetPlan : streetPlanList) {
//             assertThat(streetPlanService.getStreetPlanById(streetPlan.getId())).isEqualTo(streetPlan);
//         }
//     }
//
//
// }
