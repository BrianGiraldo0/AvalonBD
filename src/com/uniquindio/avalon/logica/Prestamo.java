package com.uniquindio.avalon.logica;

import java.util.Date;

public class Prestamo {

	private String codigo;
	private Date fecha;
	private int duracion;
	private String clienteCedula;
	private String computadorCodigo;
	
	public Prestamo()
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
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getClienteCedula() {
		return clienteCedula;
	}
	public void setClienteCedula(String clienteCedula) {
		this.clienteCedula = clienteCedula;
	}
	public String getComputadorCodigo() {
		return computadorCodigo;
	}
	public void setComputadorCodigo(String computadorCodigo) {
		this.computadorCodigo = computadorCodigo;
	}
}
