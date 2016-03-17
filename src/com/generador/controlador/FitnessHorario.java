package com.generador.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.RETURN;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import com.generador.modelo.Materia;
import com.generador.utilidad.MultiMapa;

public class FitnessHorario extends FitnessFunction {

	private List<Materia> listaMaterias;
	private List<Materia> auxOptimoMaterias;
	
	public FitnessHorario(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}
	
	@Override
	protected double evaluate(IChromosome arg0) {
		Double valor = 0.0;
		Integer creditos = 0;
		Integer restriccion = 1;
		auxOptimoMaterias = new ArrayList<Materia>();
		for (int i = 0; i < arg0.size(); i++) {
			
			//Se considera si es verdadero
			if ((Boolean)arg0.getGene(i).getAllele()) {
				
				//Restriccion repeticion
				if (isRepetido(listaMaterias.get(i))) {
					restriccion = 0;
				}
			
				//Restriccion cruze horarios
				if (isCruceHorario(listaMaterias.get(i))) {
					restriccion = 0;
				}
				
				auxOptimoMaterias.add(listaMaterias.get(i));
				valor += listaMaterias.get(i).getIntPrioridad();
				creditos += listaMaterias.get(i).getIntCreditos();
				
				//Materias obligatorias
				if (listaMaterias.get(i).getCategoria().getStrSubCategoria().equals("OBLIGATORIAS")){
					valor += 100;
				}
				
				//Restriccion creditos
				if (creditos > 30) {
					restriccion = 0;
				}
			}
		}
		return valor * restriccion;	
	}
	
	protected Boolean isRepetido (Materia materia) {
		
		for (int i = 0; i < auxOptimoMaterias.size(); i++) {
			if (auxOptimoMaterias.get(i).getStrCodigo().equals(materia.getStrCodigo()))
					return true;
		}
		return false;
	}
	
	protected Boolean isCruceHorario (Materia materia) {
		
		for (int i = 0; i < auxOptimoMaterias.size(); i++) {
			if (auxOptimoMaterias.get(i).getHorario().colisiones(materia.getHorario()).contains(true))
					return true;
		}
		return false;
	}
}
