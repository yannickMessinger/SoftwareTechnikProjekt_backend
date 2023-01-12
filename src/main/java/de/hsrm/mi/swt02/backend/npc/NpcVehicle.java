package de.hsrm.mi.swt02.backend.npc;

import org.python.core.PyInteger;
import org.python.core.PyLong;
import org.python.util.PythonInterpreter;

public class NpcVehicle {

    PythonInterpreter pyInterp;

    public NpcVehicle() {
        this.pyInterp = new PythonInterpreter();
        pyInterp.execfile("src/main/java/de/hsrm/mi/swt02/backend/npc/NpcDriveScript.py");
    }

    public void setNpcParams(int x, int z, int streetRotation, int carRotation, long streetId) {
        pyInterp.set("x", new PyInteger(x));
        pyInterp.set("z", new PyInteger(z));
        pyInterp.set("streetR", new PyInteger(streetRotation));
        pyInterp.set("carR", new PyInteger(carRotation));
        pyInterp.set("streetId", new PyLong(streetId));
        pyInterp.set("newCarRot", new PyInteger(-1));
        pyInterp.set("newXCoord", new PyInteger(-1));
        pyInterp.set("newZCoord", new PyInteger(-1));

       

    }

    public void calcNextMapEle() {
        pyInterp.exec("script = NpcDriveScript(x, z, streetR, carR, streetId)");

        if (pyInterp.get("streetId").asInt() == 0) {
            pyInterp.exec("script.drive()");

        } else if (pyInterp.get("streetId").asInt() == 1) {
            pyInterp.exec("script.curveStreet()");

        } else if(pyInterp.get("streetId").asInt() == 2){
            pyInterp.exec("script.intersectionStreet()");
        }

        pyInterp.exec("newCarRot = script.getCarRot()");
        pyInterp.exec("newXCoord = script. getXCoord()");
        pyInterp.exec("newZCoord = script. getZCoord()");
       

    }

    public int retCarRot() {
        return pyInterp.get("newCarRot").asInt();
    }

    public int retXCoord(){
        return pyInterp.get("newXCoord").asInt();
    }

    public int retZCoord(){
        return pyInterp.get("newZCoord").asInt();
    }
}
