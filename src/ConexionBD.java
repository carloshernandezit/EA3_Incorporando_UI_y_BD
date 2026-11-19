import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private final String url;
    private final String usuario;
    private final String contrasena;

    public ConexionBD(String url, String usuario, String contrasena) {
        this.url = url;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean probarConexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // try-with-resources: la conexión se cierra sola, funcione o falle
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            return conexion.isValid(5);
        }
    }
}