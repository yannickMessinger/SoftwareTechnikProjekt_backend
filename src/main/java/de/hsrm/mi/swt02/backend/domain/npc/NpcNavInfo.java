package de.hsrm.mi.swt02.backend.domain.npc;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.Getter;
import lombok.Setter;

/**
 * class that holds all ne necessary navigation information that is wrapped in DTO and transferred to frontend
 * @param npcId npc that requested update
 * @param newGameAssetRotation the new rotation that npc needs to be set to
 * @param nextUpperMapObject the next Map Element of the npc
 * @param nextnextUpperMapObject the map element following 
 */
@Getter
@Setter
public class NpcNavInfo {
    private long npcId;
    private int newGameAssetRotation;
    private MapObject nextUpperMapObject;
    private MapObject nextnextUpperMapObject;

    public NpcNavInfo () {

    }

    public NpcNavInfo (int newGameAssetRotation, MapObject nextnextUpperMapObject, MapObject nextUpperMapObject) {
        this.newGameAssetRotation = newGameAssetRotation;
        this.nextnextUpperMapObject = nextnextUpperMapObject;
        this.nextUpperMapObject = nextUpperMapObject;
    }

}
