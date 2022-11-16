package de.hsrm.mi.swt02.backend.api.lobby;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {

    @Autowired
    private LobbyRepository lobbyRepository;

    @Override
    @Transactional
    public List<Lobby> findAllLobbys() {
        
        Optional<List<Lobby>> allLobbys = Optional.of(lobbyRepository.findAll());
        
        if(allLobbys.isEmpty()){
            //logger
        }

        return allLobbys.get();
    }

    @Override
    @Transactional
    public Lobby findLobbyById(long id) {
       
        Optional<Lobby> foundLobby = lobbyRepository.findById(id);

        if(foundLobby.isEmpty()){
            //logger
        }

        return foundLobby.get();
    }

    @Override
    @Transactional
    public void deleteLobby(long id) {

        //Todo: remove possible realtionships if existent
        lobbyRepository.deleteById(id);
        
    }

    @Override
    public long createLobby(String lobbyname) {
       
        Lobby createLobby = new Lobby();
        createLobby.setLobbyName(lobbyname);

        return lobbyRepository.save(createLobby).getId();
    }

    @Override
    public void updateLobby(long id) {
        Optional<Lobby> findLobby = lobbyRepository.findById(id);

        if(findLobby.isPresent()){
        
        }
    }

    
    
}
