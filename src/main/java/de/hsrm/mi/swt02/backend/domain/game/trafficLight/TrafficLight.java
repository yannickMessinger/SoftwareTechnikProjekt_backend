package de.hsrm.mi.swt02.backend.domain.game.trafficLight;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import de.hsrm.mi.swt02.backend.domain.game.crossroad.Crossroad;
import lombok.Getter;
import lombok.Setter;

/**
 * A class representing a traffic light.
 * This class contains the current state of the traffic light and methods for
 * getting and setting the state.
 * It also overrides methods from Object class such as toString, hashCode, and
 * equals.
 */
@Getter
@Setter
@Entity
public class TrafficLight implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Light currentState = Light.GREEN;
    @ManyToOne
    private Crossroad cr;

    /**
     * Constructor for creating an instance of the TrafficLight with an
     * auto-generateed Id.
     */
    public TrafficLight() {}

    @Override
    public String toString() {
        return "TrafficLight [id=" + id + ", currentState=" + currentState + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
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
        TrafficLight other = (TrafficLight) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (currentState != other.currentState)
            return false;
        return true;
    }
}