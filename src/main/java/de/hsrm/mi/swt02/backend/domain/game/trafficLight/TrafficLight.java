package de.hsrm.mi.swt02.backend.domain.game.trafficLight;

import java.util.UUID;

import org.springframework.stereotype.Component;

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
@Component
public class TrafficLight {

    /*
     * @Id @GeneratedValue
     * private long id;
     * For the case we want to persist the lights in a database.
     */
    private String id;
    private Light currentState = Light.GREEN;

    /**
     * Constructor for creating an instance of the TrafficLight with an
     * auto-generateed Id.
     */
    public TrafficLight() {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

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
