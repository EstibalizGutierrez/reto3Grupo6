package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Cliente;
import Modelo.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class Menu_Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private Cliente clientePerfil = Usuario.getCliente();

	public Menu_Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 220, 0, 0};
		gbl_contentPane.rowHeights = new int[]{57, 60, 64, 56};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ventanaDeLogin = new Login();
				ventanaDeLogin.setVisible(true);
				Menu_Cliente.this.dispose();
			}
		});
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 0;
		gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		JLabel lblElige = new JLabel("Elige");
		lblElige.setHorizontalAlignment(SwingConstants.CENTER);
		lblElige.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblElige = new GridBagConstraints();
		gbc_lblElige.insets = new Insets(0, 0, 5, 5);
		gbc_lblElige.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblElige.gridx = 1;
		gbc_lblElige.gridy = 0;
		contentPane.add(lblElige, gbc_lblElige);
		
		JButton btnDescubrirMusica = new JButton("Descubrir Musica");
		btnDescubrirMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Artistas ventanaDeArtistas = new Artistas();
				ventanaDeArtistas.setVisible(true);
				Menu_Cliente.this.dispose();
			}
		});
		
		JButton btnPerfil = new JButton(clientePerfil.getUsuario());
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perfil ventanaDePerfil = new Perfil();
				ventanaDePerfil.setVisible(true);
				Menu_Cliente.this.dispose();
			}
		});
		btnPerfil.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerfil.gridx = 2;
		gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		btnDescubrirMusica.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnDescubrirMusica = new GridBagConstraints();
		gbc_btnDescubrirMusica.insets = new Insets(0, 0, 5, 5);
		gbc_btnDescubrirMusica.gridx = 1;
		gbc_btnDescubrirMusica.gridy = 1;
		contentPane.add(btnDescubrirMusica, gbc_btnDescubrirMusica);
		
		JButton btnDescubrirPodcast = new JButton("Descubrir Podcast");
		btnDescubrirPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Podcasters ventanaDePodcasters = new Podcasters();
				ventanaDePodcasters.setVisible(true);
				Menu_Cliente.this.dispose();
			}
		});
		btnDescubrirPodcast.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnDescubrirPodcast = new GridBagConstraints();
		gbc_btnDescubrirPodcast.insets = new Insets(0, 0, 5, 5);
		gbc_btnDescubrirPodcast.gridx = 1;
		gbc_btnDescubrirPodcast.gridy = 2;
		contentPane.add(btnDescubrirPodcast, gbc_btnDescubrirPodcast);
		
		JButton btnMisPlaylist = new JButton("Mis Playlist");
		btnMisPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Playlist ventanaDePlaylist = new Playlist();
				ventanaDePlaylist.setVisible(true);
				Menu_Cliente.this.dispose();
			}
		});
		btnMisPlaylist.setFont(new Font("Constantia", Font.BOLD, 15));
		GridBagConstraints gbc_btnMisPlaylist = new GridBagConstraints();
		gbc_btnMisPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_btnMisPlaylist.gridx = 1;
		gbc_btnMisPlaylist.gridy = 3;
		contentPane.add(btnMisPlaylist, gbc_btnMisPlaylist);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
