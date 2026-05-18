package Vista;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Color;

import Modelo.Cancion;
import Modelo.ControladorSonido;
import Controlador.CancionDAO;

public class PanelReproduccion extends JPanel {
	private static final long serialVersionUID = -7213619404088130177L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public PanelReproduccion() {
		setBounds(100, 100, 659, 499);
		setLayout(null);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(183, 35, 278, 253);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(lblFoto);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Constantia", Font.BOLD, 15));
		btnPlay.setBounds(279, 311, 91, 27);
		add(btnPlay);
		
		JButton btnAnterior = new JButton("<");
		btnAnterior.setFont(new Font("Constantia", Font.BOLD, 15));
		btnAnterior.setBounds(218, 311, 53, 27);
		add(btnAnterior);
		
		JButton btnSiguiente = new JButton(">");
		btnSiguiente.setFont(new Font("Constantia", Font.BOLD, 15));
		btnSiguiente.setBounds(381, 311, 53, 27);
		add(btnSiguiente);
		
		JTextArea txtInfoCancion = new JTextArea();
		txtInfoCancion.setEditable(false);
		txtInfoCancion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txtInfoCancion.setBounds(84, 377, 494, 100);
		add(txtInfoCancion);
		
		JLabel lblNewLabel = new JLabel("Información de la canción:");
		lblNewLabel.setBounds(84, 355, 131, 12);
		add(lblNewLabel);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Constantia", Font.BOLD, 15));
		btnMenu.setBounds(117, 313, 91, 27);
		add(btnMenu);
		
		JButton btnFavorito = new JButton("Favorito");
		btnFavorito.setFont(new Font("Constantia", Font.BOLD, 15));
		btnFavorito.setBounds(444, 313, 91, 27);
		add(btnFavorito);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
}
