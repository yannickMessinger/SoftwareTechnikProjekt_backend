package de.hsrm.mi.swt02.backend.api.maps;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.hsrm.mi.swt02.backend.api.player.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class Map {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String mapName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @ManyToOne
    private Player owner;

//  @OneToMany(mappedBy = "map")
//  private List<mapElement> mapElementList;

    public Map() {
        this.mapName = "";
        this.creationDate = null;
        this.owner = null;
//      this.mapElementList = new ArrayList<>();
    }

    public Map(String mapName, LocalDate creationDate, Player owner) {
        this.mapName = mapName;
        this.creationDate = creationDate;
        this.owner = owner;
    }

}
