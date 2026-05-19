package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private final String URL = "jdbc:mysql://localhost:33060/reto3spotify";
	private final String USER = "root";					//CAMBIARLO DEPENDIENDO DE LA RUTA , USER Y PASSWD DE TU ORDENADOR
	private final String PASSWORD = "elorrieta";

	private Connection conexion;
	
	public Connection getConnection() {
		
		try {
			
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			
			if (conexion != null) {
				
				System.out.println("Conexión exitosa");
				
			} 
			
		} catch (SQLException error) {
			
			System.out.println("Error de SQL: " + error.getMessage());
			error.printStackTrace();
			
		} catch (Exception error2) {
			
			System.out.println("Error inesperado: " + error2.getMessage());
			error2.printStackTrace();
			
		}
		
		return conexion;
		
	}
	
	public void cerrarConexion() {
		
		try {
			
			conexion.close();
			
		} catch (SQLException error) {
			
			System.out.println("No se ha podido cerrar la conexión");
			error.printStackTrace();
			
		}
		
	}
	
	
}
