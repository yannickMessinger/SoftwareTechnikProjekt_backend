package de.hsrm.mi.swt02.backend.domain.game.position;

import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
public class PlayerPosition {

    @Id
    private long id;

    @Version
    private long version;
    private double posX;
    private double posY;
    private double rotation;
    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    public PlayerPosition(Player player, double posX, double posY, double rotation) {
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
        this.player = player;
    }

    public PlayerPosition() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerPosition that = (PlayerPosition) o;
        return getId() == that.getId() && getVersion() == that.getVersion() && Double.compare(that.getPosX(), getPosX()) == 0 && Double.compare(that.getPosY(), getPosY()) == 0 && Double.compare(that.getRotation(), getRotation()) == 0 && getPlayer().equals(that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion(), getPosX(), getPosY(), getRotation(), getPlayer());
    }

    @Override
    public String toString() {
        return "PlayerPosition{" +
                "id=" + id +
                ", version=" + version +
                ", posX=" + posX +
                ", posY=" + posY +
                ", rotation=" + rotation +
                ", player=" + player +
                '}';
    }
}
