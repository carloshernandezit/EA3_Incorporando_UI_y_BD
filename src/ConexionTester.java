import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ConexionTester extends JFrame {

    private JTextField campoUrl;
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonProbar;
    private JTextArea areaResultado;

    public ConexionTester() {
        setTitle("Probador de conexión JDBC - MySQL");
        setSize(520, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        construirInterfaz();
    }

    private void construirInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        campoUrl = new JTextField("jdbc:mysql://localhost:3306/interfaz");
        campoUsuario = new JTextField("root");
        campoContrasena = new JPasswordField();
        botonProbar = new JButton("Probar conexión");

        panelFormulario.add(new JLabel("URL de conexión:"));
        panelFormulario.add(campoUrl);
        panelFormulario.add(new JLabel("Usuario:"));
        panelFormulario.add(campoUsuario);
        panelFormulario.add(new JLabel("Contraseña:"));
        panelFormulario.add(campoContrasena);
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(botonProbar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add(panelPrincipal);

        // ActionListener del botón
        botonProbar.addActionListener(e -> ejecutarPrueba());
    }

    private void ejecutarPrueba() {
        String url = campoUrl.getText().trim();
        String usuario = campoUsuario.getText().trim();
        String contrasena = new String(campoContrasena.getPassword());

        ConexionBD conexionBD = new ConexionBD(url, usuario, contrasena);

        try {
            if (conexionBD.probarConexion()) {
                mostrarMensaje("Conexión exitosa a: " + url, new Color(0, 128, 0));
            }
        } catch (ClassNotFoundException ex) {
            mostrarMensaje("Fallo del driver: no se encontró el driver JDBC de MySQL.\n"
                    + ex.getMessage(), Color.RED);
        } catch (SQLException ex) {
            mostrarMensaje("Fallo de conexión.\nError técnico: " + ex.getMessage(), Color.RED);
        }
    }

    private void mostrarMensaje(String mensaje, Color color) {
        areaResultado.setForeground(color);
        areaResultado.setText(mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConexionTester().setVisible(true));
    }
}