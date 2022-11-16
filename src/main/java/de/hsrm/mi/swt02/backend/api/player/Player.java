package de.hsrm.mi.swt02.backend.api.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.api.lobby.Lobby;

import java.util.Objects;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String userName;

    @ManyToOne
    private Lobby lobby;

    public Player(String userName) {
        this.userName = userName;
    }

    public Player() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", version=" + version +
                ", userName='" + userName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && version == player.version && userName.equals(player.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, userName);
    }
}
