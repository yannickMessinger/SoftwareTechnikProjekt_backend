package de.hsrm.mi.swt02.backend.npc;


import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class NpcVehicle {

    PythonInterpreter pyInterp;

    public NpcVehicle() {
        this.pyInterp = new PythonInterpreter();
    }

    public void print() {
        pyInterp.exec("print('Hello World!')");

    }

    public NpcVehicle initNpcScript(){
        pyInterp.execfile("src\\main\\java\\de\\hsrm\\mi\\swt02\\backend\\npc\\NpcScript.py"); 
	    PyObject buildingObject = pyInterp.get("NpcScript").__call__(); 
 
        //Cast the created object to our Java interface 
	    return (NpcVehicle) buildingObject.__tojava__(NpcVehicle.class);  
    }
}

       

    
    

