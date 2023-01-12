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
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
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
            if (o.getObjectTypeId() >= 0 && o.getObjectTypeId() <= 2) {
                streetObjects.add(o);
            } else if (o.getObjectTypeId() >= 7 && o.getObjectTypeId() <= 16) {
                presentPedestrians.add(o);
            }
        }
        int numberToGenerate = amount - presentPedestrians.size();
        if (numberToGenerate > 0 ) {
            return createPedestrians(numberToGenerate, mapId, streetObjects);
        } else if (numberToGenerate < 0) {
            numberToGenerate *= -1;
            List<ObjectPosition> allPositions = positionService.findAllPositions();
            List<ObjectPosition> pedestrianPositions = findAllPedestrianPositions(allPositions);
            for (int i=0; i<numberToGenerate; i++) {
                ObjectPosition posToDelete = pedestrianPositions.get(i);
                presentPedestrians.remove(mapObjRepo.findById(posToDelete.getId()));
                mapObjRepo.deleteById(posToDelete.getMapObjectId());
                positionService.deletePosition(posToDelete.getId());
            }
        }
        return presentPedestrians;
    }

    private List<ObjectPosition> findAllPedestrianPositions (List<ObjectPosition> allPositions) {
        List<ObjectPosition> pedestrians = new ArrayList<>();
        for (ObjectPosition o: allPositions) {
            MapObject mapObject = mapObjRepo.findById(o.getMapObjectId()).orElseThrow();
            if (mapObject.getObjectTypeId() >= 7 && mapObject.getObjectTypeId() <= 16) {
                pedestrians.add(o);
            }
        }
        return pedestrians;
    }

    private List<MapObject> createPedestrians(int amount, long mapId, List<MapObject> streets) {
        Map map = mapService.getMapById(mapId);
        List<MapObject> pedestrians = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int objectTypeId = generateRandomInt(7, 16);
            int randomIndex = generateRandomInt(0, streets.size() - 1);
            int x = streets.get(randomIndex).getX();
            int y = streets.get(randomIndex).getY();
            MapObject newPedestrian = new MapObject(objectTypeId, x, y, streets.get(randomIndex).getRotation());
            newPedestrian.setMap(map);
            mapObjRepo.save(newPedestrian);
            double posX = x;
            double posY = y;
            if (streets.get(randomIndex).getRotation() % 2 == 0) {
                posX += (double) generateRandomInt(1,9)/10;
                posY += generateRandomInt(0,1) == 1 ? 0.1 : 0.9;
            } else {
                posX += generateRandomInt(0,1) == 1 ? 0.1 : 0.9;
                posY += (double) generateRandomInt(1,9)/10;
            }
            positionService.createPosition(newPedestrian.getId(), posX, posY, streets.get(randomIndex).getRotation());
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
