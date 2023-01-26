package de.hsrm.mi.swt02.backend.domain.npc;


import java.util.List;



import org.python.core.PyInteger;
import org.python.core.PyLong;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NpcNavigationSystem {
    Logger logger = LoggerFactory.getLogger(NpcNavigationSystem.class);

    PythonInterpreter pyInterp;
    private List<MapObject> list;
    MapObject currentMapObject;
    MapObject nextUpperMapObj;
    MapObject nextnextMapObject;

    private int npcRot;
    private NpcNavInfo info;
    private long npcId;
   

    public NpcNavigationSystem() {
        this.pyInterp = new PythonInterpreter();
        pyInterp.execfile("src/main/java/de/hsrm/mi/swt02/backend/domain/npc/NpcDriveScript.py");
        this.currentMapObject = new MapObject();
        this.nextUpperMapObj = new MapObject();
        this.info = new NpcNavInfo();
    
    }

    /**
     * 
     * @param list list with all MapElements from current map, gets handed in from MapImpl Service when NpcVehicle Class is instaniated
     * @param currMapEleX X Coordinate of the current MapElement that the Npc Vehicle that requested the script is currently positioned on
     * @param currMapEleY Y Coordinate of the current MapElement that the Npc Vehicle that requested the script is currently positioned on
     * @param npcRot current rotation of the Npc Car that requested the script 
     */
    public void setNpcNavigationParams(List<MapObject> list, int currMapEleX, int currMapEleY, int npcRot, long npcId) {
        
        
        this.list = list;
        this.npcRot = npcRot;
        this.npcId = npcId;

        /**
         * finds the current Map Object based on the given X and Y coordinates from the Npc Vehicle Update Request. Sets the "currentMapObject" object to filter result. 
        */
        this.currentMapObject = this.list.stream()
            .filter(mapObj -> mapObj.getX() == currMapEleX && mapObj.getY() == currMapEleY)
            .findFirst().get();

        /**
         * Method call to determine MapObject that is directly "above" the Npc Vehicle, depending on the orientation of the Npc Vehicle
         * result is assigned to "nextUpperMapObj" object.
         */
        
          //eig macht das doch auch das script.... 
         try{
            this.nextUpperMapObj = findNextUpperMapObj();
        }catch(Exception e){
            logger.error("kein nächstes upperMap Ele gefunden!!");
           
        }
       


        /**
         * preparation for Python Script call. Initializes all necessary values. Calculates the X and Y coordinates of the next Map Element, based on
         * the street orientation and NpcVehicle orientation of the "nextUpperMapObj" object. 
         */
        this.setNavigationScriptParams(nextUpperMapObj.getX(), nextUpperMapObj.getY(), nextUpperMapObj.getRotation(),
                this.npcRot, nextUpperMapObj.getObjectTypeId());
    }

    //updates / sets parameters that the python script is using to calculate the x ad y coordinates of the next new Map Element
    public void setNavigationScriptParams(int x, int z, int streetRotation, int carRotation, long objectTypeId) {
        pyInterp.set("x", new PyInteger(x));
        pyInterp.set("z", new PyInteger(z));
        pyInterp.set("streetR", new PyInteger(streetRotation));
        pyInterp.set("carR", new PyInteger(carRotation));
        pyInterp.set("objectTypeId", new PyLong(objectTypeId));
        pyInterp.set("currentCarRot", new PyInteger(-1));
        pyInterp.set("newCarRot", new PyInteger(-1));
        pyInterp.set("newXCoord", new PyInteger(-1));
        pyInterp.set("newZCoord", new PyInteger(-1));

    }

    //triggers python script, outputs the coordinates of next map ele and new npc car rotation
    public NpcNavInfo getDirections() {
        pyInterp.exec("script = NpcDriveScript(x, z, streetR, carR, objectTypeId)");

            /**
             * Determines if the map Element that was handed to the script is straight or curved. Than correct calculation method is executed in script.
             */
            pyInterp.exec("script.determineDrivingDirection()");


            pyInterp.exec("currentCarRot = script.getCurrentCarRotation()");

            /**
             * new X and Y coordinates of the following Map Object, based on the "nextUpperMapObj" object and the new rotation that the Npc Vehicle must rotate to.
             */
            pyInterp.exec("newCarRot = script.getNewCarRotation()");
            pyInterp.exec("newXCoord = script.getNextUpperMapEleX()");
            pyInterp.exec("newZCoord = script.getNextUpperMapEleZ()");

            /**
             * trys to find the new nextnext MapObject with the previously calculated X and Y coordinates. If not found it is set to currentMap object.
             */
            try {
                this.nextnextMapObject = this.list.stream()
                        .filter(mapObj -> mapObj.getX() == this.pyInterp.get("newXCoord").asInt()
                                && mapObj.getY() == this.pyInterp.get("newZCoord").asInt())
                        .findFirst().get();
            } catch (Exception e) {
                logger.error("Fehler nach script, kein nächstes map ele!");
                
            }

            //sets info into NpcInfo object and returns value, is than transferred back to frontend.
           
            this.info.setNpcId(this.npcId);
            this.info.setNextUpperMapObject(this.nextUpperMapObj);
            this.info.setNextnextUpperMapObject(nextnextMapObject);
            this.info.setNewGameAssetRotation(this.pyInterp.get("newCarRot").asInt());
        this.pyInterp.close();

        
        return this.info;

    }


    /**
     * 
     * @return MapObject that is directly "above" the Npc Vehicle, depending on the orientation of the Npc Vehicle
     *         result is assigned to "nextUpperMapObj" object
     */
    public MapObject findNextUpperMapObj() throws Exception{
        
        switch(this.npcRot) {
            case 0: 
                return this.nextUpperMapObj = this.list.stream().filter(mapObj -> mapObj.getY() == this.currentMapObject.getY() && mapObj.getX() == this.currentMapObject.getX() - 1).findFirst().get();
            case 1:
                return this.nextUpperMapObj = this.list.stream().filter(mapObj -> mapObj.getY() == this.currentMapObject.getY() + 1 && mapObj.getX() == this.currentMapObject.getX()).findFirst().get();
            case 2:
                return this.nextUpperMapObj = this.list.stream().filter(mapObj -> mapObj.getY() == this.currentMapObject.getY() && mapObj.getX() == this.currentMapObject.getX() + 1 ).findFirst().get();
            case 3:
                return this.nextUpperMapObj = this.list.stream().filter(mapObj -> mapObj.getY() == this.currentMapObject.getY() - 1 && mapObj.getX() == this.currentMapObject.getX()).findFirst().get();
            default:
                return new MapObject();
        }
    }

   
}
