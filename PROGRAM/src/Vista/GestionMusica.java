package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JTable;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionMusica frame = new GestionMusica();
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
	public GestionMusica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMusica = new JLabel("Gestionar Música");
		lblMusica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMusica.setBounds(204, 10, 143, 22);
		contentPane.add(lblMusica);
		
		JLabel lblArtista = new JLabel("Artista:");
		lblArtista.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArtista.setBounds(62, 67, 67, 17);
		contentPane.add(lblArtista);
		
		txtArtista = new JTextField();
		txtArtista.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtArtista.setBounds(139, 67, 143, 20);
		contentPane.add(txtArtista);
		txtArtista.setColumns(10);
		
		JLabel lblAlbum = new JLabel("Album:");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlbum.setBounds(62, 113, 67, 22);
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
		
		JLabel lblCancion = new JLabel("Canción:");
		lblCancion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCancion.setBounds(62, 171, 75, 19);
		contentPane.add(lblCancion);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] nuevaFila = {txtArtista.getText(), txtAlbum.getText(), txtCancion.getText()};
				DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
				modelo.addRow(nuevaFila);
			}
		});
		btnCrear.setFont(new Font("Constantia", Font.BOLD, 15));
		btnCrear.setBounds(97, 234, 115, 27);
		contentPane.add(btnCrear);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaMusica.getSelectedRow();
				if(fila >= 0) {
					DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
					modelo.setValueAt(txtArtista.getText(), fila, 0);
					modelo.setValueAt(txtAlbum.getText(), fila, 1);
					modelo.setValueAt(txtCancion.getText(), fila, 2);
				}
			}
		});
		btnModificar.setFont(new Font("Constantia", Font.BOLD, 15));
		btnModificar.setBounds(97, 287, 115, 27);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Constantia", Font.BOLD, 15));
		btnEliminar.setBounds(97, 337, 115, 27);
		contentPane.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 52, 235, 333);
		contentPane.add(scrollPane);
		
		tablaMusica = new JTable();
		tablaMusica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Artista", "Album", "Cancion"
			}
		));
		scrollPane.setViewportView(tablaMusica);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Constantia", Font.BOLD, 15));
		btnAtras.setBounds(10, 14, 115, 27);
		contentPane.add(btnAtras);

	}
}
