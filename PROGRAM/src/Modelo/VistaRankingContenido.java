package Modelo;

import java.time.LocalTime;

import Modelo.Enums.tipoAudio;;

public class VistaRankingContenido {
	
	private String nombreCancion;
	private tipoAudio tipo;
	private int numReproducciones;
	private LocalTime duracion;
	private String nombreMusico;
	
	
	public VistaRankingContenido(String nombreCancion, tipoAudio tipo, int numReproducciones, LocalTime duracion,
			String nombreMusico) {

		this.nombreCancion = nombreCancion;
		this.tipo = tipo;
		this.numReproducciones = numReproducciones;
		this.duracion = duracion;
		this.nombreMusico = nombreMusico;
	}


	public String getNombreCancion() {
		return nombreCancion;
	}


	public tipoAudio getTipo() {
		return tipo;
	}


	public int getNumReproducciones() {
		return numReproducciones;
	}


	public LocalTime getDuracion() {
		return duracion;
	}


	public String getNombreMusico() {
		return nombreMusico;
	}


	
	
	

}
