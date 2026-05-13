package Modelo;
import java.time.LocalDate;
public class Premium {
	
	private Cliente idCliente;
	private LocalDate fechaCaducidad;
	
	

	public Premium(Cliente idCliente, LocalDate fechaCaducidad) {

		this.idCliente = idCliente;
		this.fechaCaducidad = fechaCaducidad;
	}
	
	
	public Cliente getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	
	

}
