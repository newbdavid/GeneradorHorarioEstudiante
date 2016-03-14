package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jsoup.Jsoup;

public class PanelArchivos extends Panel {

	private JPanel panelArchivosLocales;
	private JLabel lblLocalMateriasPosibles, lblPathMateriaPosibles, lblLocalHorarios, lblPathHorarios;
	private JButton btnMateriasPosibles, btnHorarios;
	
	public PanelArchivos() {
		panelArchivosLocales = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		setContenido();
		
		//Listener
		btnMateriasPosibles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				File fileMaterias = selector();
				if (fileMaterias != null) {
					if (fileMaterias.getAbsolutePath().matches(".*Materias Posibles\\.html$")) {
						try {
							setDocMateriasPosibles(Jsoup.parse(fileMaterias, "UTF-8", ""));
						} catch (IOException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,"Conversión: Materias Posibles invalido\n");
						}
						lblPathMateriaPosibles.setText(fileMaterias.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(null,
								"Archivo: Materias Posibles invalido\nSeleccionar archivo Materias Posibles.html");
					}
				}
				
			}
		});

		btnHorarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				File fileHorarios = selector();
				if (fileHorarios != null){
					if (fileHorarios.getAbsolutePath().matches(".*Horarios\\.html$")) {
						try {
							setDocHorarioMaterias(Jsoup.parse(fileHorarios, "UTF-8", ""));
						} catch (IOException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,"Conversión: Horarios Materias invalido\n");
						}
						lblPathHorarios.setText(fileHorarios.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(null,
								"Archivo: Horarios invalido\n Seleccionar archivo Horarios.html");
					}
				}
			}
		});
	}
	
	private File selector() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo HTML", "HTML"));
		
		int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	@Override
	protected void setContenido() {
		lblLocalMateriasPosibles = new JLabel("Archivo - Materias Posibles");
		lblPathMateriaPosibles = new JLabel("");
		lblLocalMateriasPosibles.setHorizontalTextPosition(JLabel.CENTER);
		btnMateriasPosibles = new JButton("Seleccionar");

		lblLocalHorarios = new JLabel("Archivo - Horarios");
		lblPathHorarios = new JLabel("");
		lblLocalHorarios.setHorizontalTextPosition(JLabel.CENTER);
		btnHorarios = new JButton("Seleccionar");

		panelArchivosLocales.add(lblLocalMateriasPosibles,BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel("                   "),BorderLayout.CENTER);
		panelArchivosLocales.add(btnMateriasPosibles, BorderLayout.CENTER);
		panelArchivosLocales.add(lblPathMateriaPosibles, BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel(
				"                                                                 "
				+ "                              "),BorderLayout.CENTER);
		panelArchivosLocales.add(lblLocalHorarios, BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel("                   "),BorderLayout.CENTER);
		panelArchivosLocales.add(btnHorarios, BorderLayout.CENTER);
		panelArchivosLocales.add(lblPathHorarios, BorderLayout.CENTER);
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
