package Modelo;

import java.time.LocalDate;

public class VistaRankingAlbumes {
	
	private String titulo;
	private LocalDate lanzamiento;
	private String genero;
	private String nombreMusico;
	private int numReproducciones;
	
	
	public VistaRankingAlbumes(String titulo, LocalDate lanzamiento, String genero, String nombreMusico,
			int numReproducciones) {

		this.titulo = titulo;
		this.lanzamiento = lanzamiento;
		this.genero = genero;
		this.nombreMusico = nombreMusico;
		this.numReproducciones = numReproducciones;
	}


	public String getTitulo() {
		return titulo;
	}


	public LocalDate getLanzamiento() {
		return lanzamiento;
	}


	public String getGenero() {
		return genero;
	}


	public String getNombreMusico() {
		return nombreMusico;
	}


	public int getNumReproducciones() {
		return numReproducciones;
	}
	
	
	

}
