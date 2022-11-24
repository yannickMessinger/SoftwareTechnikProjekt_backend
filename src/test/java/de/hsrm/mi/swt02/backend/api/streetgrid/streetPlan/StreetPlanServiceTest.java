package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos.AddStreetPlanRequestDTO;
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
public class StreetPlanServiceTest {

    @Autowired
    private StreetPlanService streetPlanService;

    @Autowired
    private StreetPlanRepository streetPlanRepository;

    private List<StreetPlan> initDB() {
        streetPlanRepository.deleteAll();
        List<StreetPlan> streePlanList = new ArrayList<>();

        streePlanList.add(streetPlanRepository.save(new StreetPlan("TestLobby", "3", "BUILD_MODE", "30", "20")));
        streePlanList.add(streetPlanRepository.save(new StreetPlan("TestLobby2", "2", "BUILD_MODE", "30", "60")));
        streePlanList.add(streetPlanRepository.save(new StreetPlan("TestLobby3", "4", "BUILD_MODE", "20", "10")));

        return streePlanList;
    }

    @Test
    void PreTest() {
        assertThat(streetPlanService).isNotNull();
        assertThat(streetPlanRepository).isNotNull();
    }

    @Test
    void createStreetPlanTest() {
        //Setup
        streetPlanRepository.deleteAll();
        long id0 = streetPlanService.saveStreetPlan(new AddStreetPlanRequestDTO("TestLobby0", "3", "BUILD_MODE", "70", "20"));
        long id1 = streetPlanService.saveStreetPlan(new AddStreetPlanRequestDTO("TestLobby1", "3", "BUILD_MODE", "20", "20"));

        //Test
        assertThat(streetPlanRepository.count()).isEqualTo(2);

        assertThat(streetPlanRepository.findById(id0).orElseThrow()).isEqualTo(streetPlanService.getStreetPlanById(id0));
        assertThat(streetPlanRepository.findById(id1).orElseThrow()).isEqualTo(streetPlanService.getStreetPlanById(id1));
    }

    @Test
    void deleteStreetPlanTest() {
        //Setup
        List<StreetPlan> streetPlanList = initDB();

        //Test
        for (var streetPlan : streetPlanList) {
            streetPlanService.deleteStreetPlanById(streetPlan.getId());
            assertThat(streetPlanRepository.findById(streetPlan.getId()).isPresent()).isFalse();
        }
        assertThat(streetPlanRepository.count()).isZero();
    }

    @Test
    void findAllStreetPlansTest() {
        //Setup
        List<StreetPlan> streetPlanList = initDB();
        List<StreetPlan> streetPlanListFromService = streetPlanService.findAllStreetPlans();

        Iterator<StreetPlan> iterator = streetPlanList.iterator();

        //Test
        for (var streetPlanFromService : streetPlanListFromService) {
            StreetPlan streetPlan = iterator.next();

            assertThat(streetPlanFromService.getNumOfPlayers()).isEqualTo(streetPlan.getNumOfPlayers());
            assertThat(streetPlanFromService.getId()).isEqualTo(streetPlan.getId());
            assertThat(streetPlanFromService.getLobbyMode()).isEqualTo(streetPlan.getLobbyMode());
            assertThat(streetPlanFromService.getLobbyName()).isEqualTo(streetPlan.getLobbyName());
            assertThat(streetPlanFromService.getSizeX()).isEqualTo(streetPlan.getSizeX());
            assertThat(streetPlanFromService.getSizeY()).isEqualTo(streetPlan.getSizeY());

        }
    }

    @Test
    void findStreetPlanByIdTest() {
        //Setup
        List<StreetPlan> streetPlanList = initDB();

        //Test
        for (var streetPlan : streetPlanList) {
            assertThat(streetPlanService.getStreetPlanById(streetPlan.getId())).isEqualTo(streetPlan);
        }
    }


}
