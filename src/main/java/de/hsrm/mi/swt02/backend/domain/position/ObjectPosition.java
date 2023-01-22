package de.hsrm.mi.swt02.backend.domain.position;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
public class ObjectPosition {

    @Id
    @GeneratedValue
    long id;
    @Version
    long version;

    long mapObjectId;
    double posX;
    double posY;
    double rotation;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public ObjectPosition() {}

    public ObjectPosition(long mapObjectId, double posX, double posY, double rotation) {
        this.mapObjectId = mapObjectId;
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectPosition that = (ObjectPosition) o;
        return getId() == that.getId() && getVersion() == that.getVersion() && getMapObjectId() == that.getMapObjectId() && Double.compare(that.getPosX(), getPosX()) == 0 && Double.compare(that.getPosY(), getPosY()) == 0 && Double.compare(that.getRotation(), getRotation()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion(), getMapObjectId(), getPosX(), getPosY(), getRotation());
    }

    @Override
    public String toString() {
        return "ObjectPosition{" +
                "id=" + id +
                ", version=" + version +
                ", mapObjectId=" + mapObjectId +
                ", posX=" + posX +
                ", posY=" + posY +
                ", rotation=" + rotation +
                '}';
    }
}
