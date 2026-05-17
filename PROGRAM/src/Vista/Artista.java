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

public class Artista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> listDiscos;
	private DefaultListModel<String> modeloLista;
	private JTextArea txtInformacion;
	private JLabel lblFoto;
	private Cliente clientePerfil = Usuario.getCliente();

	public Artista(String nombreArtista) {
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

		// --- BOTÓN PERFIL ---
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
		
		// --- APARTADO DE INFORMACION ---
		JLabel lblInformacion = new JLabel("Informacion");
		GridBagConstraints gbc_lblInformacion = new GridBagConstraints();
		gbc_lblInformacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformacion.gridx = 2;
		gbc_lblInformacion.gridy = 1;
		contentPane.add(lblInformacion, gbc_lblInformacion);
		listDiscos = new JList<>(modeloLista);
		listDiscos.addMouseListener(new MouseAdapter() {
		// --- EVENTO PARA SELECCIONAR EL ALBUM Y QUE NOS LLEVE A LA NUEVA PESTANA
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int idx = listDiscos.locationToIndex(e.getPoint());
					if (idx != -1) {
						String sel = modeloLista.getElementAt(idx);
						String tituloAlbum = sel.split(" - ")[0];
						String idAlbum = obtenerIdAlbumPorTitulo(tituloAlbum);
						new Album(idAlbum, tituloAlbum).setVisible(true);
						dispose();
					}
				}
			}
		});
		// --- SCROLL DE INFORMACION ---
		JScrollPane scrollDiscos = new JScrollPane(listDiscos);
		GridBagConstraints gbc_scrollD = new GridBagConstraints();
		gbc_scrollD.insets = new Insets(0, 0, 5, 5);
		gbc_scrollD.fill = GridBagConstraints.BOTH;
		gbc_scrollD.gridx = 0; gbc_scrollD.gridy = 2; gbc_scrollD.gridheight = 2;
		contentPane.add(scrollDiscos, gbc_scrollD);
		
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
		cargarDatosArtista(nombreArtista);
	}
	
	/**
	 * Carga la información de un artista y sus álbumes en la interfaz.
	 * @param nombre El nombre artístico seleccionado por el usuario.
	 */
	private void cargarDatosArtista(String nombre) {
		Conexion db = new Conexion();
		Connection con = db.getConnection();
		
		try {
			// Obtenemos género, descripción, imagen y usamos una consulta
			String sqlArtista = "SELECT Genero, Descripcion, Imagen, IdArtista, " +
                               "(SELECT YEAR(MIN(Anno)) FROM Album WHERE IdMusico = Artista.IdArtista) as AnnoInicio " +
                               "FROM Artista WHERE NombreArtistico = ?";
			PreparedStatement psArt = con.prepareStatement(sqlArtista);
			psArt.setString(1, nombre);
			ResultSet rsArt = psArt.executeQuery();
			// Formatear y mostrar la información textual en el TextArea
			if (rsArt.next()) {
				String infoText = "Genero: " + rsArt.getString("Genero") + "\n" +
                                 "Año de Inicio: " + rsArt.getString("AnnoInicio") + "\n\n" +
                                 "Descripcion:\n" + rsArt.getString("Descripcion");
				txtInformacion.setText(infoText);
				txtInformacion.setCaretPosition(0);

				String urlImg = rsArt.getString("Imagen");
				String idArt = rsArt.getString("IdArtista");

				new Thread(() -> {
					try {
						URL url = new URL(urlImg);
						Image img = ImageIO.read(url);
						ImageIcon icon = new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
						lblFoto.setIcon(icon);
						lblFoto.setText("");
					} catch (Exception e) {
						lblFoto.setIcon(null);
						lblFoto.setText("Sin Imagen");
					}
				}).start();

				// Consulta para álbumes y contar canciones
				String sqlAlb = "SELECT a.Titulo, YEAR(a.Anno) as Anno, (SELECT COUNT(*) FROM Cancion c WHERE c.IdAlbum = a.IdAlbum) as NumCanciones " +
                               "FROM Album a WHERE a.IdMusico = ?";
				PreparedStatement psAlb = con.prepareStatement(sqlAlb);
				psAlb.setString(1, idArt);
				ResultSet rsAlb = psAlb.executeQuery();

				while (rsAlb.next()) {
					String item = rsAlb.getString("Titulo") + " - " + 
					              rsAlb.getString("Anno") + " - " + 
					              rsAlb.getInt("NumCanciones") + " kanta";
					modeloLista.addElement(item);
				}
			}
		} catch (SQLException e) {
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