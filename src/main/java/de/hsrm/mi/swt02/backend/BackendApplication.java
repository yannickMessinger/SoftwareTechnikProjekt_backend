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
		
		NpcVehicle npc = new NpcVehicle();
		npc.setNpcParams(0, 1, 1, 1, 0);
		npc.calcNextMapEle();
		
		npc.setNpcParams(0, 2, 1, 1, 1);
		npc.calcNextMapEle();

		npc.setNpcParams(1, 2, 2, 2, 0);
		npc.calcNextMapEle();

		npc.setNpcParams(2, 2, 2, 2, 1);
		npc.calcNextMapEle();

		npc.setNpcParams(2, 1, 3, 3, 0);
		npc.calcNextMapEle();

		npc.setNpcParams(2, 0, 3, 3, 1);
		npc.calcNextMapEle();

		npc.setNpcParams(1, 0, 0, 0, 0);
		npc.calcNextMapEle();

		npc.setNpcParams(0, 0, 0, 0, 1);
		npc.calcNextMapEle();
	}

}
