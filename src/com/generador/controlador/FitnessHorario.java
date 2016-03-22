package com.generador.controlador;

import java.util.ArrayList;
import java.util.List;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import com.generador.modelo.Horario;
import com.generador.modelo.Materia;

public class FitnessHorario extends FitnessFunction {

	private List<Materia> listaMaterias;
	private List<Materia> auxOptimoMaterias;
	private List<Integer> listaSeleccionado;
	private int minCreditos;
	private int maxCreditos;
	
	public FitnessHorario(List<Materia> listaMaterias, int minCreditos, int maxCreditos, List<Integer> listaSeleccionado) {
		this.listaMaterias = listaMaterias;
		this.minCreditos = minCreditos;
		this.maxCreditos = maxCreditos;
		this.listaSeleccionado = listaSeleccionado;
	}
	
	@Override
	protected double evaluate(IChromosome cromosoma) {
		Double valor = 0.0;
		Integer creditos = 0;
		Integer restriccion = 1;
		auxOptimoMaterias = new ArrayList<Materia>();
		
		for (int i = 0; i < cromosoma.size(); i++) {
			
			//Se considera si es verdadero
			if ((Boolean)cromosoma.getGene(i).getAllele()) {
				
				//Restriccion repeticion
				if (isRepetido(listaMaterias.get(i)))
					return 0.0;
			
				//Restriccion cruze horarios
				if (isCruceHorario(listaMaterias.get(i)))
					return 0.0;
				
				auxOptimoMaterias.add(listaMaterias.get(i));
				valor += listaMaterias.get(i).getIntPrioridad();
				creditos += listaMaterias.get(i).getIntCreditos();
				
				//Materias obligatorias
				if (listaMaterias.get(i).getCategoria().getStrCategoria().equals("FORMACION PROFESIONAL")
						&& listaMaterias.get(i).getCategoria().getStrSubCategoria().equals("OBLIGATORIAS"))
					valor += 10;
				
				//Lista seleccionado
				if (listaSeleccionado.contains(i))
					valor += 100;
				
				//Restriccion max creditos
				if (creditos > maxCreditos)
					return 0.0;
			}
		}
				
		//Restriccion min creditos
		if (creditos < minCreditos)
			return 0.0;
		/*
		List<Horario> listaHorarios = new ArrayList<Horario>();
		Horario sumatorio, horasHuecas;
		//Restricción horas huecas
		for (Materia materia : auxOptimoMaterias) {
			listaHorarios.add(materia.getHorario());
		}
		
		sumatorio = Horario.sumatorioHorarios(listaHorarios);
		horasHuecas = Horario.horasHuecas(sumatorio);*/
		//System.out.println("fitness: "+valor * restriccion);
		
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
