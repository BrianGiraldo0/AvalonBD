package com.uniquindio.avalon.logica;

public class Empleado {

	
	private String cedula;
	private String nombre;
	private String ciudad;
	private String correo;
	private String direccion;
	
	
	
	
	public Empleado(String cedula, String nombre, String correo, String direccion, String ciudad) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.correo = correo;
		this.direccion = direccion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}
