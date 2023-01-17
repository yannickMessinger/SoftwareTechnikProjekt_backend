package de.hsrm.mi.swt02.backend.npc;

import de.hsrm.mi.swt02.backend.api.map.dto.AddMapObjectRequestDTO;
import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectResponseDTO;

import java.util.List;

public record NpcInfoResponseDTO(
     int newGameAssetRotation,
     GetMapObjectResponseDTO currentMapObject,
     GetMapObjectResponseDTO nextUpperMapObject
) {
    public static NpcInfoResponseDTO from(NpcInfo info) {
        return new NpcInfoResponseDTO(
            info.getNewGameAssetRotation(),
            GetMapObjectResponseDTO.from(info.getCurrentMapObject()),
            GetMapObjectResponseDTO.from(info.getNextUpperMapObject())
        );
    }
}
