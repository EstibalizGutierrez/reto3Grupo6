package Modelo;

import java.time.LocalTime;

import Modelo.Enums.tipoAudio;

public class Cancion extends Audio {
	
	private Album idAlbum;
	private String cancion;
	private String artistasInvitados;
	private boolean reproduciendo;
	
	
	public Cancion(String idAudio, String nombre, LocalTime duracion, String archivo, tipoAudio tipo, int numReproducciones, Album idAlbum, String cancion, String artistasInvitados) {
		super(idAudio, nombre, duracion, archivo, tipo, numReproducciones);
		this.idAlbum = idAlbum;
		this.cancion = cancion;
		this.artistasInvitados = artistasInvitados;
		this.reproduciendo = false;
				
	}
	
	//constructor para INFOPLAYLIST
	public Cancion (String nombre, LocalTime duracion, int numReproducciones) {
		
		super(nombre,duracion,numReproducciones);
		
	}

	public boolean sonando() {
		if (!reproduciendo) {
			return true;
		} else {
			return false;
		}
	}

	public String getArtistasInvitados() {
		return artistasInvitados;
	}


	public void setArtistasInvitados(String artistasInvitados) {
		this.artistasInvitados = artistasInvitados;
	}


	public Album getIdAlbum() {
		return idAlbum;
	}
	
	public String getCancion() {
		return cancion;
	}

	public void setCancion(String cancion) {
		this.cancion = cancion;
	}
	
}
