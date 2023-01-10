package de.hsrm.mi.swt02.backend.domain.game.position;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class MapObjectPosition {
    @Id
    private long id;

    @Version
    private long version;
    private double posX;
    private double posY;
    private double rotation;

    @OneToOne(cascade = CascadeType.ALL)
    private MapObject mapObject;

    public MapObjectPosition(MapObject mapObject, double posX, double posY, double rotation) {
        this.mapObject = mapObject;
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
    }

    public MapObjectPosition() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapObjectPosition that = (MapObjectPosition) o;
        return id == that.id && version == that.version && Double.compare(that.posX, posX) == 0 && Double.compare(that.posY, posY) == 0 && Double.compare(that.rotation, rotation) == 0 && mapObject.equals(that.mapObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, posX, posY, rotation, mapObject);
    }

    @Override
    public String toString() {
        return "MapObjectPosition{" +
                "id=" + id +
                ", version=" + version +
                ", posX=" + posX +
                ", posY=" + posY +
                ", rotation=" + rotation +
                ", mapObject=" + mapObject +
                '}';
    }
}
