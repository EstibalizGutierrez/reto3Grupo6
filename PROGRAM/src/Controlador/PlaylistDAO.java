package Controlador;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import Modelo.Cancion;
import Modelo.Cliente;
import Modelo.Musico;
import Modelo.Playlist;
import Modelo.PlaylistCanciones;

import java.util.ArrayList;

public class PlaylistDAO {

	
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public ArrayList<String> listaPlaylists (Cliente cliente) {
		
		Connection conexion = null;
		ArrayList<String> listaPlaylist = new ArrayList<>();
		
		String sql = "Select Titulo "
					+ "FrOM Playlist "
					+ "where IdCliente = ?";
		
		try {
			
			conexion = conn.getConnection();
		 	
			statement = conexion.prepareStatement(sql);
			statement.setString(1, cliente.getIdCliente());
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				listaPlaylist.add(resultSet.getString("Titulo"));
				
			}
			
		} catch (SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return listaPlaylist;
		
	}
	
	
	
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
	 * mETODO para insertar playlist desde la ventana gestion de playlist
	 * 
	 * @param Cliente cliente, Playlist playlist
	 * @return boolean
	 * 
	 */
	
	public boolean playlistInsertar (Cliente cliente, Playlist playlist) {
		
		Connection conexion = null;
		boolean playlistValida = false;
		
		String sql = "Insert into Playlist (IdList,Titulo, FechaCreacion, IdCliente) "
					+ " Values (?,?,?,?)";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion != null) {
				
				statement = conexion.prepareStatement(sql);
				
				//pasamos null porque es autoincrement int
				statement.setObject(1, null);
				statement.setString(2, playlist.getTitulo());
				statement.setDate(3, Date.valueOf(playlist.getFechaCreacion()));
				statement.setString(4, cliente.getIdCliente());
				
				int filas = statement.executeUpdate();
				 
				if (filas > 0) {
					
					playlistValida = true;
					
				}
				
			}
			
		} catch (SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return playlistValida;
		
	}
	
	
	/**
	 * Metodo para eliminar playlist usando el Titulo como parametro
	 * 
	 * @param playlist
	 * @return
	 */
	
	public boolean eliminarPlaylist (Playlist playlist) {
		
		Connection conexion = null;
		boolean borrado = false;
		
		String sql = "DELETE FROM Playlist where Titulo = ?";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion != null) {
				
				
				statement = conexion.prepareStatement(sql);
				
				statement.setString(1, playlist.getTitulo());
				
				int filasAfectadas = statement.executeUpdate();
				
				if (filasAfectadas > 0) {
					
					borrado = true;
					
				}
			}
		} catch (SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return borrado;
		
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
	
	
	
	/**
	 * Metodo para contar el numero de playlists que tiene cada cliente (free) para restringirle a max 3
	 * 
	 * @param id
	 * @return int num
	 */
	
	public int numeroPlaylists(String id) {
		
		Connection conexion = null;
		int numPL = 0;
		
		String sql = "SELECT COUNT(*) as Num FROM Playlist WHERE IdCliente = ?";	
		
		try {
			conexion = conn.getConnection();
			
			if (conexion!= null) {
				statement =conexion.prepareStatement(sql);
				statement.setString(1, id);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					numPL = resultSet.getInt("Num");
				}
				
			} 
		} catch(SQLException error) {
			error.printStackTrace();
		}
		return numPL;
	}
	
	
	
}
