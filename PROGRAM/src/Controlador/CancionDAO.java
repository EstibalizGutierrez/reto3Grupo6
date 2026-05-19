package Controlador;

import javax.swing.*;

import Modelo.Artista;
import Modelo.Audio;
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
	
	public boolean insertarCancion(Cancion nuevaCancion) {
	    Connection conexion = null;
	    boolean insertado = false;
	    
	    String sqlAudio = "INSERT INTO Audio (IdAudio, Nombre, Duracion, Archivo, Tipo, NReproducciones) "
	                    + "VALUES (?, ?, '03:30', 'archivo.mp3', 'Cancion', 0)";
	    
	    String sqlCancion = "INSERT INTO Cancion (IdCancion, IdAlbum, ArtistasInvitados) "
	                      + "VALUES (?, ?, ?)";
	    
	    try {
	        conexion = conn.getConnection();
	        
	        if (conexion != null) {
	            
	            statement = conexion.prepareStatement(sqlAudio);
	            statement.setString(1, nuevaCancion.getIdAudio()); 
	            statement.setString(2, nuevaCancion.getNombre());  
	            int filasAudio = statement.executeUpdate();
	            
	            if (filasAudio > 0) {
	                statement = conexion.prepareStatement(sqlCancion);
	                
	                statement.setString(1, nuevaCancion.getIdAudio()); 
	                statement.setString(2, nuevaCancion.getIdAlbum().getIdAlbum()); 
	                statement.setString(3, nuevaCancion.getArtistasInvitados());
	                
	                int filasCancion = statement.executeUpdate();
	                
	                if (filasCancion > 0) {
	                    insertado = true;
	                }
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return insertado;
	}
	
	public boolean modificarCancion(Cancion cancionEditada) {
	    Connection conexion = null;
	    boolean modificado = false;
	    
	    String sqlAudio = "UPDATE Audio SET Nombre = ? WHERE IdAudio = ?";
	    
	    String sqlCancion = "UPDATE Cancion SET IdAlbum = ?, ArtistasInvitados = ? WHERE IdCancion = ?";
	    
	    try {
	        conexion = conn.getConnection();
	        
	        if (conexion != null) {
	            statement = conexion.prepareStatement(sqlAudio);
	            statement.setString(1, cancionEditada.getNombre());
	            statement.setString(2, cancionEditada.getIdAudio());
	            int filasAudio = statement.executeUpdate();
	            
	            if (filasAudio > 0) {
	                statement = conexion.prepareStatement(sqlCancion);
	                statement.setString(1, cancionEditada.getIdAlbum().getIdAlbum());
	                statement.setString(2, cancionEditada.getArtistasInvitados());
	                statement.setString(3, cancionEditada.getIdAudio());
	                
	                int filasCancion = statement.executeUpdate();
	                if (filasCancion > 0) {
	                    modificado = true;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return modificado;
	}
	
	public boolean eliminarCancion(Audio idAudio) {
	    Connection conexion = null;
	    boolean eliminado = false;

	    String sqlCancion = "DELETE FROM Cancion WHERE IdCancion = ?";
	    
	    String sqlAudio = "DELETE FROM Audio WHERE IdAudio = ?";
	    
	    try {
	        conexion = conn.getConnection();
	        
	        if (conexion != null) {
	            statement = conexion.prepareStatement(sqlCancion);
	            statement.setString(1, idAudio.getIdAudio());
	            int filasCancion = statement.executeUpdate();
	            
	            // PASO B: Si se eliminó de Cancion, procedemos a borrar el Audio global
	            if (filasCancion > 0) {
	                statement = conexion.prepareStatement(sqlAudio);
	                statement.setString(1, idAudio.getIdAudio());
	                int filasAudio = statement.executeUpdate();
	                
	                if (filasAudio > 0) {
	                    eliminado = true;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return eliminado;
	}
	
}