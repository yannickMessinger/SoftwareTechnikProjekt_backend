package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to persist StreetObjects.
 */
public interface StreetObjectRepository extends JpaRepository<StreetObject, Long> {
    
}
