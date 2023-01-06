package de.hsrm.mi.swt02.backend.npc;



import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class NpcVehicle {

    PythonInterpreter pyInterp;
    

    public NpcVehicle() {
        this.pyInterp = new PythonInterpreter();
        pyInterp.execfile("src/main/java/de/hsrm/mi/swt02/backend/npc/NpcDriveScript.py");
    }


    public void setNpcParams(int x,int z, int streetRotation, int carRotation, int streetId){
        pyInterp.set("x", new PyInteger(x));
        pyInterp.set("z", new PyInteger(z));
        pyInterp.set("streetR", new PyInteger(streetRotation));
        pyInterp.set("carR", new PyInteger(carRotation));
        pyInterp.set("streetId", new PyInteger(streetId));

        
        
    }
    

    public void calcNextMapEle(){
        pyInterp.exec("script = NpcDriveScript(x, z, streetR, carR, streetId)");
        
        if(pyInterp.get("streetId").asInt() == 0){
            pyInterp.exec("script.drive()");

        }else if(pyInterp.get("streetId").asInt() == 1){
            pyInterp.exec("script.curveStreet()");
            
        }
       
     
        
	    
    }
}

       

    
    

