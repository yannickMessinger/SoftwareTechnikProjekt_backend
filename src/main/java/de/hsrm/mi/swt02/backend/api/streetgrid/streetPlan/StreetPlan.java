package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Objects;

@Entity
public class StreetPlan {

    @GeneratedValue
    @Id
    private long id;
    @Version
    private long version;

    private String lobbyName;
    private String numOfPlayers;
    private String lobbyMode;
    private String sizeX;
    private String sizeY;


    public StreetPlan() {
    }

    public StreetPlan(String lobbyName, String numOfPlayers, String lobbyMode, String sizeX, String sizeY) {
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

    public String getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(String numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public String getLobbyMode() {
        return lobbyMode;
    }

    public void setLobbyMode(String lobbyMode) {
        this.lobbyMode = lobbyMode;
    }

    public String getSizeX() {
        return sizeX;
    }

    public void setSizeX(String size) {
        this.sizeX = size;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public String getSizeY() {
        return sizeY;
    }

    public void setSizeY(String sizeY) {
        this.sizeY = sizeY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetPlan that = (StreetPlan) o;
        return id == that.id && version == that.version && Objects.equals(lobbyName, that.lobbyName) && Objects.equals(numOfPlayers, that.numOfPlayers) && Objects.equals(lobbyMode, that.lobbyMode) && Objects.equals(sizeX, that.sizeX) && Objects.equals(sizeY, that.sizeY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, lobbyName, numOfPlayers, lobbyMode, sizeX, sizeY);
    }
}
