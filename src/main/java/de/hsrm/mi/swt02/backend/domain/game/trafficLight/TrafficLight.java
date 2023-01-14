package de.hsrm.mi.swt02.backend.domain.game.trafficLight;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficLight {
    private Light currentState;

    public TrafficLight() {
        this.currentState = Light.GREEN;
    }
    
    @Override
    public String toString() {
        return "TrafficLight [currentState=" + currentState + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        if (currentState != other.currentState)
            return false;
        return true;
    }    
}

