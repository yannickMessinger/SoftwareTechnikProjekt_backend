package de.hsrm.mi.swt02.backend.domain.map;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
/**
 * Class that represents placable content that is placed on MapObject such as car, pedestrian or train. Contains to sets of position variables.
 * x and y come from the 2d editor, x3d and z3d are the coresponding pixel positions in the 3D presentation.
 */
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
    private double x3d;
    private double z3d;
    private int rotation;
    private String texture;
    @ManyToOne
    private MapObject mapObject;
    private int userId;

    public GameAsset () {
    }
    
    /**
     * 
     * @param objectTypeId Id to identfiy type of asset, such as car, pedestrian or train. 
     * @param x x coordinate of the placing in the 2D Street Editor, between 0 and 1
     * @param y y coordinate of the placing in the 2D Street Editor between 0 and 1
     * @param rotation rotation of the Asset, betwenn 0 and 3
     * @param texture string that contains path to the right texture
     * @param userId id of the user who placed the asset
     * 
     * x3d and z3d are calculated and set afterwards.
     */
    public GameAsset (int objectTypeId, double x, double y, int rotation, String texture, int userId) {
        this.objectTypeId = objectTypeId;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.texture = texture;
        this.userId = userId;
        this.x3d = 0.0; 
        this.z3d = 0.0;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameAsset gameAsset = (GameAsset) o;
        return id == gameAsset.id && version == gameAsset.version && objectTypeId == gameAsset.objectTypeId && x == gameAsset.x && y == gameAsset.y && rotation == gameAsset.rotation && texture.equals(gameAsset.texture);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id, version, objectTypeId, x, y, rotation, texture);
    }

    @Override
    public String toString () {
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
