package de.hsrm.mi.swt02.backend.domain.map;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Setter
@Getter
public class MapObjectType {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private long objectTypeID;
    private String objectName;
    private String img;
    private ObjectTypeEnum type;


    public MapObjectType() {
    }

    public MapObjectType(long objectTypeID, String objectName, String img, ObjectTypeEnum type, boolean rotatable) {
        this.objectTypeID = objectTypeID;
        this.objectName = objectName;
        this.img = img;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapObjectType that = (MapObjectType) o;
        return id == that.id && version == that.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
