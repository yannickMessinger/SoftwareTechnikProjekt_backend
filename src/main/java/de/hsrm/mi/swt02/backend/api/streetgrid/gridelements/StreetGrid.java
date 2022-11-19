package de.hsrm.mi.swt02.backend.api.streetgrid.gridelements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import de.hsrm.mi.swt02.backend.api.streetgrid.dtos.AddColumnDataDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.dtos.AddStreetGridRequestDTO;

//@Entity
public class StreetGrid {
    
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String mapname;


    private List<List<ColumnData>> gridData;


    public StreetGrid(){
        this.mapname= "testMap";
        this. gridData = new ArrayList<List<ColumnData>>();

    }


    public List<List<ColumnData>> getGridData() {
        return gridData;
    }


    public void setGridData(List<List<ColumnData>> gridData) {
        this.gridData = gridData;
    }

    

}
