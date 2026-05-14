package Modelo;

import java.time.LocalDate;

public class PlaylistCanciones {
	
	private Playlist idPlaylist;
	private Cancion idCancion;
	private LocalDate fechaCreacion;
		
	
	public PlaylistCanciones(Playlist idPlaylist, Cancion idCancion, LocalDate fechaCreacion) {
		this.idPlaylist = idPlaylist;
		this.idCancion = idCancion;
		this.fechaCreacion = fechaCreacion;
	}
	
	
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Playlist getIdPlaylist() {
		return idPlaylist;
	}
	public Cancion getIdCancion() {
		return idCancion;
	}

	
	
	
}
