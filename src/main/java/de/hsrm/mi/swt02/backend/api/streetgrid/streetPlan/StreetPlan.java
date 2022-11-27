package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.api.lobby.LobbyMode;



@Entity
public class StreetPlan {

    @GeneratedValue
    @Id
    private long id;
    @Version
    private long version;

    private String lobbyName;
    private int numOfPlayers;
    private LobbyMode lobbyMode;
    private int sizeX;
    private int sizeY;


    public StreetPlan() {
    }

    public StreetPlan(String lobbyName, int numOfPlayers, LobbyMode lobbyMode, int sizeX, int sizeY) {
        this.lobbyName = lobbyName;
        this.numOfPlayers = numOfPlayers;
        this.lobbyMode = lobbyMode;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lobbyName == null) ? 0 : lobbyName.hashCode());
        result = prime * result + numOfPlayers;
        result = prime * result + ((lobbyMode == null) ? 0 : lobbyMode.hashCode());
        result = prime * result + sizeX;
        result = prime * result + sizeY;
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
        StreetPlan other = (StreetPlan) obj;
        if (lobbyName == null) {
            if (other.lobbyName != null)
                return false;
        } else if (!lobbyName.equals(other.lobbyName))
            return false;
        if (numOfPlayers != other.numOfPlayers)
            return false;
        if (lobbyMode != other.lobbyMode)
            return false;
        if (sizeX != other.sizeX)
            return false;
        if (sizeY != other.sizeY)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "StreetPlan [lobbyName=" + lobbyName + ", numOfPlayers=" + numOfPlayers + ", lobbyMode=" + lobbyMode
                + ", sizeX=" + sizeX + ", sizeY=" + sizeY + "]";
    }

    

    

    
}
