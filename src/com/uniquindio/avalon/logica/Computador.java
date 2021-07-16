package com.uniquindio.avalon.logica;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
}
