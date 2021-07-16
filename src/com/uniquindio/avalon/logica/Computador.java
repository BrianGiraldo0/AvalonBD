package com.uniquindio.avalon.logica;

import java.util.ArrayList;

public class Computador {

	private String codigo;
	private String categoria;
	private boolean ocupado;
	private ArrayList<Componente> componentes;
	private ArrayList<ReporteMantenimiento> reportesMantenimiento;
	private ArrayList<Prestamo> prestamos;
	
	
	

	public Computador(String codigo, String categoria, boolean ocupado) {
		super();
		this.codigo = codigo;
		this.categoria = categoria;
		this.ocupado = ocupado;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}

	public ArrayList<ReporteMantenimiento> getReportesMantenimiento() {
		return reportesMantenimiento;
	}

	public void setReportesMantenimiento(ArrayList<ReporteMantenimiento> reportesMantenimiento) {
		this.reportesMantenimiento = reportesMantenimiento;
	}

	public ArrayList<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(ArrayList<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	
}
