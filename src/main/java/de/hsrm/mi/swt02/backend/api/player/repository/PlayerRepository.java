package de.hsrm.mi.swt02.backend.api.player.repository;

import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
