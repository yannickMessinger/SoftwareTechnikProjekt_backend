package de.hsrm.mi.swt02.backend.domain.game.trafficLight;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
    A class representing a traffic light.
    This class contains the current state of the traffic light and methods for getting and setting the state.
    It also overrides methods from Object class such as toString, hashCode, and equals.
*/
@Getter
@Setter
@Component
public class TrafficLight {
    private Light currentState;

    /**
     * Constructor for creating an instance of the TrafficLight.
     * 
     * The initial state of the traffic light is set to GREEN.
     */
    public TrafficLight() {
        this.currentState = Light.GREEN;
    }
    
    /**
     * This method returns a string representation of the traffic light object.
     * 
     * @return the string representation of the traffic light object.
     */
    @Override
    public String toString() {
        return "TrafficLight [currentState=" + currentState + "]";
    }

    /**
     * This method returns the hash code of the traffic light object.
     * 
     * @return the hash code of the traffic light object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
        return result;
    }

    /**
     * This method compares the current traffic light object with another object.
     * 
     * @param obj the object
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
        if (currentState != other.currentState)
            return false;
        return true;
    }    
}

