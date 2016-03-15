package com.generador.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Clase encargada de almacenar los horarios de cada materia
 * <p>
 * Toma los valores obtenidos de HorariosMaterias y almacena cada dia en un
 * Lista de objetos Calendar
 * </p>
 * 
 * @author Luis Reinoso
 *
 */

public class Horario {

	private List<Integer> lunes = new ArrayList<Integer>();
	private List<Integer> martes = new ArrayList<Integer>();
	private List<Integer> miercoles = new ArrayList<Integer>();
	private List<Integer> jueves = new ArrayList<Integer>();
	private List<Integer> viernes = new ArrayList<Integer>();
	private List<Integer> sabado = new ArrayList<Integer>();
	StringTokenizer tokens;
	
	/**
	 * Constructor de la clase Horario
	 * 
	 * @param lunes
	 *            Horarios del día lunes
	 * @param martes
	 *            Horarios del día martes
	 * @param miercoles
	 *            Horarios del día miercoles
	 * @param jueves
	 *            Horarios del día jueves
	 * @param viernes
	 *            Horarios del día viernes
	 * @param sabado
	 *            Horarios del día sabado
	 */
	public Horario(String lunes, String martes, String miercoles,
			String jueves, String viernes, String sabado) {

		/*
		 * Debido a caraceter vacio inicial desde la fuente de datos se ve en la
		 * necesidad de limpiar los datos de entrada
		 */
		
		this.lunes = generadorHorarioDia(lunes);
		this.martes = generadorHorarioDia(martes);
		this.miercoles = generadorHorarioDia(miercoles);
		this.jueves = generadorHorarioDia(jueves);
		this.viernes = generadorHorarioDia(viernes);
		this.sabado = generadorHorarioDia(sabado);
		
	}
	
	/**
	 * 
	 * @param horario Horario correspondiente a un día de una materia
	 * @return 
	 * Una lista que contiene los horarios
	 * si no existe horario: null
	 * 			
	 */
	
	public List<Integer> generadorHorarioDia(String horario) {
		
		List<Integer> listaHorario = null;
		if (!horario.equals(" ")) {
			listaHorario = new ArrayList<Integer>(2);
			tokens = new StringTokenizer(horario, "-");
			listaHorario.add(Integer.parseInt(tokens.nextToken().replaceAll(" ", "")));
			listaHorario.add(Integer.parseInt(tokens.nextToken().replaceAll(" ", "")));
			return listaHorario;
		} else {
			return listaHorario;
		}
	}

	/**
	 * @return Horario del día lunes
	 */
	public List<Integer> getLunes() {
		return lunes;
	}

	/**
	 * @param lunes
	 *            Horario del día lunes
	 */
	public void setLunes(List<Integer> lunes) {
		this.lunes = lunes;
	}

	/**
	 * @return Horario del día martes
	 */
	public List<Integer> getMartes() {
		return martes;
	}

	/**
	 * @param martes
	 *            Horario del día martes
	 */
	public void setMartes(List<Integer> martes) {
		this.martes = martes;
	}

	/**
	 * @return Horario del día miercoles
	 */
	public List<Integer> getMiercoles() {
		return miercoles;
	}

	/**
	 * @param miercoles
	 *            Horario del día miercoles
	 */
	public void setMiercoles(List<Integer> miercoles) {
		this.miercoles = miercoles;
	}

	/**
	 * @return Horario del día jueves
	 */
	public List<Integer> getJueves() {
		return jueves;
	}

	/**
	 * @param jueves
	 *            Horario del día jueves
	 */
	public void setJueves(List<Integer> jueves) {
		this.jueves = jueves;
	}

	/**
	 * @return Horario del día viernes
	 */
	public List<Integer> getViernes() {
		return viernes;
	}

	/**
	 * @param viernes
	 *            Horario del día viernes
	 */
	public void setViernes(List<Integer> viernes) {
		this.viernes = viernes;
	}

	/**
	 * @return Horario del día sábado
	 */
	public List<Integer> getSabado() {
		return sabado;
	}

	/**
	 * @param sabado
	 *            Horario del día sábado
	 */
	public void setSabado(List<Integer> sabado) {
		this.sabado = sabado;
	}

