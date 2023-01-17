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
    private MapObject currentMapObject;
    private MapObject nextUpperMapObject;
    public NpcInfo(){

    }

    public NpcInfo(int newGameAssetRotation, MapObject currentMapObject, MapObject nextUpperMapObject){
        this.newGameAssetRotation = newGameAssetRotation;
        this.currentMapObject = currentMapObject;
        this.nextUpperMapObject = nextUpperMapObject;
    }
    
}
