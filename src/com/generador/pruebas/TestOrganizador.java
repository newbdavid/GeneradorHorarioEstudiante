package com.generador.pruebas;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import com.generador.controlador.Organizador;
import com.generador.modelo.Datos;
import com.generador.modelo.Materia;
import com.generador.utilidad.MultiMapaMaterias;

public class TestOrganizador {

	private MultiMapaMaterias mapMaterias;
	private Organizador organizador;
	private List<List<Materia>> listaResultados;
	private List<Materia> listaResultado;
	private List<Integer> listaMateriasSeleccionadas;
	private int maxCreditos, minCreditos;

	public TestOrganizador() {
		listaMateriasSeleccionadas = new ArrayList<Integer>(1);
		listaMateriasSeleccionadas.add(18);
		maxCreditos = 30;
		minCreditos = 20;
		
		Datos datosProcesados = new Datos(documento("Materias Posibles"), documento("Horarios"));
		this.mapMaterias = datosProcesados.getMapMaterias();
		
		imprimir();
		this.organizador = new Organizador(mapMaterias , minCreditos ,maxCreditos, listaMateriasSeleccionadas);
		optimo();
		this.listaResultados = organizador.getListaSoluciones();
	}

	//@Test
	public void testMateriaSeleccionada() {
		
		ArrayList<Boolean> contenido = new ArrayList<Boolean>(2);
		
		if (listaResultados != null && listaMateriasSeleccionadas.size() > 0) {
			
			for (List<Materia> materias : listaResultados) {
				for (Integer i : listaMateriasSeleccionadas) {
					contenido.add(materias.contains(mapMaterias.getAllMaterias().get(i)));
				}
			}
			
			assertTrue(!contenido.contains(false));				
			
		} else {
			assertNull(listaResultado);
		}	
	}

	@Test
	public void testNumeroCreditos() {
		ArrayList<Boolean> contenido = new ArrayList<Boolean>(2);
		int creditos = 0;
		
		if (listaResultados.size() > 0) {
			
			for (List<Materia> materias : listaResultados) {
				creditos = 0;
				
				for (Materia materia : materias)
					creditos += materia.getIntCreditos();
				
				System.out.println("creditos: "+creditos);

				if (creditos > maxCreditos || creditos < minCreditos)
					contenido.add(true);
			}
			
			assertTrue(!contenido.contains(true));				
			
		} else {
			assertTrue(true);
		}
		
	}
	
	public void imprimir() {
		for (int i = 0; i < mapMaterias.getAllMaterias().size(); i++) {
			System.out.println(i+" "+mapMaterias.getAllMaterias().get(i).toString());
		}
	}
	
	public void optimo() {
		organizador.calcularHorarioOptimo();
		System.out.println("Soluciones: "+organizador.getListaSoluciones().size());
		System.out.println();
	}

	public Document documento(String nombreArchivo) {

		File input = new File("./"+nombreArchivo+".html");
		try {
			Document doc = Jsoup.parse(input, "UTF-8");
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error conversion");
		}
		return null;
	}
}
