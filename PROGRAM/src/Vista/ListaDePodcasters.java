package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// Clase de la ventana de la interfaz gráfica para listar podcasters
public class ListaDePodcasters extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;

	// Configura la ventana y todos los componentes visuales
	public ListaDePodcasters() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Configuración del sistema de orden para posicionar componentes
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{73, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{53, 143, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Botón para regresar al menú anterior
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Cliente ventanaDeMenuCliente = new Menu_Cliente();
				ventanaDeMenuCliente.setVisible(true);
				ListaDePodcasters.this.dispose(); // Cierra la ventana actual
			}
		});
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 0;
		gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		// Label del título principal
		JLabel lblListaPodcasters = new JLabel("Lista de Podcasters");
		lblListaPodcasters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblListaPodcasters = new GridBagConstraints();
		gbc_lblListaPodcasters.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaPodcasters.gridx = 2;
		gbc_lblListaPodcasters.gridy = 0;
		contentPane.add(lblListaPodcasters, gbc_lblListaPodcasters);
		
		// Botón para redirigir a la lista de artistas generales
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaDeArtistas ventanaDeArtistas = new ListaDeArtistas();
				ventanaDeArtistas.setVisible(true);
				ListaDePodcasters.this.dispose();
			}
		});
		btnPerfil.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerfil.gridx = 4;
		gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		
		// Desplegable (ComboBox) que contendrá los nombres de los podcasters
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
		
		// Botón para acceder al perfil del podcaster seleccionado en el ComboBox
		JButton btnVerPodcaster = new JButton("Ver Podcaster");
		btnVerPodcaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String nombreSeleccionado = (String) comboBox.getSelectedItem();
	                if (nombreSeleccionado != null) {
	                	// Abre la ventana de detalle del podcaster pasando su nombre como parámetro
	                	Podcaster ventanaDePodcaster = new Podcaster(nombreSeleccionado);
	                	ventanaDePodcaster.setVisible(true);
	                    ListaDePodcasters.this.dispose();
	                    System.out.println("Cargando a: " + nombreSeleccionado);
	                }
	            }
	        });
		btnVerPodcaster.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnVerPodcaster = new GridBagConstraints();
		gbc_btnVerPodcaster.insets = new Insets(0, 0, 0, 5);
		gbc_btnVerPodcaster.gridx = 2;
		gbc_btnVerPodcaster.gridy = 2;
		contentPane.add(btnVerPodcaster, gbc_btnVerPodcaster);
		
		// Llamada al método para cargar los datos desde la base de datos al iniciar la ventana
		rellenarComboPodcasters();
	}
	
	// Método que conecta con MySQL y llena el ComboBox con los nombres artísticos
	private void rellenarComboPodcasters() {
		// Consulta SQL que une las tablas Artista y Podcaster para obtener los nombres
	    String sql = "SELECT Artista.NombreArtistico " +
	                 "FROM Artista " +
	                 "JOIN Podcaster ON Artista.IdArtista = Podcaster.IdPodcaster";
	    
	    // Gestión de recursos para el cierre automático de la conexión
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/reto3spotify", "root", "");
	    	 Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {
	    	
	    	// Limpia elementos para evitar duplicados
	        comboBox.removeAllItems();
	        // Recorre las filas obtenidas de la base de datos y las añade al combobox
	        while (rs.next()) {
	            comboBox.addItem(rs.getString("NombreArtistico"));
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al cargar musicos: " + e.getMessage());
	    }
	}
}

