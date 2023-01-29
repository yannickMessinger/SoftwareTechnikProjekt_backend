package de.hsrm.mi.swt02.backend.api.game.crossroad.dto;

/**
 * Represents the request body for adding a new {@link Crossroad} to the system.
 * @param tlAmount is the amount of {@link TrafficLight} }
 */
public record AddCrossroadRequestDTO(int tlAmount) {}
