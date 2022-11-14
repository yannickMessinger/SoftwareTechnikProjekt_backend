package de.hsrm.mi.swt02.backend.api.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository uRepo;

    @Override
    public List<Player> findAllPlayers() {
        return uRepo.findAll();
    }

    @Override
    public Player findPlayerById(long id) {

        Optional<Player> player = uRepo.findById(id);

        if (player.isEmpty()) {
            // logger
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
