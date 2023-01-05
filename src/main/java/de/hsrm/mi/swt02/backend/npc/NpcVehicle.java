package de.hsrm.mi.swt02.backend.npc;

import org.python.util.PythonInterpreter;

public class NpcVehicle {
    
		PythonInterpreter pyInterp;
        

        public NpcVehicle(){
            this.pyInterp =  new PythonInterpreter();
        }

        public void print(){
            pyInterp.exec("print('Hello Florian World, uffbasse!')");
            
        }
		
		  
}
