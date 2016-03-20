package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private String strPathMateriasPosibles = "";
	private String strPathHorarios = "";
	
	private Panel panelWeb, panelCookies, panelArchivosLocales;
	private PanelConfiguracion panelTodosHorarios;
	private PanelVisualizador panelVisualizador;
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
		JMenuItem itemConfiguracion = new JMenuItem("Configuración");
		JMenuItem itemOrganizador = new JMenuItem("Organizador");
		menuPlanificador.add(itemConfiguracion);
		menuPlanificador.add(itemOrganizador);

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

		// TODO JPanel Organizador
		itemOrganizador.setEnabled(false);

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

		itemConfiguracion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Documentos documentos;
				documentos = Documentos.instancia();
				
				if (documentos.isValid()) {
					
					datosProcesados = new Datos(documentos.getDocMateriasPosibles(), documentos.getDocHorarioMaterias());
					
					if (panelTodosHorarios != null) {
						int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea volver a configurar el organizador?\n"
								+ "Nota: Se establecera la configuración por defecto"
			        			, "Confirmación", JOptionPane.YES_NO_OPTION);
			        	 if (respuesta == JOptionPane.YES_OPTION) {
			        		 panelTodosHorarios = new PanelConfiguracion(datosProcesados.getMapMaterias().getAllMaterias());
			        	 }
					} else {
						panelTodosHorarios = new PanelConfiguracion(datosProcesados.getMapMaterias().getAllMaterias());
					}
					
					itemOrganizador.setEnabled(true);
					gui.setContentPane(panelTodosHorarios.getPanelConfiguracion());
					gui.repaint();
					gui.setVisible(true);
				}
			}
		});
		
		itemOrganizador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				itemOrganizador.setEnabled(false);
				
				Documentos documentos;
				documentos = Documentos.instancia();
				
				if (documentos.isValid() && datosProcesados != null) {
					
					organizador = new Organizador(panelTodosHorarios.getListaMaterias()
							, panelTodosHorarios.getMinCreditos()
							, panelTodosHorarios.getMaxCreditos()
							, panelTodosHorarios.getListaSeleccionado());
					
					organizador.calcularHorarioOptimo();
					
					if (organizador.getListaSoluciones().size() > 0) {
						System.out.println("Soluciones: "+ organizador.getListaSoluciones().size());
						panelVisualizador = new PanelVisualizador(organizador.getListaSoluciones());
					} else {
						JOptionPane.showMessageDialog(null, "No existe soluciones");
					}

					gui.setContentPane(panelVisualizador.getPanelVisualizador());
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
}