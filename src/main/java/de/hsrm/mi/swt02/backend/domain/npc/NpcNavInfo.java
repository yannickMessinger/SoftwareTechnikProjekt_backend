package de.hsrm.mi.swt02.backend.domain.npc;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.Getter;
import lombok.Setter;


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
