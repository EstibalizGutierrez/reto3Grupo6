package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu_Administrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_Administrador frame = new Menu_Administrador();
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
	public Menu_Administrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 29, 428, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menú Administrador");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(126, 10, 172, 26);
		panel.add(lblNewLabel);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ventanaLogin = new Login();
		        ventanaLogin.setVisible(true); // Reabre el login
		        dispose();
			}
		});
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		btnAtras.setBounds(10, 10, 71, 27);
		panel.add(btnAtras);
		
		JButton btnGestionarMsica = new JButton("Gestionar Música");
		btnGestionarMsica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionMusica ventanaMusica = new GestionMusica();
				dispose();
		        ventanaMusica.setVisible(true);
			}
		});
		btnGestionarMsica.setFont(new Font("Constantia", Font.BOLD, 15));
		btnGestionarMsica.setBounds(126, 76, 172, 27);
		panel.add(btnGestionarMsica);
		
		JButton btnGestionarPodcast = new JButton("Gestionar Podcast");
		btnGestionarPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		btnGestionarPodcast.setFont(new Font("Constantia", Font.BOLD, 15));
		btnGestionarPodcast.setBounds(126, 138, 172, 27);
		panel.add(btnGestionarPodcast);
		
		JButton btnEstadsticas = new JButton("Estadísticas");
		btnEstadsticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estadisticas ventanaEstats = new Estadisticas();
				dispose();
		        ventanaEstats.setVisible(true);
			}
		});
		btnEstadsticas.setFont(new Font("Constantia", Font.BOLD, 15));
		btnEstadsticas.setBounds(126, 195, 172, 27);
		panel.add(btnEstadsticas);

	}
}
