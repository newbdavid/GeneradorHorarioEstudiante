package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.generador.modelo.Materia;

public class PanelVisualizador {

	private JPanel panelVisualizador;
	private JTable tblMaterias, tblVisualizador;

	public PanelVisualizador(List<List<Materia>> listaSoluciones) {
		panelVisualizador = new JPanel();
		panelVisualizador.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelVisualizador.setSize(500, 480);
		setContenido(listaSoluciones);
	}

	protected void setContenido(List<List<Materia>> listaSoluciones) {

		for (List<Materia> listaMaterias : listaSoluciones) {
			tblVisualizador = tblVisualizador(listaMaterias);

			JScrollPane scrollVisualizador = new JScrollPane(tblVisualizador);
			scrollVisualizador.setPreferredSize(new Dimension(228, 200));

			tblMaterias = tblMaterias(listaMaterias);

			JScrollPane scrollMaterias = new JScrollPane(tblMaterias);
			scrollMaterias.setPreferredSize(new Dimension(240, 200));

			panelVisualizador.add(scrollVisualizador, BorderLayout.CENTER);
			panelVisualizador.add(scrollMaterias, BorderLayout.CENTER);
		}
	}

	public DefaultTableModel fillTblMaterias(List<Materia> listaMaterias) {
		String[] colName = { "Codigo", "Nombre", "Paralelo", "Aula", "Horario",
				"Creditos", "Num Matricua", "Categoria", "Prioridad" };

		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.setColumnIdentifiers(colName);
		tblMaterias = new JTable(tableModel);

		Object[] datos = new String[9];

		for (Materia materia : listaMaterias) {
			datos = materia.getInfoMateria();
			tableModel.addRow(datos);
		}
		return tableModel;
	}

	public void fillTblVisualizador(List<Materia> listaMaterias) {

	}

	public JPanel getPanelVisualizador() {
		return panelVisualizador;
	}

	public List<Integer> listaCeldasRellenar (List<Integer> horario) {

		List<Integer> numeros = new ArrayList<Integer>();
		List<Integer> desplazamientos = new ArrayList<Integer>();

		numeros.add(horario.get(0) - 7);
		desplazamientos.add(horario.get(1) - horario.get(0));

		for (int i = 0; i < desplazamientos.size(); i++) {
			for (int j = numeros.get(i) + 1; j < numeros.get(i) + desplazamientos.get(i); j++) {
				numeros.add(j);
			}
		}

		return numeros;
	}

	public JTable tblVisualizador (List<Materia> listaMaterias) {

		String[] colName = { "", "L", "M", "MI", "J", "V", "S"};

		//JTable no editable
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableModel.setColumnIdentifiers(colName);
		JTable tblVisualizador = new JTable(tableModel);

		//Primera columna de identificadores
		tblVisualizador.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {

			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(JLabel.CENTER);
			}

			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
				if (table != null) {
					JTableHeader header = table.getTableHeader();

					if (header != null) {
						setForeground(header.getForeground());
						setBackground(header.getBackground());
						setFont(header.getFont());
					}
				}

				if (isSelected) {
					setFont(getFont().deriveFont(Font.BOLD));
				}

				setValue(value);
				return this;
			}

		});

		//Contenido Jtable
		for (int i = 0; i < 15; i++) {
			Object[] datos = {Integer.toString(i+7),"","","","","",""};
			tableModel.addRow(datos);
		}

		tblVisualizador.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblVisualizador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		List<Integer> numeros;
		
		//Configuracion ancho columnas
		tblVisualizador.getColumnModel().getColumn(0).setPreferredWidth(30);
		
		for (int dia = 1; dia < colName.length; dia++) {
			
			//Configuracion ancho columnas
			tblVisualizador.getColumnModel().getColumn(dia).setPreferredWidth(30);

			//Color celdas
			numeros = new ArrayList<Integer>();

			for (Materia materia : listaMaterias) {
				switch (dia) {
				case 1:
					if (materia.getHorario().getLunes() != null)
						numeros.addAll(listaCeldasRellenar(materia.getHorario().getLunes()));
					break;
				case 2:
					if (materia.getHorario().getMartes() != null)
						numeros.addAll(listaCeldasRellenar(materia.getHorario().getMartes()));
					break;
				case 3:
					if (materia.getHorario().getMiercoles() != null)
						numeros.addAll(listaCeldasRellenar(materia.getHorario().getMiercoles()));
					break;
				case 4:
					if (materia.getHorario().getJueves() != null)
						numeros.addAll(listaCeldasRellenar(materia.getHorario().getJueves()));
					break;
				case 5:
					if (materia.getHorario().getViernes() != null)
						numeros.addAll(listaCeldasRellenar(materia.getHorario().getViernes()));
					break;
				case 6:
					if (materia.getHorario().getSabado() != null)
						numeros.addAll(listaCeldasRellenar(materia.getHorario().getSabado()));
					break;
					
				default:
					break;
				}
			}
			TableColumn columna= tblVisualizador.getColumnModel().getColumn(dia);
			columna.setCellRenderer(new RellenoCeldas(numeros));
		}
		return tblVisualizador;
	}
	
	public JTable tblMaterias(List<Materia> listaMaterias) {
	
		JTable tblMaterias = new JTable();
		tblMaterias.setModel(fillTblMaterias(listaMaterias));
		tblMaterias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMaterias.setFillsViewportHeight(true);
		tblMaterias.getColumnModel().getColumn(1).setPreferredWidth(300);
		tblMaterias.getColumnModel().getColumn(4).setPreferredWidth(300);
		
		return tblMaterias;
	}

	private class RellenoCeldas extends DefaultTableCellRenderer {

		List<Integer> numeros;

		public RellenoCeldas(List<Integer> numeros) {
			this.numeros = numeros;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value
				, boolean isSelected, boolean hasFocus,	int row, int column) {

			TableModel tableModel = (TableModel) table.getModel();
			JLabel l = (JLabel) super.getTableCellRendererComponent(table, value
					, isSelected, hasFocus, row, column);

			//Condicion para rellenar
			if (numeros.contains(row)) {
				l.setBackground(Color.LIGHT_GRAY);
			} else {
				l.setBackground(Color.WHITE);
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}
}

