package Maitane;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class menu_Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu_Cliente frame = new menu_Cliente();
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
	public menu_Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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
//levara hacia atras (registro o login)
			}
		});
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.insets = new Insets(0, 0, 0, 5);
		gbc_btnAtras.gridheight = 4;
		gbc_btnAtras.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAtras.gridx = 0;
		gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		JLabel lblElige = new JLabel("Elige");
		lblElige.setHorizontalAlignment(SwingConstants.CENTER);
		lblElige.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblElige = new GridBagConstraints();
		gbc_lblElige.insets = new Insets(0, 0, 5, 5);
		gbc_lblElige.anchor = GridBagConstraints.NORTH;
		gbc_lblElige.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblElige.gridx = 1;
		gbc_lblElige.gridy = 0;
		contentPane.add(lblElige, gbc_lblElige);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//mostrara el perfil del usuario que haya iniciado sesion o se haya registrado
			}
		});
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerfil.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPerfil.gridx = 2;
		gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		
		JButton btnDescubrirPodcast = new JButton("Descubrir Podcast");
		btnDescubrirPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// llevaria a un apartado donde mostrara todas las playlist disponibles sacados de la bbdd (aun no existe)
			}
		});
		GridBagConstraints gbc_btnDescubrirPodcast = new GridBagConstraints();
		gbc_btnDescubrirPodcast.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_btnDescubrirPodcast.insets = new Insets(0, 0, 5, 5);
		gbc_btnDescubrirPodcast.gridx = 1;
		gbc_btnDescubrirPodcast.gridy = 1;
		contentPane.add(btnDescubrirPodcast, gbc_btnDescubrirPodcast);
		
		JButton btnDescubrirMusica = new JButton("Descubrir Musica");
		btnDescubrirMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// llevaria a un apartado donde mostrara todos los podcasters disponibles sacados de la bbdd (aun no existe)
			}
		});
		GridBagConstraints gbc_btnDescubrirMusica = new GridBagConstraints();
		gbc_btnDescubrirMusica.insets = new Insets(0, 0, 5, 5);
		gbc_btnDescubrirMusica.gridx = 1;
		gbc_btnDescubrirMusica.gridy = 2;
		contentPane.add(btnDescubrirMusica, gbc_btnDescubrirMusica);
		
		JButton btnMiPlaylist = new JButton("Mi Playlist");
		GridBagConstraints gbc_btnMiPlaylist = new GridBagConstraints();
		gbc_btnMiPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_btnMiPlaylist.gridx = 1;
		gbc_btnMiPlaylist.gridy = 3;
		contentPane.add(btnMiPlaylist, gbc_btnMiPlaylist);

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
