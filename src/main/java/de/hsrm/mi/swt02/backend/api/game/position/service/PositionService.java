package de.hsrm.mi.swt02.backend.api.game.position.service;

import de.hsrm.mi.swt02.backend.domain.game.position.PlayerPosition;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {

    List<PlayerPosition> findAllPositions();
    void deletePosition(long id);
    long createPosition(Player player, int x, int y);
    void savePosition(long playerId, int x, int y);
}
