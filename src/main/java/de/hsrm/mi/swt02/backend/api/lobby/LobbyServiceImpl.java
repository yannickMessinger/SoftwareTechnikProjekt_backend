package de.hsrm.mi.swt02.backend.api.lobby;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {

    @Autowired
    private LobbyRepository lobbyRepository;

    @Override
    public List<Lobby> findAllLobbys() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Lobby findLobbyById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteLobby(long id) {
        // TODO Auto-generated method stub
        
    }
    
}
