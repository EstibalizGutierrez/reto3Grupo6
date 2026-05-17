package Vista;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controlador.Conexion;

public class Album extends JFrame {

    private JPanel contentPane;
    private JList<String> listCanciones;
    private DefaultListModel<String> modeloLista;
    private JTextArea txtInfoAlbum;
    private JLabel lblPortada;
    private JLabel lblTituloAlbum;

    public Album(String idAlbum, String tituloAlbum) {
        setTitle("Ventana de Álbum");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        // Establecemos Absolute Layout
        contentPane.setLayout(null);

        // --- BOTÓN ATRÁS ---
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(10, 10, 80, 25);
        btnAtras.addActionListener(e -> {
        	Artista ventanaDeArtista = new Artista(tituloAlbum);
			ventanaDeArtista.setVisible(true);
			Album.this.dispose();
            dispose();
        });
        contentPane.add(btnAtras);

        lblTituloAlbum = new JLabel("Álbum: " + tituloAlbum);
        lblTituloAlbum.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTituloAlbum.setBounds(100, 10, 400, 25);
        contentPane.add(lblTituloAlbum);

        // --- LISTA DE CANCIONES (IZQUIERDA) ---
        JLabel lblKanta = new JLabel("Lista de canciones:");
        lblKanta.setBounds(20, 50, 200, 20);
        contentPane.add(lblKanta);

        modeloLista = new DefaultListModel<>();
        listCanciones = new JList<>(modeloLista);
        JScrollPane scrollCanciones = new JScrollPane(listCanciones);
        scrollCanciones.setBounds(20, 80, 300, 350);
        contentPane.add(scrollCanciones);

        // --- INFORMACIÓN DEL DISCO (DERECHA) ---
        JLabel lblInfoT = new JLabel("Información del disco:");
        lblInfoT.setBounds(350, 50, 200, 20);
        contentPane.add(lblInfoT);

        txtInfoAlbum = new JTextArea();
        txtInfoAlbum.setEditable(false);
        txtInfoAlbum.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtInfoAlbum.setBounds(350, 80, 350, 100);
        contentPane.add(txtInfoAlbum);

        // --- PORTADA DEL ÁLBUM ---
        lblPortada = new JLabel("Cargando portada...");
        lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
        lblPortada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblPortada.setBounds(350, 190, 250, 250);
        contentPane.add(lblPortada);

        // Cargar los datos desde la BD
        cargarDatos(idAlbum);
    }

    private void cargarDatos(String idAlbum) {
        Conexion db = new Conexion();
        Connection con = db.getConnection();

        try {
            String sqlInfo = "SELECT a.Anno, a.Imagen, " +
                             "(SELECT COUNT(*) FROM Cancion WHERE IdAlbum = a.IdAlbum) as TotalCanciones, " +
                             "(SELECT SUM(au.Duracion) FROM Audio au JOIN Cancion c ON au.IdAudio = c.IdCancion WHERE c.IdAlbum = a.IdAlbum) as DuracionTotal " +
                             "FROM Album a WHERE a.IdAlbum = ?";
            
            PreparedStatement ps = con.prepareStatement(sqlInfo);
            ps.setString(1, idAlbum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int duracionSegundos = rs.getInt("DuracionTotal");
                String tiempoFormateado = (duracionSegundos / 60) + ":" + String.format("%02d", (duracionSegundos % 60));
                
                String info = "Colaboradores: Varios\n" + // En tu SQL no hay tabla de colaboradores por álbum, se podría sacar de ArtistasInvitados
                             "Fecha de publicación: " + rs.getString("Anno") + "\n" +
                             "Cantidad de canciones: " + rs.getInt("TotalCanciones") + "\n" +
                             "Duración total: " + tiempoFormateado + " min";
                
                txtInfoAlbum.setText(info);

                // Cargar Imagen
                String urlImg = rs.getString("Imagen");
                new Thread(() -> {
                    try {
                        URL url = new URL(urlImg);
                        Image img = ImageIO.read(url);
                        lblPortada.setIcon(new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
                        lblPortada.setText("");
                    } catch (Exception e) {
                        lblPortada.setText("Sin imagen");
                    }
                }).start();
            }

            // 2. Obtener lista de canciones
            String sqlCanciones = "SELECT au.Nombre, au.Duracion, au.NReproducciones " +
                                  "FROM Audio au JOIN Cancion c ON au.IdAudio = c.IdCancion " +
                                  "WHERE c.IdAlbum = ?";
            PreparedStatement psC = con.prepareStatement(sqlCanciones);
            psC.setString(1, idAlbum);
            ResultSet rsC = psC.executeQuery();

            while (rsC.next()) {
                String dur = rsC.getInt("Duracion") + ":00"; // Simplificado
                String item = rsC.getString("Nombre") + " - " + rsC.getInt("NReproducciones") + " repros - " + dur;
                modeloLista.addElement(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.cerrarConexion();
        }
    }
}