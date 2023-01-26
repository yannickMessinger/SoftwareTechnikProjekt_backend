package de.hsrm.mi.swt02.backend.api.game.position.repository;

import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<ObjectPosition, Long> {
}
