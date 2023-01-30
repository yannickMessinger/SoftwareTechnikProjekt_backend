package de.hsrm.mi.swt02.backend.domain.map;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents MapObject / MapTile that is placed in the 2D Editor, such as street (straight, curve, intersection),
 * buildings and nature elements. Contains two sets of position variables.
 * x and y coordinates from 2D StreetEditor and centerX3d and centerZ3d that represent the center point of each map object and are used
 * to place the MapObject correctly in the 3D world.
 */

@Entity
@Getter
@Setter
public class MapObject {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;
    private long objectTypeId;
    private int x;
    private int y;
    private int centerX3d;
    private int centerZ3d;
    /**
     * rotation * 90° (0-3)
     */
    private int rotation;

    @ManyToOne
    private Map map;

    @OneToMany (mappedBy = "mapObject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection (LazyCollectionOption.FALSE)
    private List<GameAsset> gameAssets;

    public MapObject () {
    }

    /**
     * 
     * @param objectTypeId id to differ between straight, curve, intersection, building etc.
     * @param x x coordinate in 2D Editor
     * @param y y coordiante in 2D Editor
     * @param rotation rotation of the MapObject
     * 
     * 3D center coordiantes (centerX3d,centerZ3d) are calculated and set later on.
     */
    public MapObject (long objectTypeId, int x, int y, int rotation) {
        this.objectTypeId = objectTypeId;
        this.x = x;
        this.y = y;
        this.rotation = rotation % 4;
        this.gameAssets = new ArrayList<GameAsset>();
        this.centerX3d = -1;
        this.centerZ3d = -1;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapObject that = (MapObject) o;
        return id == that.id && version == that.version;
    }

    @Override
    public int hashCode () {
        return Objects.hash(id, version);
    }

    @Override
    public String toString () {
        return "MapObject [id=" + id + ", version=" + version + ", objectTypeId=" + objectTypeId + ", x=" + x + ", y="
                + y + ", rotation=" + rotation + ", map=" + map + ", gameAssets=" + gameAssets + "]";
    }
}


