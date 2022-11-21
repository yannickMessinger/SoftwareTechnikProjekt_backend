package de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;

@Entity
public class StreetConstructionKit {
    
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String Object_ID; 
    private String Object_Name; 
    private String img;
    private String type;
    private String rotatable;


    public StreetConstructionKit(){

    }



    public StreetConstructionKit(String object_ID, String object_Name, String img, String type, String rotatable) {
        Object_ID = object_ID;
        Object_Name = object_Name;
        this.img = img;
        this.type = type;
        this.rotatable = rotatable;
    }



    public String getObject_ID() {
        return Object_ID;
    }



    public String getObject_Name() {
        return Object_Name;
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



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (version ^ (version >>> 32));
        result = prime * result + ((Object_ID == null) ? 0 : Object_ID.hashCode());
        result = prime * result + ((Object_Name == null) ? 0 : Object_Name.hashCode());
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
        if (Object_ID == null) {
            if (other.Object_ID != null)
                return false;
        } else if (!Object_ID.equals(other.Object_ID))
            return false;
        if (Object_Name == null) {
            if (other.Object_Name != null)
                return false;
        } else if (!Object_Name.equals(other.Object_Name))
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
        return "StreetConstructionKit [Object_ID=" + Object_ID + ", Object_Name=" + Object_Name + "]";
    }

    


    

    


    


}
