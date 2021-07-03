package com.uniquindio.avalon.logica;

public class Componente {

	private String nombre;
	private String codigoComputador;
	private	int codigo;
	private String descipcion;
	
	public Componente()
	{
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoComputador() {
		return codigoComputador;
	}
	public void setCodigoComputador(String codigoComputador) {
		this.codigoComputador = codigoComputador;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescipcion() {
		return descipcion;
	}
	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}
}
