package POJOs;

import POJOs.Enums.idIdioma;
import POJOs.Enums.tipoUsuario;
import java.time.LocalDate;

public class Cliente {
	
	private String idCliente;
	private String usuario;
	private String contrasena;
	private String nombre;
	private String apellido;
	private idIdioma idioma;
	private LocalDate fechaNacimiento;
	private LocalDate fechaRegistro;
	private tipoUsuario tipo;
	
	
	//Constructor para registro
	
	public Cliente(String usuario, String contrasena, String nombre, String apellido, idIdioma idioma,
			LocalDate fechaNacimiento, LocalDate fechaRegistro, tipoUsuario tipo) {

		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.idioma = idioma;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.tipo = tipo;
	}
	
	
	//Constructor para login
	
	public Cliente (String usuario, String contrasena) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		
	}


	public String getUsuario() {
		return usuario;
	}


	public String getContrasena() {
		return contrasena;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public idIdioma getIdioma() {
		return idioma;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}


	public tipoUsuario getTipo() {
		return tipo;
	}

}
