package de.hsrm.mi.swt02.backend.api.game.trafficLight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;

/**
 * The TrafficLightRepository interface extends {@link JpaRepository} to provide
 * basic CRUD operations for the {@link TrafficLight} entity.
 */
public interface TrafficLightRepository extends JpaRepository<TrafficLight, Long> {
    
}
