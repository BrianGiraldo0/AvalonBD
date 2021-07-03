package com.uniquindio.avalon.logica;

import java.util.Date;

public class Recarga {
	
	private String codigo;
	private int total;
	private int valorCargar;
	private Date fecha;
	private String clienteCedula;
	private String empleadoCedula;
	
	public Recarga()
	{
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getValorCargar() {
		return valorCargar;
	}

	public void setValorCargar(int valorCargar) {
		this.valorCargar = valorCargar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getClienteCedula() {
		return clienteCedula;
	}

	public void setClienteCedula(String clienteCedula) {
		this.clienteCedula = clienteCedula;
	}

	public String getEmpleadoCedula() {
		return empleadoCedula;
	}

	public void setEmpleadoCedula(String empleadoCedula) {
		this.empleadoCedula = empleadoCedula;
	}
}
