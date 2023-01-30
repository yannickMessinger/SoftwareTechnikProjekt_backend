package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO to transfer MapObjects from backend to frontend.
 * @param objectId unique id of the Mapobject
 * @param objectTypeId objectTypeId to differ between different kinds 
 * @param x x 2D Editor coordinate of the MapObject.
 * @param y y 2D Editor coordinate of the MapObject
 * @param centerX3d 3D x center coordinate for 3D world
 * @param centerZ3d 3D z center coordinate for 3D world
 * @param rotation rotation of the MapObject
 * @param game_assets list of gameAssets that were placed on the MapObject
 */
public record GetMapObjectResponseDTO(
        long objectId,
        long objectTypeId,
        int x,
        int y,
        int centerX3d,
        int centerZ3d,
        int rotation,
        List<GameAssetDTO> game_assets

) {
    public static GetMapObjectResponseDTO from (MapObject s) {
        List<GameAssetDTO> gameAssetRequestDTOS = new ArrayList<>();
        s.getGameAssets().forEach(ele -> gameAssetRequestDTOS.add(GameAssetDTO.from(ele)));

        return new GetMapObjectResponseDTO(
                s.getId(),
                s.getObjectTypeId(),
                s.getX(),
                s.getY(),
                s.getCenterX3d(),
                s.getCenterZ3d(),
                s.getRotation(),
                gameAssetRequestDTOS
        );
    }
}
