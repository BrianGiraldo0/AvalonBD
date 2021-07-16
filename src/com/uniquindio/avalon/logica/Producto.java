package com.uniquindio.avalon.logica;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Producto {

	
private String codigo;
private String descripcion;
private String nombre;
private int precio;
private Date fechaInicioGarantia;
private Date fechaFinGarantia;
}
