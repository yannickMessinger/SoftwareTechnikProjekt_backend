package de.hsrm.mi.swt02.backend.api.npc.dto;


import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectResponseDTO;
import de.hsrm.mi.swt02.backend.domain.npc.NpcNavInfo;



public record NpcNavInfoResponseDTO(
    long npcId,
     int newGameAssetRotation,
     GetMapObjectResponseDTO nextUpperMapObject,
     GetMapObjectResponseDTO nextnextUpperMapObject
) {
    public static NpcNavInfoResponseDTO from(NpcNavInfo info) {
        return new NpcNavInfoResponseDTO(
            info.getNpcId(),
            info.getNewGameAssetRotation(),
            GetMapObjectResponseDTO.from(info.getNextUpperMapObject()),
            GetMapObjectResponseDTO.from(info.getNextnextUpperMapObject())
        );
    }
}
