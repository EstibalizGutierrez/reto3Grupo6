package Controlador;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MusicoDAO {
	
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public ArrayList<String> listaMusicos () {
		
		Connection conexion = null;
		ArrayList<String>listaArtistas = new ArrayList(); //será el return
		
		String sql ="SELECT a.NombreArtistico "
					+ "FROM Artista a "
					+ "JOIN Musico m ON a.IdArtista = m.IdMusico ";
		
		try {
			
			conexion = conn.getConnection();
			
			if(conexion!= null) {
				statement = conexion.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					listaArtistas.add(resultSet.getString("NombreArtistico"));
				}
			}		
		}catch (SQLException error) {
			error.printStackTrace();
		}
			return listaArtistas;
	}

	
	

		public ArrayList<String> obtenerInfoArtista(String nombre) {
			
			Connection conexion = null;
			ArrayList<String> info = new ArrayList<>(); 
			
			String sql = "SELECT Genero, Descripcion, Imagen, IdArtista, " +
	                     "(SELECT YEAR(MIN(Anno)) FROM Album WHERE IdMusico = Artista.IdArtista) as AnnoInicio " +
	                     "FROM Artista WHERE NombreArtistico = ?";
			
			try {
				conexion = conn.getConnection();
				
				if(conexion != null) {
					statement = conexion.prepareStatement(sql);
					statement.setString(1, nombre);
					resultSet = statement.executeQuery();
					
					if(resultSet.next()) {
						info.add(resultSet.getString("Genero"));      
						info.add(resultSet.getString("Descripcion")); 
						info.add(resultSet.getString("Imagen"));      
						info.add(resultSet.getString("IdArtista"));   
						info.add(resultSet.getString("AnnoInicio")); 
					}
				}		
			} catch (SQLException error) {
				error.printStackTrace();
			}
			
			return info;
		}

		public ArrayList<String> listaAlbumesArtista(String idArtista) {
			
			Connection conexion = null;
			ArrayList<String> listaAlb = new ArrayList<>();
			
			String sql = "SELECT a.Titulo, YEAR(a.Anno) as Anno, " +
	                     "(SELECT COUNT(*) FROM Cancion c WHERE c.IdAlbum = a.IdAlbum) as NumCanciones " +
	                     "FROM Album a WHERE a.IdMusico = ?";
			
			try {
				conexion = conn.getConnection();
				
				if(conexion != null) {
					statement = conexion.prepareStatement(sql);
					statement.setString(1, idArtista);
					resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						String item = resultSet.getString("Titulo") + " - " + 
	                                 resultSet.getString("Anno") + " - " + 
	                                 resultSet.getInt("NumCanciones") + " kanta";
						listaAlb.add(item);
					}
				}		
			} catch (SQLException error) {
				error.printStackTrace();
			}
			
			return listaAlb;
		}
		
		
		
		/**
		 *metodo que nos sirve para guardar la ruta de la imagen y poder ilustrarla
		 *
		 *@param String idArtista
		 *@return String
		 *
		 */
		public String rutaImagenArtista (String idArtista) {
			
			Connection conexion = null;
			String ruta = null ;//return
					
					String sql = "Select A.Imagen from Artista A join Musico M"
							+ "	 ON A.IdArtista = M.IdMusico"
							+ "		where A.IdArtista = ?";
			
					try {
						
						conexion = conn.getConnection();
						
						if (conexion != null) {
							statement = conexion.prepareStatement(sql);
							statement.setString(1, idArtista);
							resultSet = statement.executeQuery();
							
							if (resultSet.next()) {
								ruta = resultSet.getString("Imagen");
							}
						}
					} catch(SQLException error) {
						
						error.printStackTrace();
					}
					return ruta;
		}
	}
