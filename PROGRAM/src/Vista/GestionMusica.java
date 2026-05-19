package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controlador.CancionDAO;
import Modelo.Cancion;
import Modelo.Album;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionMusica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtArtista;
	private JTextField txtAlbum;
	private JTextField txtCancion;
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tablaMusica;


	public GestionMusica() {
		setTitle("Gestionar Musica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMusica = new JLabel("Gestionar Musica");
		lblMusica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMusica.setBounds(204, 10, 143, 22);
		contentPane.add(lblMusica);
		
		JLabel lblArtista = new JLabel("ID Artista:");
		lblArtista.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArtista.setBounds(38, 67, 91, 17);
		contentPane.add(lblArtista);
		
		txtArtista = new JTextField();
		txtArtista.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtArtista.setBounds(139, 67, 143, 20);
		contentPane.add(txtArtista);
		txtArtista.setColumns(10);
		
		JLabel lblAlbum = new JLabel("ID Album:");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlbum.setBounds(38, 117, 91, 22);
		contentPane.add(lblAlbum);
		
		txtAlbum = new JTextField();
		txtAlbum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAlbum.setColumns(10);
		txtAlbum.setBounds(139, 118, 143, 20);
		contentPane.add(txtAlbum);
		
		txtCancion = new JTextField();
		txtCancion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCancion.setColumns(10);
		txtCancion.setBounds(139, 171, 143, 20);
		contentPane.add(txtCancion);
		
		JLabel lblCancion = new JLabel("Cancion:");
		lblCancion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCancion.setBounds(38, 172, 113, 19);
		contentPane.add(lblCancion);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String artista = txtArtista.getText();
		        String nombreCancion = txtCancion.getText();
		        String idAlbum = txtAlbum.getText();
		        
		        Album albumTemporal = new Album(idAlbum); 
		        albumTemporal.setIdAlbum(idAlbum); 
		        
		        Cancion cancionObjeto = new Cancion("0", nombreCancion, albumTemporal, artista);
		       
		        CancionDAO dao = new CancionDAO();
		        boolean resultado = dao.insertarCancion(cancionObjeto);
		        
		        if (resultado) {
		            DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
		            modelo.addRow(new Object[]{ artista, nombreCancion, idAlbum });
		            
		            txtCancion.setText("");
		            txtAlbum.setText("");
		            txtArtista.setText("");
		            
		            JOptionPane.showMessageDialog(btnCrear, "Canción creada e insertada");
		        } else {
		            JOptionPane.showMessageDialog(btnCrear, "Error al insertar");
		        }
		    }
		});
		btnCrear.setFont(new Font("Constantia", Font.BOLD, 15));
		btnCrear.setBounds(97, 228, 115, 27);
		contentPane.add(btnCrear);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaMusica.getSelectedRow();
		        
		        if (filaSeleccionada >= 0) {
		            DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
		            
		            String idAudioReal = modelo.getValueAt(filaSeleccionada, 0).toString();
		            
		            String nuevoNombre = txtCancion.getText();
		            String nuevoIdAlbum = txtAlbum.getText();
		            String nuevosInvitados = txtArtista.getText();
		            
		            Album albumTemporal = new Album(nuevoIdAlbum); 
		            Cancion cancionEditada = new Cancion(idAudioReal, nuevoNombre, albumTemporal, nuevosInvitados);
		            
		            CancionDAO dao = new CancionDAO();
		            boolean resultado = dao.modificarCancion(cancionEditada);
		            
		            if (resultado) {
		                modelo.setValueAt(nuevoNombre, filaSeleccionada, 1);
		                modelo.setValueAt(nuevoIdAlbum, filaSeleccionada, 2);
		                
		                txtCancion.setText("");
		                txtAlbum.setText("");
		                txtArtista.setText("");
		                
		                JOptionPane.showMessageDialog(btnModificar, "Cancion modificada");
		            } else {
		                JOptionPane.showMessageDialog(btnModificar, "Error.");
		            }
		            
		        } else {
		            JOptionPane.showMessageDialog(btnModificar, "selecciona una canción de la tabla primero.");
		        }
			}
		});
		btnModificar.setFont(new Font("Constantia", Font.BOLD, 15));
		btnModificar.setBounds(97, 280, 115, 27);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaMusica.getSelectedRow();
		        
		        if (filaSeleccionada >= 0) {
		            DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
		            
		            String idAudioReal = modelo.getValueAt(filaSeleccionada, 0).toString();
		            
		            Cancion audioABorrar = new Cancion(idAudioReal, null, null, null);
		            
		            CancionDAO dao = new CancionDAO();
		            boolean exito = dao.eliminarCancion(audioABorrar);
		            
		            if (exito == true) {
		                modelo.removeRow(filaSeleccionada);
		                
		                txtCancion.setText("");
		                txtAlbum.setText("");
		                txtArtista.setText("");
		                
		                JOptionPane.showMessageDialog(btnEliminar, "Canción eliminada correctamente.");
		            } else {
		                JOptionPane.showMessageDialog(btnEliminar, "Error.");
		            }
		            
		        } else {
		            JOptionPane.showMessageDialog(btnEliminar, "selecciona una cancion primero.");
		        }
			}
		});
		btnEliminar.setFont(new Font("Constantia", Font.BOLD, 15));
		btnEliminar.setBounds(97, 337, 115, 27);
		contentPane.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 52, 235, 333);
		contentPane.add(scrollPane);
		
		tablaMusica = new JTable();
		tablaMusica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaMusica.getSelectedRow();
		        if (fila >= 0) {
		            DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
		            txtArtista.setText(modelo.getValueAt(fila, 1).toString());
		            txtAlbum.setText(modelo.getValueAt(fila, 2).toString());
		            txtCancion.setText(modelo.getValueAt(fila, 3).toString());
		        }
			}
		});
		tablaMusica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Artista", "Album", "Cancion"
			}
		));
		scrollPane.setViewportView(tablaMusica);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        
		        Menu_Administrador menuAdmin = new Menu_Administrador(); 
		        
		        menuAdmin.setVisible(true);
			}
		});
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		btnAtras.setBounds(10, 14, 115, 27);
		contentPane.add(btnAtras);

	}
}
