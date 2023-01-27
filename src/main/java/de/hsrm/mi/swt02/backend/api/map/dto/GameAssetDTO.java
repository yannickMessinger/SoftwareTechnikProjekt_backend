package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.GameAsset;

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
