package de.hsrm.mi.swt02.backend.domain.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String userName;


    @ManyToOne
    private Lobby activeLobby;


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


    public boolean isHost(Lobby lobby) {
        return lobby.getHostID() == this.id;
    }


}
