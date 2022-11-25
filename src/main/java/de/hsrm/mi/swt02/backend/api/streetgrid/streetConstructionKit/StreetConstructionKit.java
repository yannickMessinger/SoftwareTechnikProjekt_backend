package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.enums.KitType;

/**
 * Entity thats used to persist Elements of the StreetEditor, very similar with corresponding DTO's but with additional id
 * and version attributes for db management
 * @param object_ID id of the object from frontend
 * @param object_Name name of the object, e.g. straight road tile 
 * @param img corresponding image of the tile
 * @param type to specifiy type of element, e.g. road, building etc.
 * @param rotatable angle of rotation the element is in (heading).
 */

@Entity
public class StreetConstructionKit {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private long object_ID; 
    private String object_Name; 
    private String img;
    private KitType type;
    private boolean rotatable;


    public StreetConstructionKit(){

    }



    public StreetConstructionKit(long object_ID, String object_Name, String img, KitType type, boolean rotatable) {
        this.object_ID = object_ID;
        this.object_Name = object_Name;
        this.img = img;
        this.type = type;
        this.rotatable = rotatable;
    }



    public long getObject_ID() {
        return object_ID;
    }



    public void setObject_ID(long object_ID) {
        this.object_ID = object_ID;
    }



    public String getObject_Name() {
        return object_Name;
    }



    public void setObject_Name(String object_Name) {
        this.object_Name = object_Name;
    }



    public String getImg() {
        return img;
    }



    public void setImg(String img) {
        this.img = img;
    }



    public KitType getKitType() {
        return type;
    }



    public void setKitType(KitType type) {
        this.type = type;
    }



    public boolean isRotatable() {
        return rotatable;
    }



    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }



    public long getId() {
        return id;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (object_ID ^ (object_ID >>> 32));
        result = prime * result + ((object_Name == null) ? 0 : object_Name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        StreetConstructionKit other = (StreetConstructionKit) obj;
        if (object_ID != other.object_ID)
            return false;
        if (object_Name == null) {
            if (other.object_Name != null)
                return false;
        } else if (!object_Name.equals(other.object_Name))
            return false;
        if (type != other.type)
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "StreetConstructionKit [object_ID=" + object_ID + ", object_Name=" + object_Name + ", type=" + type
                + "]";
    }


    
    
    
    


    

    


    


}
