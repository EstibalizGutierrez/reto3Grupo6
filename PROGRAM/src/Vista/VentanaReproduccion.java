package Vista;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.Color;

import Modelo.InfoCancion;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import Controlador.AlbumDAO;
import Controlador.CancionDAO;

public class VentanaReproduccion extends JFrame {
	private JPanel contentPane;
	private JLabel lblFoto;
	private JButton btnMenu;
	private JButton btnAnterior;
	private JButton btnPlay;
	private JButton btnSiguiente;
	private JButton btnFavorito;
	private JLabel lblNewLabel;
	private JTextArea txtInfoCancion;
	private BasicPlayer reproductor;
	private String nombreCancion;
	private String tituloAlbum;
	private String nombreArtista;
	private JButton btnAtras;
	private boolean pausa = false;
	private JButton btnStop;
	private File archivo;
	
	
	public VentanaReproduccion(String nombreCancion,String tituloAlbum, String nombrArtista) {
		
		this.nombreCancion = nombreCancion;
		this.tituloAlbum = tituloAlbum;
		this.nombreArtista = nombreArtista;
		
		
		setTitle("Reproductor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(242, 10, 385, 291);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblFoto);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(386, 311, 106, 27);
		btnPlay.setFont(new Font("Constantia", Font.BOLD, 15));
		btnPlay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		        salidaAudio();
		    }
		});
		contentPane.add(btnPlay);
		
		btnStop = new JButton("Stop");
		btnStop.setFont(new Font("Constantia", Font.BOLD, 15));
		btnStop.setBounds(386, 348, 106, 27);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				btnStop();
				}});
		contentPane.add(btnStop);
		
		
		btnAnterior = new JButton("<");
		btnAnterior.setBounds(318, 319, 53, 27);
		btnAnterior.setFont(new Font("Constantia", Font.BOLD, 15));
		contentPane.add(btnAnterior);
		
		btnSiguiente = new JButton(">");
		btnSiguiente.setBounds(509, 319, 53, 27);
		btnSiguiente.setFont(new Font("Constantia", Font.BOLD, 15));
		contentPane.add(btnSiguiente);
		
		txtInfoCancion = new JTextArea();
		txtInfoCancion.setBounds(147, 377, 629, 161);
		txtInfoCancion.setEditable(false);
		txtInfoCancion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txtInfoCancion.setFont((new Font("Constantia", Font.BOLD, 15)));
		contentPane.add(txtInfoCancion);
		
		lblNewLabel = new JLabel("Información de la canción:");
		lblNewLabel.setBounds(147, 355, 208, 17);
		contentPane.add(lblNewLabel);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(180, 319, 91, 27);
		btnMenu.setFont(new Font("Constantia", Font.BOLD, 15));
		btnMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu_Cliente ventana = new Menu_Cliente();
				dispose();
				ventana.setVisible(true);
			}			
			
		});
		contentPane.add(btnMenu);
		
		btnFavorito = new JButton("Favorito");
		btnFavorito.setBounds(616, 319, 106, 27);
		btnFavorito.setFont(new Font("Constantia", Font.BOLD, 15));
		contentPane.add(btnFavorito);
		
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(25, 10, 80, 25);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				AlbumVentana ventana = new AlbumVentana(tituloAlbum,nombreArtista);
				dispose();
				ventana.setVisible(true);
			}
		});
		contentPane.add(btnAtras);


		cargarDatos(nombreCancion);
		
		
	}
	
	/**
	 * 
	 * METODO QUE se encarga de cargar los datos de la cancion escogida
	 * @param nombreCancion
	 */
	
public void cargarDatos(String nombreCancion) {
		
		CancionDAO dao = new CancionDAO();
		ArrayList<InfoCancion> lista = dao.infoCancion(nombreCancion);
		
		if (lista != null && !lista.isEmpty()) {
			// Pillamos el objeto que ha devuelto tu DAO
			InfoCancion info = lista.get(0);
			
			// Solo nombre del artista y la información que pediste
			txtInfoCancion.setText("Canción: " + info.getNombreCancion() + "\n" +
			                       "Artista: " + info.getNombreArtista() + "\n" +
			                       "Duración: " + info.getDuracion() + "\n" +
			                       "Reproducciones: " + info.getReproducciones());
			
			//instanciamos el daoAlbum para obtener la ruta de la portada 
			AlbumDAO daoAlb = new AlbumDAO();
			String rutaAlbum = daoAlb.rutaPortadaAlbum(info.getNombreAlbum());
			
			if (rutaAlbum != null ) {
				ImageIcon imageIcon = new ImageIcon(rutaAlbum);
				
				Image imagen = imageIcon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);	
				lblFoto.setIcon(new ImageIcon(imagen)); 
				lblFoto.setText(""); 
				
			} else {

				lblFoto.setIcon(null);
				lblFoto.setText("Sin Portada");
				lblFoto.setHorizontalAlignment(JLabel.CENTER); 
			}
			
		}
	}


	/**
	 * metodo que será evento de PLAY cuyo objetivo es realizar la salida de la cancion(audio)
	 * 
	 */

	public void salidaAudio() {
		
		CancionDAO dao = new CancionDAO();
		String ruta = dao.obtenerRutaAudio(nombreCancion);
			
			if (ruta !=null) {
				//cuando no sea nula metemos la ruta en el file
				archivo = new File(ruta);
				try {
	
					if(reproductor!= null) {
						
					} else {
						
						reproductor = new BasicPlayer(); //lo instanciamos si es null
					}
							
					if (reproductor.getStatus() == reproductor.PAUSED || reproductor.getStatus() == reproductor.PLAYING) {
						
						reproductor.stop();
						
					}
					
					reproductor.open(archivo);
					reproductor.play();
							
						}catch (Exception error) {
								error.printStackTrace();
								JOptionPane.showConfirmDialog(btnPlay, "Error inesperado");
								
						}
						
			} else {
				
				JOptionPane.showMessageDialog(btnPlay, "No se ha encontrado la canción");
				
			}
							
			}

	
	public void btnStop()  {
		
		try {
			
			if (reproductor != null) {
				
				if(!pausa) {
					
					reproductor.pause();
					pausa = true;
					btnStop.setText("Reanudar");
				} else {
					
					reproductor.resume();
					pausa = false;
					btnStop.setText("Stop");
				}
			}
		}catch (Exception error) {
			
			error.printStackTrace();
		}
		
	}
	
	
	public void siguiente() {
		
		
		
	}
	
	

	/**
	 * metodo para asegurarnos de parar el audio cuando cerremos la ventana 
	 */
	public void dispose() {
	    try {
	        if (reproductor != null) {
	            reproductor.stop(); // nos aseguramos que detenga el audio por completo
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    super.dispose();
	}
	
	
}



