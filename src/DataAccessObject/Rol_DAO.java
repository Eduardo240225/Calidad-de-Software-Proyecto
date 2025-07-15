package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Rol_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO que permite gestionar los datos de los roles (ROL) en la base de datos.
 * Implementa la interfaz CRUD para operaciones estándar.
 */
public class Rol_DAO implements CRUD<Rol_DTO> {

    /** Objeto para ejecutar instrucciones SQL. */
    PreparedStatement ps;

    /** Resultado de las consultas SQL. */
    ResultSet rs;

    /** Objeto de conexión a la base de datos. */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión.
     */
    public Rol_DAO() {
        conexion = new Conexion();
    }

    /**
     * Busca un rol por su descripción exacta.
     *
     * @param t DTO que contiene la descripción a buscar.
     * @return El objeto Rol_DTO encontrado o null si no se encuentra.
     */
    public Rol_DTO buscar_por_descripcion(Rol_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM ROL WHERE descripcion = ?");
            ps.setString(1, t.getDescripcion());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setDescripcion(rs.getString(2));
                encontrado = true;
            }
            return encontrado ? t : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Busca un rol por su ID.
     *
     * @param t DTO con el ID del rol a buscar.
     * @return El objeto Rol_DTO encontrado o null si no existe.
     */
    public Rol_DTO buscar_por_id(Rol_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM ROL WHERE id = ?");
            ps.setInt(1, t.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setDescripcion(rs.getString(2));
                encontrado = true;
            }
            return encontrado ? t : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Lista todos los roles registrados en la base de datos.
     *
     * @return Lista de objetos Rol_DTO con todos los registros.
     */
    @Override
    public List<Rol_DTO> listar() {
        List<Rol_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM ROL");
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol_DTO r = new Rol_DTO();
                r.setId(rs.getInt(1));
                r.setDescripcion(rs.getString(2));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }

    /**
     * Método no implementado para agregar un nuevo rol.
     *
     * @param t Objeto Rol_DTO con los datos a agregar.
     * @return false por defecto.
     * @throws UnsupportedOperationException Indica que la funcionalidad no está desarrollada.
     */
    @Override
    public boolean agregar(Rol_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método no implementado para actualizar un rol existente.
     *
     * @param t Objeto Rol_DTO con los datos a actualizar.
     * @return false por defecto.
     * @throws UnsupportedOperationException Indica que la funcionalidad no está desarrollada.
     */
    @Override
    public boolean actualizar(Rol_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método no implementado para eliminar un rol.
     *
     * @param t Objeto Rol_DTO con el ID a eliminar.
     * @return false por defecto.
     * @throws UnsupportedOperationException Indica que la funcionalidad no está desarrollada.
     */
    @Override
    public boolean eliminar(Rol_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método no implementado para buscar roles por texto.
     *
     * @param t Texto de búsqueda.
     * @return null por defecto.
     * @throws UnsupportedOperationException Indica que la funcionalidad no está desarrollada.
     */
    @Override
    public List<Rol_DTO> buscar(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
