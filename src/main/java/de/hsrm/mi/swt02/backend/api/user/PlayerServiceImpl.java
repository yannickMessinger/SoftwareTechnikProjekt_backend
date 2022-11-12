package de.hsrm.mi.swt02.backend.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository uRepo;

    @Override
    public List<Player> findAllUsers() {
        return uRepo.findAll();
    }

    @Override
    public Player findUserById(long id) {

        Optional<Player> user = uRepo.findById(id);

        if (user.isEmpty()) {
            // logger
        }
        return user.get();
    }

    @Override
    public void deleteUser(long id) {

        uRepo.deleteById(id);
    }

    @Override
    public Player createUser(String userName) {

        return uRepo.save(new Player(userName));
    }
}
