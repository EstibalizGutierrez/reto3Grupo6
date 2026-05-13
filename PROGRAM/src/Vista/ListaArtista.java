package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ListaArtista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtListaDiscos;
	private JTextField txtInformacion;

	public ListaArtista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 439);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{164, 85, 157, 0};
		gbl_contentPane.rowHeights = new int[]{59, 30, 147, 154, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Artistas ventanaDeArtistas = new Artistas();
				ventanaDeArtistas.setVisible(true);
				ListaArtista.this.dispose();
			}
		});
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 0;
		gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		JLabel lblNombreArtista = new JLabel("si");
		lblNombreArtista.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNombreArtista = new GridBagConstraints();
		gbc_lblNombreArtista.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreArtista.gridx = 1;
		gbc_lblNombreArtista.gridy = 0;
		contentPane.add(lblNombreArtista, gbc_lblNombreArtista);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Artistas ventanaDeArtistas = new Artistas();
				ventanaDeArtistas.setVisible(true);
				ListaArtista.this.dispose();
			}
		});
		btnPerfil.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerfil.gridx = 2;
		gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		
		JLabel lblListaDiscos = new JLabel("Lista de Discos");
		lblListaDiscos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblListaDiscos = new GridBagConstraints();
		gbc_lblListaDiscos.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaDiscos.gridx = 0;
		gbc_lblListaDiscos.gridy = 1;
		contentPane.add(lblListaDiscos, gbc_lblListaDiscos);
		
		JLabel lblInformacion = new JLabel("Informacion");
		lblInformacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblInformacion = new GridBagConstraints();
		gbc_lblInformacion.insets = new Insets(0, 0, 5, 0);
		gbc_lblInformacion.gridx = 2;
		gbc_lblInformacion.gridy = 1;
		contentPane.add(lblInformacion, gbc_lblInformacion);
		
		txtListaDiscos = new JTextField();
		GridBagConstraints gbc_txtListaDiscos = new GridBagConstraints();
		gbc_txtListaDiscos.insets = new Insets(0, 0, 5, 5);
		gbc_txtListaDiscos.fill = GridBagConstraints.BOTH;
		gbc_txtListaDiscos.gridx = 0;
		gbc_txtListaDiscos.gridy = 2;
		contentPane.add(txtListaDiscos, gbc_txtListaDiscos);
		txtListaDiscos.setColumns(10);
		
		txtInformacion = new JTextField();
		GridBagConstraints gbc_txtInformacion = new GridBagConstraints();
		gbc_txtInformacion.insets = new Insets(0, 0, 5, 0);
		gbc_txtInformacion.fill = GridBagConstraints.BOTH;
		gbc_txtInformacion.gridx = 2;
		gbc_txtInformacion.gridy = 2;
		contentPane.add(txtInformacion, gbc_txtInformacion);
		txtInformacion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

	}

}
