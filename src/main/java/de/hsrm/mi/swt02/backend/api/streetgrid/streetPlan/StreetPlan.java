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
    private String size;


    public StreetPlan() {
    }

    public StreetPlan(String lobbyName, String numOfPlayers, String lobbyMode, String size) {
        this.lobbyName = lobbyName;
        this.numOfPlayers = numOfPlayers;
        this.lobbyMode = lobbyMode;
        this.size = size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetPlan that = (StreetPlan) o;
        return lobbyName.equals(that.lobbyName) && numOfPlayers.equals(that.numOfPlayers) && lobbyMode.equals(that.lobbyMode) && size.equals(that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lobbyName, numOfPlayers, lobbyMode, size);
    }

    @Override
    public String toString() {
        return "StreetPlan{" +
                "lobbyName='" + lobbyName + '\'' +
                ", numOfPlayers='" + numOfPlayers + '\'' +
                ", lobbyMode='" + lobbyMode + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
