package de.hsrm.mi.swt02.backend.api.streetgrid;

import de.hsrm.mi.swt02.backend.api.streetgrid.gridelements.StreetGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreetGridServiceImpl implements StreetGridService {

    @Autowired
    private StreetGridRepository gridRepository;

    @Override
    public StreetGrid saveStreetGrid(String gridData) {
        StreetGrid streetGrid = new StreetGrid(gridData);
        return gridRepository.save(streetGrid);
    }

    public StreetGrid getStreetGridById(long id) {
        Optional<StreetGrid> streetGridOpt = gridRepository.findById(id);
        if (streetGridOpt.isEmpty()) {
            // logger
        }
        return streetGridOpt.get();
    }
}
