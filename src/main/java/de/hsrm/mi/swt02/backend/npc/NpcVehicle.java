package de.hsrm.mi.swt02.backend.npc;

import java.util.List;

import org.python.core.PyInteger;
import org.python.core.PyLong;
import org.python.util.PythonInterpreter;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcVehicle {

    PythonInterpreter pyInterp;
    private List<MapObject> list;
    MapObject currentMapObject;
    MapObject nextMapObject;


    /**
     * current X and Y coordinates of current map element on which npc car is placed on and 
     * current rotation of npc car. npcPosX, npcPosY and npcRot maybe not necessary....
     */
    private int npcPosX;
    private int npcPosY;
    private int npcRot;
    private NpcInfo info;

    public NpcVehicle() {
        this.pyInterp = new PythonInterpreter();
        pyInterp.execfile("src/main/java/de/hsrm/mi/swt02/backend/npc/NpcDriveScript.py");
        this.currentMapObject = new MapObject();
        this.nextMapObject = new MapObject();
        this.info = new NpcInfo();

    }

    public void setNpcParams(List<MapObject> list, int npcPosX, int npcPosY, int npcRot) {
        //npcPosX, npcPosY and npcRot maybe not necessary....
        this.npcPosX = npcPosX;
        this.npcPosY = npcPosY;
        this.npcRot = npcRot;
        
        this.list = list;

        this.currentMapObject = list.stream()
                .filter(mapObj -> mapObj.getX() == this.npcPosX && mapObj.getY() == this.npcPosY)
                .findFirst().get();

        this.setScriptParams(currentMapObject.getX(), currentMapObject.getY(), currentMapObject.getRotation(),
                this.npcRot, currentMapObject.getObjectTypeId());
    }

    //updates / sets parameters that the python script is using to calculate the x ad y coordinates of the next new Map Element
    public void setScriptParams(int x, int z, int streetRotation, int carRotation, long streetId) {
        pyInterp.set("x", new PyInteger(x));
        pyInterp.set("z", new PyInteger(z));
        pyInterp.set("streetR", new PyInteger(streetRotation));
        pyInterp.set("carR", new PyInteger(carRotation));
        pyInterp.set("streetId", new PyLong(streetId));
        pyInterp.set("newCarRot", new PyInteger(-1));
        pyInterp.set("newXCoord", new PyInteger(-1));
        pyInterp.set("newZCoord", new PyInteger(-1));

    }

    //triggers python script, outputs the coordinates of next map ele and new npc car rotation
    public NpcInfo calcNextMapEle() {

        /**
         * Script is run twice because it is always starting from the current Map Element. In first iteration it outputs the
         * x and y coordinates (those are savend in NpcInfo object) of the new Map Element. 
         * New Map element is searched in list and script is run a second time to calculate the new 
         * Npc Car rotation (new NpcCar rotation is also saved in NpcInfo Object).
         */
        for (int i = 0; i < 2; i++) {
            pyInterp.exec("script = NpcDriveScript(x, z, streetR, carR, streetId)");

            if (pyInterp.get("streetId").asInt() == 0) {
                pyInterp.exec("script.drive()");

            } else if (pyInterp.get("streetId").asInt() == 1) {
                pyInterp.exec("script.curveStreet()");

            } else if (pyInterp.get("streetId").asInt() == 2) {
                pyInterp.exec("script.intersectionStreet()");
            }

            pyInterp.exec("newCarRot = script.getCarRot()");
            pyInterp.exec("newXCoord = script. getXCoord()");
            pyInterp.exec("newZCoord = script. getZCoord()");

            try {
                this.nextMapObject = this.list.stream()
                        .filter(mapObj -> mapObj.getX() == this.pyInterp.get("newXCoord").asInt()
                                && mapObj.getY() == this.pyInterp.get("newZCoord").asInt())
                        .findFirst().get();
            } catch (Exception e) {
                this.nextMapObject = this.currentMapObject;
            }

            if (i == 0) {
                this.info.setNextMapEleobjectTypeId(this.nextMapObject.getObjectTypeId());
                this.info.setNextMapEleX(this.nextMapObject.getX());
                this.info.setNextMapEleY(this.nextMapObject.getY());
                this.info.setNextMapElerotation(this.nextMapObject.getRotation());
            }
            this.info.setNewGameAssetRotation(pyInterp.get("newCarRot").asInt());

            //sets script parameters to next map ele
            this.setScriptParams(nextMapObject.getX(), nextMapObject.getY(), nextMapObject.getRotation(),this.pyInterp.get("newCarRot").asInt(), nextMapObject.getObjectTypeId());

        }

        //return NpcInfo Object with coordinates of new MapEle and new Npc Car rotation
        return this.info;

    }

   
}
