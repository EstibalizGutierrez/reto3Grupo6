package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ClienteDAO;
import Controlador.PremiumDAO;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import Modelo.Cliente;
import Modelo.Idioma;
import Modelo.Premium;
import Modelo.Enums.idIdioma;
import Modelo.Enums.tipoUsuario;

import java.awt.Color;


public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String SQLException = null;
	private JPanel contentPane;
	private JPanel panelPremium;
	private JButton botonAtras;
	private JComboBox comboBoxIdiomas;
	private JTextField nombreTxt;
	private JTextField usuarioTxt;
	private JTextField contrasenaTxt;
	private JTextField confirmarTxt;
	private JTextField fechaRegistroTxt;
	private JTextField fechaPremiumTxt;
	private JTextField apellidoTxt;
	private JButton botonRegistro;
	private JLabel lblFechaNacimiento;
	private JLabel lblAviso;
	private JTextField fechaNacimientoTxt;
	private JPanel panelAviso;
	private JLabel lblPreguntaPremium;
	private JComboBox comboBoxPremiumSiNo;
	
	public Registro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 601);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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

		panelPremium = new JPanel();
		panelPremium.setBackground(new Color(128, 128, 0));
		panelPremium.setBounds(207, 442, 352, 52);
		panelPremium.setVisible(true);
		contentPane.add(panelPremium);
		
		
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
		contentPane.add(lblFechaPremiumLimite);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblIdioma.setBounds(226, 347, 116, 36);
		contentPane.add(lblIdioma);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblApellido.setBounds(571, 88, 116, 36);
		contentPane.add(lblApellido);
		
		nombreTxt = new JTextField();
		nombreTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreTxt.setColumns(10);
		nombreTxt.setBounds(396, 88, 143, 32);
		contentPane.add(nombreTxt);
		
		usuarioTxt = new JTextField();
		usuarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuarioTxt.setColumns(10);
		usuarioTxt.setBounds(396, 125, 143, 32);
		contentPane.add(usuarioTxt);
		
		contrasenaTxt = new JTextField();
		contrasenaTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contrasenaTxt.setColumns(10);
		contrasenaTxt.setBounds(396, 167, 143, 32);
		contentPane.add(contrasenaTxt);
		
		confirmarTxt = new JTextField();
		confirmarTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmarTxt.setColumns(10);
		confirmarTxt.setBounds(396, 210, 143, 32);
		contentPane.add(confirmarTxt);
		
		fechaRegistroTxt = new JTextField();
		fechaRegistroTxt.setEditable(false);
		fechaRegistroTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fechaRegistroTxt.setColumns(10);
		fechaRegistroTxt.setBounds(396, 301, 143, 32);
		//Formateamos la fechaRegistro y la rellenamos automaticamente y no editable con la fecha actual
		fechaRegistroTxt.setText(LocalDate.now().toString()); 
		contentPane.add(fechaRegistroTxt);		
		
		
		fechaPremiumTxt = new JTextField();
		fechaPremiumTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fechaPremiumTxt.setColumns(10);
		fechaPremiumTxt.setBounds(396, 450, 143, 32);
		contentPane.add(fechaPremiumTxt);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		apellidoTxt.setColumns(10);
		apellidoTxt.setBounds(697, 88, 143, 32);
		contentPane.add(apellidoTxt);
		
		lblFechaNacimiento = new JLabel("Fecha Nac:");
		lblFechaNacimiento.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblFechaNacimiento.setBounds(226, 254, 116, 36);
		contentPane.add(lblFechaNacimiento);
		
		fechaNacimientoTxt = new JTextField();
		fechaNacimientoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fechaNacimientoTxt.setColumns(10);
		fechaNacimientoTxt.setBounds(396, 253, 143, 32);
		contentPane.add(fechaNacimientoTxt);
		
		

		lblAviso = new JLabel("      ** ej:  2025-12-31 **");
		lblAviso.setForeground(new Color(255, 0, 0));
		lblAviso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAviso.setBackground(new Color(255, 0, 0));
		lblAviso.setBounds(601, 315, 213, 48);
		contentPane.add(lblAviso);
		
		panelAviso = new JPanel();
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
		comboBoxPremiumSiNo.setBounds(396, 400, 95, 29);
		comboBoxPremiumSiNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxPremiumSiNo.getSelectedIndex() == 1) {
					
					panelPremium.setVisible(false);
					
				} else {
					
					panelPremium.setVisible(true);
					
				}
				
			}	
			
		});
		contentPane.add(comboBoxPremiumSiNo);
		
		
		
		String[] idiomas = {"ES","EU", "EN", "FR","DE","CA","AR"};
		comboBoxIdiomas = new JComboBox<>(idiomas);
		comboBoxIdiomas.setBounds(396, 349, 68, 30);
		contentPane.add(comboBoxIdiomas);
		
		botonRegistro = new JButton("REGISTRARSE");
		botonRegistro.setFont(new Font("Constantia", Font.BOLD, 15));
		botonRegistro.setBounds(396, 504, 156, 43);
		botonRegistro.addActionListener(new ActionListener() {

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
					//formateo de fechaNacimiento
					String fecNac = fechaNacimientoTxt.getText(); 
					LocalDate fechaNacimiento = LocalDate.parse(fecNac);
					
					LocalDate fechaRegistro = LocalDate.now();
					
					//Creamos el tipo de usuario y lo cambiamos dependiendo de si es premium o no
					
					tipoUsuario tipo;
					
					if (comboBoxPremiumSiNo.getSelectedIndex() == 1) {
						
						tipo = tipoUsuario.Premium;
						
					} else {
						
						tipo = tipoUsuario.Free;
						
					}
					
					String idiomaSeleccionado = comboBoxIdiomas.getSelectedItem().toString();
					Idioma idioma = new Idioma(idIdioma.valueOf(idiomaSeleccionado));

					//instanciamos dao
					ClienteDAO daoC = new ClienteDAO();
					String idCliente = daoC.generarId(); //generamos el ID
					
					//insertamos cliente mediante metodo
					Cliente clienteInsertado = new Cliente(idCliente, nombre, apellido, usuario, contrasena, fechaNacimiento,fechaRegistro,tipo,idioma);
				
					if (contrasena.equals(confirmar)) {
						
						if(daoC.clienteInsertar(clienteInsertado)) {
			                
			                
			                if (tipo == tipoUsuario.Premium) {

			                    String fecLimit = fechaPremiumTxt.getText();
			                    LocalDate fechaCaducidadPremium = LocalDate.parse(fecLimit);
			                    
			                    clienteInsertado = new Cliente(idCliente);
			                    Premium premium = new Premium(clienteInsertado, fechaCaducidadPremium);    
			                    PremiumDAO pdao = new PremiumDAO();
			                    
			                   
			                    if (pdao.insertarPremium(premium)) {
			                   
			                    	JOptionPane.showMessageDialog(botonRegistro, "Cliente Premium registrado correctamente");
			                        Login login = new Login();
				                    dispose();
				                    login.setVisible(true);
			                  
			                    } else {
			                    	
			                        JOptionPane.showMessageDialog(botonRegistro, "Error al insertar en tabla Premium");
			                    }
			              
			                } else {

			                    JOptionPane.showMessageDialog(botonRegistro, "Cliente registrado correctamente");
			                    Login login = new Login();
			                    dispose();
			                    login.setVisible(true);
			                }
			                
			            } else {
			           
			            	JOptionPane.showMessageDialog(botonRegistro, "Error al insertar el cliente");
			          
			            }
			            
			        } else {
			       
			        	JOptionPane.showMessageDialog(botonRegistro, "Las contraseñas no coinciden");
			       
			        }
					
				} catch (Exception error) {
					
					JOptionPane.showMessageDialog(botonRegistro, "Error con los datos del cliente");
					
				}
				
		    }
		});
		
		contentPane.add(botonRegistro);
		

		
	}
	
	/**
	 * Metodo para formatear fecha y pasar de localdate a string
	 * 
	 * @return String
	 */
	
	public String formatearFecha() {
		
		LocalDate fecha = LocalDate.now();
		DateTimeFormatter formateo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fechaFormateada = fecha.format(formateo);
		
		return fechaFormateada;
		
	}
}
