package Controlador;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class AlbumDAO {
	
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;

	
	/**
	 * mETODO PARA OBTENER la info del album mediante el titulo y guardarlo en otro ArrayList<>()
	 * 
	 * @param titulo
	 * @return
	 */
	
	public ArrayList<String> obtenerInfoAlbum(String tituloAlbum) {
	    Connection conexion = null;
	    ArrayList<String> info = new ArrayList<>();
	    
	    String sql = "SELECT a.IdAlbum, a.Anno, a.Imagen, " +
	                 "(SELECT COUNT(*) FROM Cancion WHERE IdAlbum = a.IdAlbum) as Total " +
	                 "FROM Album a WHERE a.Titulo = ?";
	    try {
	        conexion = conn.getConnection();
	        
	        if (conexion != null) {
	        	
	            statement = conexion.prepareStatement(sql);
	            statement.setString(1, tituloAlbum);
	            resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	            	
	                info.add(resultSet.getString("IdAlbum")); 
	                info.add(resultSet.getString("Anno"));    
	                info.add(resultSet.getString("Imagen"));  
	                info.add(resultSet.getString("Total"));   
	            }
	        }
	    } catch (SQLException error) {
	    	
	    	
	        error.printStackTrace();
	    }
	    return info;
	}
	

	
	public ArrayList<String> listaCancionesAlbum(String idAlbum) {
	    Connection conexion = null;
	    ArrayList<String> canciones = new ArrayList<>();
	    
	    String sql = "SELECT au.Nombre, au.Duracion " +
	                 "FROM Audio au JOIN Cancion c ON au.IdAudio = c.IdCancion " +
	                 "WHERE c.IdAlbum = ?";
	    try {
	        conexion = conn.getConnection();
	        
	        if (conexion != null) {
	        	
	            statement = conexion.prepareStatement(sql);
	            statement.setString(1, idAlbum);
	            resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {
	                // Sacamos el objeto LocalTime
	                LocalTime duracion = resultSet.getObject("Duracion", LocalTime.class);
	                
	                
	                canciones.add(resultSet.getString("Nombre") + " - " + duracion);
	            }
	        }
	    } catch (SQLException error) {
	       
	    	
	    	error.printStackTrace();
	    }
	    return canciones;
	}

	public String rutaPortadaAlbum(String nombreAlbum) {
		
		Connection conexion = null;
		String ruta = null;
		String sql = "SELECT Imagen from Album where Titulo = ?";
		
		try {
			
			conexion = conn.getConnection();
			if(conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				statement.setString(1, nombreAlbum);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					ruta = resultSet.getString("Imagen");
					
				}
				
			}
		}catch (SQLException error) {
			error.printStackTrace();
		}
		return ruta;
	}
	
	/**
	 * METODO  para obtener el titulo del album mediante nombre de una cancion
	 * @param cancion
	 * @return
	 */
	public String obtenerTituloAlbum(String cancion) {
		
		Connection conexion = null;
		String titulo = null;
		String sql = "SELECT A.Titulo "
				+ "FROM Album A "
				+ "JOIN Cancion C ON A.IdAlbum = C.IdAlbum "
				+ "JOIN Audio AU ON C.IdCancion = AU.IdAudio "
				+ "WHERE AU.Nombre = ?";	
		
		try {
			conexion = conn.getConnection();
			if(conexion!=null) {
				
				statement = conexion.prepareStatement(sql);
				statement.setString(1, cancion);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					titulo = resultSet.getString("Titulo");
					
				}
				
			}
		}catch(SQLException error) {
			error.printStackTrace();
		}
		return titulo;
	}
	
}
