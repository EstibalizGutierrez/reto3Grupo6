package Controlador;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import Modelo.Cliente;
import Modelo.Premium;
import Modelo.Enums.idIdioma;
import Modelo.Enums.tipoUsuario;
import Controlador.Conexion;

public class PremiumDAO {
	
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
	
	
	/**
	 * Metodo que sirve para obtener la fechaLimite del premium usando como parametro
	 *  el constructor Cliente que solo tiene el id como paraametro
	 * 
	 * 
	 * @param cliente
	 * @return LocalDate
	 */
	
	public LocalDate obtenerFechaLimitePremium (Cliente cliente) {
		
		Connection conexion = null;
		
		//variable para el return donde guardaremos la fecha limite del premium
		LocalDate fechaLimitePremium = null;
		
		String sql = "Select FechaCaducidad "
					+ "FROM Premium "
					+ "WHERE IdCliente = ?";
		
		try {
			
			conexion = conn.getConnection();
			
			if (conexion!= null) {
				
				statement = conexion.prepareStatement(sql);
				//Asignamos valor
				statement.setString(1, cliente.getIdCliente());
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					
					fechaLimitePremium = resultSet.getDate("FechaCaducidad").toLocalDate();
					
				}
											
			}
			
		} catch (SQLException error) {
			
			error.printStackTrace();
			
		}
		
		return fechaLimitePremium;
		
	}
	
}


