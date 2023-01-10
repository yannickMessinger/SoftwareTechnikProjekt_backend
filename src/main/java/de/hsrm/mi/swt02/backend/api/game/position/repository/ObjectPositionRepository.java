package de.hsrm.mi.swt02.backend.api.game.position.repository;

import de.hsrm.mi.swt02.backend.domain.game.position.MapObjectPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectPositionRepository extends JpaRepository<MapObjectPosition, Long> {
}
