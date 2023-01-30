package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.GameAsset;

/**
 * DTO to transfer GameAssets like npc cars, trains and pedestrians
 * @param assetId id of the asset
 * @param objectTypeId objectTypeId of the asset to differ between car, train, pedestrian etc
 * @param x 2D x asset coordinate from editor
 * @param y 2D y asset coordinate from editor
 * @param x3d 3D x asset pixel coordinate for 3D world
 * @param z3d 3D z asset pixel coordinate for 3D world
 * @param rotation rotation of the asset between 0 and 3
 * @param texture path to connect right texture with asset
 * @param userId user id that the asset belongs to
 */
public record GameAssetDTO(
        long assetId,
        int objectTypeId,
        double x,
        double y,
        double x3d,
        double z3d,
        int rotation,
        String texture,
        int userId
) {

    public static GameAssetDTO from (GameAsset asset) {
        return new GameAssetDTO(
                asset.getId(),
                asset.getObjectTypeId(),
                asset.getX(),
                asset.getY(),
                asset.getX3d(),
                asset.getZ3d(),
                asset.getRotation(),
                asset.getTexture(),
                asset.getUserId()
        );
    }
}
