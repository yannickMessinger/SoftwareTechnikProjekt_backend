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

    /**
     * @return list containing all MapObjects of repository.
     */
    @Override
    @Transactional
    public List<MapObject> findAllMapObjects() {

        Optional<List<MapObject>> allMaps = Optional.of(mapObjRepo.findAll());

        if (allMaps.isEmpty()) {
            log.info("Not found");

        }

        return allMaps.get();
    }

    /**
     * gets single MapObject by the handed id
     *
     * @param id id to look for the correct object
     * @return returns MapObject if found
     */
    @Override
    @Transactional
    public MapObject getMapObjectById(long id) {
        Optional<MapObject> foundMapObj = mapObjRepo.findById(id);

        return foundMapObj.orElse(null);
    }

    /**
     * deletes single MapObject by the given id and delete MapObject from
     * MapObjectList in Map
     *
     * @param id id of the MapObject to be deleted.
     */
    @Override
    @Transactional
    public void deleteMapObjectById(long id) {
        this.getMapObjectById(id).getMap().getMapObjects().remove(this.getMapObjectById(id));
        mapObjRepo.deleteById(id);
    }

    /**
     * adds incoming MapObjects from frontend to the repository.
     *
     * @param mapObjects initial converting from JSON to regular java object from
     *                   incoming Request Body in corresponding REST Controller,
     *                   every Entity is saved individually.
     * @param mapId      id to add the MapObjects to the right Map (in MapObjects
     *                   list) and add Map field to the right MapObject
     * @return returns the id of the Entity that was saved last.
     */
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

    /**
     * deletes all MapObjects from the Map with the given id.
     */
    @Override
    @Transactional
    public void deleteAllMapObjectsFromMapById(long id) {
        Map foundMap = mapService.getMapById(id);

        for (MapObject ele : foundMap.getMapObjects()) {
            mapObjRepo.deleteById(ele.getId());
        }
    }


    /**
     * Add a new MapObjekt to the right Map and save in database that comes from Message Broker.
     * If MapObject has a field that already exist, it will be deleted. Also correct center coordinates
     * of the map object are calculated and set to place map object correctly in 3D.
     *
     * @param mapObjectDTO - came from Broker (create) channel
     * @param mapId        id of map that MapObject was placed on
     */

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

    /**
     * Delete a MapObject that comes from Message Broker.
     *
     * @param mapObjectDTO - came from Broker (delete) channel
     */
    @Override
    @Transactional
    public void deleteMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
            .ifPresent(mapObject -> mapObjRepo.delete(mapObject));
    }

    /**
     * Update a new MapObjekt that comes from Message Broker (rotation).
     * This means that the rotation is reset and a new GameAssets are added to the MapObject.
     *
     * @param mapObjectDTO - came from Broker (update) channel
     * @param mapId        id of map that MapObject was placed on
     */
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

    /**
     * @param gameAssetDTOs GameAssets such as cars, train or pedestrians that are placed on this one MapObject
     * @param mapObject     mapObject that the GameAssets are placed on
     *                      Also calls methods calcPixelPosX and calcPixelPosZ to determine correct pixel positions of the gameAssets and
     *                      set the values so that they can be placed correctly in 3d.
     */

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

    /**
     * Help method: Delete old GameAssets from MapObject
     *
     * @param mapObject get cleared from GameAssets
     */
    private void deleteOldGameAssetsFromMapObject(MapObject mapObject) {
        mapObject.getGameAssets().forEach(ele -> {
            ele.setMapObject(null);
            gameAssetRepo.delete(ele);
        });
        mapObject.getGameAssets().clear();
    }

    /**
     * Help Method: Find the new MapObject by X and Y.
     * Find the right MapObject in mapObjectList.
     * Find MapObject which has the same x and y positions as the mapObjectDTO.
     *
     * @param mapObjectList - List ob existing MapObjects
     * @param mapObjectDTO  - New DTO that came from broker.
     */
    private Optional<MapObject> findMapObjectByXandY(List<MapObject> mapObjectList, AddMapObjectRequestDTO mapObjectDTO) {
        return mapObjectList.stream()
            .filter(c -> c.getX() == mapObjectDTO.x() && c.getY() == mapObjectDTO.y())
            .findFirst();
    }


    /**
     * Calculates the center 3D xcoord for mapobject.
     *
     * @param curMapObjY Y Coordinate of the MapObject from 2D Editor, needs to be called with y value because
     *                   x and y axis are switched in editor
     * @return the X Center coordinate of the mapObject so that the x3d value can be set to it.
     */
    public int calcMapEleCenterX(int curMapObjY) {
        int curMapObjcenterX = ((int) (this.GRID_SIZE_X * -0.5 + curMapObjY * this.FIELD_SIZE + this.FIELD_SIZE / 2));
        return curMapObjcenterX;
    }


    /**
     * calculates the center 3D zcoord for mapobject
     *
     * @param curMapObjX X Coordinate of the MapObject from 2D Editor, needs to be called with x value because
     *                   x and y axis are switched in editor
     * @return the Z Center coordinate of the mapObject so that the z3d value can be set to it.
     */
    public int calcMapEleCenterZ(int curMapObjX) {
        int curMapObjcenterZ = ((int) (this.GRID_SIZE_Y * -0.5 + curMapObjX * this.FIELD_SIZE + this.FIELD_SIZE / 2));
        return curMapObjcenterZ;
    }


    /**
     * calculates the x pixelpos of asset for 3D
     *
     * @param curMapObjcenterX center 3D x coord of mapobject the asset is placed on, necessary to calculate upper left origin and place asset
     *                         accordingly
     * @param gameAssetX       2D x editor coordiante of the gameAsset
     * @return the corresponding 3D x pixelposition of the gameAsset.
     */
    public double calcPixelPosNpcX(int curMapObjcenterX, double gameAssetX) {
        double originX = curMapObjcenterX - this.FIELD_SIZE / 2;
        double npcPosX = originX + gameAssetX * this.FIELD_SIZE;

        return npcPosX;
    }

    /**
     * calculates the z pixelpos of asset for 3D
     *
     * @param curMapObjcenterZ center 3D z coord of mapobject the asset is placed on, necessary to calculate upper left origin and place asset
     *                         accordingly
     * @param gameAssetZ       2D y editor coordiante of the gameAsset
     * @return the corresponding 3D z pixelposition of the gameAsset
     */
    public double calcPixelPosNpcZ(int curMapObjcenterZ, double gameAssetZ) {


        double originZ = curMapObjcenterZ - this.FIELD_SIZE / 2;
        double npcPosZ = originZ + gameAssetZ * this.FIELD_SIZE;

        return npcPosZ;
    }
}
