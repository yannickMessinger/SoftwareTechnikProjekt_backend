package de.hsrm.mi.swt02.backend.domain.game.trafficLight;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class TrafficLight {
    @Id @GeneratedValue
    private long id;
    private Light currentState;

    public TrafficLight() {
        this.currentState = Light.GREEN;
    }
    
    @Override
    public String toString() {
        return "TrafficLight [id=" + id + ", currentState=" + currentState + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        if (id != other.id)
            return false;
        if (currentState != other.currentState)
            return false;
        return true;
    }    
}

