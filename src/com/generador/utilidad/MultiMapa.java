package com.generador.utilidad;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.generador.modelo.Materia;

// Clase para utilizar un multimapa

public class MultiMapa extends LinkedHashMap<String, List<Materia>> {
	
	public void put(String key, Materia mat) {
		List<Materia> current = get(key);
		if (current == null) {
			current = new ArrayList<Materia>();
			super.put(key, current);
		}
		current.add(mat);
	}
	
	public List<Materia> getAllMaterias(){
	
		List<Materia> listaMaterias = new ArrayList<Materia>(5);
		
		for (int i = 0; i < this.keySet().toArray().length; i++) {
			listaMaterias.addAll(this.get(this.keySet().toArray()[i]));
		}
		
		return listaMaterias;
	}
}
