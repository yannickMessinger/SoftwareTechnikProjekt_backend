package de.hsrm.mi.swt02.backend.api.streetgrid.gridelements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

//@Entity
public class StreetGrid {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String mapname;


    private List<ArrayList> gridData;


    public StreetGrid(){
        this.mapname= "testMap";
        this. gridData = new ArrayList<ArrayList>();

    }

    public void parseStreetData(Object dto){
        List<ArrayList> parseList = (List<ArrayList>)dto;
        
        }
    }


