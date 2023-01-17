package de.hsrm.mi.swt02.backend.domain.game.crossroad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

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
@Component
public class Crossroad {
    @Id
    @GeneratedValue
    private long id;
    private List<TrafficLight> trafficLights = new ArrayList<>();
    private boolean tlArrangement = false;

    public Crossroad() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((trafficLights == null) ? 0 : trafficLights.hashCode());
        result = prime * result + (tlArrangement ? 1231 : 1237);
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
        if (id != other.id)
            return false;
        if (trafficLights == null) {
            if (other.trafficLights != null)
                return false;
        } else if (!trafficLights.equals(other.trafficLights))
            return false;
        if (tlArrangement != other.tlArrangement)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Crossroad [id=" + id + ", trafficLights=" + trafficLights + ", tlArrangement=" + tlArrangement + "]";
    }
}
