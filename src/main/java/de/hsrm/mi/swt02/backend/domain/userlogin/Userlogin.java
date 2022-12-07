package de.hsrm.mi.swt02.backend.domain.userlogin;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Userlogin {

    @Id
    @GeneratedValue
    private long id;
    
    @Version
    private long version;

    private String username;
    private String password;

    public Userlogin(String username, String password){
        this.username = username;
        this.password = password;
    }
}
