package com.generador.modelo;

import java.util.List;

public class HorarioOptimo {

	List<Materia> listaMaterias;
	
	public HorarioOptimo(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}
	
	public int creditosTotal() {
		
		int creditos = 0;
		for (Materia materia : listaMaterias)
			creditos += materia.getIntCreditos();
		
		return creditos;
	}
	
}
