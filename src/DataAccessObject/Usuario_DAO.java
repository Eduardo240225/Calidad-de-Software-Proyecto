package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Usuario_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para la gestión de usuarios.
 * Implementa operaciones CRUD y consultas adicionales utilizando procedimientos almacenados.
 */
public class Usuario_DAO implements CRUD<Usuario_DTO> {

    /** Permite ejecutar instrucciones SQL (INSERT, UPDATE, DELETE). */
    PreparedStatement ps;

    /** Almacena los resultados de consultas (SELECT). */
    ResultSet rs;

    /** Objeto de conexión a la base de datos. */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión con la base de datos.
     */
    public Usuario_DAO() {
        conexion = new Conexion();
    }

    /**
     * Agrega un nuevo usuario mediante procedimiento almacenado.
     *
     * @param t Objeto Usuario_DTO con los datos del nuevo usuario.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    @Override
    public boolean agregar(Usuario_DTO t) {
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Agregar_Usuario ?, ?, ?, ?, ?, ?, ?;");
            ps.setInt(1, t.getId_sesion());
            ps.setString(2, t.getDni());
            ps.setString(3, t.getNombre());
            ps.setString(4, t.getApellidos());
            ps.setString(5, String.valueOf(t.getSexo()));
            ps.setString(6, t.getTelefono());
            ps.setString(7, t.getCorreo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param t Objeto Usuario_DTO con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    @Override
    public boolean actualizar(Usuario_DTO t) {
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Actualizar_Usuario ?, ?, ?, ?, ?, ?, ?, ?;");
            ps.setInt(1, t.getId_sesion());
            ps.setString(2, t.getDni());
            ps.setString(3, t.getNombre());
            ps.setString(4, t.getApellidos());
            ps.setString(5, String.valueOf(t.getSexo()));
            ps.setString(6, t.getTelefono());
            ps.setString(7, t.getCorreo());
            ps.setInt(8, t.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param t Objeto Usuario_DTO con el ID del usuario a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    @Override
    public boolean eliminar(Usuario_DTO t) {
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Eliminar_Usuario ?;");
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Lista todos los usuarios desde una vista.
     *
     * @return Lista de objetos Usuario_DTO.
     */
    @Override
    public List<Usuario_DTO> listar() {
        List<Usuario_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM Vista_Lista_Usuarios;");
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario_DTO u = new Usuario_DTO();
                u.setId(rs.getInt(1));
                u.setId_sesion(rs.getInt(2));
                u.setDni(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellidos(rs.getString(5));
                u.setSexo(rs.getString(6).charAt(0));
                u.setTelefono(rs.getString(7));
                u.setCorreo(rs.getString(8));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }

    /**
     * Busca los datos de un usuario por su ID de sesión.
     *
     * @param t DTO con el ID de sesión.
     * @return Objeto Usuario_DTO encontrado o null si no existe.
     */
    public Usuario_DTO buscar_sesion(Usuario_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Obtener_Datos_Usuarios_Por_Sesion ?;");
            ps.setInt(1, t.getId_sesion());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setNombre(rs.getString(2));
                t.setApellidos(rs.getString(3));
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
     * Obtiene el ID de sesión a partir del ID del usuario.
     *
     * @param t DTO con el ID del usuario.
     * @return Objeto Usuario_DTO con ID de sesión, o null si no se encuentra.
     */
    public Usuario_DTO buscar_por_id(Usuario_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Obtener_Sesion_Por_Usuario ?;");
            ps.setInt(1, t.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId_sesion(rs.getInt(1));
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
     * Busca usuarios por coincidencia textual usando un procedimiento almacenado.
     *
     * @param t Texto de búsqueda.
     * @return Lista de objetos Usuario_DTO coincidentes.
     */
    @Override
    public List<Usuario_DTO> buscar(String t) {
        List<Usuario_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Buscar_Usuarios ?;");
            ps.setString(1, t);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario_DTO u = new Usuario_DTO();
                u.setId(rs.getInt(1));
                u.setId_sesion(rs.getInt(2));
                u.setDni(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellidos(rs.getString(5));
                u.setSexo(rs.getString(6).charAt(0));
                u.setTelefono(rs.getString(7));
                u.setCorreo(rs.getString(8));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }

    /**
     * Lista usuarios filtrados por campo y contenido (encabezado y búsqueda).
     *
     * @param busqueda Texto de búsqueda.
     * @param encabezado Nombre del campo por el que se filtrará.
     * @return Lista de usuarios coincidentes.
     */
    public List<Usuario_DTO> listar_encabezado(String busqueda, String encabezado) {
        List<Usuario_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Listar_Encabezados_Usuario ?, ?;");
            ps.setString(1, busqueda);
            ps.setString(2, encabezado);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario_DTO u = new Usuario_DTO();
                u.setId(rs.getInt(1));
                u.setId_sesion(rs.getInt(2));
                u.setDni(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellidos(rs.getString(5));
                u.setSexo(rs.getString(6).charAt(0));
                u.setTelefono(rs.getString(7));
                u.setCorreo(rs.getString(8));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }
}
