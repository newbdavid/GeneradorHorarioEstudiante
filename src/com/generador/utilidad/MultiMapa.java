package com.generador.utilidad;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.generdor.modelo.Materia;

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
}
