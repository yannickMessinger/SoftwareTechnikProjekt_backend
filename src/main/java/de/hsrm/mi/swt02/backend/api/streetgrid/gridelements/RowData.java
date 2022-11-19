package de.hsrm.mi.swt02.backend.api.streetgrid.gridelements;

import java.util.ArrayList;
import java.util.List;

public class RowData {
    
    private List<ColumnData> columnData;


    public RowData(){
        this.columnData = new ArrayList<ColumnData>();
    }


    public List<ColumnData> getColumnData() {
        return columnData;
    }


    public void setColumnData(List<ColumnData> columnData) {
        this.columnData = columnData;
    }

    public void addColumnData(ColumnData colData){
        this.columnData.add(colData);
    }


    
}
