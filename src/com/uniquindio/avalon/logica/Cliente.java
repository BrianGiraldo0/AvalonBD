package com.uniquindio.avalon.logica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Cliente {
	
	private String cedula;
	private String nickname;
	private String clave;
	private String correo;
	private int saldo;
	

	public Cliente (String nickname){
		this.nickname = nickname;
	}
	
}
