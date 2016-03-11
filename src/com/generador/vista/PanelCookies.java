package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.generador.modelo.Cookies;
import com.generador.utilidad.JTextFieldLimit;

/**
 * Clase que ofrece la vista de login por medio de Cookies
 * 
 * @author Luis Reinoso
 *
 */

public class PanelCookies extends PanelInicio {

	private JPanel panelCookies;
	private Cookies cookie;
	private JLabel labelCookies;
	private JTextField txtfCookies;
	private JButton btnSession;
	
	public PanelCookies() {
		panelCookies = new JPanel();
		panelCookies.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCookies.setSize(500, 480);
		setContenido();
		
		//Observer
		cookie = Cookies.instancia();
		cookie.addObserver(this);
		
		//Listener
		btnSession.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtfCookies.getText().length() == 24) {
					cookie.setStrCookies(txtfCookies.getText());
				} else {
					JOptionPane.showMessageDialog(null,
							"Cookie de Sesión inválido");
				}
			}
		});
	}
	
	@Override
	public void setContenido() {
		labelCookies = new JLabel("Ingresar Cookies de Sesión");
		labelCookies.setHorizontalTextPosition(JLabel.CENTER);
		labelCookies.setHorizontalAlignment(JLabel.CENTER);

		txtfCookies = new JTextField(25);
		txtfCookies.setDocument(new JTextFieldLimit(24));
		txtfCookies.setHorizontalAlignment(JLabel.CENTER);

		btnSession = new JButton("Aceptar");

		panelCookies.add(labelCookies, BorderLayout.CENTER);
		panelCookies.add(txtfCookies, BorderLayout.CENTER);
		panelCookies.add(btnSession, BorderLayout.CENTER);
	}
	
	@Override
	public JPanel getPanel() {
		return panelCookies;
	}

	@Override
	public void update(Observable o, Object arg) {
		txtfCookies.setText(cookie.getStrCookies());
	}
}
