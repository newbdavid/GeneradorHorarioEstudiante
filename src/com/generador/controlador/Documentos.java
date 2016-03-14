package com.generador.controlador;

import java.util.Observable;

import javax.swing.JOptionPane;

import org.jsoup.nodes.Document;

public class Documentos extends Observable {
	
	private Document docMateriasPosibles;
	private Document docHorarioMaterias;
	
	public static Documentos datos = null;
	
	public static Documentos instancia() {
		
		if (datos == null) {
			datos = new Documentos();
		}
		return datos;
	}
	
	public Document getDocMateriasPosibles() {
		return docMateriasPosibles;
	}

	public void setDocMateriasPosibles(Document docMateriasPosibles) {
		this.docMateriasPosibles = docMateriasPosibles;
	}

	public Document getDocHorarioMaterias() {
		return docHorarioMaterias;
	}

	public void setDocHorarioMaterias(Document docHorarioMaterias) {
		this.docHorarioMaterias = docHorarioMaterias;
	}

	public static Documentos getDatos() {
		return datos;
	}

	public static void setDatos(Documentos datos) {
		Documentos.datos = datos;
	}

	public boolean isValid() {
		if (docHorarioMaterias != null && docMateriasPosibles != null) {
			if (!(docMateriasPosibles.title().matches(".*Error$") && docHorarioMaterias.title().matches(".*Error$"))) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Error: Origen de datos");
				return false;
			}			
		} else {
			JOptionPane.showMessageDialog(null, "Error: Origen de datos vacío, verificar autenticación");
			return false;
		}
	}
}
