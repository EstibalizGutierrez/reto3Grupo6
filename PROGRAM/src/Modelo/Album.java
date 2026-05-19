package Modelo;

import java.time.LocalDate;

public class Album {
	
	private String idAlbum;
	private String titulo;
	private LocalDate anno;
	private String genero;
	private String imagen;
	private Musico idMusico;
	
	
	public Album(String idAlbum, String titulo, LocalDate anno, String genero, String imagen, Musico idMusico) {

		this.idAlbum = idAlbum;
		this.titulo = titulo;
		this.anno = anno;
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


	public LocalDate getanno() {
		return anno;
	}


	public void setanno(LocalDate anno) {
		this.anno = anno;
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

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
