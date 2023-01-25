package de.hsrm.mi.swt02.backend.api.map.repository;


import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Respository to manage entitys of Type MapObjectType
 */
public interface MapObjectTypeRepository extends JpaRepository<MapObjectType, Long> {

}
