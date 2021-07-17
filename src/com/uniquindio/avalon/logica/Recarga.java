package com.uniquindio.avalon.logica;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Recarga {
	
	private String codigo;
	private int total;
	private int valorCargar;
	private Date fecha;
	private String clienteCedula;
	private String empleadoCedula;
	
}
