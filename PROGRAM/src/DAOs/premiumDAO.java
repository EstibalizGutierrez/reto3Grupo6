package DAOs;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import POJOs.Cliente;
import POJOs.Premium;
import POJOs.Enums.idIdioma;
import POJOs.Enums.tipoUsuario;
import UTILS.Conexion;

public class premiumDAO {
	
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	/**
	 * 	Metodo para insertar en tabla premium los clientes premium y su fecha de caducidad del contrato
	 * 
	 * @param Premium cliente
	 * @return boolean
	 * 
	 */

	public boolean insertarPremium (Premium cliente) {
		
		Connection conexion = null;
		boolean premiumValido = false;
		
		//Consulta sql
		
		String sql = "Insert into Premium (IdCliente, FechaCaducidad)"
					+ "VALUES (?,?)";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				
				statement.setString(1, cliente.getIdCliente().getIdCliente()); 
				statement.setDate(2, Date.valueOf(cliente.getFechaCaducidad()));

				int filas = statement.executeUpdate();
				
				if (filas > 0) {
					
					premiumValido = true;
					
				}
				
			}
			
		} catch (SQLException error) {
			error.printStackTrace();
			
		}
		
		return premiumValido;
		
	}
}


