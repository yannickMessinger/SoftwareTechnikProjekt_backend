package de.hsrm.mi.swt02.backend.npc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapEleCoordinates {
    private int x;
    private int y;

    public MapEleCoordinates(){

    }

    public MapEleCoordinates(int x,int y){
        this.x = x;
        this.y = y;
    }
    
}
