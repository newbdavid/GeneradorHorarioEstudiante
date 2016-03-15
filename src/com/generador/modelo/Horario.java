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

	private List<Calendar> lunes = new ArrayList<Calendar>();
	private List<Calendar> martes = new ArrayList<Calendar>();
	private List<Calendar> miercoles = new ArrayList<Calendar>();
	private List<Calendar> jueves = new ArrayList<Calendar>();
	private List<Calendar> viernes = new ArrayList<Calendar>();
	private List<Calendar> sabado = new ArrayList<Calendar>();
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
		
		/*
		if (!lunes.equalsIgnoreCase(" ")) {
			Calendar aux1 = Calendar.getInstance();
			Calendar aux2 = Calendar.getInstance();

			tokens = new StringTokenizer(lunes, "-");
			aux1.set(2015, 8, 31,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.lunes.add(aux1);
			aux2.set(2015, 8, 31,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.lunes.add(aux2);
		} else {
			Calendar aux1 = Calendar.getInstance();
			aux1.set(2015, 1, 1, 1, 0);
			this.lunes.add(aux1);
			this.lunes.add(aux1);
		}

		if (!martes.equalsIgnoreCase(" ")) {
			Calendar aux1 = Calendar.getInstance();
			Calendar aux2 = Calendar.getInstance();

			tokens = new StringTokenizer(martes, "-");
			aux1.set(2015, 8, 1,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.martes.add(aux1);
			aux2.set(2015, 8, 1,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.martes.add(aux2);
		} else {
			Calendar aux1 = Calendar.getInstance();
			aux1.set(2015, 1, 1, 1, 0);
			this.martes.add(aux1);
			this.martes.add(aux1);
		}

		if (!miercoles.equalsIgnoreCase(" ")) {
			Calendar aux1 = Calendar.getInstance();
			Calendar aux2 = Calendar.getInstance();

			tokens = new StringTokenizer(miercoles, "-");
			aux1.set(2015, 8, 2,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.miercoles.add(aux1);
			aux2.set(2015, 8, 2,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.miercoles.add(aux2);
		} else {
			Calendar aux1 = Calendar.getInstance();
			aux1.set(2015, 1, 1, 1, 0);
			this.miercoles.add(aux1);
			this.miercoles.add(aux1);
		}

		if (!jueves.equalsIgnoreCase(" ")) {
			Calendar aux1 = Calendar.getInstance();
			Calendar aux2 = Calendar.getInstance();

			tokens = new StringTokenizer(jueves, "-");
			aux1.set(2015, 8, 3,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")),
					0, 0);
			this.jueves.add(aux1);
			aux2.set(2015, 8, 3,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")),
					0, 0);
			this.jueves.add(aux2);
		} else {
			Calendar aux1 = Calendar.getInstance();
			aux1.set(2015, 1, 1, 1, 0);
			this.jueves.add(aux1);
			this.jueves.add(aux1);
		}

		if (!viernes.equalsIgnoreCase(" ")) {
			Calendar aux1 = Calendar.getInstance();
			Calendar aux2 = Calendar.getInstance();

			tokens = new StringTokenizer(viernes, "-");
			aux1.set(2015, 9, 4,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.viernes.add(aux1);
			aux2.set(2015, 9, 4,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.viernes.add(aux2);
		} else {
			Calendar aux1 = Calendar.getInstance();
			aux1.set(2015, 1, 1, 1, 0);
			this.viernes.add(aux1);
			this.viernes.add(aux1);
		}

		if (!sabado.equalsIgnoreCase(" ")) {
			Calendar aux1 = Calendar.getInstance();
			Calendar aux2 = Calendar.getInstance();

			tokens = new StringTokenizer(sabado, "-");
			aux1.set(2015, 9, 5,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.sabado.add(aux1);
			aux2.set(2015, 9, 5,
					Integer.parseInt(tokens.nextToken().replaceAll(" ", "")), 0);
			this.sabado.add(aux2);
		} else {
			Calendar aux1 = Calendar.getInstance();
			aux1.set(2015, 1, 1, 1, 0);
			this.sabado.add(aux1);
			this.sabado.add(aux1);
		}
		*/
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
	public List<Calendar> getLunes() {
		return lunes;
	}

	/**
	 * @param lunes
	 *            Horario del día lunes
	 */
	public void setLunes(List<Calendar> lunes) {
		this.lunes = lunes;
	}

	/**
	 * @return Horario del día martes
	 */
	public List<Calendar> getMartes() {
		return martes;
	}

	/**
	 * @param martes
	 *            Horario del día martes
	 */
	public void setMartes(List<Calendar> martes) {
		this.martes = martes;
	}

	/**
	 * @return Horario del día miercoles
	 */
	public List<Calendar> getMiercoles() {
		return miercoles;
	}

	/**
	 * @param miercoles
	 *            Horario del día miercoles
	 */
	public void setMiercoles(List<Calendar> miercoles) {
		this.miercoles = miercoles;
	}

	/**
	 * @return Horario del día jueves
	 */
	public List<Calendar> getJueves() {
		return jueves;
	}

	/**
	 * @param jueves
	 *            Horario del día jueves
	 */
	public void setJueves(List<Calendar> jueves) {
		this.jueves = jueves;
	}

	/**
	 * @return Horario del día viernes
	 */
	public List<Calendar> getViernes() {
		return viernes;
	}

	/**
	 * @param viernes
	 *            Horario del día viernes
	 */
	public void setViernes(List<Calendar> viernes) {
		this.viernes = viernes;
	}

	/**
	 * @return Horario del día sábado
	 */
	public List<Calendar> getSabado() {
		return sabado;
	}

	/**
	 * @param sabado
	 *            Horario del día sábado
	 */
	public void setSabado(List<Calendar> sabado) {
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

		return choca;
	}
}