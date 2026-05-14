package Modelo;

import java.time.LocalDate;

public class Playlist {

	private String idPlaylist;
	private String titulo;
	private LocalDate fechaCreacion;
	private Cliente idCliente;
	
	
	
	public Playlist(String idPlaylist, String titulo, LocalDate fechaCreacion, Cliente idCliente) {
		this.idPlaylist = idPlaylist;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.idCliente = idCliente;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public String getIdPlaylist() {
		return idPlaylist;
	}



	public Cliente getIdCliente() {
		return idCliente;
	}
	
	
	
	
}