	/**
	 * Verifica si hay colision en el horario
	 * 
	 * @param anotherHorario
	 *            Horario a comparar
	 * @return Lista de Bool que indica True si colisiona o False si no
	 */
	public List<Boolean> choca(Horario anotherHorario) {
		List<Boolean> choca = new ArrayList<>();

		/*
		if ((this.lunes.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.lunes
				.get(0).get(Calendar.HOUR_OF_DAY) && this.lunes.get(1).get(
				Calendar.HOUR_OF_DAY) > anotherHorario.lunes.get(0).get(
				Calendar.HOUR_OF_DAY))
				|| (this.lunes.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.lunes
						.get(1).get(Calendar.HOUR_OF_DAY) && this.lunes.get(1)
						.get(Calendar.HOUR_OF_DAY) > anotherHorario.lunes
						.get(1).get(Calendar.HOUR_OF_DAY)))
			choca.add(true);
		else
			choca.add(false);

		if ((this.martes.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.martes
				.get(0).get(Calendar.HOUR_OF_DAY) && this.martes.get(1).get(
				Calendar.HOUR_OF_DAY) > anotherHorario.martes.get(0).get(
				Calendar.HOUR_OF_DAY))
				|| (this.martes.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.martes
						.get(1).get(Calendar.HOUR_OF_DAY) && this.martes.get(1)
						.get(Calendar.HOUR_OF_DAY) > anotherHorario.martes.get(
						1).get(Calendar.HOUR_OF_DAY)))
			choca.add(true);
		else
			choca.add(false);

		if ((this.miercoles.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.miercoles
				.get(0).get(Calendar.HOUR_OF_DAY) && this.miercoles.get(1).get(
				Calendar.HOUR_OF_DAY) > anotherHorario.miercoles.get(0).get(
				Calendar.HOUR_OF_DAY))
				|| (this.miercoles.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.miercoles
						.get(1).get(Calendar.HOUR_OF_DAY) && this.miercoles
						.get(1).get(Calendar.HOUR_OF_DAY) > anotherHorario.miercoles
						.get(1).get(Calendar.HOUR_OF_DAY)))
			choca.add(true);
		else
			choca.add(false);

		if ((this.jueves.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.jueves
				.get(0).get(Calendar.HOUR_OF_DAY) && this.jueves.get(1).get(
				Calendar.HOUR_OF_DAY) > anotherHorario.jueves.get(0).get(
				Calendar.HOUR_OF_DAY))
				|| (this.jueves.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.jueves
						.get(1).get(Calendar.HOUR_OF_DAY) && this.jueves.get(1)
						.get(Calendar.HOUR_OF_DAY) > anotherHorario.jueves.get(
						1).get(Calendar.HOUR_OF_DAY)))
			choca.add(true);
		else
			choca.add(false);

		if ((this.viernes.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.viernes
				.get(0).get(Calendar.HOUR_OF_DAY) && this.viernes.get(1).get(
				Calendar.HOUR_OF_DAY) > anotherHorario.viernes.get(0).get(
				Calendar.HOUR_OF_DAY))
				|| (this.viernes.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.viernes
						.get(1).get(Calendar.HOUR_OF_DAY) && this.viernes
						.get(1).get(Calendar.HOUR_OF_DAY) > anotherHorario.viernes
						.get(1).get(Calendar.HOUR_OF_DAY)))
			choca.add(true);
		else
			choca.add(false);

		if ((this.sabado.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.sabado
				.get(0).get(Calendar.HOUR_OF_DAY) && this.sabado.get(1).get(
				Calendar.HOUR_OF_DAY) > anotherHorario.sabado.get(0).get(
				Calendar.HOUR_OF_DAY))
				|| (this.sabado.get(0).get(Calendar.HOUR_OF_DAY) <= anotherHorario.sabado
						.get(1).get(Calendar.HOUR_OF_DAY) && this.sabado.get(1)
						.get(Calendar.HOUR_OF_DAY) > anotherHorario.sabado.get(
						1).get(Calendar.HOUR_OF_DAY)))
			choca.add(true);
		else
			choca.add(false);
		 */
		return choca;
	}
}