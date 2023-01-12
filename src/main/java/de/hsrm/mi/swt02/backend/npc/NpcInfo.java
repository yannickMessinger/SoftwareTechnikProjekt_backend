package de.hsrm.mi.swt02.backend.npc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcInfo {
    
   
    private long nextMapEleobjectTypeId;
    private int nextMapEleX;
    private int nextMapEleY;
    private int nextMapElerotation;
    private int newGameAssetRotation;

    public NpcInfo(){

    }

    public NpcInfo(long nextMapEleobjectTypeId, int nextMapEleX,int nextMapEleY,int nextMapElerotation,int newGameAssetRotation){
        this.nextMapEleobjectTypeId = nextMapEleobjectTypeId;
        this.nextMapEleX = nextMapEleX;
        this.nextMapEleY = nextMapEleY;
        this.nextMapElerotation = nextMapElerotation;
        this.newGameAssetRotation = newGameAssetRotation;
    }
    
}
