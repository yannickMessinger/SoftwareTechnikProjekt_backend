package de.hsrm.mi.swt02.backend.api.npc.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;

public record NpcNavInfoRequestDTO(long mapId, long npcId, int npcRotation, MapObject currentMapObject) {

}
