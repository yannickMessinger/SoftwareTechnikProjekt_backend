package de.hsrm.mi.swt02.backend.api.map.dto;

public record AddMapObjectRequestDTO(
        long objectTypeId,
        int x,
        int y,
        int rotation
) {}
