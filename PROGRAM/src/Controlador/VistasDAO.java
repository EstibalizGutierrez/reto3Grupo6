package Controlador;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import Modelo.VistaRankingAlbumes;
import Modelo.VistaRankingArtistas;
import Modelo.VistaRankingContenido;
import Modelo.Enums.tipoAudio;
import Modelo.Enums.caracteristicaMusico;

public class VistasDAO {
	
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;

	
	/**
	 * Metodo que se usa para obtener los datos de la vista y guardarlos en un arraylist para luego
	 * pasarlos a la JTable
	 * 
	 * @return ArrayList
	 */
	
	public ArrayList<VistaRankingContenido> tablaRankingContenido () {
		
		Connection conexion = null;
		VistaRankingContenido vista = null; 
		ArrayList<VistaRankingContenido> ranking = new ArrayList<>();
		
		String sql = "SELECT * FROM vista_ranking_contenido ";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					//METEMOS LA INFO de la vista en la clase VistaRankingCAnciones 
					vista = new VistaRankingContenido(
							resultSet.getString("Titulo"),
							tipoAudio.valueOf(resultSet.getString("Tipo")),
							resultSet.getInt("NReproducciones"),
							resultSet.getObject("Duracion", LocalTime.class),
							resultSet.getString("Autor"));		
					
					ranking.add(vista);
					
				}
				
			} 
			
		} catch(SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return ranking;
		
	}
	
	/**
	 * Metodo que se usa para obtener los datos de la vista y guardarlos en un arraylist para luego
	 * pasarlos a la JTable
	 * 
	 * @return ArrayList
	 */
	public ArrayList<VistaRankingAlbumes> tablaRankingAlbum() {
		
		Connection conexion = null;
		VistaRankingAlbumes vista = null; 
		ArrayList<VistaRankingAlbumes> ranking = new ArrayList<>();
		
		String sql = "SELECT * FROM AlbumesMasEscuchados ";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion!=null) {
				statement = conexion.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					
					vista = new VistaRankingAlbumes(
							resultSet.getString("Titulo"),
							resultSet.getObject("Anno", LocalDate.class),
							resultSet.getString("Genero"),
							resultSet.getString("NombreArtistico"),
							resultSet.getInt("TotalReproducciones")
							);
					
					ranking.add(vista);
					
				}
			}
			
		}catch(SQLException error) {
			error.printStackTrace();
		}
		return ranking;
	}
	
	/**
	 * Metodo que se usa para obtener los datos de la vista y guardarlos en un arraylist para luego
	 * pasarlos a la JTable
	 * 
	 * @return ArrayList
	 */
	public ArrayList<VistaRankingArtistas> tablaRankingArtista() {
		
		Connection conexion = null;
		VistaRankingArtistas vista = null; 
		ArrayList<VistaRankingArtistas> ranking = new ArrayList<>();
		
		String sql = "SELECT * FROM ArtistasMasEscuchados ";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					
					vista = new VistaRankingArtistas(
							resultSet.getString("NombreArtistico"),
							caracteristicaMusico.valueOf(resultSet.getString("Caracteristica")),
							resultSet.getInt("TotalReproducciones")
							);
					
					ranking.add(vista);
				}
				
			}
			
		}catch(SQLException error) {
			error.printStackTrace();
		}
		return ranking;
	}
	
}
