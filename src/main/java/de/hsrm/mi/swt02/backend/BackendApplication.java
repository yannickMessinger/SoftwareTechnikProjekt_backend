package de.hsrm.mi.swt02.backend;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.hsrm.mi.swt02.backend.npc.NpcVehicle;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BackendApplication.class, args);

		NpcVehicle npc = new NpcVehicle(1,2,0,1,2);
		//npc.initNpcScript();
		//npc.createNpcObj(1,2,0,1,2);
		 
	}

}
