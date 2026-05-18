package Controlador;

import javax.swing.*;

import Modelo.Cancion;
import Modelo.PlaylistCanciones;
import Modelo.Musico;
import Modelo.Playlist;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class CancionDAO {
	
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	
	/**
	 * 
	 * Metodo que nos sirve para generar una lista de informacion 
	 * sobre cada cancion de cada playlist
	 * 
	 * @param p
	 * @return ArrayList<InfoPlaylist>
	 */
	
	public ArrayList<PlaylistCanciones> mostrarListaPlaylist (Playlist p) {
		
		Connection conexion = null;
		ArrayList<PlaylistCanciones> listaCanciones = new ArrayList<>();
		PlaylistCanciones info = null;
		
		String sql = "SELECT "
	            + "A.Nombre, "
	            + "AR.NombreArtistico, "
	            + "A.NReproducciones, "
	            + "A.Duracion "
	            + "FROM Playlist P   "
	            + "JOIN Playlist_Canciones PC ON P.IdList = PC.IdList "
	            + "JoIN Audio A ON PC.IdCancion = A.IdAudio "
	            + "JoIN Cancion C ON A.IdAudio = C.IdCancion "
	            + "JOIN Album AL ON C.IdAlbum = AL.IdAlbum "
	            + "JOIN Musico M ON AL.IdMusico = M.IdMusico   "
	            + "join Artista AR ON M.IdMusico = AR.IdArtista  "
	            + "WHERE P.IdList = ? ";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion != null) {
				
				statement = conexion.prepareStatement(sql);
				statement.setInt(1, p.getIdPlaylist());
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					
					//rellenamos los dos parametros de infoplaylist con la informacion
							Cancion cancion= new Cancion(
							resultSet.getString("Nombre"),
							resultSet.getObject("Duracion", LocalTime.class),
							resultSet.getInt("NReproducciones"));

							Musico musico = new Musico(resultSet.getString("NombreArtistico"));
							
							info = new PlaylistCanciones(cancion,musico);
							
							listaCanciones.add(info);		
				}
				
			}
			
		} catch (SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return listaCanciones;
		
	}


	/**
	 * Metodo que se basa en obtener el id de la cancion cuyo nombre sea X (parametro)
	 * 
	 * @param p
	 * @return int
	 */
	
	public int obtenerIdPlaylist(Playlist p) {
		
		Connection conexion = null;
		int id = 0;
		
		String sql = "Select IdList from Playlist where Titulo = ?";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion != null) {
				
				statement = conexion.prepareStatement(sql);
				statement.setString(1, p.getTitulo());
				resultSet = statement.executeQuery();
				
				
				if (resultSet.next()) {
					
					id = resultSet.getInt("IdList");
					
				}
				
			}
			
		} catch (SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return id;
		
	}
	
	
	
}