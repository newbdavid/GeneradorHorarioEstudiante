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
				," "," ", "9-11"," "," "," "
				," "," ", "11-13"," "," "," "
				,false,false,false,false,false,false
			},
			{7, 9,caracter+"7-"+caracter+"9"
				," ","11-13"," "," ","11-13"," "
				," "," ", "11-13"," "," "," "
				,false,false,false,false,false,false
			},
			{7, 9,caracter+"7- 9"
				," ","9-11"," ","9-11"," "," "
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
}