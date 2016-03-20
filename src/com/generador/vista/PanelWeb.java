package com.generador.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.generador.modelo.Cookies;

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
	private JTextField txtCedula;
	private JPasswordField txtPassword;
	private JComboBox<String> cboPeriodo;
	private Cookies cookie;

	public PanelWeb() {
		panelWeb = new JPanel();
		panelWeb.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panelWeb.setSize(500, 480);
		setContenido();

		cookie = Cookies.instancia();

		//Listener
		btnConectar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isValido()) {
					if (login()) {
						JOptionPane.showMessageDialog(null, "Conexion exitosa");
						setDocHorarioMaterias(getDocHorarioMaterias());
						setDocMateriasPosibles(getDocMateriasPosibles());
					}
				}
			}
		});

	}

	public boolean isValido () {

		boolean valido = true;
		String strError = "Error:";

		if (txtCedula.getText().length() != 10) {
			strError += "\nLongitud cédula";
			valido = false;
		}

		if (new String(txtPassword.getPassword()).length() < 5) {
			strError += "\nLongitud mínima contraseña";
			valido = false;
		}

		if (!valido)
			JOptionPane.showMessageDialog(null, strError);

		return valido;
	}

	public boolean login() {

		String strError = "Error:";
		
		//Configuracion webClient
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		HtmlPage page;

		try {
			page = webClient.getPage("https://saew.epn.edu.ec");
			HtmlForm form = page.getFormByName("aspnetForm");

			HtmlSelect selectUser = form.getSelectByName("ctl00$ContentPlaceHolder1$cmbmodo");
			selectUser.setSelectedIndex(3);
			webClient.waitForBackgroundJavaScript(3000);

			form = page.getFormByName("aspnetForm");

			HtmlTextInput inputCedula = form.getInputByName("ctl00$ContentPlaceHolder1$UserName");

			//Ingresar Usuario
			inputCedula.setText(txtCedula.getText());
			webClient.waitForBackgroundJavaScript(4000);

			HtmlPasswordInput inputPassword = form.getInputByName("ctl00$ContentPlaceHolder1$Password");

			//Ingresar Password
			inputPassword.setText(new String(txtPassword.getPassword()));
			webClient.waitForBackgroundJavaScript(100);

			HtmlSubmitInput btnIngresar = form.getInputByName("ctl00$ContentPlaceHolder1$LoginButton");
			page = btnIngresar.click();
			webClient.waitForBackgroundJavaScript(100);
			
			if (page.getElementById("ctl00_ContentPlaceHolder1_FailureText").getTextContent()
					.equalsIgnoreCase("No existe usuario")) {
				strError += "\nNo existe usuario";
			} else {
				if (page.getElementById("ctl00_ContentPlaceHolder1_FailureText").getTextContent()
						.equalsIgnoreCase("Clave no Válida")) {
					strError += "\nClave no Válida";
				}
			}

			form = page.getFormByName("aspnetForm");
			selectUser = form.getSelectByName("ctl00$ContentPlaceHolder1$cmbperiodo");

			//Seleccionar periodo
			selectUser.setSelectedIndex(cboPeriodo.getSelectedIndex());
			btnIngresar = form.getInputByName("ctl00$ContentPlaceHolder1$LoginButton");

			btnIngresar.click();
			webClient.waitForBackgroundJavaScript(100);
			
			page = btnIngresar.click();
			webClient.waitForBackgroundJavaScript(100);
			
			//Autenticación carrera
			page = webClient.getPage("https://saew.epn.edu.ec/SAEINF/MateriasPosibles.aspx");
			
			if (page.getElementById("ctl00_ContentPlaceHolder1_lblMensajeError").getTextContent()
					.equalsIgnoreCase("Seleccione una Carrera")) {
				selectUser = (HtmlSelect) page.getElementById("ctl00_ContentPlaceHolder1_cmbCarrera");
			
				//Selecionado primera opcion de carrera
				selectUser.setSelectedIndex(0);
				webClient.waitForBackgroundJavaScript(1000);
			}
			
			cookie.setStrCookies(webClient.getCookieManager().getCookie("ASP.NET_SessionId").getValue());

		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error:\nConexión no establecida");
			return false;
		} catch (ElementNotFoundException e) {
			JOptionPane.showMessageDialog(null, strError+"\nDatos de materias sin procesar");
			return false;
		}
		return true;
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
		PlainDocument docCedula = new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

				//Caracteres permitidos: Solo digitos
				for (int i=0;i<str.length();i++) {					
					if (!Character.isDigit((str.charAt(i)))) {
						return;
					}
				}

				//Limitar maximo caracteres
				if ((getLength() + str.length()) <= 10) {
					super.insertString(offs, str, a);
				}
			}
		};
		txtCedula.setDocument(docCedula);
		txtCedula.setHorizontalAlignment(JLabel.CENTER);

		lblPass = new JLabel("Clave: ");
		lblPass.setHorizontalTextPosition(JLabel.CENTER);
		lblPass.setHorizontalAlignment(JLabel.CENTER);

		txtPassword = new JPasswordField(25);
		PlainDocument docPass = new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

				boolean borrar = true;

				//Caracteres permitidos: *,-,@,.,!,&,#
				for (int i=0;i<str.length();i++) {					
					if (!Character.isJavaIdentifierPart((str.charAt(i)))) {
						if (Character.compare('*', str.charAt(i)) == 0)
							borrar = false;

						if (Character.compare('*', str.charAt(i)) == 0)
							borrar = false;

						if (Character.compare('-', str.charAt(i)) == 0)
							borrar = false;

						if (Character.compare('@', str.charAt(i)) == 0)
							borrar = false;

						if (Character.compare('.', str.charAt(i)) == 0)
							borrar = false;

						if (Character.compare('!', str.charAt(i)) == 0)
							borrar = false;

						if (Character.compare('&', str.charAt(i)) == 0)
							borrar = false;

						if (Character.compare('#', str.charAt(i)) == 0)
							borrar = false;

						if (borrar){
							return;
						}
					}
				}

				//Limitar maximo caracteres
				if ((getLength() + str.length()) <= 12) {
					super.insertString(offs, str, a);
				}

			}
		};

		txtPassword.setDocument(docPass);
		txtPassword.setHorizontalAlignment(JLabel.CENTER);

		lblPeriodo = new JLabel("Periodo: ");
		lblPeriodo.setHorizontalTextPosition(JLabel.CENTER);
		lblPeriodo.setHorizontalAlignment(JLabel.CENTER);

		cboPeriodo = new JComboBox<>();
		cboPeriodo.addItem("2016-A");
		cboPeriodo.addItem("2015-R");
		cboPeriodo.addItem("2015-B");

		btnConectar = new JButton("Conectar");

		panelWeb.add(lblCedula, BorderLayout.CENTER);
		panelWeb.add(txtCedula, BorderLayout.CENTER);
		panelWeb.add(new JLabel("                          "),BorderLayout.CENTER);
		panelWeb.add(lblPass, BorderLayout.CENTER);
		panelWeb.add(txtPassword, BorderLayout.CENTER);
		panelWeb.add(new JLabel("                          "),BorderLayout.CENTER);
		panelWeb.add(lblPeriodo, BorderLayout.CENTER);
		panelWeb.add(cboPeriodo,BorderLayout.CENTER);
		panelWeb.add(new JLabel("                          "),BorderLayout.CENTER);
		panelWeb.add(btnConectar, BorderLayout.CENTER);
	}

	public Document getDocMateriasPosibles() {

		Document materiasPosibles = null;
		try {
			materiasPosibles = Jsoup.connect("https://saew.epn.edu.ec/SAEINF/MateriasPosibles.aspx")
					.cookie("ASP.NET_SessionId", cookie.getStrCookies())
					.get();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error: Obtener materias posibles");
		}

		return materiasPosibles;
	}

	public Document getDocHorarioMaterias() {

		Document horarios = null;
		try {
			horarios = Jsoup.connect("https://saew.epn.edu.ec/SAEINF/HorariosMaterias.aspx")
					.cookie("ASP.NET_SessionId", cookie.getStrCookies())
					.get();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error: Obtener materias posibles");
		}

		return horarios;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
