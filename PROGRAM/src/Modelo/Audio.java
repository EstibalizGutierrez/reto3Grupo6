package Modelo;

import java.time.LocalTime;

import Modelo.Enums.tipoAudio;;

public abstract class Audio {
	
	private String idAudio;
	private String nombre;
	private LocalTime duracion;
	private String archivo;
	private tipoAudio tipo;
	private int numReproducciones;
	

	public Audio(String idAudio, String nombre, LocalTime duracion, String archivo, tipoAudio tipo, int numReproducciones) {

		this.idAudio = idAudio;
		this.nombre = nombre;
		this.duracion = duracion;
		this.archivo = archivo;
		this.tipo = tipo;
		this.numReproducciones = numReproducciones;
	}
	
	//CONSTRUCTOR PARA LA INFOPLAYLIST
	public Audio (String nombre, LocalTime duracion, int numReproducciones ) {
		
		this.nombre = nombre;
		this.duracion = duracion;
		this.numReproducciones = numReproducciones;
		
	}
	
	
	public String getIdAudio() {
		return idAudio;
	}
	public void setIdAudio(String idAudio) {
		this.idAudio = idAudio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalTime getDuracion() {
		return duracion;
	}
	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public tipoAudio getTipo() {
		return tipo;
	}
	public void setTipo(tipoAudio tipo) {
		this.tipo = tipo;
	}
	public int getNumReproducciones() {
		return numReproducciones;
	}
	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones = numReproducciones;
	}
	
	
	
	
}
