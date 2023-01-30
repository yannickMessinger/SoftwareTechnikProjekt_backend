package de.hsrm.mi.swt02.backend.websocket.model.npc;
/**
 * DTO to transfer NPC position informations to all clients
 * @param npcId id of the npc that the position information belongs to. Necessary to set right npc to correct position
 * @param npcPosX x position that npc needs to be set to
 * @param npcPosZ z position that npc needs to be set to
 * @param npcRotation rotation position that npc needs to be set to
 */
public record INpcPositionDTO(long npcId, double npcPosX, double npcPosZ, int npcRotation) {

}


