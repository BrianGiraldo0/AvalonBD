package com.uniquindio.avalon.logica;

public class CompraProducto {

	private int cantidad;
	private String productoCodigo;
	private String compraCodigo;
	
	public CompraProducto()
	{
		
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getProductoCodigo() {
		return productoCodigo;
	}
	public void setProductoCodigo(String productoCodigo) {
		this.productoCodigo = productoCodigo;
	}
	public String getCompraCodigo() {
		return compraCodigo;
	}
	public void setCompraCodigo(String compraCodigo) {
		this.compraCodigo = compraCodigo;
	}
}
