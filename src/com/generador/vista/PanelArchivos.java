package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelArchivos extends Panel {

	private JPanel panelArchivosLocales;
	private JLabel labelLocalMateriasPosibles;
	private JLabel labelPathMateriaPosibles;
	private JButton btnMateriasPosibles;
	private JLabel labelLocalHorarios;
	private JLabel labelPathHorarios;
	private JButton btnHorarios;
	
	public PanelArchivos() {
		panelArchivosLocales = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		setContenido();
		
		//Listener
		btnMateriasPosibles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileMatariasPosibles = new JFileChooser();
				int result = fileMatariasPosibles.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileMatariasPosibles.getSelectedFile();

					if (selectedFile.getAbsolutePath().matches(".*Materias Posibles\\.html$")) {
						//setStrPathMateriasPosibles(selectedFile.getAbsolutePath());
						labelPathMateriaPosibles.setText(selectedFile.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(null,
								"Archivo: Materias Posibles invalido\n"
						+ "Seleccionar archivo Materias Posibles.html");
					}

				}
			}
		});

		btnHorarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileHorarios = new JFileChooser();
				int result = fileHorarios.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileHorarios.getSelectedFile();

					if (selectedFile.getAbsolutePath().matches(".*Horarios\\.html$")) {
						//setStrPathHorarios(selectedFile.getAbsolutePath());
						labelPathHorarios.setText(selectedFile.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(null,
								"Archivo: Horarios invalido\n"
										+ "Seleccionar archivo Horarios.html");
					}
				}
			}
		});
	}
	
	@Override
	protected void setContenido() {
		labelLocalMateriasPosibles = new JLabel("Archivo - Materias Posibles");
		labelPathMateriaPosibles = new JLabel("");
		labelLocalMateriasPosibles.setHorizontalTextPosition(JLabel.CENTER);
		btnMateriasPosibles = new JButton("Seleccionar");

		labelLocalHorarios = new JLabel("Archivo - Horarios");
		labelPathHorarios = new JLabel("");
		labelLocalHorarios.setHorizontalTextPosition(JLabel.CENTER);
		btnHorarios = new JButton("Seleccionar");

		panelArchivosLocales.add(labelLocalMateriasPosibles,BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel("                   "),BorderLayout.CENTER);
		panelArchivosLocales.add(btnMateriasPosibles, BorderLayout.CENTER);
		panelArchivosLocales.add(labelPathMateriaPosibles, BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel(
				"                                                                 "
				+ "                              "),BorderLayout.CENTER);
		panelArchivosLocales.add(labelLocalHorarios, BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel("                   "),BorderLayout.CENTER);
		panelArchivosLocales.add(btnHorarios, BorderLayout.CENTER);
		panelArchivosLocales.add(labelPathHorarios, BorderLayout.CENTER);
	}
	
	@Override
	protected JPanel getPanel() {
		return panelArchivosLocales;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
