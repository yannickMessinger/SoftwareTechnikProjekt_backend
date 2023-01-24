package de.hsrm.mi.swt02.backend.npc;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;

public record NpcInfoRequestDTO(long mapId, long npcId, int npcRotation, MapObject currentMapObject) {
    
}
