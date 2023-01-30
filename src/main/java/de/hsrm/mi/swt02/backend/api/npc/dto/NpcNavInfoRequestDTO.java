package de.hsrm.mi.swt02.backend.api.npc.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
/**
 * DTO that is used to transfer navigation information from frontend to backend.
 * @param mapId id of the map that the npc is currently on
 * @param npcId id of the npc that is asking for direction updates
 * @param npcRotation current rotation of the npc
 * @param currentMapObject current MapObject / tile that the npc has reached its limit and need navigation updates.
 */
public record NpcNavInfoRequestDTO(long mapId, long npcId, int npcRotation, MapObject currentMapObject) {

}
