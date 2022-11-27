package de.hsrm.mi.swt02.backend.api.lobby;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.player.Player;
import de.hsrm.mi.swt02.backend.api.player.PlayerServiceImpl;

@Service
public class LobbyServiceImpl implements LobbyService {

    Logger logger = LoggerFactory.getLogger(LobbyServiceImpl.class);
    @Autowired
    private LobbyRepository lobbyRepository;

    @Autowired
    private PlayerServiceImpl playerService;

    @Override
    @Transactional
    public List<Lobby> findAllLobbys() {

        List<Lobby> lobbys = lobbyRepository.findAll();

        if (lobbys.isEmpty()) {
            logger.warn("DB is empty.. found no Lobby´s");
        }

        return lobbys;
    }

    @Override
    @Transactional
    public Lobby findLobbyById(long id) {

        Optional<Lobby> foundLobby = lobbyRepository.findById(id);

        if (foundLobby.isEmpty()) {
            logger.warn("No Lobby with given ID was found");
        }

        return foundLobby.get();
    }

    @Override
    @Transactional
    public void deleteLobby(long id) {

        // logger.warn("No Lobby with given ID was found");
        //Todo: remove possible realtionships if existent
        lobbyRepository.deleteById(id);

    }

    @Override
    public long createLobby(Lobby lobby) {
       
        Lobby createLobby = new Lobby(lobby.getLobbyName(), lobby.getNumOfPlayers(), lobby.getLobbyMode());
        
        //DTO aus Frontend anpassen und PlayerID mitschicken der Lobby hosted um Host korrekt zu setzen
        Player host = playerService.findPlayerById(lobby.getHostID());
        createLobby.setHost(host);
        //Beziehungen setzen!
        
        
        

        return lobbyRepository.save(createLobby).getId();
    }

    @Override
    public void updateLobby(long id) {
        Optional<Lobby> findLobby = lobbyRepository.findById(id);

        // logger.warn("No Lobby with given ID was found");
        if (findLobby.isPresent()) {

        }
    }


}
