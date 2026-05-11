package DAOs;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import UTILS.Conexion;

public class audioDAO {
	
	private Conexion c = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;


	public String audioInsertar (String idAudio) {
		
		Connection conexion = null;
		String ruta = null; //será el return

		String sql = "Select Archivo from Audio where IdAudio = ?";
		
		try {
			
			conexion = c.getConnection();
			
			if (conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				statement.setString(1, idAudio);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					 ruta = resultSet.getString("Archivo");
					
					System.out.println("Ruta del archivo: " + ruta);
					
				}
				
			}
			
		} catch (SQLException error) {
			
			System.out.println("Error con la busqueda de datos: " + error.getMessage());
			
		}
		
		return ruta;
		
	}

}
