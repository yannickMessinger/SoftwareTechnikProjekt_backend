package de.hsrm.mi.swt02.backend.npc;


import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectResponseDTO;



public record NpcInfoResponseDTO(
    long npcId,
     int newGameAssetRotation,
     GetMapObjectResponseDTO nextUpperMapObject,
     GetMapObjectResponseDTO nextnextUpperMapObject
) {
    public static NpcInfoResponseDTO from(NpcInfo info) {
        return new NpcInfoResponseDTO(
            info.getNpcId(),
            info.getNewGameAssetRotation(),
            GetMapObjectResponseDTO.from(info.getNextUpperMapObject()),
            GetMapObjectResponseDTO.from(info.getNextnextUpperMapObject())
        );
    }
}
