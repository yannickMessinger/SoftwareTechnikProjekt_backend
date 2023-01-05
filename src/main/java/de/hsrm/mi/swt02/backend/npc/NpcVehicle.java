package de.hsrm.mi.swt02.backend.npc;

import org.python.util.PythonInterpreter;

public class NpcVehicle {
    
		PythonInterpreter pyInterp = new PythonInterpreter();

        public void printHello(){
            pyInterp.exec("print('Hello Python World!')");
        }
		
		  
}
