package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Cliente;
import Modelo.Usuario;
import Controlador.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelUsuario;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JComboBox comboBoxOpcionUsuario;
	private JButton botonRegistro;
	private Cliente clienteExistente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		setTitle("Login");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblUsuario.setBounds(301, 196, 116, 36);
		contentPane.add(lblUsuario);
		
		lblContrasena = new JLabel("Contrasena:");
		lblContrasena.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblContrasena.setBounds(301, 265, 116, 36);
		contentPane.add(lblContrasena);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsuario.setBounds(417, 198, 143, 32);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JTextField();
		txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(417, 265, 143, 32);
		contentPane.add(txtContrasena);
		
		panelUsuario = new JPanel();
		panelUsuario.setBackground(new Color(192, 192, 192));
		panelUsuario.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 128), new Color(255, 255, 0), new Color(255, 255, 0), new Color(255, 0, 255)));
		panelUsuario.setBounds(187, 75, 524, 364);
		contentPane.add(panelUsuario);
		panelUsuario.setLayout(null);
		
		String [] opciones = {"Cliente" , "Administrador"};
		comboBoxOpcionUsuario = new JComboBox<>(opciones);
		comboBoxOpcionUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxOpcionUsuario.setBounds(248, 250, 124, 20);
		panelUsuario.add(comboBoxOpcionUsuario);
		
		JButton botonLogin = new JButton("LOGIN");
		botonLogin.setFont(new Font("Constantia", Font.BOLD, 15));
		botonLogin.setBounds(121, 298, 118, 31);
		botonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String user = txtUsuario.getText();
				String password = txtContrasena.getText();
				
				clienteExistente = new Cliente(user,password);
				ClienteDAO dao = new ClienteDAO();
				
				Cliente clienteConTodosLosDatos = dao.clienteLogin(clienteExistente);
				
				if (clienteConTodosLosDatos !=  null && comboBoxOpcionUsuario.getSelectedIndex() == 0) {
					
					//Instanciamos la clase static de Usuario para poder guardar el dato del cliente 
					//logueado en todas las clases y asi poder seguir el flujo de la app en base al Usuario
					
					Usuario.setUsuario(clienteConTodosLosDatos);
					
					
					dispose();
					Menu_Cliente panelMenuCliente = new Menu_Cliente();
					panelMenuCliente.setVisible(true);
					
				} else {
					
					JOptionPane.showMessageDialog(botonLogin, "Usuario o contrasena incorrectos");
					
				}
				
				
				
			}
			
		});
		panelUsuario.add(botonLogin);
		
		botonRegistro = new JButton("REGISTRO");
		botonRegistro.setFont(new Font("Constantia", Font.BOLD, 15));
		botonRegistro.setBounds(265, 298, 118, 31);
		botonRegistro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Registro panelRegistro = new Registro();
				dispose();
				panelRegistro.setVisible(true);
				
				
			}			
			
		});
		panelUsuario.add(botonRegistro);

	}
}
