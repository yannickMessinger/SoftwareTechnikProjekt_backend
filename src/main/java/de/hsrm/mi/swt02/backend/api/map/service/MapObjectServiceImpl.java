package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.GameAssetDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.GameAssetRepository;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
import de.hsrm.mi.swt02.backend.domain.map.GameAsset;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service Class to handle CRUD operations on the MapObjectRepository.
 */

@Slf4j
@Service
public class MapObjectServiceImpl implements MapObjectService {

    @Autowired
    private MapObjectRepository mapObjRepo;

    @Autowired
    private MapService mapService;

    @Autowired
    private GameAssetRepository gameAssetRepo;

    @Override
    @Transactional
    public List<MapObject> findAllMapObjects () {

        Optional<List<MapObject>> allMaps = Optional.of(mapObjRepo.findAll());

        return allMaps.get();
    }

    @Override
    @Transactional
    public MapObject getMapObjectById (long id) {
        Optional<MapObject> foundMapObj = mapObjRepo.findById(id);

        if (foundMapObj.isEmpty()) {
            log.warn("No Map was found");
        }

        return foundMapObj.orElseThrow();
    }

    @Override
    @Transactional
    public void deleteMapObjectById (long id) {
        this.getMapObjectById(id).getMap().getMapObjects().remove(this.getMapObjectById(id));
        mapObjRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Long createMapObject (AddMapObjectsRequestDTO mapObjects, long mapId) {

        Map foundMap = mapService.getMapById(mapId);

        this.deleteAllMapObjectsFromMapById(mapId);

        if (!mapObjects.mapObjects().isEmpty()) {
            for (AddMapObjectRequestDTO ele : mapObjects.mapObjects()) {
                MapObject nMapObj = new MapObject(ele.objectTypeId(), ele.x(), ele.y(), ele.rotation());
                nMapObj.setMap(mapService.getMapById(mapId));
                foundMap.getMapObjects().add(nMapObj);
                mapObjRepo.save(nMapObj);

            }
        }

        mapService.saveEditedMap(foundMap);

        return foundMap.getId();
    }

    @Override
    @Transactional
    public void deleteAllMapObjectsFromMapById (long id) {
        Map foundMap = mapService.getMapById(id);

        for (MapObject ele : foundMap.getMapObjects()) {
            mapObjRepo.deleteById(ele.getId());
        }
    }

    @Override
    public List<MapObject> getAllMapObjectsFromMap (long id) {
        return mapService.getMapById(id).getMapObjects();
    }


    @Override
    @Transactional
    public void addNewMapObjectFromBroker (AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
                .ifPresent(mapObject -> mapObjRepo.delete(mapObject));
        MapObject mapObject = new MapObject(mapObjectDTO.objectTypeId(), mapObjectDTO.x(), mapObjectDTO.y(), mapObjectDTO.rotation());
        mapObjectList.add(mapObject);
        mapObject.setMap(map);
        mapObjRepo.save(mapObject);
    }

    @Override
    @Transactional
    public void deleteMapObjectFromBroker (AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
                .ifPresent(mapObject -> mapObjRepo.delete(mapObject));
    }

    @Transactional
    public void updateMapObjectFromBroker (AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
                .ifPresent(mapObject -> {
                    mapObject.setRotation(mapObjectDTO.rotation());
                    if (!mapObjectDTO.game_assets().isEmpty()) {
                        this.addNewGameAssetToMapObject(mapObjectDTO.game_assets(), mapObject);
                    }
                    mapObjRepo.save(mapObject);
                });
    }

    private void addNewGameAssetToMapObject (List<GameAssetDTO> gameAssetDTOs, MapObject mapObject) {
        deleteOldGameAssetsFromMapObject(mapObject);
        gameAssetDTOs.forEach(ele -> {
            GameAsset gameAsset = new GameAsset(ele.objectTypeId(), ele.x(), ele.y(), ele.rotation(), ele.texture());
            mapObject.getGameAssets().add(gameAsset);
            gameAsset.setMapObject(mapObject);
            gameAssetRepo.save(gameAsset);
        });
    }

    private void deleteOldGameAssetsFromMapObject (MapObject mapObject) {
        mapObject.getGameAssets().forEach(ele -> {
            ele.setMapObject(null);
            gameAssetRepo.delete(ele);
        });
        mapObject.getGameAssets().clear();
    }


    private Optional<MapObject> findMapObjectByXandY (List<MapObject> mapObjectList, AddMapObjectRequestDTO mapObjectDTO) {
        return mapObjectList.stream()
                .filter(c -> c.getX() == mapObjectDTO.x() && c.getY() == mapObjectDTO.y())
                .findFirst();
    }
}
