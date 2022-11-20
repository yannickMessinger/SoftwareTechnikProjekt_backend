package de.hsrm.mi.swt02.backend.api.streetgrid;

import de.hsrm.mi.swt02.backend.api.lobby.Lobby;
import de.hsrm.mi.swt02.backend.api.lobby.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.streetgrid.gridelements.StreetGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreetGridServiceImpl implements StreetGridService {

    @Autowired
    private StreetGridRepository gridRepository;

    @Autowired
    private LobbyRepository lobbyRepository;

    /**
     * Save gridData as String - easiest way
     * @param gridData as String
     * @return StreetGrid
     */
    @Override
    public StreetGrid saveStreetGrid(String gridData) {
        StreetGrid streetGrid = new StreetGrid(gridData);
        //ToDo: add Lobby and Players Entities in StreetGrid Entity to provide connection to each other

        streetGrid = gridRepository.save(streetGrid);
        /*
        Created Test Lobby to proof getStreetGridByLobbyId() functionality:
        Lobby l = new Lobby();
        l.setStreetGrid(streetGrid);
        streetGrid.setLobby(lobbyRepository.save(l));*/
        return streetGrid;
    }

    /**
     * Get StreetGrid by Id
     * @param id
     * @return StreetGrid
     */
    public StreetGrid getStreetGridById(long id) {
        Optional<StreetGrid> streetGridOpt = gridRepository.findById(id);
        if (streetGridOpt.isEmpty()) {
            // logger
        }
        return streetGridOpt.get();
    }

    /**
     * Get StreetGrid by LobbyId
     * @param lobbyId
     * @return StreetGrid
     */
    //Doesnt work yet until Lobby and StreetGrid components are connected together
    public StreetGrid getStreetGridByLobbyId(long lobbyId) {
        //Get the right Lobby from LobbyRepository
        Optional<Lobby> lobbyOpt = lobbyRepository.findById(lobbyId);
        if (lobbyOpt.isEmpty()) {
            // logger
        }
        //Get matching StreetGrid to the Lobby from GridRepository
        Optional<StreetGrid> streetGridOpt = gridRepository.findById(lobbyOpt.get().getStreetGrid().getId());
        if (streetGridOpt.isEmpty()) {
            // logger
        }
        return streetGridOpt.get();
    }

    /**
     * Delete StreetGrid
     * @param id
     */
    public void deleteStreetGridById(long id) {
        gridRepository.deleteById(id);
    }
}
