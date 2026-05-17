package Modelo;

public class PlaylistCanciones {

	private Cancion cancion;
	private Musico musico;

	
	
	public PlaylistCanciones(Cancion cancion, Musico musico) {

		this.cancion = cancion;
		this.musico = musico;

		
	}



	public Cancion getCancion() {
		return cancion;
	}



	public Musico getMusico() {
		return musico;
	}


	
	
}
