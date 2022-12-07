package de.hsrm.mi.swt02.backend.api.userlogin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.hsrm.mi.swt02.backend.domain.userlogin.Userlogin;

public interface UserloginRepository extends JpaRepository<Userlogin, Long> {
    @Query("select u from Userlogin u where u.username=?1 and u.password=?2")
    public Optional<Userlogin> findUserByUsernameAndPassword(String username, String password);
}
