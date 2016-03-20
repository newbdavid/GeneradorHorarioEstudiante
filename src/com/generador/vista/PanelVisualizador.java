package com.generador.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import com.generador.modelo.Materia;

public class PanelVisualizador {

	private JPanel panelVisualizador;
	private JTable tblMaterias, tblVisualizador;
	
	public PanelVisualizador(List<List<Materia>> listaSoluciones) {
		panelVisualizador = new JPanel();
		panelVisualizador.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelVisualizador.setSize(500, 480);
		System.out.println(listaSoluciones.size());
		setContenido(listaSoluciones.get(0));
	}
	
	protected void setContenido(List<Materia> listaMaterias) {
		
		System.out.println("Tamaño solucion: "+listaMaterias.size());
		tblMaterias = new JTable();
		tblMaterias.setModel(fillTblOptimo(listaMaterias));
		panelVisualizador.add(new JScrollPane(tblMaterias), BorderLayout.CENTER);
	}
	
	public DefaultTableModel fillTblOptimo(List<Materia> listaMatarias) {
		String[] colName = { "Codigo", "Nombre", "Paralelo", "Aula", "Horario",
				"Creditos", "Num Matricua", "Categoria", "Prioridad" };
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(colName);
		tblMaterias = new JTable(tableModel);

		Object[] datos = new String[9];
		
		for (Materia materia : listaMatarias) {
			datos = materia.getInfoMateria();
			tableModel.addRow(datos);
		}
		
		return tableModel;
	}
	
	public JPanel getPanelVisualizador() {
		return panelVisualizador;
	}
}
