package com.generador.vista;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JPanel;

/**
 * Clase que ofrece la vista de login por medio de Form
 * 
 * @author Luis Reinoso
 *
 */

public class PanelWeb extends PanelInicio {
	
	private JPanel panelWeb;
	
	public PanelWeb() {
		panelWeb = new JPanel();
		panelWeb.setLayout(new FlowLayout());
		panelWeb.setSize(500, 480);
		panelWeb.setVisible(true);
	}
	
	@Override
	public JPanel getPanel() {
		return panelWeb;
	}

	@Override
	public void setContenido() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
