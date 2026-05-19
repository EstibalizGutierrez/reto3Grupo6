package Controlador;

import javax.swing.*;

import Modelo.Artista;
import Modelo.Audio;
import Modelo.Cancion;
import Modelo.InfoCancion;
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
	 * Metodo para obtener info de la cancion para plasmar en la ventana reproductora de canciones
	 * @param c
	 * @param m
	 * @return ArrayList<InfoCancion>
	 */
	public ArrayList<InfoCancion> infoCancion (String nombreC) {
		
		Connection conexion = null;
		ArrayList<InfoCancion> listaCanciones = new ArrayList<>();

		String sql = "SELECT A.Nombre, AR.NombreArtistico, A.Duracion, A.NReproducciones, AL.Titulo "
		           + "FROM Audio A "
		           + "JOIN Cancion C ON A.IdAudio = C.IdCancion "
		           + "JOIN Album AL ON C.IdAlbum = AL.IdAlbum "
		           + "JOIN Artista AR ON AL.IdMusico = AR.IdArtista "
		           + "WHERE A.Nombre = ?";
		
		try {
			
			conexion = conn.getConnection();
			
			if(conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				statement.setString(1, nombreC);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					InfoCancion info = new InfoCancion(
							resultSet.getString("Nombre"),
							resultSet.getString("NombreArtistico"),
							resultSet.getObject("Duracion", LocalTime.class),
							resultSet.getInt("NReproducciones"),
							resultSet.getString("Titulo")
							);
					
					listaCanciones.add(info);
					
				}
			}
			
		} catch(SQLException error) {
			error.printStackTrace();
		}
		return listaCanciones;
	}
	
	
	public String obtenerRutaAudio (String nombreCancion) {
		
		Connection conexion = null;
		String ruta = null;
		
		String sql = "Select Archivo from Audio where Nombre = ?";
		
		try {
			conexion = conn.getConnection();
			
			if (conexion != null) {
				statement = conexion.prepareStatement(sql);
				statement.setString(1, nombreCancion);
				resultSet = statement.executeQuery();	
				
				if (resultSet.next()) {
					
					ruta = resultSet.getString("Archivo");
				}
				
			}
		}catch(SQLException error) {
			error.printStackTrace();
			
		}
		return ruta;
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