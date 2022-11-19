package de.hsrm.mi.swt02.backend.api.streetgrid;

import de.hsrm.mi.swt02.backend.api.streetgrid.dtos.AddStreetGridRequestDTO;

public interface StreetGridService {
    long createStreetGrid(String gridName, AddStreetGridRequestDTO dto);
}
