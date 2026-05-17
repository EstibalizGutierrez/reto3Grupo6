package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.CancionDAO;
import javax.swing.DefaultListModel;

import Modelo.Cliente;
import Modelo.Playlist;
import Modelo.PlaylistCanciones;
import Modelo.Usuario;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JScrollPane;

public class InfoPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonPerfil;
	
	private Cliente clientePerfil = Usuario.getCliente();
	private ArrayList<PlaylistCanciones> lstPlaylistCanciones = new ArrayList<>();
	private JList<String> jotaLista;

	private String nombrePL;
	
	public InfoPlaylist(String nombrePlaylist) {
		
		this.nombrePL = nombrePlaylist;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jotaLista = new JList<String>();
		jotaLista.setBackground(new Color(128, 0, 128));
		jotaLista.setBounds(23, 80, 888, 456);
		contentPane.add(jotaLista);
		
		botonPerfil = new JButton(clientePerfil.getUsuario());
		botonPerfil.setFont(new Font("Constantia", Font.BOLD, 15));
		botonPerfil.setBounds(808, 22, 118, 31);
		contentPane.add(botonPerfil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 80, 888, 456);
		contentPane.add(scrollPane);
		
	
		CancionDAO dao = new CancionDAO();
		Playlist pNombre = new Playlist(nombrePL);
		int idPL = dao.obtenerIdPlaylist(pNombre);
		
		Playlist pId = new Playlist(idPL);
		
		lstPlaylistCanciones = dao.mostrarListaPlaylist(pId);

		rellenarLista(lstPlaylistCanciones);
		
	}
	
	/**
	 * 
	 * Metodo que sirve para rellenar el JList con los datos de la
	 * playlist individualmente 
	 * 
	 * @param lstPLC
	 */
	
	
	public void rellenarLista(ArrayList<PlaylistCanciones> lstPLC) {
				
		DefaultListModel<String> modelo = new DefaultListModel<>();
		
		for (int i = 0; i < lstPLC.size(); i++) {
			//PASAMOS la info a variables                                        
			String nombre = lstPLC.get(i).getCancion().getNombre();
			String nombreArtistico = lstPLC.get(i).getMusico().getNombre();
			int numRepro = lstPLC.get(i).getCancion().getNumReproducciones();
			LocalTime duracion = lstPLC.get(i).getCancion().getDuracion();
			
			//concatenamos para que nos deje incluirla en el modelo
			String fila = nombre + " | " + nombreArtistico + " | " + numRepro + " | " + duracion;
			
			modelo.addElement(fila);
			
		}

		jotaLista.setModel(modelo);
	
	}
}
