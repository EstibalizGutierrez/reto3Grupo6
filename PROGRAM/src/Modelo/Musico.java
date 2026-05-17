package Modelo;

import Modelo.Enums.caracteristicaMusico;;

public class Musico extends Artista{
	
	private caracteristicaMusico caracteristica;

	
	public Musico(String idCliente, String nombre, String genero, String imagen, String descripcion, caracteristicaMusico caracteristica) {
		
		super(idCliente, nombre, genero, imagen, descripcion);
		this.caracteristica = caracteristica;
		
	}

	
	public Musico (String nombre) {
		
		super(nombre);
		
	}

	public caracteristicaMusico getCaracteristica() {
		return caracteristica;
	}

	
	
	
}
