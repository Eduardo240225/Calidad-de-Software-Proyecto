package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Cliente_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 * Clase DAO (Data Access Object) para manejar operaciones relacionadas con la entidad Cliente.
 * Implementa operaciones CRUD básicas y consultas personalizadas hacia la base de datos.
 */
public class Cliente_DAO implements CRUD<Cliente_DTO> {

    /** Objeto para ejecutar sentencias SQL (INSERT, UPDATE, DELETE). */
    PreparedStatement ps;

    /** Objeto para almacenar resultados de consultas SQL (SELECT). */
    ResultSet rs;

    /** Objeto que gestiona la conexión con la base de datos. */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión con la base de datos.
     */
    public Cliente_DAO() {
        conexion = new Conexion();
    }

    /**
     * Método para agregar un nuevo cliente.
     * 
     * @param t Objeto Cliente_DTO con los datos del cliente a insertar.
     * @return true si se inserta correctamente, false en caso contrario.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public boolean agregar(Cliente_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método para actualizar los datos de un cliente existente.
     * 
     * @param t Objeto Cliente_DTO con los datos actualizados.
     * @return true si se actualiza correctamente, false en caso contrario.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public boolean actualizar(Cliente_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método para eliminar un cliente.
     * 
     * @param t Objeto Cliente_DTO con el ID del cliente a eliminar.
     * @return true si se elimina correctamente, false en caso contrario.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public boolean eliminar(Cliente_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Busca clientes por un texto dado usando un procedimiento almacenado.
     * 
     * @param t Texto de búsqueda (por ejemplo, nombre o DNI).
     * @return Lista de objetos Cliente_DTO que coincidan con la búsqueda.
     */
    @Override
    public List<Cliente_DTO> buscar(String t) {
        List<Cliente_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Buscar_Cliente ?;");
            ps.setString(1, t);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente_DTO u = new Cliente_DTO();
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellidos(rs.getString(4));
                u.setSexo(rs.getString(5).charAt(0));
                u.setTelefono(rs.getString(6));
                u.setCorreo(rs.getString(7));
                u.setDireccion(rs.getString(8));
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
     * Lista todos los clientes almacenados en la base de datos.
     * 
     * @return Lista completa de objetos Cliente_DTO.
     */
    @Override
    public List<Cliente_DTO> listar() {
        List<Cliente_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM CLIENTE;");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente_DTO u = new Cliente_DTO();
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellidos(rs.getString(4));
                u.setSexo(rs.getString(5).charAt(0));
                u.setTelefono(rs.getString(6));
                u.setCorreo(rs.getString(7));
                u.setDireccion(rs.getString(8));
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
     * Lista clientes aplicando un filtro de búsqueda y ordenamiento por encabezado.
     * 
     * Utiliza un procedimiento almacenado `Proc_Listar_Encabezados_Cliente`.
     * 
     * @param busqueda   Texto que será usado como filtro.
     * @param encabezado Nombre del campo por el cual se ordenará o filtrará.
     * @return Lista filtrada de objetos Cliente_DTO.
     */
    public List<Cliente_DTO> listar_encabezado(String busqueda, String encabezado) {
        List<Cliente_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Listar_Encabezados_Cliente ?, ?;");
            ps.setString(1, busqueda);
            ps.setString(2, encabezado);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente_DTO u = new Cliente_DTO();
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellidos(rs.getString(4));
                u.setSexo(rs.getString(5).charAt(0));
                u.setTelefono(rs.getString(6));
                u.setCorreo(rs.getString(7));
                u.setDireccion(rs.getString(8));
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }
    
    public Cliente_DTO buscar_por_id(Cliente_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM CLIENTE WHERE id = ?");
            ps.setInt(1, t.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setDni(rs.getString(2));
                t.setNombre(rs.getString(3));
                t.setApellidos(rs.getString(4));
                t.setSexo(rs.getString(5).charAt(0));
                t.setTelefono(rs.getString(6));
                t.setCorreo(rs.getString(7));
                t.setDireccion(rs.getString(8));
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
    
}
