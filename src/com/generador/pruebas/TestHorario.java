package com.generador.pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.generador.modelo.Horario;

@RunWith(Parameterized.class)
public class TestHorario {

	String strHorario, caracter;
	Horario horario1, horario2;
	int inicio, fin;
	int vacio = 160;
	List<Integer> listaLimpieza;
	List<Boolean> listaChoque;

	public TestHorario(int inicio, int fin, String strHorario
			, String lunes, String martes, String miercoles
			, String jueves, String viernes, String sabado
			, String lunes2, String martes2, String miercoles2
			, String jueves2, String viernes2, String sabado2
			, Boolean boolLunes, Boolean boolMartes, Boolean boolMiercoles
			, Boolean boolJueves, Boolean boolViernes, Boolean boolSabado
			) 
	{
		this.horario1 = new Horario(lunes, martes, miercoles, jueves, viernes, sabado);
		this.horario2 = new Horario(lunes2, martes2, miercoles2, jueves2, viernes2, sabado2);
		this.listaLimpieza = new ArrayList<>(2);
		this.listaChoque = new ArrayList<>(6);
		this.listaChoque.add(boolLunes);
		this.listaChoque.add(boolMartes);
		this.listaChoque.add(boolMiercoles);
		this.listaChoque.add(boolJueves);
		this.listaChoque.add(boolViernes);
		this.listaChoque.add(boolSabado);
		this.strHorario = strHorario;
		this.inicio = inicio;
		this.fin = fin;
		this.caracter = ""+(char)vacio;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> horarios() {

		int vacio = 160;
		String caracter = ""+(char)vacio;

		return Arrays.asList(new Object[][]{
			{7, 9," 7-9"
				,"7-9"," ","7-9"," "," "," "
				,"7-9"," ","7-9"," "," "," "
				,true,false,true,false,false,false
			},
			{7, 9," 7- 9"
				,"7-9"," ", "11-13"," "," "," "
				,"9-11"," ", "11-13"," "," "," "
				,false,false,true,false,false,false
			},
			{7, 9," 7-"+caracter+"9"
				,"7-9","9-11", "9-11"," "," ","7-17"
				,"11-13","13-17 ", "11-13"," "," "," "
				,false,false,false,false,false,false
			},
			{7, 9,caracter+"7-"+caracter+"9"
				,"15-17","11-13"," "," ","11-13","11-17"
				,"13-15"," ", "11-13"," "," "," "
				,false,false,false,false,false,false
			},
			{7, 9,caracter+"7- 9"
				," ","9-11"," ","9-11"," ","7-11"
				,"7- 9"," ", "10-12"," "," "," "
				,false,false,false,false,false,false
			},
			{11 ,13," "
				,"11-13", "11-13", "11-13"," "," "," "
				,"10-13", "9-13", "10-11"," "," "," "
				,true,true,false,false,false,false
			},
			{13 ,11," "
				," "," ", "15-17"," ","14-15"," "
				," "," ", "14-16"," ","13-15"," "
				,false,false,true,false,true,false
			},
			{13, 11,caracter
				," "," ", "14-16"," ","13-15"," "
				," "," ", "15-17"," ","14-15"," "
				,false,false,true,false,true,false
			}
		});
	}

	@Before
	public void inicio(){
		if (!strHorario.equals(" ") && (!strHorario.equals(caracter))) {
			this.listaLimpieza.add(this.inicio);
			this.listaLimpieza.add(this.fin);
		} else {
			listaLimpieza = null;
		}
	}

	@Test
	public void testGeneradorHorarioDia() {
		assertEquals(listaLimpieza, horario1.generadorHorarioDia(strHorario));
	}

	@Test
	public void testColision () {

		List<Boolean> listaResultado = new ArrayList<>(6);

		listaResultado.add(horario1.colision(horario1.getLunes(), horario2.getLunes()));
		listaResultado.add(horario1.colision(horario1.getMartes(), horario2.getMartes()));
		listaResultado.add(horario1.colision(horario1.getMiercoles(), horario2.getMiercoles()));
		listaResultado.add(horario1.colision(horario1.getJueves(), horario2.getJueves()));
		listaResultado.add(horario1.colision(horario1.getViernes(), horario2.getViernes()));
		listaResultado.add(horario1.colision(horario1.getSabado(), horario2.getSabado()));

		assertEquals(listaChoque, listaResultado);
	}

	@Test
	public void testColisiones () {

		List<Boolean> listaResultado = new ArrayList<>(6);
		listaResultado = horario1.colisiones(horario2);

		assertEquals(listaChoque, listaResultado);
	}

	@Test
	public void getHorarios(){
		List<List<Integer>> horarios = new ArrayList<List<Integer>>();

		horarios.add(horario1.getLunes());
		horarios.add(horario1.getMartes());
		horarios.add(horario1.getMiercoles());
		horarios.add(horario1.getJueves());
		horarios.add(horario1.getViernes());
		horarios.add(horario1.getSabado());

		assertEquals(6, horarios.size());
	}


	@Test
	public void suma () {

		Horario resultado = new Horario(" ", " ", " ", " ", " ", " ");
		if (!listaChoque.contains(true)) {
			resultado.setLunes(sumaHora(resultado.getLunes(), horario1.getLunes()));
			resultado.setLunes(sumaHora(resultado.getLunes(), horario2.getLunes()));
			resultado.setLunes(sumaHora(resultado.getLunes(), horario1.getMartes()));
			resultado.setLunes(sumaHora(resultado.getLunes(), horario2.getMartes()));
		}
		assertEquals(horario1.getSabado(),resultado.getLunes());
	}

	public List<Integer> sumaHora (List<Integer> hora1, List<Integer> hora2) {

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

	@Test
	public void sumaHorario() {

		Horario materia1 = new Horario("7-9", "7-10", " ", " ", " ", " ");
		Horario materia2 = new Horario("9-11", "11-14", " ", " ", " ", " ");
		Horario materia3 = new Horario("11-13", "19-20", " ", " ", " ", " ");
		Horario materia4 = new Horario("13-15", " ", " ", " ", " ", " ");
		Horario resultado = new Horario(" ", " ", " ", " ", " ", " ");

		List<Horario> listaHorarios = new ArrayList<Horario>();
		listaHorarios.add(materia1);
		listaHorarios.add(materia2);
		listaHorarios.add(materia3);
		listaHorarios.add(materia4); 

		resultado = sumatorioHorarios(listaHorarios);

		System.out.println();
		System.out.println("Horas semana: "+resultado.toString());
		System.out.println("Horas huecas: "+horasHuecas(resultado).toString());

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
}