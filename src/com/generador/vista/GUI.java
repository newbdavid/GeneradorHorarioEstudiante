package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.generador.controlador.Documentos;
import com.generador.controlador.Organizador;
import com.generador.modelo.Datos;
import com.generador.modelo.Materia;

/**
 * Clase encargada de desplegar la información en interfaz gráfica
 * 
 * @author Luis Reinoso
 *
 */
public class GUI {

	private String strCookie = "";
	private JTable tblTodosHorarios, tblOptimos;
	private String strPathMateriasPosibles = "";
	private String strPathHorarios = "";
	
	private Panel panelWeb, panelCookies, panelArchivosLocales;
	private Organizador organizador;
	private Datos datosProcesados;

	public GUI() {

		// Frame principal
		JFrame gui = new JFrame();

		// Barra de menú
		JMenuBar menuBar = new JMenuBar();

		// Menu archivo
		JMenu menuArchivo = new JMenu("Archivo");
		JMenu menuLogin = new JMenu("Origen de datos");
		JMenuItem itemWebSession = new JMenuItem("Iniciar sesión SAEW");
		JMenuItem itemCookieSession = new JMenuItem("Cookies de sesión");
		JMenuItem itemLocalFiles = new JMenuItem("Archivos locales");
		JMenuItem itemExportar = new JMenuItem("Exportar");
		JMenuItem itemSalir = new JMenuItem("Salir");
		menuArchivo.add(menuLogin);
		menuLogin.add(itemWebSession);
		menuLogin.add(itemCookieSession);
		menuLogin.add(itemLocalFiles);
		menuArchivo.add(itemExportar);
		menuArchivo.add(itemSalir);

		// Menu Planificador
		JMenu menuPlanificador = new JMenu("Planificador");
		JMenuItem itemPlanTodos = new JMenuItem("Todos horarios");
		JMenuItem itemPlanOrganizador = new JMenuItem("Organizador");
		menuPlanificador.add(itemPlanTodos);
		menuPlanificador.add(itemPlanOrganizador);

		// Menu Ayuda
		JMenu ayuda = new JMenu("Ayuda");
		JMenuItem itemAcerca = new JMenuItem("Acerca de");
		ayuda.add(itemAcerca);

		// Agregando menus
		menuBar.add(menuArchivo);
		menuBar.add(menuPlanificador);
		menuBar.add(ayuda);

		// JPanel Bienvenida
		JPanel panelBienvenida = new JPanel();
		panelBienvenida.setLayout(new BorderLayout());
		panelBienvenida.setSize(500, 480);

		JLabel labelBienvenida = new JLabel("<html><b>Bienvenido</b><p>"
				+ "Selecciona cualquiera de los tres métodos para "
				+ "cargar la información de las materias. "
				+ "<ul><li>Autenticar - Web SAEW</li>"
				+ "<li>Ingresar cookies de sesión</li>"
				+ "<li>Cargar archivos con información de horarios</li>"
				+ "</ul>" + "ver menú Archivo -> Login.</p></hmtl>");
		labelBienvenida.setHorizontalTextPosition(JLabel.CENTER);
		labelBienvenida.setHorizontalAlignment(JLabel.CENTER);
		panelBienvenida.add(labelBienvenida, BorderLayout.CENTER);

		// JPanel Web Form
		panelWeb = new PanelWeb();
		
		// JPanel Cookies Session
		panelCookies = new PanelCookies();
		
		// JPanel Archivos Locales
		panelArchivosLocales = new PanelArchivos();
		
		// TODO JPanel Exportar
		itemExportar.setEnabled(false);

		// JPanel todos los horarios
		JPanel panelTodosHorarios = new JPanel();
		panelTodosHorarios.setLayout(new BorderLayout());
		panelTodosHorarios.setSize(500, 480);

		JLabel labelTabla = new JLabel("Materias disponibles");
		panelTodosHorarios.add(labelTabla, BorderLayout.NORTH);
		panelTodosHorarios.add(new JScrollPane(this.tblTodosHorarios),
				BorderLayout.CENTER);

		// TODO JPanel Organizador
		JPanel panelOptimo = new JPanel();
		panelOptimo.setLayout(new BorderLayout());
		panelOptimo.setSize(500, 480);

		JLabel labelTablaOptimo = new JLabel("Optimo");
		panelOptimo.add(labelTablaOptimo, BorderLayout.NORTH);
		panelOptimo.add(new JScrollPane(this.tblOptimos),
				BorderLayout.CENTER);

		// JFrame
		gui.setLayout(new BorderLayout());
		gui.setTitle("Generador de Horario");
		gui.setBounds(500, 500, 500, 500);
		gui.setJMenuBar(menuBar);
		gui.setContentPane(panelBienvenida);
		gui.setVisible(true);

		// Eventos
		itemWebSession.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setContentPane(panelWeb.getPanel());
				gui.repaint();
				gui.setVisible(true);
			}
		});

		itemCookieSession.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setContentPane(panelCookies.getPanel());
				gui.repaint();
				gui.setVisible(true);
			}
		});

		itemLocalFiles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setContentPane(panelArchivosLocales.getPanel());
				gui.repaint();
				gui.setVisible(true);
			}
		});

		itemSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		itemPlanTodos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Documentos documentos;
				documentos = Documentos.instancia();
				
				if (documentos.isValid()) {
					
					System.out.println("see");
					datosProcesados = new Datos(documentos.getDocMateriasPosibles(), documentos.getDocHorarioMaterias());
					System.out.println("see2");
					System.out.println(datosProcesados.getMapMaterias().size());
					fillTblTodosHorarios(datosProcesados.getMapMaterias().getAllMaterias());
					
					gui.setContentPane(new JScrollPane(tblTodosHorarios));
					gui.repaint();
					gui.setVisible(true);
				}
			}
		});
		
		itemPlanOrganizador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				itemPlanOrganizador.setEnabled(false);

				List<Integer> listaSelecionado = new ArrayList<Integer>(1);
				
				Documentos documentos;
				documentos = Documentos.instancia();
				
				if (documentos.isValid() && datosProcesados != null) {
					
					organizador = new Organizador(datosProcesados.getMapMaterias(), 15, 30, listaSelecionado);
					organizador.calcularHorarioOptimo();
					
					if (organizador.getListaSoluciones().size() > 0) 
						fillTblOptimo(organizador.getListaSoluciones());
					else
						JOptionPane.showMessageDialog(null, "No existe soluciones");

					gui.setContentPane(new JScrollPane(tblOptimos));
					gui.repaint();
					gui.setVisible(true);
				}
			}
		});

		itemAcerca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Desarrollador: Luis Reinoso\n"
										+ "Más información: github.com/lomejordejr.com\n"
										+ "Licencia: GPL3\n");
			}
		});

		gui.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}

	public String getStrCookie() {
		return strCookie;
	}

	public void setStrCookie(String strCookie) {
		this.strCookie = strCookie;
	}

	public String getStrPathMateriasPosibles() {
		return strPathMateriasPosibles;
	}

	public void setStrPathMateriasPosibles(String strPathMateriasPosibles) {
		this.strPathMateriasPosibles = strPathMateriasPosibles;
	}

	public String getStrPathHorarios() {
		return strPathHorarios;
	}

	public void setStrPathHorarios(String strPathHorarios) {
		this.strPathHorarios = strPathHorarios;
	}

	// Llenado de tabla
	public JTable getTableData() {
		return this.tblTodosHorarios;
	}

	public void fillTblTodosHorarios(List<Materia> listaMaterias) {
		String[] colName = { "Codigo", "Nombre", "Paralelo", "Aula", "Horario",
				"Creditos", "Num Matricua", "Categoria", "Prioridad" };
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(colName);
		tblTodosHorarios = new JTable(tableModel);

		Object[] datos = new String[9];
		for (Materia materia : listaMaterias) {
			System.out.println(materia.getInfoMateria().toString());
			datos = materia.getInfoMateria();
			tableModel.addRow(datos);
		}
		
		tblTodosHorarios.setModel(tableModel);
	}
	
	public void fillTblOptimo(List<List<Materia>> listaSoluciones) {
		String[] colName = { "Codigo", "Nombre", "Paralelo", "Aula", "Horario",
				"Creditos", "Num Matricua", "Categoria", "Prioridad" };
		
		Object[] vacio = {"","","","","","","","",""};
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(colName);
		tblOptimos = new JTable(tableModel);

		for (List<Materia> solucion : listaSoluciones) {
		
			Object[] datos = new String[9];
			for (Materia materia : solucion) {
				datos = materia.getInfoMateria();
				tableModel.addRow(datos);
			}
			tableModel.addRow(vacio);
			
		}
		tblOptimos.setModel(tableModel);
	}
}