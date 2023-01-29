package de.hsrm.mi.swt02.backend.api.game.trafficLight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

public interface TrafficLightRepository extends JpaRepository<TrafficLight, Long> {
    
}
