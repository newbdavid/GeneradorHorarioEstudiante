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
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
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
import com.generador.utilidad.JTextFieldLimit;

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
	private JTextField txtCedula, txtPass;
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
				
				if (login()) {
					JOptionPane.showMessageDialog(null, "Test");
					setDocHorarioMaterias(getDocHorarioMaterias());
					setDocMateriasPosibles(getDocMateriasPosibles());
				} else {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		
	}
	
	public boolean login() {
		
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
			webClient.waitForBackgroundJavaScript(3000);

			HtmlPasswordInput inputPassword = form.getInputByName("ctl00$ContentPlaceHolder1$Password");
			
			//Ingresar Password
			inputPassword.setText(txtPass.getText());
			webClient.waitForBackgroundJavaScript(100);

			HtmlSubmitInput btnIngresar = form.getInputByName("ctl00$ContentPlaceHolder1$LoginButton");
			page = btnIngresar.click();
			webClient.waitForBackgroundJavaScript(100);

			form = page.getFormByName("aspnetForm");
			selectUser = form.getSelectByName("ctl00$ContentPlaceHolder1$cmbperiodo");
			
			//Seleccionar periodo
			selectUser.setSelectedIndex(cboPeriodo.getSelectedIndex());
			btnIngresar = form.getInputByName("ctl00$ContentPlaceHolder1$LoginButton");
			
			btnIngresar.click();
			webClient.waitForBackgroundJavaScript(100);

			page = btnIngresar.click();
			cookie.setStrCookies(webClient.getCookieManager().getCookie("ASP.NET_SessionId").getValue());
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: Conexión no establecida");
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
		txtCedula.setDocument(new JTextFieldLimit(10));
		txtCedula.setHorizontalAlignment(JLabel.CENTER);
		
		lblPass = new JLabel("Clave: ");
		lblPass.setHorizontalTextPosition(JLabel.CENTER);
		lblPass.setHorizontalAlignment(JLabel.CENTER);

		txtPass = new JTextField(25);
		txtPass.setDocument(new JTextFieldLimit(12));
		txtPass.setHorizontalAlignment(JLabel.CENTER);
		
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
		panelWeb.add(txtPass, BorderLayout.CENTER);
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
