package com.uniquindio.avalon.logica;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompraProducto {

	private int cantidad;
	private String productoCodigo;
	private String compraCodigo;
	public CompraProducto(int cantidad, String productoCodigo) {
		super();
		this.cantidad = cantidad;
		this.productoCodigo = productoCodigo;
	}
	
	
	
	
}
