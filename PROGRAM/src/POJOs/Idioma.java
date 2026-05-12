package POJOs;

import POJOs.Enums.idIdioma;;

public class Idioma {
	
	private idIdioma idIdioma;
	private String descripcion;
	
	
	public Idioma(idIdioma idIdioma, String descripcion) {

		this.idIdioma = idIdioma;
		this.descripcion = descripcion;
	}
	
	//constructor para solo recibir el id
	
	public Idioma (idIdioma idIdioma) {
		
		this.idIdioma = idIdioma;
		
	}


	public idIdioma getIdIdioma() {
		return idIdioma;
	}


	public void setIdIdioma(idIdioma idIdioma) {
		this.idIdioma = idIdioma;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}

