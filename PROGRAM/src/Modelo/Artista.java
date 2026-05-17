package Modelo;

public abstract class  Artista {
	
	private String idCliente;
	private String nombre;
	private String genero;
	private String imagen;
	private String descripcion;
	
	
	
	
	public Artista(String idCliente, String nombre, String genero, String imagen, String descripcion) {

		this.idCliente = idCliente;
		this.nombre = nombre;
		this.genero = genero;
		this.imagen = imagen;
		this.descripcion = descripcion;
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
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
