package Modelo;
import Modelo.Enums.caracteristicaMusico;
public class VistaRankingArtistas {

	private String nombreArtistico;
	private caracteristicaMusico tipo;
	private int totalReproducciones;
	
	
	public VistaRankingArtistas(String nombreArtistico, caracteristicaMusico tipo, int totalReproducciones) {

		this.nombreArtistico = nombreArtistico;
		this.tipo = tipo;
		this.totalReproducciones = totalReproducciones;
	}


	public String getNombreArtistico() {
		return nombreArtistico;
	}


	public caracteristicaMusico getTipo() {
		return tipo;
	}


	public int getTotalReproducciones() {
		return totalReproducciones;
	}
	
	
	
}
