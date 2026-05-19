package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controlador.AlbumDAO;
import Controlador.Conexion;
import Controlador.MusicoDAO;
import Modelo.Cliente;
import Modelo.Usuario;

public class AlbumVentana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JList<String> listCanciones;
    private DefaultListModel<String> modeloLista;
    private JTextArea txtInfoAlbum;
    private JLabel lblPortada;
    private JLabel lblTituloAlbum;
    private Cliente clientePerfil = Usuario.getCliente();
    
    private String tituloAlb;
    private String nombreArt;

    public AlbumVentana(String tituloAlbum, String nombreArtista) {
    	this.tituloAlb = tituloAlbum;
    	this.nombreArt = nombreArtista;
    	
    	
        setTitle("Lista de Albumes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // bton atras
        JButton btnAtras = new JButton("Atras");
        btnAtras.setBounds(10, 10, 80, 25);
        btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArtistaVentana ventanaDeArtista = new ArtistaVentana(nombreArtista);
				ventanaDeArtista.setVisible(true);
				dispose();				
			}
        
        });
        contentPane.add(btnAtras);

        lblTituloAlbum = new JLabel("Album: " + tituloAlbum);
        lblTituloAlbum.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTituloAlbum.setBounds(100, 10, 400, 25);
        contentPane.add(lblTituloAlbum);

        // --- LISTA DE CANCIONES (parte izquierda) ---
        JLabel lblKanta = new JLabel("Lista de canciones:");
        lblKanta.setBounds(20, 50, 200, 20);
        contentPane.add(lblKanta);

        modeloLista = new DefaultListModel<>();
        listCanciones = new JList<>(modeloLista);
        JScrollPane scrollCanciones = new JScrollPane(listCanciones);
        scrollCanciones.setBounds(20, 80, 300, 350);
        listCanciones.addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent click) {
        		
        		if (click.getClickCount() == 2) {
        			String cancion = listCanciones.getSelectedValue();        			
        			
        			if(cancion!=null) {
        				
        				//cortamos hasta cuando empieza el - porque solo queremos el titulo de la cancion no lo demas
        				String nombreLimpio = cancion.split(" - ")[0];
        				VentanaReproduccion ventana = new VentanaReproduccion(nombreLimpio, tituloAlbum,nombreArtista);
        				dispose();
        				ventana.setVisible(true);
        				
        			}
        		}
        		
        	}
        	
        });
        contentPane.add(scrollCanciones);

        // --- INFORMACION DEL DISCO (parte derechas) ---
        JLabel lblInfoT = new JLabel("Información del disco:");
        lblInfoT.setBounds(350, 50, 200, 20);
        contentPane.add(lblInfoT);

        txtInfoAlbum = new JTextArea();
        txtInfoAlbum.setEditable(false);
        txtInfoAlbum.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtInfoAlbum.setBounds(350, 80, 350, 100);
        contentPane.add(txtInfoAlbum);

        // --- PORTADA DEL alLBUM ---
        lblPortada = new JLabel("");
        lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
        lblPortada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblPortada.setBounds(350, 190, 250, 250);
        contentPane.add(lblPortada);
        
        JButton btnPerfil = new JButton(clientePerfil.getUsuario());
        btnPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Perfil().setVisible(true);
    			dispose();
        	}
        });
        btnPerfil.setBounds(635, 11, 89, 23);
        contentPane.add(btnPerfil);

        cargarDatos(tituloAlbum);
  
    }
    
    
/**
 * Metodo para cargar los datos del album
 * @param titulo para almacenar el nombre del album
 */
    public void cargarDatos(String titulo) {
		AlbumDAO dao = new AlbumDAO();
		ArrayList<String> info = dao.obtenerInfoAlbum(titulo);

		if (!info.isEmpty()) {
			String idAlbum = info.get(0);
			txtInfoAlbum.setText("Lanzamiento: " + info.get(1) + "\n" +
					             "Canciones: " + info.get(3));
			String ruta = info.get(2);
			if (ruta != null) {
				
				ImageIcon icono = new ImageIcon(ruta);
				Image imagen = icono.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
				lblPortada.setIcon(new ImageIcon(imagen));
				lblPortada.setText("");
			}

			// lista de canciones con el LocalTime que sale del DAO
			ArrayList<String> canciones = dao.listaCancionesAlbum(idAlbum);
			modeloLista.clear(); //se limpia para que no se acumule uno debjo de otros
			
			for (int i = 0; i < canciones.size(); i++) {
				modeloLista.addElement(canciones.get(i));
			}
		}
	}
}