package de.hsrm.mi.swt02.backend.api.streetgrid.gridelements;


import de.hsrm.mi.swt02.backend.api.lobby.Lobby;
import de.hsrm.mi.swt02.backend.api.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class StreetGrid {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @Column(columnDefinition = "TEXT")
    private String gridData;

    @OneToMany(mappedBy = "streetGrid")
    public List<Player> players;

    @OneToOne(mappedBy = "streetGrid")
    public Lobby lobby;

    public StreetGrid(String gridData) {
        this.players = new ArrayList<>();
        this.gridData = gridData;
    }


    public StreetGrid() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetGrid that = (StreetGrid) o;
        return id == that.id && version == that.version && gridData.equals(that.gridData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, gridData);
    }

    public String getGridData() {
        return gridData;
    }

    public void setGridData(String gridData) {
        this.gridData = gridData;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
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
}
