package de.hsrm.mi.swt02.backend.api.streetgrid.streetObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Entity thats used to persist Elements of StreetObject's , very similar with corresponding DTO's but with additional id
 * and version attributes for db management
 * @param object_ID id of the object from frontend
 * @param x X-Coordinate / Position in StreetGrid / MapEditor
 * @param y Y-Coordinate / Position in StreetGrid / MapEditor
 * @param rotation angle of rotation the element was set to.
 */

@Entity
public class StreetObject {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;




    private long object_ID;
    private int x;
    private int y;
    private int rotation;



    public StreetObject(){

    }




    public StreetObject(long object_ID, int x, int y, int rotation) {
        this.object_ID = object_ID;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }




    public long getObject_ID() {
        return object_ID;
    }




    public void setObject_ID(long object_ID) {
        this.object_ID = object_ID;
    }




    public int getX() {
        return x;
    }




    public void setX(int x) {
        this.x = x;
    }




    public int getY() {
        return y;
    }




    public void setY(int y) {
        this.y = y;
    }




    public int getRotation() {
        return rotation;
    }




    public void setRotation(int rotation) {
        this.rotation = rotation;
    }


    
    public long getId() {
        return this.id;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (object_ID ^ (object_ID >>> 32));
        result = prime * result + x;
        result = prime * result + y;
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
        if (object_ID != other.object_ID)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }




    @Override
    public String toString() {
        return "StreetObject [object_ID=" + object_ID + ", x=" + x + ", y=" + y + ", rotation=" + rotation + "]";
    }


    



    
    


    


}
