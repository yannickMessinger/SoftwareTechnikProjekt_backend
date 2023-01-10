package de.hsrm.mi.swt02.backend.api.game.position.service;

import de.hsrm.mi.swt02.backend.domain.game.position.MapObjectPosition;
import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {

    List<PlayerPosition> findAllPositions();
    void deletePosition(long id);
    long createPosition(Player player, double x, double y, double rotation);
    long createPosition(MapObject mapObject, double x, double y, double rotation);
    void savePosition(long playerPositionId, double x, double y, double rotation);
    MapObjectPosition saveObjectPosition(MapObjectPosition mapObjectPosition, double x, double y, double rotation);
}
