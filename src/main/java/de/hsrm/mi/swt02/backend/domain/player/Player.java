package de.hsrm.mi.swt02.backend.domain.player;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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


    @ManyToOne(cascade = CascadeType.REMOVE)
    private Lobby activeLobby;

    
    @OneToMany(mappedBy = "host",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Lobby> hostedLobbys;
    

    public Player(String userName) {
        this.userName = userName;
        this.hostedLobbys = new ArrayList<Lobby>();
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

    
    public void AddHostToHostedLobbyList(Lobby addLobby){
        this.hostedLobbys.add(addLobby);
    }

    public void removeLobbyFromHostedLobbyList(Lobby removeLobby){
        this.hostedLobbys.remove(removeLobby);
    }

    

}
