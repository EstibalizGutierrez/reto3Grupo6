package Modelo;

import Modelo.Enums.idIdioma;
import Modelo.Enums.tipoUsuario;
import java.time.LocalDate;

public class Cliente {
	
	private String idCliente;
	private String nombre;
	private String apellido;
	private String usuario;
	private String contrasena;
	private LocalDate fechaNacimiento;
	private LocalDate fechaRegistro;
	private tipoUsuario tipo;
	private Idioma idIdioma;


	
	
	//Constructor para registro

	public Cliente(String idCliente, String nombre, String apellido, String usuario, String contrasena,
			LocalDate fechaNacimiento, LocalDate fechaRegistro, tipoUsuario tipo, Idioma idIdioma) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.tipo = tipo;
		this.idIdioma = idIdioma;
	}
	

	
	//Constructor para login
	
	public Cliente (String usuario, String contrasena) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		
	}
	
	
	//COnstructor para guardar datos editables en el perfil del usuario
	
	public Cliente (String nombre, String apellido, String usuario, String contrasena, LocalDate fechaNacimiento,tipoUsuario tipo, Idioma idIdioma) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = tipo;
		this.idIdioma = idIdioma;
		
	}

	
	//constructor solo para guardar el id
	
	public Cliente (String idCliente) {
		
		this.idCliente = idCliente;
		
	}


	public String getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getContrasena() {
		return contrasena;
	}



	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}



	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}



	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}



	public tipoUsuario getTipo() {
		return tipo;
	}



	public void setTipo(tipoUsuario tipo) {
		this.tipo = tipo;
	}



	public Idioma getIdIdioma() {
		return idIdioma;
	}



	public void setIdIdioma(Idioma idIdioma) {
		this.idIdioma = idIdioma;
	}

}

