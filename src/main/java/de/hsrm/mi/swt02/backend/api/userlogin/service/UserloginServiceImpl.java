package de.hsrm.mi.swt02.backend.api.userlogin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swt02.backend.api.userlogin.repository.UserloginRepository;
import de.hsrm.mi.swt02.backend.domain.userlogin.Userlogin;

@Service
public class UserloginServiceImpl implements UserloginService {
    @Autowired
    UserloginRepository userRepo;

    @Override 
    public Userlogin createUser(String username, String password){
        Userlogin newUser = new Userlogin(username, password);
        return userRepo.save(newUser);
    }

    @Override
    public Optional<Userlogin> findUserByUsernameAndPassword(String username, String password){
        Optional<Userlogin> user = userRepo.findUserByUsernameAndPassword(username, password);
        return user;
    }
}
