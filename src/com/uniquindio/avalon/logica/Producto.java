package com.uniquindio.avalon.logica;

import java.util.Date;

public class Producto {

	
private String codigo;
private String descripcion;
private String nombre;
private int precio;
private Date fechaInicioGarantia;
private Date fechaFinGarantia;



public Producto() {
	super();
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public int getPrecio() {
	return precio;
}
public void setPrecio(int precio) {
	this.precio = precio;
}
public Date getFechaInicioGarantia() {
	return fechaInicioGarantia;
}
public void setFechaInicioGarantia(Date fechaInicioGarantia) {
	this.fechaInicioGarantia = fechaInicioGarantia;
}
public Date getFechaFinGarantia() {
	return fechaFinGarantia;
}
public void setFechaFinGarantia(Date fechaFinGarantia) {
	this.fechaFinGarantia = fechaFinGarantia;
}

}
