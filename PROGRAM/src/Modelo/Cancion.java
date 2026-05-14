package Modelo;

import Modelo.Enums.tipoAudio;

public class Cancion extends Audio {
	
	private Album idAlbum;
	private String artistasInvitados;
	
	
	public Cancion(String idAudio, String nombre, int duracion, String archivo, tipoAudio tipo, int numReproducciones, Album idAlbum, String artistasInvitados) {
		super(idAudio, nombre, duracion, archivo, tipo, numReproducciones);
		this.idAlbum = idAlbum;
		this.artistasInvitados = artistasInvitados;
				
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
