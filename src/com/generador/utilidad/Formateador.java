package com.generador.utilidad;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Formateador {

	/**
	 * Esta es la función que valida que entren solo números en el campo de la cédula y su longitud sea 10
	 * @return null
	 */
	public static MaskFormatter formatCedula(){
		try {
			return new MaskFormatter("##########");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Esta función hace que se reemplacen los caracteres ingresados por puntos
	 * @return mask for the password
	 */
	public static MaskFormatter formatPass(){
		try {
			int nulo = 0;
			MaskFormatter mask = new MaskFormatter("************");
			mask.setPlaceholderCharacter((char)nulo);//aqui se hace se que se cambie el caracter por un punto
			return mask;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Esto formatea el campo de las cookies.
	 * @return null
	 */
	public static MaskFormatter formatCookies(){
		try {
			return new MaskFormatter("************************");//Esto formatea el numero de caracteres que se pueden ingresar
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
