package de.hsrm.mi.swt02.backend.api.player;

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

        uRepo.deleteById(id);
    }

    @Override
    public Player createPlayer(String userName) {

        return uRepo.save(new Player(userName));
    }
}
