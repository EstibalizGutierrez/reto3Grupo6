package Modelo;

import java.time.LocalDate;

public class Playlist {

	private int idPlaylist;
	private String titulo;
	private LocalDate fechaCreacion;
	private Cliente idCliente;
	
	
	
	public Playlist(int idPlaylist, String titulo, LocalDate fechaCreacion, Cliente idCliente) {
		this.idPlaylist = idPlaylist;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.idCliente = idCliente;
	}

	
	//Constructor con 3 parametros para la insercion de nuevas playlist
	
	public Playlist(int idPlaylist, String titulo, LocalDate fechaCreacion) {
		
		this.idPlaylist = idPlaylist;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		
	}
	
	//Constructor con un parametro para la eliminacion de playlist
	
	public Playlist (String titulo) {
		
		this.titulo = titulo;
		
	}
	
	
	public Playlist (int idPlaylist) {
		
		this.idPlaylist = idPlaylist;
		
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



	public int getIdPlaylist() {
		return idPlaylist;
	}



	public Cliente getIdCliente() {
		return idCliente;
	}
	
	
	
	
}
