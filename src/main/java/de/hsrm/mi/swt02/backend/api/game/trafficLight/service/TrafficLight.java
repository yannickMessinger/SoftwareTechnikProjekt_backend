package de.hsrm.mi.swt02.backend.api.game.trafficLight.service;

public record TrafficLight(Light currentState) {
    public TrafficLight {
        currentState = Light.GREEN;
    }
}

