package com.uniquindio.avalon.logica;

import java.util.Date;

public class Compra {

	private String codigo;
	private Date fecha;
	private String direccion;
	private String clienteCedula;
	private String telefono;
	private	int ciudadCodigo;
	
	public Compra()
	{
		
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getClienteCedula() {
		return clienteCedula;
	}
	public void setClienteCedula(String clienteCedula) {
		this.clienteCedula = clienteCedula;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getCiudadCodigo() {
		return ciudadCodigo;
	}
	public void setCiudadCodigo(int ciudadCodigo) {
		this.ciudadCodigo = ciudadCodigo;
	}
}
