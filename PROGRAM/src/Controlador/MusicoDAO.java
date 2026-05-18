package Controlador;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MusicoDAO {
	
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public ArrayList<String> listaMusicos () {
		
		Connection conexion = null;
		ArrayList<String>listaArtistas = new ArrayList(); //return
		
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

}
