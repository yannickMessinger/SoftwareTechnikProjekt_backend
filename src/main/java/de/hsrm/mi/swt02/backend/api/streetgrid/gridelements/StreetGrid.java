package de.hsrm.mi.swt02.backend.api.streetgrid.gridelements;


import java.util.Objects;
import javax.persistence.*;

@Entity
public class StreetGrid {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @Column(columnDefinition = "TEXT")
    private String gridData;

    public StreetGrid(String gridData) {
        this.gridData = gridData;
    }

    public StreetGrid() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetGrid that = (StreetGrid) o;
        return id == that.id && version == that.version && gridData.equals(that.gridData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, gridData);
    }

    public String getGridData() {
        return gridData;
    }

    public void setGridData(String gridData) {
        this.gridData = gridData;
    }
}
