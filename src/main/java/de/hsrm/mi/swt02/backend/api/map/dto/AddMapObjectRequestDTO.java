package de.hsrm.mi.swt02.backend.api.map.dto;


import java.util.List;
/**
 * DTO that contains information to add single new MapObject from frontend to backend
 * @param objectTypeId id to differ between different types like straight, curve intersection, building etc.
 * @param x x 2D Editor coordinate of the MapObject that is supposed to be added to backend. 
 * @param y y 2D Editor coordinate of the MapObject that is supposed to be added to backend.
 * @param rotation rotation of the MapObject 
 * @param game_assets list of GameAssets that were placed on the MapObject
 */
public record AddMapObjectRequestDTO(
        long objectTypeId,
        int x,
        int y,
        int rotation,
        List<GameAssetDTO> game_assets
) {
}
