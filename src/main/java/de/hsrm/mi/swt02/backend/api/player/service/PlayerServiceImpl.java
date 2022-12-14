package de.hsrm.mi.swt02.backend.api.player.service;

import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository uRepo;

    Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Override
    public List<Player> findAllPlayers() {

        List<Player> players = uRepo.findAll();

        if (players.isEmpty()) {
            logger.warn("DB is empty.. no Players were found");
        }

        return players;
    }

    @Override
    public Player findPlayerById(long id) {

        Optional<Player> player = uRepo.findById(id);

        if (player.isEmpty()) {
            logger.warn("No Player with given ID was found");
        }
        return player.get();
    }

    @Override
    public void deletePlayer(long id) {
        Lobby activeLobby = findPlayerById(id).getActiveLobby();
        Player delPlayer = this.findPlayerById(id);
        if (activeLobby == null) {
            uRepo.deleteById(id);
            return;
        }
        
        for (Player ele : activeLobby.getPlayerList()) {
            if (ele.getActiveLobby().isHostedBy(id)) {
                ele.setActiveLobby(null);
            }
        }

        if (activeLobby.isHostedBy(id)) {
            activeLobby.setHost(null);
        }

        activeLobby.getPlayerList().remove(findPlayerById(id));

        for (Lobby lobby : delPlayer.getHostedLobbys()) {
            lobby.setHost(null);
            for (Player player : lobby.getPlayerList()) {
                player.setActiveLobby(null);
            }
        }

        findPlayerById(id).setActiveLobby(null);

        uRepo.deleteById(id);
    }

    @Override
    public Player findPlayerByUsernameAndPassword(String username, String password){
        Optional<Player> player = uRepo.findPlayerByUsernameAndPassword(username, password);
        if (player.isEmpty()){
            return null;
        }
        return player.get();
    }

    @Override
    public Player createPlayer(String userName, String password) {

        return uRepo.save(new Player(userName, password));
    }
}
