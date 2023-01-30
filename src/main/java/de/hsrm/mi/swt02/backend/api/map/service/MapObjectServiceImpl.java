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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Class to handle CRUD operations on the MapObjectRepository.
 */

@Service
@Slf4j
public class MapObjectServiceImpl implements MapObjectService {

    private final int GRID_SIZE_X = 300;
    private final int GRID_SIZE_Y = 200;
    private final int FIELD_SIZE = 10;
    @Autowired
    private MapObjectRepository mapObjRepo;
    @Autowired
    private MapService mapService;
    @Autowired
    private GameAssetRepository gameAssetRepo;

    @Override
    @Transactional
    public List<MapObject> findAllMapObjects() {

        Optional<List<MapObject>> allMaps = Optional.of(mapObjRepo.findAll());

        if (allMaps.isEmpty()) {
            log.info("Not found");

        }

        return allMaps.get();
    }

    @Override
    @Transactional
    public MapObject getMapObjectById(long id) {
        Optional<MapObject> foundMapObj = mapObjRepo.findById(id);

        return foundMapObj.orElse(null);
    }

    @Override
    @Transactional
    public void deleteMapObjectById(long id) {
        this.getMapObjectById(id).getMap().getMapObjects().remove(this.getMapObjectById(id));
        mapObjRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Long createMapObject(AddMapObjectsRequestDTO mapObjects, long mapId) {

        Map foundMap = mapService.getMapById(mapId);

        this.deleteAllMapObjectsFromMapById(mapId);

        long returnValue = 0L;

        if (!mapObjects.mapObjects().isEmpty()) {
            for (AddMapObjectRequestDTO ele : mapObjects.mapObjects()) {
                MapObject nMapObj = new MapObject(ele.objectTypeId(), ele.x(), ele.y(), ele.rotation());
                nMapObj.setCenterX3d(ele.y());
                nMapObj.setCenterZ3d(ele.x());
                nMapObj.setMap(mapService.getMapById(mapId));
                foundMap.getMapObjects().add(nMapObj);
                returnValue = mapObjRepo.save(nMapObj).getId();
            }
        }

        mapService.saveEditedMap(foundMap);

        return returnValue;
    }

    @Override
    @Transactional
    public void deleteAllMapObjectsFromMapById(long id) {
        Map foundMap = mapService.getMapById(id);

        for (MapObject ele : foundMap.getMapObjects()) {
            mapObjRepo.deleteById(ele.getId());
        }
    }

    @Override
    @Transactional
    public void addNewMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
            .ifPresent(mapObject -> mapObjRepo.delete(mapObject));
        MapObject mapObject = new MapObject(mapObjectDTO.objectTypeId(), mapObjectDTO.x(), mapObjectDTO.y(), mapObjectDTO.rotation());

        mapObject.setCenterX3d(calcMapEleCenterX(mapObjectDTO.y()));
        mapObject.setCenterZ3d(calcMapEleCenterZ(mapObjectDTO.x()));


        mapObjectList.add(mapObject);
        mapObject.setMap(map);
        mapObject.setGameAssets(new ArrayList<GameAsset>());
        this.addNewGameAssetToMapObject(mapObjectDTO.game_assets(), mapObject);
        mapObjRepo.save(mapObject);
    }

    @Override
    @Transactional
    public void deleteMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
            .ifPresent(mapObject -> mapObjRepo.delete(mapObject));
    }

    @Transactional
    public void updateMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
            .ifPresent(mapObject -> {
                mapObject.setRotation(mapObjectDTO.rotation());
                deleteOldGameAssetsFromMapObject(mapObject);
                if (!mapObjectDTO.game_assets().isEmpty()) {
                    this.addNewGameAssetToMapObject(mapObjectDTO.game_assets(), mapObject);
                }
                mapObjRepo.save(mapObject);
            });
    }

    private void addNewGameAssetToMapObject(List<GameAssetDTO> gameAssetDTOs, MapObject mapObject) {
        gameAssetDTOs.forEach(ele -> {
            GameAsset gameAsset = new GameAsset(ele.objectTypeId(), ele.x(), ele.y(), ele.rotation(), ele.texture(), ele.userId());

            gameAsset.setX3d(calcPixelPosNpcX(mapObject.getCenterX3d(), gameAsset.getX()));
            gameAsset.setZ3d(calcPixelPosNpcX(mapObject.getCenterZ3d(), gameAsset.getY()));


            mapObject.getGameAssets().add(gameAsset);
            gameAsset.setMapObject(mapObject);
            gameAssetRepo.save(gameAsset);
        });
    }

    private void deleteOldGameAssetsFromMapObject(MapObject mapObject) {
        mapObject.getGameAssets().forEach(ele -> {
            ele.setMapObject(null);
            gameAssetRepo.delete(ele);
        });
        mapObject.getGameAssets().clear();
    }

    private Optional<MapObject> findMapObjectByXandY(List<MapObject> mapObjectList, AddMapObjectRequestDTO mapObjectDTO) {
        return mapObjectList.stream()
            .filter(c -> c.getX() == mapObjectDTO.x() && c.getY() == mapObjectDTO.y())
            .findFirst();
    }


    //center 3D xcoord for mapobject
    public int calcMapEleCenterX(int curMapObjY) {
        int curMapObjcenterX = ((int) (this.GRID_SIZE_X * -0.5 + curMapObjY * this.FIELD_SIZE + this.FIELD_SIZE / 2));
        return curMapObjcenterX;
    }

    //center 3D zcoord for mapobject
    public int calcMapEleCenterZ(int curMapObjY) {
        int curMapObjcenterZ = ((int) (this.GRID_SIZE_Y * -0.5 + curMapObjY * this.FIELD_SIZE + this.FIELD_SIZE / 2));
        return curMapObjcenterZ;
    }

    // x pixelpos asset 3d
    public double calcPixelPosNpcX(int curMapObjcenterX, double gameAssetX) {
        double originX = curMapObjcenterX - this.FIELD_SIZE / 2;
        double npcPosX = originX + gameAssetX * this.FIELD_SIZE;

        return npcPosX;
    }

    //z pixelpos asset 3d
    public double calcPixelPosNpcZ(int curMapObjcenterZ, double gameAssetZ) {


        double originZ = curMapObjcenterZ - this.FIELD_SIZE / 2;
        double npcPosZ = originZ + gameAssetZ * this.FIELD_SIZE;

        return npcPosZ;
    }
}
