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
    /**
     * Unique identifier for the traffic light.
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * The current state of the light.
     */
    private Light currentState = Light.GREEN;
    /**
     * The crossroad the traffic light is located.
     */
    @ManyToOne
    private Crossroad cr;

    /**
     * Default constructor.
     */
    public TrafficLight() {
    }

    /**
     * Returns a string representation of the traffic light.
     *
     * @return String representation of the traffic light.
     */
    @Override
    public String toString() {
        return "TrafficLight [id=" + id + ", currentState=" + currentState + "]";
    }

    /**
     * Generates hash code for the traffic light.
     *
     * @return Hash code for the traffic light.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
        return result;
    }

    /**
     * Compares the traffic light with another object.
     *
     * @param obj Object to compare with the traffic light.
     * @return true if the objects are equal, false otherwise.
     */
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