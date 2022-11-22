package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

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

    private String object_ID; 
    private String object_Name; 
    private String img;
    private String type;
    private String rotatable;


    public StreetConstructionKit(){

    }



    public StreetConstructionKit(String object_ID, String object_Name, String img, String type, String rotatable) {
        this.object_ID = object_ID;
        this.object_Name = object_Name;
        this.img = img;
        this.type = type;
        this.rotatable = rotatable;
    }



    public String getObject_ID() {
        return object_ID;
    }



    public String getObject_Name() {
        return object_Name;
    }



    public String getImg() {
        return img;
    }



    public String getType() {
        return type;
    }



    public String getRotatable() {
        return rotatable;
    }

    



    public long getId() {
        return id;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (version ^ (version >>> 32));
        result = prime * result + ((object_ID == null) ? 0 : object_ID.hashCode());
        result = prime * result + ((object_Name == null) ? 0 : object_Name.hashCode());
        result = prime * result + ((img == null) ? 0 : img.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((rotatable == null) ? 0 : rotatable.hashCode());
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
        if (id != other.id)
            return false;
        if (version != other.version)
            return false;
        if (object_ID == null) {
            if (other.object_ID != null)
                return false;
        } else if (!object_ID.equals(other.object_ID))
            return false;
        if (object_Name == null) {
            if (other.object_Name != null)
                return false;
        } else if (!object_Name.equals(other.object_Name))
            return false;
        if (img == null) {
            if (other.img != null)
                return false;
        } else if (!img.equals(other.img))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (rotatable == null) {
            if (other.rotatable != null)
                return false;
        } else if (!rotatable.equals(other.rotatable))
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "StreetConstructionKit [Object_ID=" + object_ID + ", Object_Name=" + object_Name + "]";
    }

    


    

    


    


}
