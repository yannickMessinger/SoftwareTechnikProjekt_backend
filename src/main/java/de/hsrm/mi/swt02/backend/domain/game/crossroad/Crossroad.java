package de.hsrm.mi.swt02.backend.domain.game.crossroad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.TrafficLight;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a Crossroad in the game.
 * It contains a list of TrafficLight objects and information about the
 * arrangement of traffic lights.
 * It also contains methods for equality and hashing.
 * 
 */

@Setter
@Getter
@Entity
public class Crossroad implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "cr", cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    private List<TrafficLight> trafficLights = new ArrayList<>();
    private boolean tlArrangement = false;
    private boolean running = false;

    public Crossroad() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((trafficLights == null) ? 0 : trafficLights.hashCode());
        result = prime * result + (tlArrangement ? 1231 : 1237);
        result = prime * result + (running ? 1231 : 1237);
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
        Crossroad other = (Crossroad) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (trafficLights == null) {
            if (other.trafficLights != null)
                return false;
        } else if (!trafficLights.equals(other.trafficLights))
            return false;
        if (tlArrangement != other.tlArrangement)
            return false;
        if (running != other.running)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Crossroad [id=" + id + ", trafficLights=" + trafficLights + ", tlArrangement=" + tlArrangement
                + ", running=" + running + "]";
    }
}
