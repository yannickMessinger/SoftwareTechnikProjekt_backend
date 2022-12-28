package de.hsrm.mi.swt02.backend.domain.map;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class GameAsset {
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    private int objectTypeId;
    private double x;
    private double y;
    private int rotation;
    private String texture;
    @ManyToOne
    private MapObject mapObject;

    public GameAsset() {
    }

    public GameAsset(int objectTypeId, double x, double y, int rotation, String texture) {
        this.objectTypeId = objectTypeId;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.texture = texture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameAsset gameAsset = (GameAsset) o;
        return id == gameAsset.id && version == gameAsset.version && objectTypeId == gameAsset.objectTypeId && x == gameAsset.x && y == gameAsset.y && rotation == gameAsset.rotation && texture.equals(gameAsset.texture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, objectTypeId, x, y, rotation, texture);
    }

    @Override
    public String toString() {
        return "GameAsset{" +
                "id=" + id +
                ", version=" + version +
                ", objectTypeId=" + objectTypeId +
                ", x=" + x +
                ", y=" + y +
                ", rotation=" + rotation +
                ", texture='" + texture + '\'' +
                '}';
    }
}
