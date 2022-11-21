package de.hsrm.mi.swt02.backend.api.lobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LobbyRepository extends JpaRepository<Lobby,Long>{
    
}