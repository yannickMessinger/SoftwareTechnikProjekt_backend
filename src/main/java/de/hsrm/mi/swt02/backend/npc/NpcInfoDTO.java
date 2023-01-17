package de.hsrm.mi.swt02.backend.npc;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;

public record NpcInfoDTO(long npcId, int npcRotation, MapObject currentMapObject, MapObject nextUpperMapObject) {
    
}
