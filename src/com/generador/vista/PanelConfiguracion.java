package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import com.generador.modelo.Materia;

public class PanelConfiguracion implements Observer {

	JPanel panelConfiguracion;
	JLabel lblMinCreditos, lblMaxCreditos;
	JSpinner jspMinCreditos, jspMaxCreditos;
	JTable tblTodosHorarios;
	JButton btnGuardar;
	List<Materia> listaMaterias;
	int minCreditos, maxCreditos;
	
	public PanelConfiguracion(List<Materia> listaMaterias) {
		panelConfiguracion = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		setContenido(listaMaterias);
		this.listaMaterias = listaMaterias;
		
		//Eventos
		tblTodosHorarios.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        int fila = tblTodosHorarios.rowAtPoint(me.getPoint());
		        if (me.getClickCount() == 2) {
		        	int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea elimnar la materia selecionada?"
		        			, "Confirmación", JOptionPane.YES_NO_OPTION);
		        	 if (respuesta == JOptionPane.YES_OPTION) {
		        		 listaMaterias.remove(fila);
		        		 ((DefaultTableModel)tblTodosHorarios.getModel()).removeRow(fila);
		        	 }
		        }
		    }
		});
	}

	protected void setContenido(List<Materia> listaMaterias) {
		lblMinCreditos = new JLabel("Minimo creditos");
		lblMinCreditos.setHorizontalTextPosition(JLabel.CENTER);
		jspMinCreditos = new JSpinner(new SpinnerNumberModel(15, 0, 30, 1));

		lblMaxCreditos = new JLabel("Máximo creditos");
		lblMaxCreditos.setHorizontalTextPosition(JLabel.CENTER);
		jspMaxCreditos = new JSpinner(new SpinnerNumberModel(30, 0, 30, 1)); 
		
		String[] colName = { "Codigo", "Nombre", "Paralelo", "Aula", "Horario",
				"Creditos", "Num Matricua", "Categoria", "Prioridad" };
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.setColumnIdentifiers(colName);
		tblTodosHorarios = new JTable(tableModel);

		Object[] datos = new String[9];
		for (Materia materia : listaMaterias) {
			System.out.println(materia.getInfoMateria().toString());
			datos = materia.getInfoMateria();
			tableModel.addRow(datos);
		}
		tblTodosHorarios.setModel(tableModel);
		tblTodosHorarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblTodosHorarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollTabla = new JScrollPane(tblTodosHorarios);
		scrollTabla.setPreferredSize(new Dimension(480, 300));
		tblTodosHorarios.getColumnModel().getColumn(1).setPreferredWidth(300);
		tblTodosHorarios.getColumnModel().getColumn(4).setPreferredWidth(300);
		
		btnGuardar = new JButton("Guardar");
		
		panelConfiguracion.add(lblMinCreditos, BorderLayout.CENTER);
		panelConfiguracion.add(jspMinCreditos, BorderLayout.CENTER);
		panelConfiguracion.add(lblMaxCreditos, BorderLayout.CENTER);
		panelConfiguracion.add(jspMaxCreditos, BorderLayout.CENTER);
		panelConfiguracion.add(scrollTabla, BorderLayout.CENTER);
		panelConfiguracion.add(btnGuardar, BorderLayout.CENTER);
	}
	
	public JPanel getPanelConfiguracion() {
		return panelConfiguracion;
	}
	
	public int getMinCreditos() {
		return minCreditos;
	}

	public int getMaxCreditos() {
		return maxCreditos;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
