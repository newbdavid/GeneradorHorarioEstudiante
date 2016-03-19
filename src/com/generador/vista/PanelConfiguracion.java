package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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

public class PanelConfiguracion {

	JPanel panelConfiguracion;
	JLabel lblMinCreditos, lblMaxCreditos;
	JSpinner spMinCreditos, spMaxCreditos;
	JTable tblTodosHorarios;
	JButton btnGuardar;
	List<Materia> listaMaterias;
	List <Integer> listaSeleccionado;
	int minCreditos, maxCreditos;

	public PanelConfiguracion(List<Materia> listaMaterias) {
		panelConfiguracion = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		setContenido(listaMaterias);
		this.listaMaterias = listaMaterias;
		this.listaSeleccionado = new ArrayList<Integer>(1);
		this.minCreditos = (int) spMinCreditos.getValue();
		this.maxCreditos = (int) spMaxCreditos.getValue();

		//Eventos
		tblTodosHorarios.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				int fila = tblTodosHorarios.rowAtPoint(me.getPoint());
				if (me.getClickCount() == 2 && fila > -1 ) {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea elimnar la materia selecionada?"
							, "Confirmación", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						listaMaterias.remove(fila);
						((DefaultTableModel)tblTodosHorarios.getModel()).removeRow(fila);
					}
				}
			}
		});

		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				minCreditos = (int) spMinCreditos.getValue();
				maxCreditos = (int) spMaxCreditos.getValue();

				listaSeleccionado = new ArrayList<Integer>(1);
				for (int i = 0; i < listaMaterias.size(); i++) {
					if ((boolean)tblTodosHorarios.getModel().getValueAt(i
							,tblTodosHorarios.getColumn("Obligatorio").getModelIndex()))
						listaSeleccionado.add(i);
				}
			}
		});
	}

	protected void setContenido(List<Materia> listaMaterias) {
		lblMinCreditos = new JLabel("Minimo creditos");
		lblMinCreditos.setHorizontalTextPosition(JLabel.CENTER);
		spMinCreditos = new JSpinner(new SpinnerNumberModel(15, 0, 30, 1));

		lblMaxCreditos = new JLabel("Máximo creditos");
		lblMaxCreditos.setHorizontalTextPosition(JLabel.CENTER);
		spMaxCreditos = new JSpinner(new SpinnerNumberModel(30, 0, 30, 1)); 

		String[] colName = { "Codigo", "Nombre", "Paralelo", "Aula", "Horario",
				"Creditos", "Num Matricua", "Categoria", "Prioridad" };

		//JTable no editable
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == tblTodosHorarios.getColumn("Obligatorio").getModelIndex())
					return true;
				else
					return false;
			}
		};
		tableModel.setColumnIdentifiers(colName);
		tblTodosHorarios = new JTable(tableModel) {
			//Agregado checkbox
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 9:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		//Contenido Jtable
		Object[] datos = new String[9];
		for (Materia materia : listaMaterias) {
			System.out.println(materia.getInfoMateria().toString());
			datos = materia.getInfoMateria();
			tableModel.addRow(datos);
		}

		Object[] checkColumn = new Boolean[listaMaterias.size()];
		for (int i = 0; i < listaMaterias.size(); i++) {
			checkColumn[i] = false;
		}	
		tableModel.addColumn("Obligatorio", checkColumn);

		tblTodosHorarios.setModel(tableModel);
		tblTodosHorarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblTodosHorarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollTabla = new JScrollPane(tblTodosHorarios);
		scrollTabla.setPreferredSize(new Dimension(480, 300));

		tblTodosHorarios.getColumnModel().getColumn(1).setPreferredWidth(300);
		tblTodosHorarios.getColumnModel().getColumn(4).setPreferredWidth(300);
		tblTodosHorarios.setFillsViewportHeight(true);
		btnGuardar = new JButton("Guardar");

		panelConfiguracion.add(lblMinCreditos, BorderLayout.CENTER);
		panelConfiguracion.add(spMinCreditos, BorderLayout.CENTER);
		panelConfiguracion.add(lblMaxCreditos, BorderLayout.CENTER);
		panelConfiguracion.add(spMaxCreditos, BorderLayout.CENTER);
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
	
	public List<Integer> getListaSeleccionado() {
		return listaSeleccionado;
	}
}
