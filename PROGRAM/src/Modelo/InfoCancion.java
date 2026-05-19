package Modelo;

import java.time.LocalTime;

public class InfoCancion {
	
	private String nombreCancion;
	private String nombreArtista;
	private LocalTime duracion;
	private int reproducciones;
	private String nombreAlbum;
	
	
	public InfoCancion(String nombreCancion, String nombreArtista, LocalTime duracion, int reproducciones, String nombreAlbum) {

		this.nombreCancion = nombreCancion;
		this.nombreArtista = nombreArtista;
		this.duracion = duracion;
		this.reproducciones = reproducciones;
		this.nombreAlbum = nombreAlbum;
	}


	public String getNombreAlbum() {
		return nombreAlbum;
	}


	public String getNombreCancion() {
		return nombreCancion;
	}


	public String getNombreArtista() {
		return nombreArtista;
	}


	public LocalTime getDuracion() {
		return duracion;
	}


	public int getReproducciones() {
		return reproducciones;
	}
	
	
	

}
