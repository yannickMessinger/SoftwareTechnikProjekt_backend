package de.hsrm.mi.swt02.backend.npc;

public record NpcInfoResponseDTO(
     long nextMapEleobjectTypeId,
     int nextMapEleX,
     int nextMapEleY,
     int nextMapElerotation,
     int newGameAssetRotation
) {
    public static NpcInfoResponseDTO from(NpcInfo info) {
        return new NpcInfoResponseDTO(
            info.getNextMapEleobjectTypeId(),
            info.getNextMapEleX(),
            info.getNextMapEleY(),
            info.getNextMapElerotation(),
            info.getNewGameAssetRotation()
        );
    }
}
