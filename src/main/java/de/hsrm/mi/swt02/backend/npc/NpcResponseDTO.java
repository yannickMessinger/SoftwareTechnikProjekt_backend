package de.hsrm.mi.swt02.backend.npc;

public record NpcResponseDTO(
     long nextMapEleobjectTypeId,
     int nextMapEleX,
     int nextMapEleY,
     int nextMapElerotation,
     int newGameAssetRotation
) {
    public static NpcResponseDTO from(NpcInfo info) {
        return new NpcResponseDTO(
            info.getNextMapEleobjectTypeId(),
            info.getNextMapEleX(),
            info.getNextMapEleY(),
            info.getNextMapElerotation(),
            info.getNewGameAssetRotation()
        );
    }
}
