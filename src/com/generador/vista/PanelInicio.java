package com.generador.vista;

import java.util.Observer;

import javax.swing.JPanel;

/**
 * 
 * Clase abstracta encargada de implementar los paneles de
 * inicio 
 * 
 * @author Luis Reinoso
 *
 */

public abstract class PanelInicio implements Observer{

	protected abstract JPanel getPanel();
	protected abstract void setContenido();
}
