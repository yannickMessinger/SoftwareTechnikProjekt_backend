package de.hsrm.mi.swt02.backend.api.npc.dto;


import de.hsrm.mi.swt02.backend.api.map.dto.GetMapObjectResponseDTO;
import de.hsrm.mi.swt02.backend.domain.npc.NpcNavInfo;
/**
 * Repsonse DTO Object to transfer navigation updates from backend to frontend.
 */

public record NpcNavInfoResponseDTO(
        long npcId,
        int newGameAssetRotation,
        GetMapObjectResponseDTO nextUpperMapObject,
        GetMapObjectResponseDTO nextnextUpperMapObject
) {
    /**
     * 
     * @param info new navigation information for the npc
     * @return new navigation information for the npc, containing the id of the npc
     * to pass info to correct npc onjct in frontend, new rotation that npc needs to be set to and
     * also new MapObjects to update the intern MapObjects of the npc.
     */
    public static NpcNavInfoResponseDTO from (NpcNavInfo info) {
        return new NpcNavInfoResponseDTO(
                info.getNpcId(),
                info.getNewGameAssetRotation(),
                GetMapObjectResponseDTO.from(info.getNextUpperMapObject()),
                GetMapObjectResponseDTO.from(info.getNextnextUpperMapObject())
        );
    }
}
