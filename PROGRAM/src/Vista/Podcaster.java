package Vista;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controlador.Conexion;
import Modelo.Cliente;
import Modelo.Usuario;

public class Podcaster extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> listPodcast;
	private DefaultListModel<String> modeloLista;
	private JTextArea txtInformacion;
	private JLabel lblFoto;
	private Cliente clientePerfil = Usuario.getCliente();

	public Podcaster(String Podcaster) {
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
		
		// --- BOTÓN ATRÁS ---
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(e -> {
			ListaDePodcasters ventanaDeListaPodcaster = new ListaDePodcasters();
			ventanaDeListaPodcaster.setVisible(true);
			Podcaster.this.dispose();
		});
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 0; gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		// --- NOMBRE ARTISTA ---
		JLabel lblNombre = new JLabel(Podcaster);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1; gbc_lblNombre.gridy = 0;
		contentPane.add(lblNombre, gbc_lblNombre);

		// --- BOTÓN PERFIL ---
		JButton btnPerfil = new JButton(clientePerfil.getUsuario());
		btnPerfil.addActionListener(e -> {
			Perfil ventanaDePerfil = new Perfil();
			ventanaDePerfil.setVisible(true);
			Podcaster.this.dispose();
		});
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 5);
		gbc_btnPerfil.gridx = 2; gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		
		// --- LISTA PODCAST ---
		JLabel lblPodcast = new JLabel("Lista de Podcast");
		GridBagConstraints gbc_lblPodcast = new GridBagConstraints();
		gbc_lblPodcast.insets = new Insets(0, 0, 5, 5);
		gbc_lblPodcast.gridx = 0; gbc_lblPodcast.gridy = 1;
		contentPane.add(lblPodcast, gbc_lblPodcast);

		modeloLista = new DefaultListModel<>();
		
		// --- APARTADO DE INFORMACION ---
		JLabel lblInformacion = new JLabel("Informacion");
		GridBagConstraints gbc_lblInformacion = new GridBagConstraints();
		gbc_lblInformacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformacion.gridx = 2;
		gbc_lblInformacion.gridy = 1;
		contentPane.add(lblInformacion, gbc_lblInformacion);
		listPodcast = new JList<>(modeloLista);
		listPodcast.addMouseListener(new MouseAdapter() {
		// --- EVENTO PARA SELECCIONAR EL ALBUM Y QUE NOS LLEVE A LA NUEVA PESTANA
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int idx = listPodcast.locationToIndex(e.getPoint());
					if (idx != -1) {
						String sel = modeloLista.getElementAt(idx);
						String tituloPodcast = sel.split(" - ")[0];
						String idPodcast = obtenerIdAlbumPorTitulo(tituloPodcast);
						new Album(idPodcast, tituloPodcast).setVisible(true);
						dispose();
					}
				}
			}
		});
		// --- SCROLL DE INFORMACION ---
		JScrollPane scrollPodcast = new JScrollPane(listPodcast);
		GridBagConstraints gbc_scrollPodcast = new GridBagConstraints();
		gbc_scrollPodcast.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPodcast.fill = GridBagConstraints.BOTH;
		gbc_scrollPodcast.gridx = 0; gbc_scrollPodcast.gridy = 2; gbc_scrollPodcast.gridheight = 2;
		contentPane.add(scrollPodcast, gbc_scrollPodcast);
		
		// --- INFORMACIÓN ---
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
		
		// Cargar datos al iniciar
		cargarDatosArtista(Podcaster);
	}
	
	/**
	 * Carga la información de un artista y sus álbumes en la interfaz.
	 * @param nombre El nombre artístico seleccionado por el usuario.
	 */
	private void cargarDatosArtista(String nombre) {
	    Conexion db = new Conexion();
	    Connection con = db.getConnection();
	    
	    try {
	        String sqlArt = "SELECT IdArtista, Genero, Descripcion, Imagen FROM Artista WHERE NombreArtistico = ?";
	        PreparedStatement psArt = con.prepareStatement(sqlArt);
	        psArt.setString(1, nombre);
	        ResultSet rsArt = psArt.executeQuery();

	        if (rsArt.next()) {
	            String idArt = rsArt.getString("IdArtista");
	            String genero = rsArt.getString("Genero");
	            String desc = rsArt.getString("Descripcion");
	            String urlImg = rsArt.getString("Imagen");

	            txtInformacion.setText("Género: " + genero + "\n\nDescripción:\n" + desc);
	            txtInformacion.setCaretPosition(0);


	            new Thread(() -> {
	                try {
	                    URL url = new URL(urlImg);
	                    Image img = ImageIO.read(url);
	                    lblFoto.setIcon(new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
	                    lblFoto.setText("");
	                } catch (Exception e) {
	                    lblFoto.setText("Sin Imagen");
	                }
	            }).start();

	            String sqlPod = "SELECT au.Nombre, au.Duracion, art.Genero " +
	                            "FROM Audio au " +
	                            "INNER JOIN Podcast p ON au.IdAudio = p.IdPodcast " +
	                            "INNER JOIN Artista art ON p.IdPodcaster = art.IdArtista " +
	                            "WHERE art.IdArtista = ?";
	            
	            PreparedStatement psPod = con.prepareStatement(sqlPod);
	            psPod.setString(1, idArt);
	            ResultSet rsPod = psPod.executeQuery();

	            modeloLista.clear();
	            while (rsPod.next()) {
	                String item = rsPod.getString("Nombre") + " - " + 
	                              rsPod.getInt("Duracion") + " min - " + 
	                              rsPod.getString("Genero");
	                modeloLista.addElement(item);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error de sintaxis en MySQL: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        db.cerrarConexion();
	    }
	}
	
	
	
	private String obtenerIdAlbumPorTitulo(String titulo) {
	    String id = "";
	    Conexion db = new Conexion();
	    Connection con = db.getConnection();
	    
	    String sql = "SELECT IdAlbum FROM Album WHERE Titulo = ?";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, titulo);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            id = rs.getString("IdAlbum");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        db.cerrarConexion();
	    }
	    return id;
	}
}