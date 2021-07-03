package com.uniquindio.avalon.logica;

import java.util.ArrayList;

public class Proveedor {
	
	private String nit;
	private String nombre;
	private ArrayList<Producto> productos;
	
	
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
