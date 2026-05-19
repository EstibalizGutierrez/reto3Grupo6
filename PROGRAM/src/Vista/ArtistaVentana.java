package Vista;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controlador.Conexion;
import Controlador.MusicoDAO;
import Modelo.Cliente;
import Modelo.Usuario;

public class ArtistaVentana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> listDiscos;
	private DefaultListModel<String> modeloLista;
	private JTextArea txtInformacion;
	private JLabel lblFoto;
	private Cliente clientePerfil = Usuario.getCliente();
	
	private String nombreArtista;

	public ArtistaVentana(String nombreArtista) {
		this.nombreArtista = nombreArtista; 
		setTitle("Lista de artistas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{200, 20, 200, 0};
		gbl_contentPane.rowHeights = new int[]{40, 30, 150, 150, 0};
		contentPane.setLayout(gbl_contentPane);
		
		// --- BOTON ATRAS ---
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(e -> {
			new ListaDeArtistas().setVisible(true);
			dispose();
		});
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 0; gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		// --- NOMBRE ARTISTA ---
		JLabel lblNombre = new JLabel(nombreArtista);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1; gbc_lblNombre.gridy = 0;
		contentPane.add(lblNombre, gbc_lblNombre);

		// --- BOTON PERFIL ---
		JButton btnPerfil = new JButton(clientePerfil.getUsuario());
		btnPerfil.addActionListener(e -> {
			new Perfil().setVisible(true);
			dispose();
		});
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 5);
		gbc_btnPerfil.gridx = 2; gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		
		// --- LISTA DISCOS ---
		JLabel lblTDiscos = new JLabel("Lista de Discos");
		GridBagConstraints gbc_lblT = new GridBagConstraints();
		gbc_lblT.insets = new Insets(0, 0, 5, 5);
		gbc_lblT.gridx = 0; gbc_lblT.gridy = 1;
		contentPane.add(lblTDiscos, gbc_lblT);

		modeloLista = new DefaultListModel<>();
		listDiscos = new JList<>(modeloLista);
		
		// --- APARTADO DE INFORMACION ---
		JLabel lblInformacion = new JLabel("Informacion");
		GridBagConstraints gbc_lblInformacion = new GridBagConstraints();
		gbc_lblInformacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformacion.gridx = 2;
		gbc_lblInformacion.gridy = 1;
		contentPane.add(lblInformacion, gbc_lblInformacion);
		
		// --- SCROLL DE INFORMACION ---
		JScrollPane scrollDiscos = new JScrollPane(listDiscos);
		GridBagConstraints gbc_scrollD = new GridBagConstraints();
		gbc_scrollD.insets = new Insets(0, 0, 5, 5);
		gbc_scrollD.fill = GridBagConstraints.BOTH;
		gbc_scrollD.gridx = 0; gbc_scrollD.gridy = 2; gbc_scrollD.gridheight = 2;
		contentPane.add(scrollDiscos, gbc_scrollD);
		
		// --- INFORMACION ---
		txtInformacion = new JTextArea();
		txtInformacion.setLineWrap(true);
		txtInformacion.setWrapStyleWord(true);
		txtInformacion.setEditable(false);
		JScrollPane scrollInfo = new JScrollPane(txtInformacion);
		GridBagConstraints gbc_scrollI = new GridBagConstraints();
		gbc_scrollI.insets = new Insets(0, 0, 5, 5);
		gbc_scrollI.fill = GridBagConstraints.BOTH;
		gbc_scrollI.gridx = 2; gbc_scrollI.gridy = 2;
		contentPane.add(scrollInfo, gbc_scrollI);
		
		// --- FOTO ---
		lblFoto = new JLabel("Cargando...", SwingConstants.CENTER);
		lblFoto.setPreferredSize(new Dimension(150, 150));
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_foto = new GridBagConstraints();
		gbc_foto.insets = new Insets(0, 0, 5, 5);
		gbc_foto.gridx = 2; gbc_foto.gridy = 3;
		contentPane.add(lblFoto, gbc_foto);
		
		//Para Jlist no usamos addActionListener sino MouseListener (para que sean dos clickks no solo uno)
		listDiscos.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evento) {
		        if (evento.getClickCount() == 2) {
		            int i = listDiscos.locationToIndex(evento.getPoint());
		            if (i != -1) {
		                String sel = modeloLista.getElementAt(i);
		                // Separamos el titulo del resto de la cadena (Titulo - Anno - Canciones)
		                String tituloAlbum = sel.split(" - ")[0];
		                
		                AlbumVentana ventana= new AlbumVentana(tituloAlbum,nombreArtista);
		                dispose();
		                ventana.setVisible(true)
		                ;
		            }
		        }
		    }
		});
		
		cargarDatosArtista(nombreArtista);

	}
	

	/**
	 * metodo que usaremos para cargar los datos del artista que plasmaremos en la ventana del artista
	 * 
	 * @param nombre
	 */
	
	public void cargarDatosArtista(String nombre) {
	    MusicoDAO dao = new MusicoDAO();
	    
	    //usamos el metodo para recorrer toda la inforamcion a "info"
	    ArrayList<String> info = dao.obtenerInfoArtista(nombre);
	    //comprobamos que haya informacion
	    
	    if (info.size() > 0) {
	    	
	        //cogemos el id del artista buscando entre la informaacion para usarla luego
	        String idArtista = info.get(3);

	        // rellenamos informacion
	        txtInformacion.setText("Genero: " + info.get(0) + "\n" +
	                               "Anno de Inicio: " + info.get(4) + "\n\n" +
	                               "Descripcion:\n" + info.get(1));
	        txtInformacion.setCaretPosition(0);

	        // Llamamos al otro metodo
	        String rutaImagen = dao.rutaImagenArtista(idArtista); 
	        
	        if (rutaImagen != null) {
	            // Cargamos desde la carpeta 'imagenes'

	            ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
	            

	            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	            lblFoto.setIcon(new ImageIcon(imgEscalada));
	            lblFoto.setText(""); 
	        } else {
	            lblFoto.setText("Sin imagen");
	        }

	        //cargamos los Albumes usando el ID
	        ArrayList<String> albumes = dao.listaAlbumesArtista(idArtista);
	        modeloLista.clear();
	        for (int i = 0; i < albumes.size(); i++) {
	            modeloLista.addElement(albumes.get(i));
	        }
	    }
	}
}