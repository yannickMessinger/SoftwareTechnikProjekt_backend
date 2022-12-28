package de.hsrm.mi.swt02.backend.api.map.dto;

public record AddGameAssetRequestDTO(
        int objectTypeId,
        int x,
        int y,
        int rotation,
        String texture
) {}
