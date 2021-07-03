package com.uniquindio.avalon.logica;

import java.util.Date;

public class ReporteMantenimiento {

	private String codigo;
	private Date fecha;
	private String cedulaEmpleado;
	private String codigoComputador;
	private String observacion;
	
	
	public ReporteMantenimiento() {
		super();
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
	public String getCedulaEmpleado() {
		return cedulaEmpleado;
	}
	public void setCedulaEmpleado(String cedulaEmpleado) {
		this.cedulaEmpleado = cedulaEmpleado;
	}
	public String getCodigoComputador() {
		return codigoComputador;
	}
	public void setCodigoComputador(String codigoComputador) {
		this.codigoComputador = codigoComputador;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	
	
	
}
