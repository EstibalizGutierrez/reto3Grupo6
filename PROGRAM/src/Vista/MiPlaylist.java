package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controlador.PlaylistDAO;
import Modelo.Cliente;
import Modelo.Playlist;
import Modelo.Usuario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class MiPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPlaylists;
	JButton botonMostrarPlaylist;
	private Cliente clientePerfil = Usuario.getCliente();
	private JLabel lblNoPlaylist;
	private JButton botonCrear;
	private JButton botonBorrar;
	private JButton botonImportar;
	private JButton botonExportar;
	private JPanel panelOpciones;
	private JLabel lblNombrePlaylist;
	private JTextField nombrePlaylistTxt;
	private JButton botonGuardar;
	private JButton botonActualizar;
	private static final int NUM_MAX_PLAYLIST_FREE = 3;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiPlaylist frame = new MiPlaylist();
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
	public MiPlaylist() {
		setTitle("Gestion Playlist");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonAtras = new JButton("ATRAS");
		botonAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		botonAtras.setBounds(27, 11, 118, 31);
		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu_Cliente menu = new Menu_Cliente();
				dispose();
				menu.setVisible(true);
				
			}	
			
		});
		contentPane.add(botonAtras);
		
		JButton botonPerfil = new JButton(clientePerfil.getUsuario());
		botonPerfil.setFont(new Font("Constantia", Font.BOLD, 15));
		botonPerfil.setBounds(806, 11, 118, 31);
		botonPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Perfil perfil = new Perfil();
				dispose();
				perfil.setVisible(true);
				
			}
		});
		contentPane.add(botonPerfil);
		
		JLabel lblMisPlaylists = new JLabel("MIS PLAYLISTS:");
		lblMisPlaylists.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblMisPlaylists.setBounds(100, 67, 170, 38);
		contentPane.add(lblMisPlaylists);
		
		panelPlaylists = new JPanel();
		panelPlaylists.setBackground(new Color(128, 128, 255));
		panelPlaylists.setBounds(37, 100, 314, 438);
		panelPlaylists.setVisible(false);
		contentPane.add(panelPlaylists);
		panelPlaylists.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		
		botonMostrarPlaylist = new JButton("MOSTRAR PLAYLIST");
		botonMostrarPlaylist.setFont(new Font("Constantia", Font.BOLD, 15));
		botonMostrarPlaylist.setBounds(454, 63, 191, 47);
		botonMostrarPlaylist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPlaylist();			
			}
		
		});
		contentPane.add(botonMostrarPlaylist);
		
		botonCrear = new JButton("CREAR PLAYLIST");
		botonCrear.setForeground(new Color(255, 0, 128));
		botonCrear.setFont(new Font("Constantia", Font.BOLD, 15));
		botonCrear.setBounds(454, 126, 191, 31);
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eventoCrear();
				
			}
		});
		contentPane.add(botonCrear);
		
		botonBorrar = new JButton("BORRAR PLAYLIST");
		botonBorrar.setForeground(new Color(255, 0, 128));
		botonBorrar.setFont(new Font("Constantia", Font.BOLD, 15));
		botonBorrar.setBounds(454, 168, 191, 31);
		botonBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				eventoEliminar();
				
			}
		
		});
		contentPane.add(botonBorrar);
		
		botonImportar = new JButton("IMPORTAR");
		botonImportar.setForeground(new Color(255, 0, 128));
		botonImportar.setFont(new Font("Constantia", Font.BOLD, 15));
		botonImportar.setBounds(474, 210, 147, 31);
		contentPane.add(botonImportar);
		
		botonExportar = new JButton("EXPORTAR");
		botonExportar.setForeground(new Color(255, 0, 128));
		botonExportar.setFont(new Font("Constantia", Font.BOLD, 15));
		botonExportar.setBounds(474, 249, 147, 31);
		contentPane.add(botonExportar);
		
		panelOpciones = new JPanel();
		panelOpciones.setBackground(new Color(128, 128, 0));
		panelOpciones.setBounds(361, 300, 563, 250);
		contentPane.add(panelOpciones);
		panelOpciones.setLayout(null);
		
		
		

	}
	
	
	public void mostrarPlaylist () {
		
		try {
		
			PlaylistDAO dao = new PlaylistDAO();
			ArrayList <String> listaPlaylist = dao.listaPlaylists(clientePerfil);
			
			
			panelPlaylists.removeAll();
			panelPlaylists.setVisible(true);
				
			//ahora añadimos nuestras playlist mediante botones
				
				if (listaPlaylist.isEmpty()) {
					
					lblNoPlaylist = new JLabel("NO HAY  NINGUNA PLAYLIST CREADA");
					lblNoPlaylist.setFont(new Font("Constantia", Font.BOLD, 15));
					panelPlaylists.add(lblNoPlaylist);
					
				} else {
					
					for (int i = 0; i < listaPlaylist.size(); i++) {

					String nombrePL = listaPlaylist.get(i);
					JButton boton = new JButton(nombrePL);
					
					//Le damos un evento al boton de playlist
					boton.addActionListener(new ActionListener() {
						
						public void actionPerformed (ActionEvent e) {
						
						InfoPlaylist pagina = new InfoPlaylist(nombrePL);	
						dispose();
						pagina.setVisible(true);
						
						}
						
					});
					panelPlaylists.add(boton);			
					
				}
	
			}
			
		    panelPlaylists.revalidate();
	        panelPlaylists.repaint();
		
		} catch (Exception error) {
			
			JOptionPane.showConfirmDialog(null, "Error con la(s) playlist(s)");
			
		}
		
	}
	
	
	public void eventoCrear () {
		
		panelOpciones.removeAll();
		panelOpciones.repaint();
		
		lblNombrePlaylist = new JLabel("Nombre Playlist: ");
		lblNombrePlaylist.setFont(new Font("Constantia", Font.BOLD, 15));
		lblNombrePlaylist.setBounds(60, 28, 143, 29);
		panelOpciones.add(lblNombrePlaylist);
		
		nombrePlaylistTxt = new JTextField();
		nombrePlaylistTxt.setBounds(196, 28, 152, 26);
		panelOpciones.add(nombrePlaylistTxt);
		nombrePlaylistTxt.setColumns(10);
		
		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setFont(new Font("Constantia", Font.BOLD, 15));
		botonGuardar.setBounds(60, 84, 118, 31);
		botonGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PlaylistDAO dao = new PlaylistDAO();
				//Damos valor a las variables
				String nombre = nombrePlaylistTxt.getText();
				//lo ponemos 0 porque al ser autoincrement en mysql, el 0 lo acepta como null, no ponemos null porque los int no deja ponerlo
				int id = 0;
				String idCliente = clientePerfil.getIdCliente();
				LocalDate fechaCreacion = LocalDate.now();
				//Pasamos el idCliente a un constructor de cliente debido a que es el parametro que necesitamos, no un string de cliente
				Cliente cliente = new Cliente(idCliente);
				Playlist playL = new Playlist(id,nombre,fechaCreacion);
				
				//Validamos si es free o premium para las restricciones en caso free
			
				if (clientePerfil.getTipo().name().equals("Premium")) {
					//metemos los dos objetos como parametros para insertar la playlist

					if (dao.playlistInsertar(cliente, playL)) {
						
						JOptionPane.showMessageDialog(null, "Playlist creada");
					} 						
			}
				else if (clientePerfil.getTipo().name().equals("Free")) {
					//usamos dao para contar cuantas playlist tiene el cliente Free
					int num = dao.numeroPlaylists(clientePerfil.getIdCliente());
					
					if (num < NUM_MAX_PLAYLIST_FREE) {

						if (dao.playlistInsertar(cliente, playL)) {
							JOptionPane.showMessageDialog(botonGuardar, "Playlist creada");	
						}
					} else {
						JOptionPane.showMessageDialog(botonGuardar, "Tu suscripción no permite crear más de " + NUM_MAX_PLAYLIST_FREE + " playlists.");
					}
				
			}
			
			}});
		panelOpciones.add(botonGuardar);
		
		botonActualizar = new JButton("ACTUALIZAR PAGINA");
		botonActualizar.setFont(new Font("Constantia", Font.BOLD, 15));
		botonActualizar.setBounds(203, 84, 227, 31);
		botonActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				MiPlaylist reseteo = new MiPlaylist();
				reseteo.setVisible(true);
				
			}
				
		});
		panelOpciones.add(botonActualizar);
		
		panelOpciones.validate();
	}
	
	
	public void eventoEliminar () {
		
		panelOpciones.removeAll();
		panelOpciones.repaint();
		
		lblNombrePlaylist = new JLabel("Nombre Playlist: ");
		lblNombrePlaylist.setFont(new Font("Constantia", Font.BOLD, 15));
		lblNombrePlaylist.setBounds(60, 28, 143, 29);
		panelOpciones.add(lblNombrePlaylist);
		
		nombrePlaylistTxt = new JTextField();
		nombrePlaylistTxt.setBounds(196, 28, 152, 26);
		panelOpciones.add(nombrePlaylistTxt);
		nombrePlaylistTxt.setColumns(10);
		
		botonGuardar = new JButton("ELIMINAR");
		botonGuardar.setFont(new Font("Constantia", Font.BOLD, 15));
		botonGuardar.setBounds(60, 84, 118, 31);
		botonGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PlaylistDAO dao = new PlaylistDAO();
				//Damos valor a las variables
				String nombre = nombrePlaylistTxt.getText();
				Playlist playL = new Playlist(nombre);
				
				//metemos los dos objetos como parametros para insertar la playlist
				if (dao.eliminarPlaylist(playL)) {
					
					JOptionPane.showMessageDialog(botonGuardar, "Playlist borrada");
					dispose();
					MiPlaylist p = new MiPlaylist();
					p.setVisible(true);
					
				}
				
			}
			
		});
		panelOpciones.add(botonGuardar);
		
	}
	}