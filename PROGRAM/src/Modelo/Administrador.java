package Modelo;

public class Administrador {

	private String usuario;
	private String contrasena;
	
	
	public Administrador(String usuario, String contrasena) {
		
		this.usuario = usuario;
		this.contrasena= contrasena;
		
	}
	
	
	//creamos el metodo aqui ya que administrador no existe en la base de datos
	
	/**
	 * Metodo que usamos para validar el usuario administrador
	 *
	 * @param admin
	 * @return boolean
	 */
	
	public boolean login(Administrador admin) {
		
		boolean valido = false;
		
		if (admin.usuario.equals("adminPro") && admin.contrasena.equals("tangamandapio67")) {
			
			valido = true;
			
		}
		
		return valido; /*Como no necesitamos guardar nada del administrador puesto que lo unico relevante suyo
						es su usuario y contraseña, no guardamos nada y simplemente comprobemos que su usuario
						y password esten correctos. En este caso no guardamos ni informacion ni perfil ni nada ya que 
						solo el puede entrar*/
						
		
	}
	
}
