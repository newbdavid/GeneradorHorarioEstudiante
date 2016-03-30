package com.generador.pruebas;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.generador.modelo.Datos;
import com.generador.modelo.Materia;

public class TestHorarioOptimo {

	List<Materia> listaMaterias;
	
	public TestHorarioOptimo() {
		this.listaMaterias = new Datos(documento("Materias Posibles"), documento("Horarios"))
				.getMapMaterias()
				.getAllMaterias();
	}
	
	@Test
	public void testCreditosTotal() {
		
		int creditos = 0;
		
		for (Materia materia : listaMaterias) {
			creditos += materia.getIntCreditos();
		}

		assertEquals(93, creditos);
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
