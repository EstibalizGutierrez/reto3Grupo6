package Vista;

import Controlador.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

public class Artistas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;

	public Artistas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{73, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{53, 143, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Cliente ventanaDeMenu = new Menu_Cliente();
				ventanaDeMenu.setVisible(true);
				Artistas.this.dispose();
			}
		});
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 0;
		gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		JLabel lblListaArtistas = new JLabel("Lista de Artistas");
		lblListaArtistas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblListaArtistas = new GridBagConstraints();
		gbc_lblListaArtistas.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaArtistas.gridx = 2;
		gbc_lblListaArtistas.gridy = 0;
		contentPane.add(lblListaArtistas, gbc_lblListaArtistas);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Artistas ventanaDeArtistas = new Artistas();
				ventanaDeArtistas.setVisible(true);
				Artistas.this.dispose();
			}
		});
		btnPerfil.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerfil.gridx = 4;
		gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
		
		JButton btnVerArtista = new JButton("Ver Artista");
		btnVerArtista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String nombreSeleccionado = (String) comboBox.getSelectedItem();
	                if (nombreSeleccionado != null) {
	                	ListaArtista ventanaDeArtista = new ListaArtista();
	                	ventanaDeArtista.setVisible(true);
	                    Artistas.this.dispose();
	                    System.out.println("Cargando a: " + nombreSeleccionado);
	                }
	            }	
	        });
		btnVerArtista.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnVerArtista = new GridBagConstraints();
		gbc_btnVerArtista.insets = new Insets(0, 0, 0, 5);
		gbc_btnVerArtista.gridx = 2;
		gbc_btnVerArtista.gridy = 2;
		contentPane.add(btnVerArtista, gbc_btnVerArtista);
		rellenarComboMusicos();
	}
	
	private void rellenarComboMusicos() {
	    String sql = "SELECT Artista.NombreArtistico " +
	                 "FROM Artista " +
	                 "JOIN Musico ON Artista.IdArtista = Musico.IdMusico";

	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33060/reto3spotify", "root", "Eljohannbachx25");
	    	 Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {

	        comboBox.removeAllItems();
	        while (rs.next()) {
	            comboBox.addItem(rs.getString("NombreArtistico"));
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al cargar m�sicos: " + e.getMessage());
	    }
	}
}

