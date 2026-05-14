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
		

		

	}
	
	
	public void mostrarPlaylist () {
		
		try {
		
			PlaylistDAO dao = new PlaylistDAO();
			ArrayList <String> listaPlaylist = dao.listaPlaylists(clientePerfil);
			
			//metemos lo que haya en el panel actualmente
			Component [] panelInfo = panelPlaylists.getComponents();
		
			//añadimos la playlist
			for (int i = 0; i < panelInfo.length; i++) {
				
				panelPlaylists.remove(panelInfo[i]);
				
				}
			
			panelPlaylists.setVisible(true);
				
			//ahora añadimos nuestras playlist mediante botones
				
				if (listaPlaylist.isEmpty()) {
					
					lblNoPlaylist = new JLabel("NO HAY  NINGUNA PLAYLIST CREADA");
					lblNoPlaylist.setFont(new Font("Constantia", Font.BOLD, 15));
					panelPlaylists.add(lblNoPlaylist);
					
				} else {
					
					for (int i = 0; i < listaPlaylist.size(); i++) {

					JButton boton = new JButton(listaPlaylist.get(i));
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
		

		panelOpciones = new JPanel();
		panelOpciones.setBackground(new Color(128, 128, 0));
		panelOpciones.setBounds(361, 300, 563, 250);
		contentPane.add(panelOpciones);
		panelOpciones.setLayout(null);
		
		lblNombrePlaylist = new JLabel("Nombre Playlist: ");
		lblNombrePlaylist.setFont(new Font("Constantia", Font.BOLD, 15));
		lblNombrePlaylist.setBounds(60, 28, 143, 29);
		panelOpciones.add(lblNombrePlaylist);
		
		nombrePlaylistTxt = new JTextField();
		nombrePlaylistTxt.setBounds(196, 28, 152, 26);
		panelOpciones.add(nombrePlaylistTxt);
		nombrePlaylistTxt.setColumns(10);

		PlaylistDAO dao = new PlaylistDAO();
		
		String nombre = nombrePlaylistTxt.getText();
		int id = dao.generarId();
		String idCliente = clientePerfil.getIdCliente();
		LocalDate fechaCreacion = LocalDate.now();
		
		Cliente cliente = new Cliente(idCliente);
		Playlist playL = new Playlist(id,nombre,fechaCreacion, idCliente);
		
		
		
		
	}
}
