package de.hsrm.mi.swt02.backend.api.streetgrid;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hsrm.mi.swt02.backend.api.streetgrid.gridelements.StreetGrid;

public interface StreetGridRepository extends JpaRepository<StreetGrid,Long>{
    
}
