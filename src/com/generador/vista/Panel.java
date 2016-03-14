package com.generador.vista;

import java.util.Observer;

import javax.swing.JPanel;

import org.jsoup.nodes.Document;

import com.generador.controlador.Documentos;

/**
 * 
 * Clase abstracta encargada de implementar los paneles de
 * inicio 
 * 
 * @author Luis Reinoso
 *
 */

public abstract class Panel implements Observer{
	
	protected abstract JPanel getPanel();
	protected abstract void setContenido();
	
	public void setDocMateriasPosibles(Document docMateriasPosibles) {
		Documentos documentos = Documentos.instancia();
		documentos.setDocMateriasPosibles(docMateriasPosibles);
	}
	
	public void setDocHorarioMaterias(Document docHorarioMaterias) {
		Documentos documentos = Documentos.instancia();
		documentos.setDocHorarioMaterias(docHorarioMaterias);
	}	
}
