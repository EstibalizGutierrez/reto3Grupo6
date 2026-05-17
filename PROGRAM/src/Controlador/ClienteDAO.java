package Controlador;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import Modelo.Cliente;
import Modelo.Idioma;
import Modelo.Enums.idIdioma;
import Modelo.Enums.tipoUsuario;

public class ClienteDAO {
		
	private Conexion conn = new Conexion();
	private PreparedStatement statement;
	private ResultSet resultSet;

	
	

	/*
	 * Método para validar el login y obtener los datos del cliente
	 * @param Cliente datos
	 * @return Cliente
	 */
	
	public Cliente clienteLogin (Cliente datos) {
			
	Connection conexion = null;
	Cliente clienteLogueado = null; 

	String sql = "Select * from Cliente where Usuario = ? and Contrasena = ?";
			
		try {
				
			conexion = conn.getConnection();
				
			if (conexion != null) {
					
				statement = conexion.prepareStatement(sql);
				statement.setString(1, datos.getUsuario());
				statement.setString(2, datos.getContrasena());
				resultSet = statement.executeQuery();
					
				if (resultSet.next()) {
					
					//DEbido a que idIdioma es un atributo derivado de clase Idioma, la instanciamos en un objeto
					String idIdio = resultSet.getString("IdIdioma");
					Idioma idioma = new Idioma(idIdioma.valueOf(idIdio));
						
					// Si existe el cliente en el login lo rellenamos con sus datos de la BBDD
					
						clienteLogueado = new Cliente(
						resultSet.getString("IdCliente"),
						resultSet.getString("Nombre"),
						resultSet.getString("Apellido"),
						resultSet.getString("Usuario"),
						resultSet.getString("Contrasena"),
						resultSet.getDate("FechaNacimiento").toLocalDate(),
						resultSet.getDate("FechaRegistro").toLocalDate(),
						
						tipoUsuario.valueOf(resultSet.getString("Tipo")),
						idioma
						
							);
												
						}
					
				}
				
			} catch (SQLException error) {
				
				error.printStackTrace();
				
			} 
			return clienteLogueado;
			
		}
	


		/**
		 * Método para registrar un nuevo cliente a la BBDD
		 * @param Cliente nuevo
		 * @return boolean
		 */
		 
		public boolean clienteInsertar (Cliente nuevo) {
			
			Connection conexion = null;
			boolean clienteValidoInsertado = false;
			String idNuevo = generarId();

			//CONSULTA SQL
			String sql = "Insert into Cliente (IdCliente, Nombre, Apellido, Usuario, Contrasena, FechaNacimiento, FechaRegistro, Tipo, IdIdioma) "
					+ 	"values (?, ?, ?, ?, ?, ?, ?, ? , ?)";
			
			try {
				
				conexion = conn.getConnection();
				
				if (conexion != null) {
					
					statement = conexion.prepareStatement(sql);
					
					statement.setString(1, idNuevo); 
		            statement.setString(2, nuevo.getNombre());
		            statement.setString(3, nuevo.getApellido());
		            statement.setString(4, nuevo.getUsuario());
		            statement.setString(5, nuevo.getContrasena());
		            statement.setDate(6, Date.valueOf(nuevo.getFechaNacimiento()));
		            statement.setDate(7, Date.valueOf(nuevo.getFechaRegistro()));
		            statement.setString(8, nuevo.getTipo().name());  
		            statement.setString(9, nuevo.getIdIdioma().getIdIdioma().name());
					
					int filas = statement.executeUpdate();//Lo hacemos para ver si se han incluido las filas en la BBDD
					
					if (filas > 0) { //con que se haya insertado al menos una fila ya sabremos que se han insertado las otras
						
						clienteValidoInsertado = true;
												
					}
					
				}
				
			} catch (SQLException error) {
				
				error.printStackTrace();
			} 
			
			return clienteValidoInsertado;
			
		}
		
		/**
		 * metodo para generar el id del cliente de manera automatica
		 * 
		 * @return String
		 */
		 
		public String generarId() {
			Connection conexion = null;
			String nuevoId = "";
			String sql = "Select idCliente from Cliente order by IdCliente desc limit 1"; //sentencIa para guardar el id del ultimo cliente en un String
			
			try { 
				conexion = conn.getConnection();
				
				if (conexion != null) {
					
					statement = conexion.prepareStatement(sql);
					resultSet = statement.executeQuery();
					
					if (resultSet.next()) {
						String ultimoId = resultSet.getString("IdCliente"); //guardamos el ultimo id en un string
						
						int num = Integer.parseInt(ultimoId.substring(2)); //Haacemos substring para OBTENER los ultimos 3 digitos y quitarnos la parte "CL" del id "CL004" (EJEMPLO)
						
						nuevoId = "CL" + String.format("%03d", num + 1); //sSumamos y lo formateamos con %03d para que solo acepte 3 numeros y que empiece por la izquierda con 0 asi como maximo solo podria existir CL999
					}
				}
			} catch (SQLException error) {

					error.printStackTrace();
			}
			
			return nuevoId;
		}
		
		
		/**
		 *Metodo que edita los datos del usuario desde el perfil del usuario
		 *
		 *@param Cliente
		 *@return boolean 
		 * 
		 */
		
		public boolean editarCliente (Cliente editado) {
			
			Connection conexion = null;
			boolean clienteEditadoValido = false;
			String sql = "UPDATE Cliente "
						+ " SET Nombre = ?, Apellido = ?,Usuario = ?, Contrasena = ?,FechaNacimiento = ?, Tipo = ?, IdIdioma = ? " 
						+ " where IdCliente = ?";
			
			
			try {
				
				conexion = conn.getConnection();
				
				if (conexion != null) {
					
					statement = conexion.prepareStatement(sql);

					statement.setString(1, editado.getNombre());
					statement.setString(2, editado.getApellido());
					statement.setString(3, editado.getUsuario());
					statement.setString(4, editado.getContrasena());
			        statement.setDate(5, Date.valueOf(editado.getFechaNacimiento()));
			        statement.setString(6, editado.getTipo().name());
			        statement.setString(7, editado.getIdIdioma().getIdIdioma().name());
			        
			        //condicion where id = "x"
			        statement.setString(8, editado.getIdCliente());
			        
			        int filas = statement.executeUpdate();
			        
			        //si se han modificado mas de 0 filas 
			        
			        if (filas > 0) {
			        	
			        	clienteEditadoValido = true;
			        	
			        }
					
				}
				
			} catch (SQLException error) {
				
				JOptionPane.showMessageDialog(null, "No se ha podido editar el usuario");
				
			}
			
			return clienteEditadoValido;
		}

	}
	

