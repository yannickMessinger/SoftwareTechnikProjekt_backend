package de.hsrm.mi.swt02.backend.api.player.repository;

import de.hsrm.mi.swt02.backend.domain.player.Player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select p from Player p where p.userName=?1 and p.password=?2")
    Optional<Player> findPlayerByUsernameAndPassword(String userName, String password);
}
