package de.hsrm.mi.swt02.backend.domain.game.position;

import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Setter
@Getter
@Entity
public class PlayerPosition {

    @Id
    private long id;

    @Version
    private long version;
    @NotNull
    private double posX;
    @NotNull
    private double posY;
    @OneToOne(mappedBy = "playerPosition")
    private Player player;

    @Override
    public String toString() {
        return "PlayerPosition{" +
                "id=" + id +
                ", version=" + version +
                ", posX=" + posX +
                ", posY=" + posY +
                ", player=" + player +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerPosition that = (PlayerPosition) o;
        return getId() == that.getId() && getVersion() == that.getVersion() && Double.compare(that.getPosX(), getPosX()) == 0 && Double.compare(that.getPosY(), getPosY()) == 0 && Objects.equals(getPlayer(), that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion(), getPosX(), getPosY(), getPlayer());
    }
}
