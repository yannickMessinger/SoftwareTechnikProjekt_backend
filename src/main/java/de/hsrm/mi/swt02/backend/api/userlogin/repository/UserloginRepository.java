package de.hsrm.mi.swt02.backend.api.userlogin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.hsrm.mi.swt02.backend.domain.userlogin.Userlogin;

public interface UserloginRepository extends JpaRepository<Userlogin, Long> {
    @Query(value="SELECT * FROM USERLOGIN WHERE USERNAME = :username AND PASSWORD = :password", nativeQuery = true )
    public Optional<Userlogin> findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
