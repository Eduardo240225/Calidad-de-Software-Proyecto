package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Inicio_Sesion_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase DAO (Data Access Object) que gestiona las operaciones relacionadas con el inicio de sesión.
 * Implementa operaciones básicas de CRUD y métodos específicos para autenticación y actualización de contraseña.
 */
public class Inicio_Sesion_DAO implements CRUD<Inicio_Sesion_DTO> {

    /** Objeto para ejecutar sentencias SQL. */
    PreparedStatement ps;

    /** Resultado de consultas SQL. */
    ResultSet rs;

    /** Objeto para manejar la conexión a la base de datos. */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     */
    public Inicio_Sesion_DAO() {
        conexion = new Conexion();
    }

    /**
     * Verifica las credenciales de inicio de sesión utilizando procedimiento almacenado.
     *
     * @param t DTO con el usuario y contraseña.
     * @return DTO con ID e ID de rol si las credenciales son válidas, de lo contrario null.
     */
    public Inicio_Sesion_DTO iniciar_sesion(Inicio_Sesion_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Verificar_Inicio_Sesion @usuario=?, @contraseña=?;");
            ps.setString(1, t.getUsuario());
            ps.setString(2, t.getContraseña());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setId_rol(rs.getInt(2));
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
     * Verifica si la contraseña actual de un usuario es válida.
     *
     * @param t DTO con el ID del usuario y la contraseña actual.
     * @return true si la contraseña es correcta, false en caso contrario.
     */
    public boolean verificar_contraseña_actual(Inicio_Sesion_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Verificar_Contraseña_Actual ?, ?;");
            ps.setInt(1, t.getId());
            ps.setString(2, t.getContraseña());
            rs = ps.executeQuery();
            if (rs.next()) {
                encontrado = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return encontrado;
    }

    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param t DTO con el ID del usuario y la nueva contraseña.
     * @return true si la contraseña fue actualizada correctamente, false en caso contrario.
     */
    public boolean actualizar_contraseña(Inicio_Sesion_DTO t) {
        int r = 0;
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Actualizar_Contraseña ?, ?;");
            ps.setInt(1, t.getId());
            ps.setString(2, t.getContraseña());
            r = ps.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Busca un registro de inicio de sesión por ID.
     *
     * @param t DTO con el ID a buscar.
     * @return DTO completo si se encuentra, null si no existe.
     */
    public Inicio_Sesion_DTO buscar_por_id(Inicio_Sesion_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Buscar_Por_Id ?;");
            ps.setInt(1, t.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setId_rol(rs.getInt(2));
                t.setUsuario(rs.getString(3));
                t.setContraseña(rs.getString(4));
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
     * Agrega un nuevo registro de inicio de sesión.
     *
     * @param t DTO con los datos del nuevo usuario (rol, usuario, contraseña).
     * @return true si se insertó correctamente, false si ocurrió un error.
     */
    @Override
    public boolean agregar(Inicio_Sesion_DTO t) {
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Agregar_Inicio_Sesion ?, ?, ?;");
            ps.setInt(1, t.getId_rol());
            ps.setString(2, t.getUsuario());
            ps.setString(3, t.getContraseña());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Actualiza un registro de inicio de sesión.
     *
     * @param t DTO con los nuevos datos (ID, rol, usuario y contraseña).
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    @Override
    public boolean actualizar(Inicio_Sesion_DTO t) {
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Actualizar_Inicio_Sesion ?, ?, ?, ?;");
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getId_rol());
            ps.setString(3, t.getUsuario());
            ps.setString(4, t.getContraseña());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Elimina un registro de inicio de sesión.
     *
     * @param t DTO con el ID del usuario a eliminar.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    @Override
    public boolean eliminar(Inicio_Sesion_DTO t) {
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Eliminar_Inicio_Sesion ?;");
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
     * Método no implementado. Listado general de sesiones.
     *
     * @return Lista de Inicio_Sesion_DTO.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public List<Inicio_Sesion_DTO> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método no implementado. Búsqueda avanzada de sesiones.
     *
     * @param t Texto a buscar.
     * @return Lista de resultados coincidentes.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public List<Inicio_Sesion_DTO> buscar(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
