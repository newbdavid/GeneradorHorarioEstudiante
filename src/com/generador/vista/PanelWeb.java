package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.generador.utilidad.JTextFieldLimit;

/**
 * Clase que ofrece la vista de login por medio de Form
 * 
 * @author Luis Reinoso
 *
 */

public class PanelWeb extends Panel {
	
	private JPanel panelWeb;
	private JButton btnConectar;
	private JLabel lblCedula, lblPass, lblPeriodo;
	private JTextField txtCedula, txtPass;
	private JComboBox<String> cboPeriodo;
	
	public PanelWeb() {
		panelWeb = new JPanel();
		panelWeb.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panelWeb.setSize(500, 480);
		setContenido();
		
		//Listener
		btnConectar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Test");
			}
		});
		
	}
	
	@Override
	public JPanel getPanel() {
		return panelWeb;
	}

	@Override
	public void setContenido() {
		lblCedula = new JLabel("Cedula: ");
		lblCedula.setHorizontalTextPosition(JLabel.CENTER);
		lblCedula.setHorizontalAlignment(JLabel.CENTER);

		txtCedula = new JTextField(25);
		txtCedula.setDocument(new JTextFieldLimit(10));
		txtCedula.setHorizontalAlignment(JLabel.CENTER);
		
		lblPass = new JLabel("Clave: ");
		lblPass.setHorizontalTextPosition(JLabel.CENTER);
		lblPass.setHorizontalAlignment(JLabel.CENTER);

		txtPass = new JTextField(25);
		txtPass.setDocument(new JTextFieldLimit(12));
		txtPass.setHorizontalAlignment(JLabel.CENTER);
		
		lblPeriodo = new JLabel("Periodo: ");
		lblPeriodo.setHorizontalTextPosition(JLabel.CENTER);
		lblPeriodo.setHorizontalAlignment(JLabel.CENTER);
		
		cboPeriodo = new JComboBox<>();
		cboPeriodo.addItem("2016-A");
		cboPeriodo.addItem("2016-B");
		cboPeriodo.addItem("2016-C");
		
		btnConectar = new JButton("Conectar");
		
		panelWeb.add(lblCedula, BorderLayout.CENTER);
		panelWeb.add(txtCedula, BorderLayout.CENTER);
		panelWeb.add(new JLabel("                          "),BorderLayout.CENTER);
		panelWeb.add(lblPass, BorderLayout.CENTER);
		panelWeb.add(txtPass, BorderLayout.CENTER);
		panelWeb.add(new JLabel("                          "),BorderLayout.CENTER);
		panelWeb.add(lblPeriodo, BorderLayout.CENTER);
		panelWeb.add(cboPeriodo,BorderLayout.CENTER);
		panelWeb.add(new JLabel("                          "),BorderLayout.CENTER);
		panelWeb.add(btnConectar, BorderLayout.CENTER);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
