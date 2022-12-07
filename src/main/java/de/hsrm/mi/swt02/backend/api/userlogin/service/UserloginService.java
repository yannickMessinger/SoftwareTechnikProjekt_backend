package de.hsrm.mi.swt02.backend.api.userlogin.service;

import java.util.Optional;

import de.hsrm.mi.swt02.backend.domain.userlogin.Userlogin;

public interface UserloginService {
    Optional<Userlogin> findUserByUsernameAndPassword(String username, String password);
    Userlogin createUser(String username, String password);
}
