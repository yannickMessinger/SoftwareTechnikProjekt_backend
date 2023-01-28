package de.hsrm.mi.swt02.backend.api.map;


import de.hsrm.mi.swt02.backend.api.map.dto.AddMapRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testable
public class MapServiceTest {

    @Autowired
    private MapService mapService;

    @Autowired
    private MapRepository streetPlanRepository;

    private List<Map> initDB () {
        streetPlanRepository.deleteAll();
        List<Map> mapList = new ArrayList<>();

        mapList.add(streetPlanRepository.save(new Map("Testmap1", LocalDate.of(2022, 12, 24), 30, 20)));
        mapList.add(streetPlanRepository.save(new Map("Testmap2", LocalDate.of(2022, 12, 24), 30, 20)));
        mapList.add(streetPlanRepository.save(new Map("Testmap3", LocalDate.of(2022, 12, 24), 30, 20)));
        return mapList;
    }

    @Test
    void PreTest () {
        assertThat(mapService).isNotNull();
        assertThat(streetPlanRepository).isNotNull();
    }

    @Test
    void createStreetPlanTest () {
        //Setup
        streetPlanRepository.deleteAll();
        long id0 = mapService.saveMap(new AddMapRequestDTO("Testmap1", LocalDate.of(2022, 12, 24), 70, 20, 1));
        long id1 = mapService.saveMap(new AddMapRequestDTO("Testmap2", LocalDate.of(2022, 12, 24), 70, 20, 1));

        //Test
        assertThat(streetPlanRepository.count()).isEqualTo(2);

        assertThat(streetPlanRepository.findById(id0).orElseThrow()).isEqualTo(mapService.getMapById(id0));
        assertThat(streetPlanRepository.findById(id1).orElseThrow()).isEqualTo(mapService.getMapById(id1));
    }

    @Test
    void deleteStreetPlanTest () {
        //Setup
        List<Map> streetPlanList = initDB();

        //Test
        for (var streetPlan : streetPlanList) {
            mapService.deleteMapById(streetPlan.getId());
            assertThat(streetPlanRepository.findById(streetPlan.getId()).isPresent()).isFalse();
        }
        assertThat(streetPlanRepository.count()).isZero();
    }

    @Test
    void findAllStreetPlansTest () {
        //Setup
        List<Map> mapList = initDB();
        List<Map> mapListFromService = mapService.findAllMaps();

        Iterator<Map> iterator = mapList.iterator();

        //Test
        for (var mapFromService : mapListFromService) {
            Map map = iterator.next();


            assertThat(mapFromService.getId()).isEqualTo(map.getId());
            assertThat(mapFromService.getMapName()).isEqualTo(map.getMapName());
            assertThat(mapFromService.getSizeX()).isEqualTo(map.getSizeX());
            assertThat(mapFromService.getSizeY()).isEqualTo(map.getSizeY());

        }
    }

    @Test
    void findMapByIdTest () {
        //Setup
        List<Map> mapList = initDB();

        //Test
        for (var map : mapList) {
            assertThat(mapService.getMapById(map.getId())).isEqualTo(map);
        }
    }


}
