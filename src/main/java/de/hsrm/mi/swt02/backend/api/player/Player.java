package de.hsrm.mi.swt02.backend.api.player;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.api.lobby.Lobby;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.StreetPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String userName;

    //2: ein Player kann mehrere Lobbys hosten
    
    @OneToMany(mappedBy = "host")
    private List<Lobby> hostedLobbys;
    

    @ManyToOne
    private StreetPlan streetPlan;

    //1: aber ein Spieler kann immer nur in einer Lobby gleichzeitig sein
    @ManyToOne
    private Lobby activeLobby;


    
    
    public Player(String userName) {
        this.userName = userName;
        //this.hostedLobbys = new ArrayList<Lobby>();
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


    public Lobby getActiveLobby() {
        return activeLobby;
    }

    public void setActiveLobby(Lobby lobby) {
        this.activeLobby = lobby;
    }

    public StreetPlan getStreetPlan() {
        return streetPlan;
    }

    public void setStreetPlan(StreetPlan streetPlan) {
        this.streetPlan = streetPlan;
    }

    
    public List<Lobby> getHostedLobbys() {
        return hostedLobbys;
    }

    public void setHostedLobbys(List<Lobby> hostedLobbys) {
        this.hostedLobbys = hostedLobbys;
    }

    public void addLobbyToHostedLobbys(Lobby lobby){
        this.hostedLobbys.add(lobby);
    }

    public void removeLobbyFromHostedLobbys(Lobby lobby){
        this.hostedLobbys.remove(lobby);
    }

     
    public boolean isHost(Lobby lobby){
        return lobby.getHostID() == this.id;
    }

    
}
