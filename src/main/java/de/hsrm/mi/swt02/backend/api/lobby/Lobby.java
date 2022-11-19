package de.hsrm.mi.swt02.backend.api.lobby;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.api.player.Player;

@Entity
public class Lobby {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String lobbyName;
    private int numOfPlayers;
    private LobbyMode lobbyMode;
    
    //add relations
    @OneToMany(mappedBy = "lobby")
    private List<Player> playerList;


    public Lobby(){
        this.lobbyName = "";
        this.numOfPlayers = 0;
        this.lobbyMode = LobbyMode.BUILD_MODE;
        this.playerList = new ArrayList<Player>();
    }


    public Lobby(String lobbyName, int numOfPlayers,LobbyMode lobbyMode){
        this.lobbyName = lobbyName;
        this.lobbyMode = lobbyMode;
        this.numOfPlayers = numOfPlayers;
        this.playerList = new ArrayList<Player>();
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


    public String getLobbyName() {
        return lobbyName;
    }


    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }


    public int getNumOfPlayers() {
        return numOfPlayers;
    }


    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }


    public LobbyMode getLobbyMode() {
        return lobbyMode;
    }


    public void setLobbyMode(LobbyMode lobbyMode) {
        this.lobbyMode = lobbyMode;
    }

    
    public List<Player> getPlayerList() {
        return playerList;
    }

    
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    
    public void addPlayerToPlayerlist(Player addPlayer){
        this.playerList.add(addPlayer);

    }

    
    public void removePlayerFromPlayerList(Player removePlayer){
            this.playerList.remove(removePlayer);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (version ^ (version >>> 32));
        result = prime * result + ((lobbyName == null) ? 0 : lobbyName.hashCode());
        result = prime * result + numOfPlayers;
        result = prime * result + ((lobbyMode == null) ? 0 : lobbyMode.hashCode());
        result = prime * result + ((playerList == null) ? 0 : playerList.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lobby other = (Lobby) obj;
        if (id != other.id)
            return false;
        if (version != other.version)
            return false;
        if (lobbyName == null) {
            if (other.lobbyName != null)
                return false;
        } else if (!lobbyName.equals(other.lobbyName))
            return false;
        if (numOfPlayers != other.numOfPlayers)
            return false;
        if (lobbyMode != other.lobbyMode)
            return false;
        if (playerList == null) {
            if (other.playerList != null)
                return false;
        } else if (!playerList.equals(other.playerList))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Lobby [lobbyName=" + lobbyName + ", numOfPlayers=" + numOfPlayers + ", lobbyState=" + lobbyMode + "]";
    }


    

    


    

    
}
