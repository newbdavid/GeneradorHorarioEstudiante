package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.generador.utilidad.JTextFieldLimit;
import com.generador.utilidad.MultiMapa;
import com.generdor.modelo.Datos;

/**
 * Clase encargada de desplegar la información en interfaz gráfica
 * 
 * @author Luis Reinoso
 *
 */
public class GUI {

	private String strCookie = "";
	private JTable tabla;
	private String strPathMateriasPosibles = "";
	private String strPathHorarios = "";

	public GUI() throws IOException, URISyntaxException {

		// Frame principal
		JFrame gui = new JFrame();

		// Barra de menú
		JMenuBar menuBar = new JMenuBar();

		// Menu archivo
		JMenu menuArchivo = new JMenu("Archivo");
		JMenu menuLogin = new JMenu("Login");
		JMenuItem itemWebSaew = new JMenuItem("Web SAEW");
		JMenuItem itemCookieSession = new JMenuItem("Cookies de sesión");
		JMenuItem itemLocalFiles = new JMenuItem("Archivos locales");
		JMenuItem itemExportar = new JMenuItem("Exportar");
		JMenuItem itemSalir = new JMenuItem("Salir");
		menuArchivo.add(menuLogin);
		menuLogin.add(itemWebSaew);
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

		// JPanel WebSAEW
		JPanel panelWebSAEW = new JPanel();
		panelWebSAEW.setLayout(new FlowLayout());
		panelWebSAEW.setSize(500, 480);
		panelWebSAEW.setVisible(true);

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

		// JPanel Cookies Session
		JPanel panelCookies = new JPanel();
		panelCookies.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCookies.setSize(500, 480);

		JLabel labelCookies = new JLabel("Ingresar Cookies de Sesión");
		labelCookies.setHorizontalTextPosition(JLabel.CENTER);
		labelCookies.setHorizontalAlignment(JLabel.CENTER);

		JTextField txtfCookies = new JTextField(25);
		txtfCookies.setDocument(new JTextFieldLimit(24));
		txtfCookies.setHorizontalAlignment(JLabel.CENTER);

		JButton btnSession = new JButton("Aceptar");

		panelCookies.add(labelCookies, BorderLayout.CENTER);
		panelCookies.add(txtfCookies, BorderLayout.CENTER);
		panelCookies.add(btnSession, BorderLayout.CENTER);

		// JPanel Archivos Locales
		JPanel panelArchivosLocales = new JPanel(new FlowLayout(
				FlowLayout.TRAILING));

		JLabel labelLocalMateriasPosibles = new JLabel(
				"Archivo - Materias Posibles");
		JLabel labelPathMateriaPosibles = new JLabel("");
		labelLocalMateriasPosibles.setHorizontalTextPosition(JLabel.CENTER);
		JButton btnMateriasPosibles = new JButton("Seleccionar");

		JLabel labelLocalHorarios = new JLabel("Archivo - Horarios");
		JLabel labelPathHorarios = new JLabel("");
		labelLocalHorarios.setHorizontalTextPosition(JLabel.CENTER);
		JButton btnHorarios = new JButton("Seleccionar");

		panelArchivosLocales.add(labelLocalMateriasPosibles,
				BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel("                   "),
				BorderLayout.CENTER);
		panelArchivosLocales.add(btnMateriasPosibles, BorderLayout.CENTER);
		panelArchivosLocales.add(labelPathMateriaPosibles, BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel(
				"                                                                 "
						+ "                              "),
				BorderLayout.CENTER);
		panelArchivosLocales.add(labelLocalHorarios, BorderLayout.CENTER);
		panelArchivosLocales.add(new JLabel("                   "),
				BorderLayout.CENTER);
		panelArchivosLocales.add(btnHorarios, BorderLayout.CENTER);
		panelArchivosLocales.add(labelPathHorarios, BorderLayout.CENTER);

		// TODO JPanel Exportar
		itemExportar.setEnabled(false);

		// JPanel todos los horarios
		JPanel panelTodosHorarios = new JPanel();
		panelTodosHorarios.setLayout(new BorderLayout());
		panelTodosHorarios.setSize(500, 480);

		JLabel labelTabla = new JLabel("Materias disponibles");
		panelTodosHorarios.add(labelTabla, BorderLayout.NORTH);
		panelTodosHorarios.add(new JScrollPane(getTableData()),
				BorderLayout.CENTER);

		// TODO JPanel Organizador
		itemPlanOrganizador.setEnabled(false);

		// JFrame
		gui.setLayout(new BorderLayout());
		gui.setTitle("Generador de Horario");
		gui.setBounds(500, 500, 500, 500);
		gui.setJMenuBar(menuBar);
		gui.setContentPane(panelBienvenida);
		gui.setVisible(true);

		// Eventos
		itemWebSaew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gui.setContentPane(webPage());
				} catch (IOException | URISyntaxException e1) {
					JOptionPane.showMessageDialog(null,
							"¡Error en opción Web SAEW!");
					e1.printStackTrace();
				}
				gui.repaint();
				gui.setVisible(true);
			}
		});

		itemCookieSession.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setContentPane(panelCookies);
				txtfCookies.setText(getStrCookie());
				gui.repaint();
				gui.setVisible(true);
			}
		});

		btnSession.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtfCookies.getText().length() == 24) {
					setStrCookie(txtfCookies.getText());
				} else {
					JOptionPane.showMessageDialog(null,
							"Cookie de Sesión inválido");
				}
			}
		});

		itemLocalFiles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setContentPane(panelArchivosLocales);
				gui.repaint();
				gui.setVisible(true);
			}
		});

		btnMateriasPosibles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileMatariasPosibles = new JFileChooser();
				int result = fileMatariasPosibles.showOpenDialog(panelCookies);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileMatariasPosibles.getSelectedFile();

					if (selectedFile.getAbsolutePath().matches(
							".*Materias Posibles\\.html$")) {
						setStrPathMateriasPosibles(selectedFile
								.getAbsolutePath());
						labelPathMateriaPosibles.setText(selectedFile
								.getAbsolutePath());
					} else {
						JOptionPane
								.showMessageDialog(
										null,
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
				int result = fileHorarios.showOpenDialog(panelCookies);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileHorarios.getSelectedFile();

					if (selectedFile.getAbsolutePath().matches(
							".*Horarios\\.html$")) {
						setStrPathHorarios(selectedFile.getAbsolutePath());
						labelPathHorarios.setText(selectedFile
								.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(null,
								"Archivo: Horarios invalido\n"
										+ "Seleccionar archivo Horarios.html");
					}
				}
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

				if (!getStrCookie().equals("")
						|| !getStrPathHorarios().equals("")
						|| !getStrCookie().equals("")) {

					if (!getStrPathHorarios().equals("")
							&& !getStrPathMateriasPosibles().equals("")) {
						Datos dat;
						try {
							dat = new Datos(new File(
									getStrPathMateriasPosibles()), new File(
									getStrPathHorarios()));
							setUpTableData(dat.getMapMaterias());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						gui.setContentPane(new JScrollPane(getTableData()));
						gui.repaint();
						gui.setVisible(true);
					} else if (!getStrCookie().equals("")) {
						try {
							Document materiasPosibles = Jsoup
									.connect(
											"https://saew.epn.edu.ec/SAEINF/MateriasPosibles.aspx")
									.cookie("ASP.NET_SessionId", getStrCookie())
									.get();
							System.out.println(materiasPosibles.html()
									.toString());

							Document horarios = Jsoup
									.connect(
											"https://saew.epn.edu.ec/SAEINF/HorariosMaterias.aspx")
									.cookie("ASP.NET_SessionId", getStrCookie())
									.get();
							System.out.println(horarios.html().toString());

							// Verifica si hay error
							if (!(materiasPosibles.title().matches(".*Error$") || horarios
									.title().matches(".*Error$"))) {
								Datos dat = new Datos(materiasPosibles,
										horarios);
								setUpTableData(dat.getMapMaterias());
							} else {
								JOptionPane
										.showMessageDialog(
												null,
												"Error Cookies de Sesión\n"
														+ "Verificar si se encuentra autenticado.");
							}

						} catch (IOException e1) {
							JOptionPane
									.showMessageDialog(
											null,
											"Error Cookies de Sesión\n"
													+ "Verificar si se encuentra autenticado.");
							e1.printStackTrace();
						}
						gui.setContentPane(new JScrollPane(getTableData()));
						gui.repaint();
						gui.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "No entré :(");
					}
				} else {
					JOptionPane
							.showMessageDialog(null,
									"Seleccionar un método para cargar la información de las materias");
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

	// Web SAEW
	private JScrollPane webPage() throws IOException, URISyntaxException {

		JEditorPane jEditWebPage = new JEditorPane();
		jEditWebPage.setEditable(false);
		jEditWebPage.setFocusable(false);
		jEditWebPage.setVisible(true);

		CookieManager manager = new CookieManager();
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(manager);

		try {
			jEditWebPage.setPage("https://saew.epn.edu.ec");
		} catch (IOException e) {
			jEditWebPage.setContentType("text/html");
			jEditWebPage
					.setText("<html><h1>Error</h1><p>No se puede cargar https://saew.epn.edu.ec</p></html>");
		}

		JScrollPane scrollPane = new JScrollPane(jEditWebPage);
		setStrCookie(manager.getCookieStore().getCookies().get(0).toString()
				.substring(18));
		return scrollPane;
	}

	// Llenado de tabla
	public JTable getTableData() {
		return this.tabla;
	}

	public void setUpTableData(MultiMapa mapMaterias) {
		String[] colName = { "Codigo", "Nombre", "Paralelo", "Aula", "Horario",
				"Creditos", "Num Matricua", "Categoria", "Prioridad" };
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(colName);
		this.tabla = new JTable(tableModel);

		for (int i = 0; i < mapMaterias.size(); i++) {
			for (int j = 0; j < mapMaterias.get(
					mapMaterias.keySet().toArray()[i]).size(); j++) {
				Object[] datos = new String[9];
				datos = mapMaterias.get(mapMaterias.keySet().toArray()[i])
						.get(j).getInfoMateria();
				tableModel.addRow(datos);
			}
		}

		this.tabla.setModel(tableModel);
	}
}
