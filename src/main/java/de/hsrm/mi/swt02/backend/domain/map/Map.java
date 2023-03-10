package de.hsrm.mi.swt02.backend.domain.map;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * class that represents game map that contains all its map objects, each map belongs to one lobby and belongs to one map owner. 
 */

@Entity
@Getter
@Setter
public class Map {

    @GeneratedValue
    @Id
    private long id;
    @Version
    private long version;

    private String mapName;

    @JsonFormat (pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @OneToMany (mappedBy = "map", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MapObject> mapObjects;

    @ManyToOne
    private Player mapOwner;

    private int sizeX;
    private int sizeY;

    @OneToOne (mappedBy = "map")
    private Lobby lobby;

    public Map (
            String mapName,
            LocalDate creationDate,
            int sizeX,
            int sizeY
    ) {
        this.mapName = mapName;
        this.creationDate = creationDate;
        this.mapObjects = new ArrayList<>();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Map () {
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map that = (Map) o;
        return id == that.id && version == that.version;
    }

    @Override
    public int hashCode () {
        return Objects.hash(id, version);
    }
}