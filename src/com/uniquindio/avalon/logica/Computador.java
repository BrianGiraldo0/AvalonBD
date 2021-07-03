package com.uniquindio.avalon.logica;

import java.util.ArrayList;

public class Computador {

	private String codigo;
	private String categoria;
	private char ocupado;
	private ArrayList<Componente> componentes;
	private ArrayList<ReporteMantenimiento> reportesMantenimiento;
	private ArrayList<Prestamo> prestamos;
	
	public Computador()
	{
		
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
	public char getOcupado() {
		return ocupado;
	}
	public void setOcupado(char ocupado) {
		this.ocupado = ocupado;
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
