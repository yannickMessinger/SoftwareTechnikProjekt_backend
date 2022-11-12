package de.hsrm.mi.swt02.backend.api.user;

import java.util.List;

public interface PlayerService {

    public List<Player> findAllUsers();
    public Player findUserById(long id);
    public void deleteUser(long id);
    public Player createUser(String userName);
}
