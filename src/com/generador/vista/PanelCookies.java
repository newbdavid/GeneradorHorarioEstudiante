package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.generador.modelo.Cookies;
import com.generador.utilidad.Formateador;

/**
 * Clase que ofrece la vista de login por medio de Cookies
 * 
 * @author Luis Reinoso
 *
 */

public class PanelCookies extends Panel {

	private JPanel panelCookies;
	private Cookies cookie;
	private JLabel lblCookies;
	private JFormattedTextField txtCookies;
	private JButton btnAceptar;
	
	public PanelCookies() {
		panelCookies = new JPanel();
		panelCookies.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCookies.setSize(500, 480);
		setContenido();
		
		//Observer
		cookie = Cookies.instancia();
		cookie.addObserver(this);
		
		//Listener
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtCookies.getText().length() == 24) {
					cookie.setStrCookies(txtCookies.getText());
					setDocHorarioMaterias(getDocHorarioMaterias());
					setDocMateriasPosibles(getDocMateriasPosibles());
					
				} else {
					JOptionPane.showMessageDialog(null,"Cookie de Sesión inválido");
				}
			}
		});
	}
	
	public Document getDocMateriasPosibles() {
		
		Document materiasPosibles = null;
		try {
			materiasPosibles = Jsoup.connect("https://saew.epn.edu.ec/SAEINF/MateriasPosibles.aspx")
					.cookie("ASP.NET_SessionId", cookie.getStrCookies())
					.get();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error: Obtener materias posibles");
		}
		
		return materiasPosibles;
	}
	
	public Document getDocHorarioMaterias() {
		
		Document horarios = null;
		try {
			horarios = Jsoup.connect("https://saew.epn.edu.ec/SAEINF/HorariosMaterias.aspx")
					.cookie("ASP.NET_SessionId", cookie.getStrCookies())
					.get();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error: Obtener materias posibles");
		}
		
		return horarios;
	}
	
	@Override
	public void setContenido() {
		lblCookies = new JLabel("Ingresar Cookies de Sesión");
		lblCookies.setHorizontalTextPosition(JLabel.CENTER);
		lblCookies.setHorizontalAlignment(JLabel.CENTER);

		txtCookies = new JFormattedTextField(Formateador.formatCookies());
		txtCookies.setColumns(25);
		txtCookies.setHorizontalAlignment(JLabel.CENTER);

		btnAceptar = new JButton("Aceptar");

		panelCookies.add(lblCookies, BorderLayout.CENTER);
		panelCookies.add(txtCookies, BorderLayout.CENTER);
		panelCookies.add(btnAceptar, BorderLayout.CENTER);
	}
	
	@Override
	public JPanel getPanel() {
		return panelCookies;
	}

	@Override
	public void update(Observable o, Object arg) {
		txtCookies.setText(cookie.getStrCookies());
	}
}
