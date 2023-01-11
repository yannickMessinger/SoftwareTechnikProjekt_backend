package de.hsrm.mi.swt02.backend.api.map.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import de.hsrm.mi.swt02.backend.api.game.position.service.PositionService;
import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectsRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;

/**
 * Service Class to handle CRUD operations on the MapObjectRepository.
 */

@Service
public class MapObjectServiceImpl implements MapObjectService {

    @Autowired
    private MapObjectRepository mapObjRepo;

    @Autowired
    private MapService mapService;

    @Autowired
    private PositionService positionService;

    /**
     * @return list containing all MapObjects of repository.
     */
    @Override
    @Transactional
    public List<MapObject> findAllMapObjects() {

        Optional<List<MapObject>> allMaps = Optional.of(mapObjRepo.findAll());

        if (allMaps.isEmpty()) {
            // logger
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

        if (foundMapObj.isEmpty()) {
            // logger
        }

        return foundMapObj.orElseThrow();
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

        if (!mapObjects.mapObjects().isEmpty()) {
            for (AddMapObjectRequestDTO ele : mapObjects.mapObjects()) {
                MapObject nMapObj = new MapObject(ele.objectTypeId(), ele.x(), ele.y(), ele.rotation());
                nMapObj.setMap(mapService.getMapById(mapId));
                foundMap.getMapObjects().add(nMapObj);
                mapObjRepo.save(nMapObj);

            }
        }

        mapService.saveEditedMap(foundMap);

        return 0L;
    }

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
     * If MapObject has a field that already exist, it will be deleted.
     *
     * @param mapObjectDTO - came from Broker (create) channel
     */

    @Override
    public void addNewMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
                .ifPresent(mapObject -> mapObjRepo.delete(mapObject));
        MapObject mapObject = new MapObject(mapObjectDTO.objectTypeId(), mapObjectDTO.x(), mapObjectDTO.y(), mapObjectDTO.rotation());
        mapObjectList.add(mapObject);
        mapObject.setMap(map);
        mapObjRepo.save(mapObject);
    }

    /**
     * Delete a MapObject that comes from Message Broker.
     *
     * @param mapObjectDTO - came from Broker (delete) channel
     */
    @Override
    public void deleteMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
                .ifPresent(mapObject -> mapObjRepo.delete(mapObject));
    }

    /**
     * Update a new MapObjekt that comes from Message Broker (rotation).
     *
     * @param mapObjectDTO - came from Broker (update) channel
     */
    @Override
    public void updateMapObjectFromBroker(AddMapObjectRequestDTO mapObjectDTO, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> mapObjectList = map.getMapObjects();
        this.findMapObjectByXandY(mapObjectList, mapObjectDTO)
                .ifPresent(mapObject -> {
                    mapObject.setRotation(mapObjectDTO.rotation());
                    mapObjRepo.save(mapObject);
                });
    }

    /**
     * @param id Map ID
     * @return All MapObjects from Map
     */
    @Override
    public List<MapObject> getAllMapObjectsFromMap(long id) {
        return mapService.getMapById(id).getMapObjects();
    }
    private Optional<MapObject> findMapObjectByXandY(List<MapObject> mapObjectList, AddMapObjectRequestDTO mapObjectDTO) {
        return mapObjectList.stream()
                .filter(c -> c.getX() == mapObjectDTO.x() && c.getY() == mapObjectDTO.y())
                .findFirst();
    }

    /**
     *
     * @param amount of pedestrians to generate
     * @param mapId of map
     * @return List of pedestrians / mapObjects from map
     */
    @Override
    public List<MapObject> generatePedestrians(int amount, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> allObjects = new ArrayList<>(map.getMapObjects());
        List<MapObject> streetObjects = new ArrayList<>();
        List<MapObject> presentPedestrians = new ArrayList<>();
        for(MapObject o: allObjects) {
            if (o.getObjectTypeId() == 0 || o.getObjectTypeId() == 1 || o.getObjectTypeId() == 2) {
                streetObjects.add(o);
            } else if (o.getObjectTypeId() >= 7 && o.getObjectTypeId() <= 16) {
                presentPedestrians.add(o);
            }
        }
        int numberToGenerate = amount - presentPedestrians.size();
        if (numberToGenerate > 0 ) {
            int x = map.getSizeX();
            int y = map.getSizeY();
            return createPedestrians(numberToGenerate, x, y, mapId);
        } else if (numberToGenerate < 0) {
            for (int i=0; i>numberToGenerate; i--) {
                // delete pedestrians here
                return null;
            }
        }

        return presentPedestrians;
    }

    private List<MapObject> createPedestrians(int amount, int maxX, int maxY, long mapId) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> pedestrians = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int x = generateRandomInt(0, maxX);
            int y = generateRandomInt(0, maxY);
            int objectTypeId = generateRandomInt(7, 16);
            MapObject newPedestrian = new MapObject(objectTypeId, x, y, 0);
            newPedestrian.setMap(map);
            mapObjRepo.save(newPedestrian);
            positionService.createPosition(newPedestrian, x, y, 0);
            map.getMapObjects().add(newPedestrian);
            pedestrians.add(newPedestrian);
        }
        mapService.saveEditedMap(map);
        return pedestrians;
    }

    private int generateRandomInt(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range + min);
    }
}
