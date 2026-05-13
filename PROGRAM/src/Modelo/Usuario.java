package Modelo;

import Modelo.Cliente;

public class Usuario {
	
	private static Cliente usuario; 
	

	public static void setUsuario(Cliente cliente) {
		
		usuario = cliente;
		
	}
	
	public static Cliente getCliente() {
		
		return usuario;
		
	}

}
