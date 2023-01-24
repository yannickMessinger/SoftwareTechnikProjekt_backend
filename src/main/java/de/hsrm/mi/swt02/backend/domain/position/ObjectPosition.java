package de.hsrm.mi.swt02.backend.domain.position;

import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Arrays;
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
    double []rotation;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public ObjectPosition() {}

    public ObjectPosition(long mapObjectId, double posX, double posY, double []rotation) {
        this.mapObjectId = mapObjectId;
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (version ^ (version >>> 32));
        result = prime * result + (int) (mapObjectId ^ (mapObjectId >>> 32));
        long temp;
        temp = Double.doubleToLongBits(posX);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(posY);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + Arrays.hashCode(rotation);
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        ObjectPosition other = (ObjectPosition) obj;
        if (id != other.id)
            return false;
        if (version != other.version)
            return false;
        if (mapObjectId != other.mapObjectId)
            return false;
        if (Double.doubleToLongBits(posX) != Double.doubleToLongBits(other.posX))
            return false;
        if (Double.doubleToLongBits(posY) != Double.doubleToLongBits(other.posY))
            return false;
        if (!Arrays.equals(rotation, other.rotation))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

    

}  