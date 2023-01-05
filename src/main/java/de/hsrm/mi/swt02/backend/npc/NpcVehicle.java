package de.hsrm.mi.swt02.backend.npc;



import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class NpcVehicle {

    PythonInterpreter pyInterp;
    private PyObject npcScriptObj;

    public NpcVehicle(int x,int z, int streetRotation, int carRotation, int streetId) {
        this.pyInterp = new PythonInterpreter();
        pyInterp.execfile("src/main/java/de/hsrm/mi/swt02/backend/npc/NpcDriveScript.py");
        
        pyInterp.set("retX", new PyInteger(0));
        pyInterp.set("x", new PyInteger(x));
        pyInterp.set("z", new PyInteger(z));
        pyInterp.set("streetR", new PyInteger(streetRotation));
        pyInterp.set("carR", new PyInteger(carRotation));
        pyInterp.set("streetId", new PyInteger(streetId));
        pyInterp.exec("script = NpcDriveScript(x, z, streetR, carR, streetId)");
        pyInterp.exec("script.drive()");
        pyInterp.exec("script.retX()");
        
        
        
    }


    public void createNpcObj(int x,int z, int streetRotation, int carRotation, int streetId){
        npcScriptObj.__call__(new PyInteger(x),new PyInteger(z),new PyInteger(streetRotation),new PyInteger(streetId));
        
    }
    

    public void initNpcScript(){
        
 
        pyInterp.execfile("src/main/java/de/hsrm/mi/swt02/backend/npc/NpcDriveScript.py");
	    
    }
}

       

    
    

