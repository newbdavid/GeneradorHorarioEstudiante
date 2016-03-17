package com.generador.controlador;

import java.util.List;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import com.generador.modelo.Materia;
import com.generador.utilidad.MultiMapa;

public class FitnessHorario extends FitnessFunction {

	private List<Materia> listaMaterias;
	
	public FitnessHorario(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}
	
	@Override
	protected double evaluate(IChromosome arg0) {
		Double valor = 0.0;
		Integer creditos = 0;
		Integer restriccion = 1;
		for (int i = 0; i < arg0.size(); i++) {
			
			//Se considera si es verdadero
			if ((Boolean)arg0.getGene(i).getAllele()) {
				valor += valor(i,listaMaterias.get(i));
				creditos += listaMaterias.get(i).getIntCreditos();
				
				//Materias obligatorias
				if (listaMaterias.get(i).getCategoria().getStrCategoria().equals("OBLIGATORIAS")){
					valor += 200;
				}
				
				//Restriccion creditos
				if (creditos > 30) {
					restriccion = 0;
				}
			} else {
					
			}
		}
		return valor * restriccion;	
	}
		
	protected double valor(int indice, Materia materia) {
		return (double) materia.getIntPrioridad();
	}
}
