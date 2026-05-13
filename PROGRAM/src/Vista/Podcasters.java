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

public class Podcasters extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Artistas frame = new Artistas();
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
	                	Podcasters ventanaDePodcasters = new Podcasters();
	                	ventanaDePodcasters.setVisible(true);
	                    Podcasters.this.dispose();
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
		rellenarCombo();
	}
	
	private void rellenarCombo() {
        String sql = "SELECT NombreArtistico FROM Artista";
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/reto3spotify", "root", "Elorrieta00");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                comboBox.addItem(rs.getString("NombreArtistico"));
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}

