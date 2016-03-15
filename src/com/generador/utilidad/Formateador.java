package com.generador.utilidad;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Formateador {

	public static MaskFormatter formatCedula(){
		try {
			return new MaskFormatter("##########");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static MaskFormatter formatPass(){
		try {
			return new MaskFormatter("AAAAAAAAAAAA");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static MaskFormatter formatCookies(){
		try {
			return new MaskFormatter("************************");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
