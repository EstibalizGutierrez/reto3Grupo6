package aplicacion;

import java.awt.EventQueue;
import DAOs.audioDAO;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.io.FileNotFoundException;


public class pruebaAudioVentan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idAudioTxt;
	private JLabel introducirIdLbl;
	private JButton botonAudio;
	private FileInputStream fileInpStr;
	private Player reproductor;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebaAudioVentan frame = new pruebaAudioVentan();
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
	public pruebaAudioVentan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		introducirIdLbl = new JLabel("Introduce ID canción:");
		introducirIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		introducirIdLbl.setBounds(389, 210, 195, 57);
		contentPane.add(introducirIdLbl);
		
		idAudioTxt = new JTextField();
		idAudioTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idAudioTxt.setBounds(420, 266, 110, 38);
		contentPane.add(idAudioTxt);
		idAudioTxt.setColumns(10);

		
		botonAudio = new JButton("PLAY");
		botonAudio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		botonAudio.setBounds(408, 369, 135, 52);
		contentPane.add(botonAudio);
		botonAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				salidaAudioEvento();
				
			}
		});

		
		
	}
	
	public void salidaAudioEvento() {
		
		audioDAO daoPrueba = new audioDAO();
		String audioId = idAudioTxt.getText();
		
		String ruta = daoPrueba.audioInsertar(audioId);
		
		if (ruta == null || ruta.isEmpty()) {
			
			JOptionPane.showMessageDialog(botonAudio, "Esta ID no se ha encontrado");
			
		} else {
			
			Thread hiloAudio = new Thread(new Runnable() {
				
				public void run() {
				
				try {
					
					if (reproductor != null) {
						
						reproductor.close();
						
					}
					
					fileInpStr = new FileInputStream(ruta);
					reproductor = new Player(fileInpStr);
					reproductor.play();
					
				} catch (FileNotFoundException error) {
					
					JOptionPane.showMessageDialog(botonAudio, "Archivo no encontrado");
					
				} catch (Exception error) {
					
					JOptionPane.showMessageDialog(botonAudio, "Error inesperado");				
				}
					
				}
						
			});
			
			hiloAudio.start();		
		}
	}
}
