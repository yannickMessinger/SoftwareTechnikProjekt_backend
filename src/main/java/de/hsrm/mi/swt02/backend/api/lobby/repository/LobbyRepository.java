package de.hsrm.mi.swt02.backend.api.lobby.repository;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LobbyRepository extends JpaRepository<Lobby, Long> {

}