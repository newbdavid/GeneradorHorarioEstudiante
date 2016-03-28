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
					valor += 1000;
				
				//Lista seleccionado
				if (listaSeleccionado.contains(i))
					valor += 1000;
				
				//Restriccion max creditos
				if (creditos > maxCreditos)
					return 0.0;
			}
		}
				
		//Restriccion min creditos
		if (creditos < minCreditos)
			return 0.0;
				
		List<Horario> listaHorarios = new ArrayList<Horario>();
		int contadorHuecas = 0;
		Horario utilidad = new Horario(" ", " ", " ", " ", " ", " ");
		Horario sumatorio = null, horasHuecas;
		
		//Restricción horas huecas
		for (Materia materia : auxOptimoMaterias) {
			listaHorarios.add(materia.getHorario());
		}
		
		sumatorio = utilidad.sumatorioHorarios(listaHorarios);
		horasHuecas = utilidad.horasHuecas(sumatorio);
		
		for (List<Integer> hueco : horasHuecas.getHorarios()) {
			
			if (hueco != null && hueco.size() != 0) {
				contadorHuecas += hueco.size();
				
				if (hueco.contains(1)){
					valor += 20;
				} else if (hueco.contains(2)){
					valor += 15;
				} else if (hueco.contains(3)) {
					valor += 10;
				} else if (hueco.contains(4)) {
					valor += 5;
				} else {
					//Penalización horas huecas grandes
					valor -= 50;
				}
			} else {
				valor += 25;
			}
		}
		
		if (contadorHuecas == 0 ) {
			valor += 50;
		} else if (contadorHuecas < 4) {
			valor += 25;
		} else if (contadorHuecas < 6) {
			valor += 15;
		} else if (contadorHuecas < 8) {
			valor += 5;
		} else {
			valor -= 50;
		}
		
		valor -= contadorHuecas;
		
		if (valor < 0) {
			return 0.0;
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
