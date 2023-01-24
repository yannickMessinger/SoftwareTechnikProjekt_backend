package de.hsrm.mi.swt02.backend.npc;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NpcInfo {
    private long npcId;
    private int newGameAssetRotation;
    private MapObject nextUpperMapObject;
    private MapObject nextnextUpperMapObject;
    public NpcInfo(){

    }

    public NpcInfo(int newGameAssetRotation, MapObject nextnextUpperMapObject, MapObject nextUpperMapObject){
        this.newGameAssetRotation = newGameAssetRotation;
        this.nextnextUpperMapObject = nextnextUpperMapObject;
        this.nextUpperMapObject = nextUpperMapObject;
    }
    
}
