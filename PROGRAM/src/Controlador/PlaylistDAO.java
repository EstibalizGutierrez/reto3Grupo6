package Controlador;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Cliente;
import Modelo.Playlist;

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
	
}
