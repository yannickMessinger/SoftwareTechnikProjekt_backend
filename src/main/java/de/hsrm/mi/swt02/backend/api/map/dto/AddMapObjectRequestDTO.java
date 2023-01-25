package de.hsrm.mi.swt02.backend.api.map.dto;


import java.util.List;

public record AddMapObjectRequestDTO(
        long objectTypeId,
        int x,
        int y,
        int rotation,
        List<GameAssetDTO> game_assets
) {
}
