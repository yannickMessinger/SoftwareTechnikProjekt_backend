package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class StreetObject {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;




    private String object_ID;
    private String x;
    private String y;
    private String rotation;



    public StreetObject(){

    }




    public StreetObject(String object_ID, String x, String y, String rotation) {
        this.object_ID = object_ID;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }




    public String getObject_ID() {
        return object_ID;
    }




    public void setObject_ID(String object_ID) {
        this.object_ID = object_ID;
    }




    public String getX() {
        return x;
    }




    public void setX(String x) {
        this.x = x;
    }




    public String getY() {
        return y;
    }




    public void setY(String y) {
        this.y = y;
    }




    public String getRotation() {
        return rotation;
    }




    public void setRotation(String rotation) {
        this.rotation = rotation;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((object_ID == null) ? 0 : object_ID.hashCode());
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        result = prime * result + ((rotation == null) ? 0 : rotation.hashCode());
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
        StreetObject other = (StreetObject) obj;
        if (object_ID == null) {
            if (other.object_ID != null)
                return false;
        } else if (!object_ID.equals(other.object_ID))
            return false;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x))
            return false;
        if (y == null) {
            if (other.y != null)
                return false;
        } else if (!y.equals(other.y))
            return false;
        if (rotation == null) {
            if (other.rotation != null)
                return false;
        } else if (!rotation.equals(other.rotation))
            return false;
        return true;
    }




    @Override
    public String toString() {
        return "StreetObject [x=" + x + ", y=" + y + ", rotation=" + rotation + "]";
    }




    public long getId() {
        return this.id;
    }



    
    


    


}
