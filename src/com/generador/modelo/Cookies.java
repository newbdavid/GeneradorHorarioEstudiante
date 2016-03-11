package com.generador.modelo;

import java.util.Observable;

public class Cookies extends Observable{

	private String strCookies;
	public static Cookies cookie = null;
	
	public static Cookies instancia() {
		
		if (cookie == null) {
			cookie = new Cookies();
		}
		return cookie;
	}
	
	public void setStrCookies(String strCookies) {
		this.strCookies = strCookies;
		setChanged();
		notifyObservers();
	}
	
	public String getStrCookies() {
		return strCookies;
	}
	
}
