package de.hsrm.mi.swt02.backend.api.game.position.service;

import de.hsrm.mi.swt02.backend.api.game.position.repository.ObjectPositionRepository;
import de.hsrm.mi.swt02.backend.api.game.position.repository.PositionRepository;
import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectRepository;
import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.domain.game.position.MapObjectPosition;
import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService{
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    ObjectPositionRepository objectPositionRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MapObjectRepository objectRepository;

    /**
     * @return list of all PlayerPosition
     */
    @Override
    public List<PlayerPosition> findAllPositions() {
        return positionRepository.findAll();
    }

    /**
     * @param id of PlayerPosition
     */
    @Override
    public void deletePosition(long id) {
        positionRepository.deleteById(id);
    }

    /**
     * @param player Player to be persisted
     * @param x Coordinate of Player on 3d world
     * @param y Coordinate of Player on 3d world
     * @return id of created Position
     */
    @Override
    @Transactional
    public long createPosition(Player player, double x, double y, double rotation) {
        PlayerPosition playerPosition = new PlayerPosition(player, x, y, rotation);
        player.setPlayerPosition(playerPosition);
        playerRepository.save(player);
        return positionRepository.save(playerPosition).getId();
    }

    /**
     * @param mapObject MapObject to be persisted
     * @param x - coordinate of object on 3D world
     * @param y - coordinate of object on 3D world
     * @param rotation
     * @return id of created position
     */
    @Override
    public long createPosition(MapObject mapObject, double x, double y, double rotation) {
        MapObjectPosition objectPosition = new MapObjectPosition(mapObject, x, y, rotation);
        mapObject.setMapObjectPosition(this.saveObjectPosition(objectPosition, x, y, rotation));
        objectRepository.save(mapObject);
        return objectPosition.getId();
    }


    /**
     * @param playerPositionId Player ID to be updated
     * @param x Coordinate of Player on 3d world
     * @param y Coordinate of Player on 3d world
     * @param rotation Rotation of Player on 3d world
     */
    @Override
    @Transactional
    public void savePosition(long playerPositionId, double x, double y, double rotation) {
        Optional<PlayerPosition> optionalPlayerPosition = positionRepository.findById(playerPositionId);
        if (optionalPlayerPosition.isPresent()) {
            var playerPosition = optionalPlayerPosition.get();
            playerPosition.setPosX(x);
            playerPosition.setPosY(y);
            playerPosition.setRotation(rotation);
            positionRepository.save(playerPosition);
        } else
            log.info("PlayerPosition:" + playerPositionId + " not found");
    }

    /**
     * position of MapObject with given Id is updated
     * @param mapObjectPosition MapObject (found by Id) to be updated
     * @param x - cpprdinate of MapObject on 3D world
     * @param y - coordinate of MapObject on 3D world
     * @param rotation Rotation of MapObject on 3D world
     * @return
     */
    @Override
    public MapObjectPosition saveObjectPosition(MapObjectPosition mapObjectPosition, double x, double y, double rotation) {
        Optional<MapObjectPosition> optionalMapObjectPosition = objectPositionRepository.findById(mapObjectPosition.getId());
        if (optionalMapObjectPosition.isPresent()) {
            var foundMapObjectPosition = optionalMapObjectPosition.get();
            foundMapObjectPosition.setPosX(x);
            foundMapObjectPosition.setPosY(y);
            foundMapObjectPosition.setRotation(rotation);
            return objectPositionRepository.save(foundMapObjectPosition);
        } else {
            log.info("MapObjectPosition: " + mapObjectPosition.getId() + " not found");
            return objectPositionRepository.save(mapObjectPosition);
        }
    }
}
