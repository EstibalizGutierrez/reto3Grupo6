package Modelo;

import java.time.LocalDate;

public class Album {
	
	private String idAlbum;
	private String titulo;
	private LocalDate año;
	private String genero;
	private String imagen;
	private Musico idMusico;
	
	
	public Album(String idAlbum, String titulo, LocalDate año, String genero, String imagen, Musico idMusico) {

		this.idAlbum = idAlbum;
		this.titulo = titulo;
		this.año = año;
		this.genero = genero;
		this.imagen = imagen;
		this.idMusico = idMusico;
		
	}

	public Album(String idAlbum) {
	    this.idAlbum = idAlbum;
	}
	
	public String getIdAlbum() {
		return idAlbum;
	}


	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public LocalDate getAño() {
		return año;
	}


	public void setAño(LocalDate año) {
		this.año = año;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public Musico getIdMusico() {
		return idMusico;
	}


	public void setIdMusico(Musico idMusico) {
		this.idMusico = idMusico;
	}
	
	
	
	
}
