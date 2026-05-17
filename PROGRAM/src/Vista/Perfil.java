package Vista;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.time.LocalDate;
import Modelo.Cliente;
import Modelo.Enums.idIdioma;
import Modelo.Enums.tipoUsuario;
import Modelo.Premium;
import Modelo.Usuario;
import Modelo.Idioma;
import Controlador.ClienteDAO;
import Controlador.PremiumDAO;
import java.awt.Color;
import javax.swing.border.EtchedBorder;


public class Perfil extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String SQLException = null;
	private JPanel contentPane;
	private JButton botonAtras;
	private JComboBox comboBoxIdiomas;
	private JTextField nombreTxt;
	private JTextField usuarioTxt;
	private JTextField contrasenaTxt;
	private JTextField confirmarTxt;
	private JTextField fechaRegistroTxt;
	private JTextField fechaPremiumTxt;
	private JTextField apellidoTxt;
	private JButton botonGuardarCambios;
	private JLabel lblFechaNacimiento;
	private JLabel lblAviso;
	private JTextField fechaNacimientoTxt;
	private JPanel panelAviso;
	private JLabel lblPreguntaPremium;
	private JComboBox comboBoxPremiumSiNo;
	private JButton botonEditar;
	
	private Cliente clientePerfil = Usuario.getCliente();
	
	//declaro aqui el dao fechaPremium solamente para obtener la fecha del cliente logueado
	private PremiumDAO daoFecha = new PremiumDAO();
	private LocalDate fechaPremium = daoFecha.obtenerFechaLimitePremium(clientePerfil);
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Perfil() {
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(9, 13, 17, 9));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPremium = new JPanel();
		panelPremium.setBackground(new Color(128, 128, 0));
		panelPremium.setBounds(194, 443, 376, 53);
		contentPane.add(panelPremium);
		
		
		
		botonAtras = new JButton("ATRAS");
		botonAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		botonAtras.setBounds(30, 28, 118, 31);
		botonAtras.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				
				Login ventanaLogin = new Login();
				ventanaLogin.setVisible(true);
				dispose();
				
			}
			
		}); 
		contentPane.add(botonAtras);

		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblNombre.setBounds(226, 88, 116, 36);
		contentPane.add(lblNombre);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblUsuario.setBounds(226, 125, 116, 36);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrasena:");
		lblContrasena.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblContrasena.setBounds(226, 167, 116, 36);
		contentPane.add(lblContrasena);
		
		JLabel lblConfirmar = new JLabel("Confirmar:");
		lblConfirmar.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblConfirmar.setBounds(226, 214, 116, 36);
		contentPane.add(lblConfirmar);
		
		JLabel lblFechaRegistro = new JLabel("Fecha Registro:");
		lblFechaRegistro.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblFechaRegistro.setBounds(226, 301, 146, 36);
		contentPane.add(lblFechaRegistro);
		
		
		JLabel lblFechaPremiumLimite = new JLabel("Limite Premium:");
		lblFechaPremiumLimite.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblFechaPremiumLimite.setBounds(226, 450, 156, 36);
		panelPremium.add(lblFechaPremiumLimite);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblIdioma.setBounds(226, 347, 116, 36);
		contentPane.add(lblIdioma);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblApellido.setBounds(571, 88, 116, 36);
		contentPane.add(lblApellido);
		
		nombreTxt = new JTextField();
		nombreTxt.setEditable(false);
		nombreTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreTxt.setColumns(10);
		nombreTxt.setBounds(396, 88, 143, 32);
		nombreTxt.setText(clientePerfil.getNombre());
		contentPane.add(nombreTxt);
		
		usuarioTxt = new JTextField();
		usuarioTxt.setEditable(false);
		usuarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuarioTxt.setColumns(10);
		usuarioTxt.setBounds(396, 125, 143, 32);
		usuarioTxt.setText(clientePerfil.getUsuario());
		contentPane.add(usuarioTxt);
		
		contrasenaTxt = new JTextField();
		contrasenaTxt.setEditable(false);
		contrasenaTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contrasenaTxt.setColumns(10);
		contrasenaTxt.setBounds(396, 167, 143, 32);
		contrasenaTxt.setText(clientePerfil.getContrasena());
		contentPane.add(contrasenaTxt);
		
		confirmarTxt = new JTextField();
		confirmarTxt.setEditable(false);
		confirmarTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmarTxt.setColumns(10);
		confirmarTxt.setBounds(396, 210, 143, 32);
		confirmarTxt.setText(contrasenaTxt.getText());
		contentPane.add(confirmarTxt);
		
		fechaRegistroTxt = new JTextField();
		fechaRegistroTxt.setEditable(false);
		fechaRegistroTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fechaRegistroTxt.setColumns(10);
		fechaRegistroTxt.setBounds(396, 301, 143, 32);
		//Formateamos la fechaRegistro y la rellenamos automaticamente y no editable con la fecha actual
		fechaRegistroTxt.setText(clientePerfil.getFechaRegistro().toString());
		contentPane.add(fechaRegistroTxt);		
		
		
		fechaPremiumTxt = new JTextField("");
		fechaPremiumTxt.setEditable(false);
		fechaPremiumTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fechaPremiumTxt.setColumns(10);
		fechaPremiumTxt.setBounds(396, 450, 143, 32);
		
		//validamos para que en caso de que el cliente no sea Premium no ponga nada en el txt
		if (fechaPremium != null) {
			
			fechaPremiumTxt.setText(fechaPremium.toString());
			
		} 
		panelPremium.add(fechaPremiumTxt);

	
		
		apellidoTxt = new JTextField();
		apellidoTxt.setEditable(false);
		apellidoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		apellidoTxt.setColumns(10);
		apellidoTxt.setBounds(697, 88, 143, 32);
		apellidoTxt.setText(clientePerfil.getApellido());
		contentPane.add(apellidoTxt);
		
		lblFechaNacimiento = new JLabel("Fecha Nac:");
		lblFechaNacimiento.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblFechaNacimiento.setBounds(226, 254, 116, 36);
		contentPane.add(lblFechaNacimiento);
		
		fechaNacimientoTxt = new JTextField();
		fechaNacimientoTxt.setEditable(false);
		fechaNacimientoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fechaNacimientoTxt.setColumns(10);
		fechaNacimientoTxt.setBounds(396, 253, 143, 32);
		fechaNacimientoTxt.setText(clientePerfil.getFechaNacimiento().toString());
		contentPane.add(fechaNacimientoTxt);
		
		

		lblAviso = new JLabel("      ** ej:  2025-12-31 **");
		lblAviso.setForeground(new Color(255, 0, 0));
		lblAviso.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblAviso.setBackground(new Color(255, 0, 0));
		lblAviso.setBounds(571, 301, 261, 78);
		contentPane.add(lblAviso);
		
		panelAviso = new JPanel();
		panelAviso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 0, 128), null));
		panelAviso.setBackground(Color.PINK);
		panelAviso.setBounds(581, 315, 234, 43);
		contentPane.add(panelAviso);
		panelAviso.setLayout(null);
		
		lblPreguntaPremium = new JLabel("Servicio Premium");
		lblPreguntaPremium.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblPreguntaPremium.setBounds(226, 397, 160, 36);
		contentPane.add(lblPreguntaPremium);
		
		String [] opciones = {"Selecciona","SI","NO"};
		comboBoxPremiumSiNo = new JComboBox<>(opciones);
		comboBoxPremiumSiNo.setEnabled(false);
		comboBoxPremiumSiNo.setBounds(396, 400, 95, 29);
		comboBoxPremiumSiNo.setEditable(true);
		
		// condicion para saber si el usuario es premium o free debido a que el programa entiende si
		//es premium o free si la opcion comboBox es si (premium) o no (free)
		
		if (clientePerfil.getTipo() == tipoUsuario.Premium) {
			
			comboBoxPremiumSiNo.setSelectedItem("SI");
			
		} else if (clientePerfil.getTipo() == tipoUsuario.Free){
			
			comboBoxPremiumSiNo.setSelectedItem("NO");
			
		}
		
		comboBoxPremiumSiNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (comboBoxPremiumSiNo.getSelectedIndex() == 1) {
					
					panelPremium.setVisible(true);
					
				} else {
					
					panelPremium.setVisible(false);
					
				}
				
			}	
			
		});
		contentPane.add(comboBoxPremiumSiNo);
		
		
		
		String[] idiomas = {"ES","EU", "EN", "FR","DE","CA","AR"};
		comboBoxIdiomas = new JComboBox<>(idiomas);
		comboBoxIdiomas.setEnabled(false);
		comboBoxIdiomas.setEditable(true);
		comboBoxIdiomas.setBounds(396, 349, 68, 30);
		comboBoxIdiomas.setSelectedItem(clientePerfil.getIdIdioma().getIdIdioma().name());
		contentPane.add(comboBoxIdiomas);
		
		botonEditar = new JButton("EDITAR");
		botonEditar.setFont(new Font("Constantia", Font.BOLD, 15));
		botonEditar.setBounds(194, 507, 156, 43);
		botonEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				editable();
				
			}

		});
		contentPane.add(botonEditar);
		
		botonGuardarCambios = new JButton("Guardar cambios");
		botonGuardarCambios.setFont(new Font("Constantia", Font.BOLD, 15));
		botonGuardarCambios.setBounds(550, 507, 156, 43);
		botonGuardarCambios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//INSERT TABLA CLIENTE
				//asignamos valores del registro a variables
				
				try {
			
					String nombre = nombreTxt.getText();
					String apellido = apellidoTxt.getText();
					String usuario = usuarioTxt.getText();
					String contrasena = contrasenaTxt.getText();
					String confirmar = confirmarTxt.getText();
					
					//formateo de fechaNacimiento a txt
					String fecNac = fechaNacimientoTxt.getText(); 
					LocalDate fechaNacimiento = LocalDate.parse(fecNac);
					
					tipoUsuario tipo;
					
					if (comboBoxPremiumSiNo.getSelectedIndex() == 1) {
						
						tipo = tipoUsuario.Premium;
						
					} else {
						
						tipo = tipoUsuario.Free;
						
					}
					
					// Validación de fecha si decide ser Premium
					if (tipo == tipoUsuario.Premium) {
						
						String fechaLimit = fechaPremiumTxt.getText();
						
						if (fechaLimit.trim().isEmpty()) {
							
					        JOptionPane.showMessageDialog(botonGuardarCambios, "Si quieres ser Premium, debes introducir una fecha de caducidad.");
					        return;
							
						}
						
					}
					
					String idiomaSeleccionado = comboBoxIdiomas.getSelectedItem().toString();
					Idioma idioma = new Idioma(idIdioma.valueOf(idiomaSeleccionado));
					
					// Se añade el ID de clientePerfil para que el UPDATE sepa qué registro tocar
					Cliente clienteEditado = new Cliente(clientePerfil.getIdCliente(), nombre, apellido, usuario, contrasena, fechaNacimiento, clientePerfil.getFechaRegistro(), tipo, idioma);
					
					ClienteDAO daoC = new ClienteDAO();
					
					if (contrasena.equals(confirmar)) {
						
						// Se envía clienteEditado (datos nuevos) en lugar de clientePerfil (datos viejos)
						if (daoC.editarCliente(clienteEditado)) {
							
							if (tipo == tipoUsuario.Premium) {
								
								String fecLimit = fechaPremiumTxt.getText();
								LocalDate fechaCaducidadPremium = LocalDate.parse(fecLimit);
								
								String idCliente = clientePerfil.getIdCliente();
								Cliente clienteId = new Cliente(idCliente);
								Premium premium = new Premium(clienteId, fechaCaducidadPremium);
								PremiumDAO pdao = new PremiumDAO();
								
								if (pdao.insertarPremium(premium) ) {
									
									JOptionPane.showMessageDialog(botonGuardarCambios, "Premium contratado");
								
								} else {
									
									JOptionPane.showMessageDialog(botonGuardarCambios, "Error al contratar premium");
									
								}
								
							} 
							
							JOptionPane.showMessageDialog(botonGuardarCambios, "Dato(s) modificados correctamente");
							
							// Actualizamos la sesión con los nuevos datos
							Usuario.setUsuario(clienteEditado);
							
							dispose();
							Menu_Cliente menuCliente = new Menu_Cliente();
							menuCliente.setVisible(true);
							
						} else {
							
							JOptionPane.showMessageDialog(botonGuardarCambios, "Error al editar usuario");
							
						}
						
					} else {
						
						JOptionPane.showMessageDialog(botonGuardarCambios,  "Las contrasenas no coinciden");
						
					}
					
					
				} catch (Exception error) {
					
					JOptionPane.showMessageDialog(botonGuardarCambios, "Error con los datos del cliente");
					
				}
				
		    }
		});
		
		contentPane.add(botonGuardarCambios);
		
	}		
		

	
	/**
	 * 
	 * Metodo para editar datos del cliente validando si ya es premium o no
	 * 
	 */
	
	public void editable () {
		
		nombreTxt.setEditable(true);
		apellidoTxt.setEditable(true);
		usuarioTxt.setEditable(true);
		contrasenaTxt.setEditable(true);
		confirmarTxt.setEditable(true);
		fechaNacimientoTxt.setEditable(true);
		comboBoxIdiomas.setEnabled(true);
		comboBoxPremiumSiNo.setEnabled(true);
		fechaPremiumTxt.setEditable(true);
		
	}
}