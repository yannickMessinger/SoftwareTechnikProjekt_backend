package de.hsrm.mi.swt02.backend.api.map.repository;

import de.hsrm.mi.swt02.backend.domain.map.Map;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * JPA Respository to manage and persist entitys of Type Map
 */
public interface MapRepository extends JpaRepository<Map, Long> {
}
