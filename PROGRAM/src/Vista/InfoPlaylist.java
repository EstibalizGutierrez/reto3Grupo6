package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AlbumDAO;
import Controlador.CancionDAO;
import Controlador.PlaylistDAO;

import javax.swing.DefaultListModel;

import Modelo.Cliente;
import Modelo.Playlist;
import Modelo.PlaylistCanciones;
import Modelo.Usuario;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

public class InfoPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonPerfil;
	
	private Cliente clientePerfil = Usuario.getCliente();
	private ArrayList<PlaylistCanciones> lstPlaylistCanciones = new ArrayList<>();
	private JList<String> jotaLista;

	private String nombrePL;
	private JButton botonAtras;
	
	public InfoPlaylist(String nombrePlaylist) {
		
		this.nombrePL = nombrePlaylist;
		
		setTitle(nombrePL);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jotaLista = new JList<String>();
		jotaLista.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		jotaLista.setBackground(new Color(192, 192, 192));
		jotaLista.setBounds(23, 80, 888, 456);
		jotaLista.setFont(new Font("Arial", Font.BOLD, 24));
		jotaLista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent click) {
				
				if (click.getClickCount() == 2) {
					String cancion = jotaLista.getSelectedValue();
					
					if (cancion != null) {
						AlbumDAO daoAlb = new AlbumDAO();
						//limpiamos todo el selected que no sea nombre (hay varios datos como minutos, nombreartista... que no nos interesa y si cogemos el valor entero la ventana de reproduccion no lo entenderá
						String soloNombre = cancion.split(" \\| ")[0].trim();
				        String soloNombreArtista = cancion.split(" \\| ")[1].trim();
						String nombreAlbum = daoAlb.obtenerTituloAlbum(soloNombre);
						
						VentanaReproduccion ventana = new VentanaReproduccion(soloNombre, nombreAlbum,soloNombreArtista);
						dispose();
						ventana.setVisible(true);
					}
				}
				
			}
		});
		contentPane.add(jotaLista);
		
		botonPerfil = new JButton(clientePerfil.getUsuario());
		botonPerfil.setFont(new Font("Constantia", Font.BOLD, 15));
		botonPerfil.setBounds(808, 22, 118, 31);
		botonPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Perfil perfil = new Perfil();
				dispose();
				perfil.setVisible(true);
				
			}
		});
		contentPane.add(botonPerfil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 80, 888, 456);
		contentPane.add(scrollPane);
		
		botonAtras = new JButton("ATRAS");
		botonAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		botonAtras.setBounds(23, 22, 118, 31);
		botonAtras.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				
				MiPlaylist ventana = new MiPlaylist();
				dispose();
				ventana.setVisible(true);
			}
			
		});
		contentPane.add(botonAtras);
		
	
		//INSERTAMOS LOS METODOS DEL DAO, GENERANDO EL ID DE LA PLAYLIST Y LUEGO USANDOLO PARA CREAR LA LISTADECANCIONES
		PlaylistDAO dao = new PlaylistDAO();
		Playlist pNombre = new Playlist(nombrePL);
		int idPL = dao.obtenerIdPlaylist(pNombre);
		
		Playlist pId = new Playlist(idPL);
		
		lstPlaylistCanciones = dao.mostrarListaPlaylist(pId);

		rellenarLista(lstPlaylistCanciones);
		
	}
	
	/**
	 * 
	 *+ Metodo que sirve para rellenar el JList con los datos de la
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
