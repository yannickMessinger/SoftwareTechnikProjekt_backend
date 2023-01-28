package de.hsrm.mi.swt02.backend.domain.map;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class MapObjectType {

    @Id
    @GeneratedValue
    private long objectTypeId;
    @Version
    private long version;
    /*
     * groupId values meaning
     * 0 = street-element
     * 1 = building
     * 3 = vehicle
     */
    private long groupId;
    @Enumerated (EnumType.STRING)
    private ObjectTypeEnum type;
    private int rotation;
    private String name;
    private String texture;
    private String model3d;

    public MapObjectType () {
    }

    public MapObjectType (long objectTypeId, long groupId, ObjectTypeEnum type, int rotation, String name,
                          String texture, String model3d) {
        this.objectTypeId = objectTypeId;
        this.groupId = groupId;
        this.type = type;
        this.rotation = rotation;
        this.name = name;
        this.texture = texture;
        this.model3d = model3d;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (version ^ (version >>> 32));
        result = prime * result + (int) (objectTypeId ^ (objectTypeId >>> 32));
        result = prime * result + (int) (groupId ^ (groupId >>> 32));
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + rotation;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((texture == null) ? 0 : texture.hashCode());
        result = prime * result + ((model3d == null) ? 0 : model3d.hashCode());
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MapObjectType other = (MapObjectType) obj;
        if (version != other.version)
            return false;
        if (objectTypeId != other.objectTypeId)
            return false;
        if (groupId != other.groupId)
            return false;
        if (type != other.type)
            return false;
        if (rotation != other.rotation)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (texture == null) {
            if (other.texture != null)
                return false;
        } else if (!texture.equals(other.texture))
            return false;
        if (model3d == null) {
            return other.model3d == null;
        } else return model3d.equals(other.model3d);
    }
}
