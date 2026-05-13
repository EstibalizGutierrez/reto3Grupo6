package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class listaArtista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtListaDiscos;
	private JTextField txtInformacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listaArtista frame = new listaArtista();
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
	public listaArtista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{154, 85, 141, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 189, 154, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnAtras = new JButton("Atras");
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 0;
		gbc_btnAtras.gridy = 0;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		JLabel lblNombreArtista = new JLabel("si");
		GridBagConstraints gbc_lblNombreArtista = new GridBagConstraints();
		gbc_lblNombreArtista.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreArtista.gridx = 1;
		gbc_lblNombreArtista.gridy = 0;
		contentPane.add(lblNombreArtista, gbc_lblNombreArtista);
		
		JButton btnPerfil = new JButton("Perfil");
		GridBagConstraints gbc_btnPerfil = new GridBagConstraints();
		gbc_btnPerfil.anchor = GridBagConstraints.EAST;
		gbc_btnPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerfil.gridx = 2;
		gbc_btnPerfil.gridy = 0;
		contentPane.add(btnPerfil, gbc_btnPerfil);
		
		JLabel lblListaDiscos = new JLabel("Lista de Discos");
		GridBagConstraints gbc_lblListaDiscos = new GridBagConstraints();
		gbc_lblListaDiscos.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaDiscos.gridx = 0;
		gbc_lblListaDiscos.gridy = 1;
		contentPane.add(lblListaDiscos, gbc_lblListaDiscos);
		
		JLabel lblInformacion = new JLabel("Informacion");
		GridBagConstraints gbc_lblInformacion = new GridBagConstraints();
		gbc_lblInformacion.insets = new Insets(0, 0, 5, 0);
		gbc_lblInformacion.gridx = 2;
		gbc_lblInformacion.gridy = 1;
		contentPane.add(lblInformacion, gbc_lblInformacion);
		
		txtListaDiscos = new JTextField();
		GridBagConstraints gbc_txtListaDiscos = new GridBagConstraints();
		gbc_txtListaDiscos.insets = new Insets(0, 0, 5, 5);
		gbc_txtListaDiscos.fill = GridBagConstraints.BOTH;
		gbc_txtListaDiscos.gridx = 0;
		gbc_txtListaDiscos.gridy = 2;
		contentPane.add(txtListaDiscos, gbc_txtListaDiscos);
		txtListaDiscos.setColumns(10);
		
		txtInformacion = new JTextField();
		GridBagConstraints gbc_txtInformacion = new GridBagConstraints();
		gbc_txtInformacion.insets = new Insets(0, 0, 5, 0);
		gbc_txtInformacion.fill = GridBagConstraints.BOTH;
		gbc_txtInformacion.gridx = 2;
		gbc_txtInformacion.gridy = 2;
		contentPane.add(txtInformacion, gbc_txtInformacion);
		txtInformacion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

	}

}
