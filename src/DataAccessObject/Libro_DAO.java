package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Libro_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO que gestiona el acceso a los datos de los libros.
 * Implementa operaciones CRUD usando procedimientos almacenados o consultas SQL directas.
 */
public class Libro_DAO implements CRUD<Libro_DTO> {

    /** Objeto para preparar sentencias SQL. */
    PreparedStatement ps;

    /** Resultado de consultas SQL. */
    ResultSet rs;

    /** Conexión a la base de datos. */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión.
     */
    public Libro_DAO() {
        conexion = new Conexion();
    }

    /**
     * Agrega un nuevo libro a la base de datos (no implementado).
     *
     * @param t DTO del libro con los datos a insertar.
     * @return true si se agregó correctamente.
     * @throws UnsupportedOperationException Método no implementado aún.
     */
    @Override
    public boolean agregar(Libro_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Actualiza los datos de un libro existente (no implementado).
     *
     * @param t DTO con los nuevos datos del libro.
     * @return true si se actualizó correctamente.
     * @throws UnsupportedOperationException Método no implementado aún.
     */
    @Override
    public boolean actualizar(Libro_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Elimina un libro de la base de datos (no implementado).
     *
     * @param t DTO con el ID del libro a eliminar.
     * @return true si se eliminó correctamente.
     * @throws UnsupportedOperationException Método no implementado aún.
     */
    @Override
    public boolean eliminar(Libro_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Busca libros según un texto determinado utilizando un procedimiento almacenado.
     *
     * @param t Texto de búsqueda.
     * @return Lista de libros que coinciden con la búsqueda.
     */
    @Override
    public List<Libro_DTO> buscar(String t) {
        List<Libro_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Buscar_Libro ?;");
            ps.setString(1, t);
            rs = ps.executeQuery();

            while (rs.next()) {
                Libro_DTO u = new Libro_DTO();
                u.setId(rs.getInt(1));
                u.setId_categoria(rs.getInt(2));
                u.setId_editorial(rs.getInt(3));
                u.setTitulo(rs.getString(4));
                u.setAño_publicacion(rs.getInt(5));
                u.setStock(rs.getInt(6));
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
     * Lista todos los libros registrados en la base de datos.
     *
     * @return Lista completa de libros.
     */
    @Override
    public List<Libro_DTO> listar() {
        List<Libro_DTO> lista = new ArrayList<>();

        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM LIBRO;");
            rs = ps.executeQuery();

            while (rs.next()) {
                Libro_DTO u = new Libro_DTO();
                u.setId(rs.getInt(1));
                u.setId_categoria(rs.getInt(2));
                u.setId_editorial(rs.getInt(3));
                u.setTitulo(rs.getString(4));
                u.setAño_publicacion(rs.getInt(5));
                u.setStock(rs.getInt(6));
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
     * Lista libros según un filtro dinámico aplicado a una columna específica,
     * usando un procedimiento almacenado.
     *
     * @param busqueda Texto de búsqueda a filtrar.
     * @param encabezado Nombre de la columna a aplicar el filtro.
     * @return Lista de libros filtrados por la columna especificada.
     */
    public List<Libro_DTO> listar_encabezado(String busqueda, String encabezado) {
        List<Libro_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Listar_Encabezados_Libro ?, ?;");
            ps.setString(1, busqueda);
            ps.setString(2, encabezado);
            rs = ps.executeQuery();

            while (rs.next()) {
                Libro_DTO u = new Libro_DTO();
                u.setId(rs.getInt(1));
                u.setId_categoria(rs.getInt(2));
                u.setId_editorial(rs.getInt(3));
                u.setTitulo(rs.getString(4));
                u.setAño_publicacion(rs.getInt(5));
                u.setStock(rs.getInt(6));
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
