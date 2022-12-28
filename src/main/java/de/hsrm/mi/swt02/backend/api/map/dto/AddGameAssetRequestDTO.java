package de.hsrm.mi.swt02.backend.api.map.dto;

public record AddGameAssetRequestDTO(
        int objectTypeId,
        double x,
        double y,
        int rotation,
        String texture
) {}
