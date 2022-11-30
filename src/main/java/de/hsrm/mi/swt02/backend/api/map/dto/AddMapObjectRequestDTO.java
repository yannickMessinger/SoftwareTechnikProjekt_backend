package de.hsrm.mi.swt02.backend.api.map.dto;

public record AddMapObjectRequestDTO(
        long objectTypeID,
        int x,
        int y,
        int rotation
) {}
