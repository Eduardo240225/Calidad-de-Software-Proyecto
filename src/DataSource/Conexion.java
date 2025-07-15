package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase responsable de gestionar la conexión con la base de datos SQL Server.
 * <p>
 * Implementa métodos para establecer y cerrar conexiones, utilizando JDBC.
 * </p>
 * <p><strong>Base de datos:</strong> bd_biblioteca<br>
 * <strong>Servidor:</strong> DESKTOP-579H60A<br>
 * <strong>Puerto:</strong> 1433<br>
 * <strong>Seguridad:</strong> encrypt=true; trustServerCertificate=true
 * </p>
 */
public class Conexion {

    /** URL de conexión a la base de datos */
    private String url = "jdbc:sqlserver://DESKTOP-579H60A:1433;"
            + "databaseName=bd_biblioteca;encrypt=true;trustServerCertificate=true;";
    
    /** Usuario con permisos sobre la base de datos */
    private String usuario = "sa";

    /** Contraseña correspondiente al usuario */
    private String password = "12345";

    /** Objeto que representa la conexión activa con la base de datos */
    private Connection conex;

    /**
     * Constructor por defecto.
     * <p>No realiza ninguna acción al ser instanciado.</p>
     */
    public Conexion() {
    }

    /**
     * Establece una conexión con la base de datos.
     * <p>
     * Carga el controlador JDBC de SQL Server y utiliza {@code DriverManager}
     * para obtener una instancia de {@code Connection}.
     * </p>
     * @return {@code Connection} objeto activo si la conexión fue exitosa;
     *         {@code null} si ocurre algún error.
     */
    public Connection conectar() {
        try {
            // Cargar el driver JDBC de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establecer conexión
            conex = DriverManager.getConnection(url, usuario, password);

        } catch (ClassNotFoundException | SQLException ex) {
            // Mostrar mensaje en caso de error
            JOptionPane.showMessageDialog(null, "Error en la conexión de la base de datos: " + ex.getMessage());
            return null;
        }
        return conex;
    }

    /**
     * Cierra la conexión activa con la base de datos si existe y no está cerrada.
     * <p>
     * Captura excepciones y muestra un mensaje si ocurre un error durante el cierre.
     * </p>
     */
    public void desconectar() {
        if (conex != null) {
            try {
                if (!conex.isClosed()) {
                    conex.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al desconectar la base de datos");
            }
        }
    }
}
