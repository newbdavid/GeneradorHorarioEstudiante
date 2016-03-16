package com.generador.pruebas;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.xpath.operations.Bool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
import com.generador.modelo.Datos;
import com.generador.modelo.Materia;
import com.generador.utilidad.MultiMapa;

public class TestOrganizador {

	private MultiMapa mapMaterias;
	private LinkedHashMap<String, Materia> listaOptimo;
	
	public TestOrganizador() {
		Datos datosProcesados = new Datos(documento("Materias Posibles"), documento("Horarios"));
		this.mapMaterias = datosProcesados.getMapMaterias();
		
		this.listaOptimo = new LinkedHashMap<String,Materia>();
		listaOptimo.put("SIC514",mapMaterias.get("SIC514").get(0));
	}

	@Test
	public void test() {
		imprimir();
		assertTrue(verificar());
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
	
	public Boolean verificar () {
		return listaOptimo.containsKey("SIC514");
	}
	
	public void imprimir() {

		Object[] keys;
		keys =  mapMaterias.keySet().toArray();
		
		for (Object key : keys) {
			System.out.println(mapMaterias.get(key).size());
			
			for (int i = 0; i < mapMaterias.get(key).size(); i++) {
				System.out.println(mapMaterias.get(key).get(i).getStrNombre());
				System.out.println(mapMaterias.get(key).get(i).getHorario().toString());
				System.out.println(mapMaterias.get(key).get(i).getHorario().getLunes());
			}
			System.out.println(key);
			System.out.println();
		}
	}

}
