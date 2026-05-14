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
	
	public boolean playlistInsertar (Cliente cliente, Playlist playlist) {
		
		Connection conexion = null;
		boolean playlistValida = false;
		int idNuevo = generarId();
		
		String sql = "Insert into Playlist (IdList,Titulo, FechaCreacion, IdCliente "
					+ " Values (?,?,?,?)";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion != null) {
				
				statement.setInt(1, idNuevo);
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
	
	public int generarId() {
		
		Connection conexion = null;
		int nuevoId = 0;
		String sql = "Select IdList from Playlist order by IdList desc limit 1";

		try {
			
			if (conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					int ultimoId = resultSet.getInt("IdList");
					
					nuevoId = ultimoId;
				}
				
				
			}
			
		} catch (SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return nuevoId;
		
	}
	
	
}
