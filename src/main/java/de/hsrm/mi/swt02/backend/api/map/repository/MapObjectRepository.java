package de.hsrm.mi.swt02.backend.api.map.repository;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to persist MapObjects.
 */
public interface MapObjectRepository extends JpaRepository<MapObject, Long> {

}
