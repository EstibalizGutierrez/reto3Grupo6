package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Controlador.VistasDAO;
import Modelo.VistaRankingAlbumes;
import Modelo.VistaRankingArtistas;
import Modelo.VistaRankingContenido;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class Estadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaEstadisticas;
	JButton botonRankingContenido;
	JButton botonRankingAlbumes;
	JButton botonRankingArtistas;
	JButton botonAtras;
	private JLabel lblTitulo;
	private JPanel panelTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estadisticas frame = new Estadisticas();
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
	public Estadisticas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 601);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("ESTADISTICAS");
		lblTitulo.setFont(new Font("Constantia", Font.BOLD, 30));
		lblTitulo.setBounds(344, 7, 287, 44);
		contentPane.add(lblTitulo);
		
		botonAtras = new JButton("ATRAS");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Administrador menu = new Menu_Administrador();
				dispose();
				menu.setVisible(true);
			}
		});
		botonAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		botonAtras.setBounds(48, 27, 158, 46);
		contentPane.add(botonAtras);
		
		botonRankingContenido = new JButton("Contenido mas escuchado");
		botonRankingContenido.setForeground(new Color(255, 0, 128));
		botonRankingContenido.setBackground(new Color(255, 255, 255));
		botonRankingContenido.setFont(new Font("Constantia", Font.BOLD, 18));
		botonRankingContenido.setBounds(312, 61, 287, 34);
		botonRankingContenido.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
		botonRankingContenido.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				
				evento1();			
			}
			
		});
		contentPane.add(botonRankingContenido);
		
		botonRankingAlbumes = new JButton("Albumes mas escuchados");
		botonRankingAlbumes.setForeground(new Color(255, 0, 128));
		botonRankingAlbumes.setFont(new Font("Constantia", Font.BOLD, 18));
		botonRankingAlbumes.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
		botonRankingAlbumes.setBackground(Color.WHITE);
		botonRankingAlbumes.setBounds(312, 105, 287, 34);
		botonRankingAlbumes.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {			
				evento2();			
			}		
		});
		contentPane.add(botonRankingAlbumes);
		
		botonRankingArtistas = new JButton("Artistas mas escuchados");
		botonRankingArtistas.setForeground(new Color(255, 0, 128));
		botonRankingArtistas.setFont(new Font("Constantia", Font.BOLD, 18));
		botonRankingArtistas.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
		botonRankingArtistas.setBackground(Color.WHITE);
		botonRankingArtistas.setBounds(312, 149, 287, 34);
		botonRankingArtistas.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				evento3();
			}
		});
		contentPane.add(botonRankingArtistas);
		
		panelTabla = new JPanel();
		panelTabla.setBackground(new Color(128, 128, 0));
		panelTabla.setBounds(0, 194, 936, 370);
		contentPane.add(panelTabla);
		panelTabla.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(null);

	}
	
	public void evento1() {
		panelTabla.removeAll();
		
		tablaEstadisticas = new JTable();
		tablaEstadisticas.setBackground(new Color(192, 192, 192));
		tablaEstadisticas.setBounds(32, 209, 874, 332);
		JScrollPane scroll = new JScrollPane(tablaEstadisticas);
	    scroll.setBounds(32, 209, 874, 332);
	    tablaEstadisticas.setDefaultEditor(Object.class, null);
		panelTabla.add(scroll);
		
		
		VistasDAO dao = new VistasDAO();
		ArrayList<VistaRankingContenido> vistaLista = dao.tablaRankingContenido();
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Titulo");
		modelo.addColumn("Tipo");
		modelo.addColumn("Reproducciones");
		modelo.addColumn("Duración");
		modelo.addColumn("Autor");
	
		for (int i = 0; i < vistaLista.size(); i++) {
			
			Object[] fila = new Object[5];
			fila[0] = vistaLista.get(i).getNombreCancion();
			fila[1] = vistaLista.get(i).getTipo();
			fila[2] = vistaLista.get(i).getNumReproducciones();
			fila[3] = vistaLista.get(i).getDuracion();
			fila[4] = vistaLista.get(i).getNombreMusico();
		
			modelo.addRow(fila);
		}
		
		tablaEstadisticas.setModel(modelo);
		panelTabla.revalidate();
		panelTabla.repaint();
	}
	
	public void evento2() {
		panelTabla.removeAll();
		
		tablaEstadisticas = new JTable();
		tablaEstadisticas.setBackground(new Color(192, 192, 192));
		tablaEstadisticas.setBounds(32, 209, 874, 332);
		JScrollPane scroll = new JScrollPane(tablaEstadisticas);
		scroll.setBounds(32, 0, 874, 230);
	    tablaEstadisticas.setDefaultEditor(Object.class, null);
		panelTabla.add(scroll);
		
		VistasDAO dao = new VistasDAO();
		ArrayList<VistaRankingAlbumes> vistaLista = dao.tablaRankingAlbum();
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Titulo");
		modelo.addColumn("Lanzamiento");
		modelo.addColumn("Género");
		modelo.addColumn("Autor");
		modelo.addColumn("Reproducciones");
		
		for (int i = 0; i < vistaLista.size(); i++) {
			
			Object[] fila = new Object[5];
			fila[0] = vistaLista.get(i).getTitulo();
			fila[1] = vistaLista.get(i).getLanzamiento();
			fila[2] = vistaLista.get(i).getGenero();
			fila[3] = vistaLista.get(i).getNombreMusico();
			fila[4] = vistaLista.get(i).getNumReproducciones();
		
			modelo.addRow(fila);
		}
		
		tablaEstadisticas.setModel(modelo);
		panelTabla.revalidate();
		panelTabla.repaint();
	}
	
	
	public void evento3() {
		
		panelTabla.removeAll();
		
		tablaEstadisticas = new JTable();
		tablaEstadisticas.setBackground(new Color(192, 192, 192));
		tablaEstadisticas.setBounds(32, 209, 874, 332);
		JScrollPane scroll = new JScrollPane(tablaEstadisticas);
		scroll.setBounds(32, 0, 874, 230);
	    tablaEstadisticas.setDefaultEditor(Object.class, null);
		panelTabla.add(scroll);
		
		VistasDAO dao = new VistasDAO();
		ArrayList<VistaRankingArtistas> vistaLista = dao.tablaRankingArtista();
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Artista");
		modelo.addColumn("Característica");
		modelo.addColumn("Total Reproducciones");

		for (int i = 0; i< vistaLista.size(); i++) {
			
			Object [] fila = new Object[3];
			fila[0] = vistaLista.get(i).getNombreArtistico();
			fila[1] = vistaLista.get(i).getTipo();
			fila[2] = vistaLista.get(i).getTotalReproducciones();
			modelo.addRow(fila);
			
		}
		tablaEstadisticas.setModel(modelo);
		panelTabla.revalidate();
		panelTabla.repaint();
	}
}