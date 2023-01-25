package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;

import java.util.ArrayList;
import java.util.List;

public record GetMapObjectResponseDTO(
        long objectTypeId,
        int x,
        int y,
        int rotation,
        List<GameAssetDTO> game_assets

) {
    public static GetMapObjectResponseDTO from (MapObject s) {
        List<GameAssetDTO> gameAssetRequestDTOS = new ArrayList<>();
        s.getGameAssets().forEach(ele -> gameAssetRequestDTOS.add(GameAssetDTO.from(ele)));

        return new GetMapObjectResponseDTO(
                s.getObjectTypeId(),
                s.getX(),
                s.getY(),
                s.getRotation(),
                gameAssetRequestDTOS
        );
    }
}
