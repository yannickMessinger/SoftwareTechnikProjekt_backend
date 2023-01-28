package de.hsrm.mi.swt02.backend.domain.lobby;

import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Lobby {

    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    private String lobbyName;
    private int numOfPlayers;
    private LobbyModeEnum lobbyMode;

    @OneToMany (mappedBy = "activeLobby")
    private List<Player> playerList;
    @ManyToOne
    private Player host;
    @OneToOne
    private Map map;

    public Lobby () {
        this.lobbyName = "";
        this.numOfPlayers = 0;
        this.lobbyMode = LobbyModeEnum.BUILD_MODE;
        this.playerList = new ArrayList<>();
    }


    public Lobby (String lobbyName, int numOfPlayers, LobbyModeEnum lobbyMode) {
        this.lobbyName = lobbyName;
        this.lobbyMode = lobbyMode;
        this.numOfPlayers = numOfPlayers;
        this.playerList = new ArrayList<>();
    }


    public void addPlayerToPlayerlist (Player addPlayer) {
        this.playerList.add(addPlayer);

    }

    public void removePlayerFromPlayerList (Player removePlayer) {
        this.playerList.remove(removePlayer);
    }


    public int getNumOfPlayersInLobby () {
        return this.getPlayerList().size();
    }


    public Player getHost () {
        return host;
    }


    public void setHost (Player host) {
        this.host = host;
    }

    public long getHostId () {
        return this.host.getId();
    }


    @Override
    public int hashCode () {
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
    public boolean equals (Object obj) {
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
            return other.playerList == null;
        } else return playerList.equals(other.playerList);
    }


    @Override
    public String toString () {
        return "Lobby [mapName=" + lobbyName + ", numOfPlayers=" + numOfPlayers + ", lobbyState=" + lobbyMode + "]";
    }


    public boolean isHostedBy (long possiblehostId) {
        return this.host.getId() == possiblehostId;
    }
}
