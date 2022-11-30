package de.hsrm.mi.swt02.backend.api.maps;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.hsrm.mi.swt02.backend.api.lobby.Lobby;
import de.hsrm.mi.swt02.backend.api.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
public class Map {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String mapName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @OneToMany(mappedBy = "map")
    private List<Lobby> lobbies;

    @ManyToOne
    private Player owner;

//  @OneToMany(mappedBy = "map")
//  private List<mapElement> mapElementList;

    public Map() {
        this.mapName = "";
        this.creationDate = null;
        this.owner = null;
//      this.mapElementList = new ArrayList<>();
    }

    public Map(String mapName, LocalDate creationDate, Player owner) {
        this.mapName = mapName;
        this.creationDate = creationDate;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return getId() == map.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
