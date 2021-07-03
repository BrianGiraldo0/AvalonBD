package com.uniquindio.avalon.logica;

public class Ciudad {
	
	private String nombre;
	private int codigo;
	private int departamentoCodigo;
	
	public Ciudad()
	{
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getDepartamentoCodigo() {
		return departamentoCodigo;
	}
	public void setDepartamentoCodigo(int departamentoCodigo) {
		this.departamentoCodigo = departamentoCodigo;
	}
}
