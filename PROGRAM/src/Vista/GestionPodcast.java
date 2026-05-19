package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Controlador.Conexion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GestionPodcast extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDesc;
	private JTable tablaPodcaster;
	private JTextField txtTitulo;
	private JTextField txtColab;
	private JTextField txtIdPodcaster;
	private JTable tablaPodcast;
	private Conexion conn = new Conexion(); 
	private PreparedStatement statement;

	public GestionPodcast() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gestionar Podcast");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitulo.setBounds(272, 11, 188, 22);
		contentPane.add(lblTitulo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 50, 640, 391);
		contentPane.add(tabbedPane);
		
		JPanel panelPodcaster = new JPanel();
		tabbedPane.addTab("Podcaster", null, panelPodcaster, null);
		panelPodcaster.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(44, 37, 87, 17);
		panelPodcaster.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(158, 37, 120, 20);
		panelPodcaster.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDesc = new JLabel("Descripcion:");
		lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDesc.setBounds(44, 87, 104, 20);
		panelPodcaster.add(lblDesc);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(158, 87, 120, 20);
		panelPodcaster.add(txtDesc);
		
		JScrollPane scrollPodcaster = new JScrollPane();
		scrollPodcaster.setBounds(10, 121, 615, 231);
		panelPodcaster.add(scrollPodcaster);
		
		tablaPodcaster = new JTable();
		tablaPodcaster.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaPodcaster.getSelectedRow();
				if (fila >= 0) {
					DefaultTableModel modelo = (DefaultTableModel) tablaPodcaster.getModel();
					txtNombre.setText(modelo.getValueAt(fila, 1).toString());
					txtDesc.setText(modelo.getValueAt(fila, 2).toString());
				}
			}
		});
		tablaPodcaster.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IdPodcaster", "Nombre", "Descripcion"
			}
		));
		scrollPodcaster.setViewportView(tablaPodcaster);
		
		JButton btnCrearPodcaster = new JButton("Crear");
		btnCrearPodcaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String nombre = txtNombre.getText();
				String desc = txtDesc.getText();
				String sql = "INSERT INTO Podcaster (NombreArtistico, Descripcion, Imagen) VALUES (?, ?, 'default.png')";
				try {
					Connection conexion = conn.getConnection();
					if (conexion != null) {
						statement = conexion.prepareStatement(sql);
						statement.setString(1, nombre);
						statement.setString(2, desc);
						if (statement.executeUpdate() > 0) {
							DefaultTableModel modelo = (DefaultTableModel) tablaPodcaster.getModel();
							modelo.addRow(new Object[] { "Auto", nombre, desc });
							txtNombre.setText(""); 
							txtDesc.setText("");
							JOptionPane.showMessageDialog(null, "Podcaster creado");
						}
					}
				} catch (SQLException er) { 
					er.printStackTrace(); 
					}
			}
		});
		btnCrearPodcaster.setFont(new Font("Constantia", Font.BOLD, 15));
		btnCrearPodcaster.setBounds(357, 36, 78, 27);
		panelPodcaster.add(btnCrearPodcaster);
		
		JButton btnModificarPodcaster = new JButton("Modificar");
		btnModificarPodcaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int fila = tablaPodcaster.getSelectedRow();
				if (fila >= 0) {
					DefaultTableModel modelo = (DefaultTableModel) tablaPodcaster.getModel();
					String id = modelo.getValueAt(fila, 0).toString();
					String nombre = txtNombre.getText();
					String desc = txtDesc.getText();
					
					String sql = "UPDATE Podcaster SET NombreArtistico = ?, Descripcion = ? WHERE IdPodcaster = ?";
					try {
						Connection conexion = conn.getConnection();
						statement = conexion.prepareStatement(sql);
						statement.setString(1, nombre);
						statement.setString(2, desc);
						statement.setString(3, id);
						if (statement.executeUpdate() > 0) {
							modelo.setValueAt(nombre, fila, 1);
							modelo.setValueAt(desc, fila, 2);
							JOptionPane.showMessageDialog(null, "Podcaster modificado");
						}
					} catch (SQLException e) { 
						e.printStackTrace();
						}
				} else { 
					JOptionPane.showMessageDialog(null, "Selecciona una fila"); 
					}
			}
		});
		btnModificarPodcaster.setFont(new Font("Constantia", Font.BOLD, 15));
		btnModificarPodcaster.setBounds(469, 37, 104, 27);
		panelPodcaster.add(btnModificarPodcaster);
		
		JButton btnEliminarPodcaster = new JButton("Eliminar");
		btnEliminarPodcaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int fila = tablaPodcaster.getSelectedRow();
				if (fila >= 0) {
					DefaultTableModel modelo = (DefaultTableModel) tablaPodcaster.getModel();
					String id = modelo.getValueAt(fila, 0).toString();
					String sql = "DELETE FROM Podcaster WHERE IdPodcaster = ?";
					try {
						Connection conexion = conn.getConnection();
						statement = conexion.prepareStatement(sql);
						statement.setString(1, id);
						if (statement.executeUpdate() > 0) {
							modelo.removeRow(fila);
							txtNombre.setText(""); txtDesc.setText("");
							JOptionPane.showMessageDialog(null, "Podcaster eliminado");
						}
					} catch (SQLException e) { 
						e.printStackTrace(); 
						}
				} else { 
					JOptionPane.showMessageDialog(null, "Selecciona una fila"); 
					}
			}
		});
		btnEliminarPodcaster.setFont(new Font("Constantia", Font.BOLD, 15));
		btnEliminarPodcaster.setBounds(394, 80, 126, 27);
		panelPodcaster.add(btnEliminarPodcaster);
		
		JPanel panelPodcast = new JPanel();
		tabbedPane.addTab("Podcast", null, panelPodcast, null);
		panelPodcast.setLayout(null);
		
		JLabel lblTituloPodcast = new JLabel("Titulo:");
		lblTituloPodcast.setBounds(20, 28, 68, 22);
		lblTituloPodcast.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelPodcast.add(lblTituloPodcast);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(165, 32, 150, 20);
		panelPodcast.add(txtTitulo);
		
		JLabel lblColab = new JLabel("N Colaboradores:");
		lblColab.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblColab.setBounds(20, 72, 150, 17);
		panelPodcast.add(lblColab);
		
		txtColab = new JTextField();
		txtColab.setColumns(10);
		txtColab.setBounds(165, 73, 150, 20);
		panelPodcast.add(txtColab);
		
		JLabel lblIdPodcaster = new JLabel("Id Podcaster:");
		lblIdPodcaster.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdPodcaster.setBounds(20, 112, 114, 22);
		panelPodcast.add(lblIdPodcaster);
		
		txtIdPodcaster = new JTextField();
		txtIdPodcaster.setColumns(10);
		txtIdPodcaster.setBounds(165, 116, 150, 20);
		panelPodcast.add(txtIdPodcaster);
		
		JScrollPane scrollPodcast = new JScrollPane();
		scrollPodcast.setBounds(30, 153, 577, 199);
		panelPodcast.add(scrollPodcast);
		
		tablaPodcast = new JTable();
		tablaPodcast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaPodcast.getSelectedRow();
				if (fila >= 0) {
					DefaultTableModel modelo = (DefaultTableModel) tablaPodcast.getModel();
					txtTitulo.setText(modelo.getValueAt(fila, 1).toString());
					txtColab.setText(modelo.getValueAt(fila, 2).toString());
					txtIdPodcaster.setText(modelo.getValueAt(fila, 3).toString());
				}
			}
		});
		tablaPodcast.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IdPodcast", "Titulo", "Colaboradores", "IdPodcaster"
			}
		));
		scrollPodcast.setViewportView(tablaPodcast);
		
		JButton btnCrearPodcast = new JButton("Crear");
		btnCrearPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String titulo = txtTitulo.getText();
				String colab = txtColab.getText();
				String idPodcaster = txtIdPodcaster.getText();

				String sqlAudio = "INSERT INTO Audio (IdAudio, Nombre, Duracion, Archivo, Tipo, NReproducciones) "
						+ "VALUES (LAST_INSERT_ID(), ?, '00:45:00', 'podcast.mp3', 'Podcast', 0)";
				String sqlPodcast = "INSERT INTO Podcast (IdPodcast, NColaboradores, IdPodcaster) "
						+ "VALUES (LAST_INSERT_ID(), ?, ?)";

				try {
					Connection conexion = conn.getConnection();
					if (conexion != null) {
						statement = conexion.prepareStatement(sqlAudio);
						statement.setString(1, titulo);
						if (statement.executeUpdate() > 0) {
							statement = conexion.prepareStatement(sqlPodcast);
							statement.setString(1, colab);
							statement.setString(2, idPodcaster);
							if (statement.executeUpdate() > 0) {
								DefaultTableModel modelo = (DefaultTableModel) tablaPodcast.getModel();
								modelo.addRow(new Object[] { "Auto", titulo, colab, idPodcaster });
								txtTitulo.setText(""); txtColab.setText(""); txtIdPodcaster.setText("");
								JOptionPane.showMessageDialog(null, "Podcast creado");
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace(); 
					}
			}
		});
		btnCrearPodcast.setFont(new Font("Constantia", Font.BOLD, 15));
		btnCrearPodcast.setBounds(423, 31, 123, 27);
		panelPodcast.add(btnCrearPodcast);
		
		JButton btnModificarPodcast = new JButton("Modificar");
		btnModificarPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int fila = tablaPodcast.getSelectedRow();
				if (fila >= 0) {
					DefaultTableModel modelo = (DefaultTableModel) tablaPodcast.getModel();
					String id = modelo.getValueAt(fila, 0).toString();
					String titulo = txtTitulo.getText();
					String colab = txtColab.getText();
					String idPodcaster = txtIdPodcaster.getText();

					String sqlAudio = "UPDATE Audio SET Nombre = ? WHERE IdAudio = ?";
					String sqlPodcast = "UPDATE Podcast SET NColaboradores = ?, IdPodcaster = ? WHERE IdPodcast = ?";
					try {
						Connection conexion = conn.getConnection();
						statement = conexion.prepareStatement(sqlAudio);
						statement.setString(1, titulo);
						statement.setString(2, id);
						statement.executeUpdate();
						
						statement = conexion.prepareStatement(sqlPodcast);
						statement.setString(1, colab);
						statement.setString(2, idPodcaster);
						statement.setString(3, id);
						if (statement.executeUpdate() > 0) {
							modelo.setValueAt(titulo, fila, 1);
							modelo.setValueAt(colab, fila, 2);
							modelo.setValueAt(idPodcaster, fila, 3);
							JOptionPane.showMessageDialog(null, "Podcast modificado");
						}
					} catch (SQLException e) { 
						e.printStackTrace(); 
						}
				} else { 
					JOptionPane.showMessageDialog(null, "Selecciona una fila"); 
					}
			}
		});
		btnModificarPodcast.setFont(new Font("Constantia", Font.BOLD, 15));
		btnModificarPodcast.setBounds(423, 71, 123, 27);
		panelPodcast.add(btnModificarPodcast);
		
		JButton btnEliminarPodcast = new JButton("Eliminar");
		btnEliminarPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int fila = tablaPodcast.getSelectedRow();
				if (fila >= 0) {
					DefaultTableModel modelo = (DefaultTableModel) tablaPodcast.getModel();
					String id = modelo.getValueAt(fila, 0).toString();

					String sqlPodcast = "DELETE FROM Podcast WHERE IdPodcast = ?";
					String sqlAudio = "DELETE FROM Audio WHERE IdAudio = ?";
					try {
						Connection conexion = conn.getConnection();
						statement = conexion.prepareStatement(sqlPodcast);
						statement.setString(1, id);
						if (statement.executeUpdate() > 0) {
							statement = conexion.prepareStatement(sqlAudio);
							statement.setString(1, id);
							statement.executeUpdate();
							
							modelo.removeRow(fila);
							txtTitulo.setText(""); txtColab.setText(""); txtIdPodcaster.setText("");
							JOptionPane.showMessageDialog(null, "Podcast eliminado");
						}
					} catch (SQLException e) { 
						e.printStackTrace(); 
						}
				} else { 
					JOptionPane.showMessageDialog(null, "Selecciona una fila"); 
					}
			}
		});
		btnEliminarPodcast.setFont(new Font("Constantia", Font.BOLD, 15));
		btnEliminarPodcast.setBounds(420, 115, 126, 27);
		panelPodcast.add(btnEliminarPodcast);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu_Administrador panelAdmin = new Menu_Administrador();
				panelAdmin.setVisible(true);
			}
		});
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		btnAtras.setBounds(10, 16, 71, 27);
		contentPane.add(btnAtras);

	}
}
