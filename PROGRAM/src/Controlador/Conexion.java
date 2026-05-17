package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

<<<<<<< HEAD
	private final String URL = "jdbc:mysql://localhost:3308/reto3spotify";
	private final String USER = "root";					//CAMBIARLO DEPENDIENDO DE LA RUTA , USER Y PASSWD DE TU ORDENADOR
	private final String PASSWORD = "";
=======
	private final String URL = "jdbc:mysql://localhost:33060/reto3spotify";
	private final String USER = "root";					//CAMBIARLO DEPENDIENDO DE LA RUTA , USER Y PASSWD DE TU ORDENADOR
	private final String PASSWORD = "Eljohannbachx25";
>>>>>>> 24b7704096dff658f10316fb110be76e6c4dc272
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
