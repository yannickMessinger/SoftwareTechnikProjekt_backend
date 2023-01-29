package de.hsrm.mi.swt02.backend.api.game.crossroad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;

/**
 * The CrossroadRepository interface extends {@link JpaRepository} to provide
 * basic CRUD operations for the {@link Crossroad} entity.
 */
public interface CrossroadRepository extends JpaRepository<Crossroad, Long> {

}
