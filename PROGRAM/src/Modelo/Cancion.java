package Modelo;

import java.time.LocalTime;

import Modelo.Enums.tipoAudio;

public class Cancion extends Audio {
	
	private Album idAlbum;
	private String artistasInvitados;
	
	
	public Cancion(String idAudio, String nombre, LocalTime duracion, String archivo, tipoAudio tipo, int numReproducciones, Album idAlbum, String artistasInvitados) {
		super(idAudio, nombre, duracion, archivo, tipo, numReproducciones);
		this.idAlbum = idAlbum;
		this.artistasInvitados = artistasInvitados;
				
	}
	
	//constructor para INFOPLAYLIST
	public Cancion (String nombre, LocalTime duracion, int numReproducciones) {
		
		super(nombre,duracion,numReproducciones);
		
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
	
	
	

}
