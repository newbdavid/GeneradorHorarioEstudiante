package com.generador.modelo;

import java.util.ArrayList;
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
	 * si no existe horario retorna null
	 * 			
	 */

	public List<Integer> generadorHorarioDia(String horario) {

		/*
		 * Debido a caraceter vacio inicial desde la fuente de datos se ve en la
		 * necesidad de limpiar los datos de entrada
		 */

		//Caracter especial
		int vacio = 160;
		String caracter = ""+(char)vacio, inicio, fin;

		List<Integer> listaHorario = null;
		if ((!horario.equals(" ") && (!horario.equals(caracter)))) {
			listaHorario = new ArrayList<Integer>(2);
			tokens = new StringTokenizer(horario, "-");

			inicio = tokens.nextToken().trim();
			fin = tokens.nextToken().trim(); 

			if (inicio.contains(caracter))
				listaHorario.add(Integer.parseInt(inicio.replaceAll(caracter, "")));
			else
				listaHorario.add(Integer.parseInt(inicio));

			if (fin.contains(caracter))
				listaHorario.add(Integer.parseInt(fin.replaceAll(caracter, "")));
			else
				listaHorario.add(Integer.parseInt(fin));

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
	 * Verifica si hay colisiones en el horario
	 * 
	 * @param anotherHorario
	 *            Horario a comparar
	 * @return Lista de Bool que indica True si colisiona o False si no
	 */

	public List<Boolean> colisiones (Horario anotherHorario) {

		List<Boolean> colisiones = new ArrayList<>();

		colisiones.add(colision(this.getLunes(), anotherHorario.getLunes()));
		colisiones.add(colision(this.getMartes(), anotherHorario.getMartes()));
		colisiones.add(colision(this.getMiercoles(), anotherHorario.getMiercoles()));
		colisiones.add(colision(this.getJueves(), anotherHorario.getJueves()));
		colisiones.add(colision(this.getViernes(), anotherHorario.getViernes()));
		colisiones.add(colision(this.getSabado(), anotherHorario.getSabado()));

		return colisiones;
	}

	/**
	 * Verifica si el horario de un día colisiona con otro horario de un día
	 * @param horarioDia lista que contiene los horarios de un dia
	 * @param horarioDia2 lista que contiene los horarios de un dia
	 * @return true si existe colision si no false
	 */

	public Boolean colision (List<Integer> horarioDia, List<Integer> horarioDia2) {

		if (horarioDia != null && horarioDia2 != null) {
			if (
					//Todos igual
					(horarioDia.get(0) == horarioDia2.get(0)
					&& horarioDia.get(1) == horarioDia2.get(1))

					||

					//Se encuentra entre: inicio
					(horarioDia.get(0) < horarioDia2.get(0)
							&& horarioDia.get(1) > horarioDia2.get(0))

					||

					(horarioDia.get(0) < horarioDia2.get(1)
							&& horarioDia.get(1) > horarioDia2.get(1))

					||

					(horarioDia2.get(0) < horarioDia.get(0)
							&& horarioDia2.get(1) > horarioDia.get(0))

					||

					(horarioDia2.get(0) < horarioDia.get(1)
							&& horarioDia2.get(1) > horarioDia.get(1))

					||

					//Se encuentra entre: fin
					(horarioDia.get(0) > horarioDia2.get(0)
							&& horarioDia.get(1) < horarioDia2.get(0))

					||

					(horarioDia.get(0) > horarioDia2.get(1)
							&& horarioDia.get(1) < horarioDia2.get(1))

					||

					(horarioDia2.get(0) > horarioDia.get(0)
							&& horarioDia2.get(1) < horarioDia.get(0))

					||

					(horarioDia2.get(0) > horarioDia.get(1)
							&& horarioDia2.get(1) < horarioDia.get(1))
					)
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	/**
	 * Toma todos los horarios de todos los días
	 * @return Arreglo con todos horarios
	 */

	public List<List<Integer>> getHorarios(){
		List<List<Integer>> horarios = new ArrayList<List<Integer>>();

		horarios.add(this.getLunes());
		horarios.add(this.getMartes());
		horarios.add(this.getMiercoles());
		horarios.add(this.getJueves());
		horarios.add(this.getViernes());
		horarios.add(this.getSabado());

		return horarios;
	}

	private List<Integer> sumaHora (List<Integer> hora1, List<Integer> hora2) {

		boolean bandera = true;

		if (hora1 == null && hora2 == null) {
			return hora1;
		}

		if (hora1 == null && hora2 != null) {
			return hora2;
		}

		if (hora1 != null && hora2 == null) {
			return hora1;
		}

		if (hora1.size() >=4) {
			for (int i = 1; i < hora1.size()-2; i += 2) {

				if ((hora1.get(i) == hora2.get(0)) && (hora1.get(i+1) == hora2.get(1))) {
					hora1.remove(i);
					hora1.remove(i);
					return hora1;
				}
			}
		}

		for (int i = 0; i < hora1.size(); i += 2) {

			if ((hora1.get(i) == hora2.get(1)) && (hora1.get(i+1) != hora2.get(0))) {
				hora1.remove(i);
				hora1.add(i,hora2.get(0));
				bandera = false;
			} else if ((hora1.get(i+1) == hora2.get(0))	&& (hora1.get(i) != hora2.get(1))) {
				hora1.remove(i+1);
				hora1.add(i+1,hora2.get(1));
				bandera = false;
			}
		}

		if (bandera) {
			hora1.add(hora2.get(0));
			hora1.add(hora2.get(1));
		}

		return hora1;
	}

	public Horario sumatorioHorarios (List<Horario> horarios){

		Horario resultado = new Horario(" ", " ", " ", " ", " ", " ");

		for (Horario horario : horarios) {
			resultado.setLunes(sumaHora(resultado.getLunes(), horario.getLunes()));
			resultado.setMartes(sumaHora(resultado.getMartes(), horario.getMartes()));
			resultado.setMiercoles(sumaHora(resultado.getMiercoles(), horario.getMiercoles()));
			resultado.setJueves(sumaHora(resultado.getJueves(), horario.getJueves()));
			resultado.setViernes(sumaHora(resultado.getViernes(), horario.getViernes()));
			resultado.setSabado(sumaHora(resultado.getSabado(), horario.getSabado()));
		}

		if (resultado.getLunes() != null) 
			resultado.getLunes().sort(null);
		if (resultado.getMartes() != null)
			resultado.getMartes().sort(null);
		if (resultado.getMiercoles() != null)
			resultado.getMiercoles().sort(null);
		if (resultado.getJueves() != null)
			resultado.getJueves().sort(null);
		if (resultado.getViernes() != null)
			resultado.getViernes().sort(null);
		if (resultado.getSabado() != null)
			resultado.getSabado().sort(null);

		return resultado;
	}
	
	public List<Integer> intervaloDesplazamiento (List<Integer> horario) {

		List<Integer> listaDesplazamiento = new ArrayList<Integer>();
		if (horario != null) {

			for (int i = 1; i < horario.size()-1; i +=2) {
				listaDesplazamiento.add(horario.get(i+1) - horario.get(i));
			}		
			System.out.println(listaDesplazamiento);
		}
		return listaDesplazamiento;
	}

	public Horario horasHuecas (Horario horario) {

		Horario horasHuecas = new Horario(" ", " ", " ", " ", " ", " ");

		horasHuecas.setLunes(intervaloDesplazamiento(horario.getLunes()));
		horasHuecas.setMartes(intervaloDesplazamiento(horario.getMartes()));
		horasHuecas.setMiercoles(intervaloDesplazamiento(horario.getMiercoles()));
		horasHuecas.setJueves(intervaloDesplazamiento(horario.getJueves()));
		horasHuecas.setViernes(intervaloDesplazamiento(horario.getViernes()));
		horasHuecas.setSabado(intervaloDesplazamiento(horario.getSabado()));

		return horasHuecas;
	}
	
	@Override
	public String toString() {
		return "Horario [" + (lunes != null ? "lunes=" + lunes + ", " : "")
				+ (martes != null ? "martes=" + martes + ", " : "")
				+ (miercoles != null ? "miercoles=" + miercoles + ", " : "")
				+ (jueves != null ? "jueves=" + jueves + ", " : "")
				+ (viernes != null ? "viernes=" + viernes + ", " : "") + (sabado != null ? "sabado=" + sabado : "")
				+ "]";
	}
}