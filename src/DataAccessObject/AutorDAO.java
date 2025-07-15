package DataAccessObject;

import DataSource.Conexion;
import TransferObject.AutorDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para la entidad Autor.
 * Proporciona métodos CRUD y operaciones adicionales para interactuar
 * con la base de datos en relación a los autores.
 */
public class AutorDAO implements CRUD<AutorDTO> {

    /**
     * Objeto para ejecutar sentencias SQL (INSERT, UPDATE, DELETE).
     */
    PreparedStatement ps;

    /**
     * Objeto para almacenar los resultados de consultas SQL (SELECT).
     */
    ResultSet rs;

    /**
     * Objeto que gestiona la conexión con la base de datos.
     */
    Conexion conexion;

    /**
     * Constructor de la clase. Inicializa la conexión a la base de datos.
     */
    public AutorDAO() {
        conexion = new Conexion();
    }

    /**
     * Agrega un nuevo autor a la base de datos.
     *
     * @param t Objeto AutorDTO con los datos del autor.
     * @return true si se insertó correctamente, false en caso contrario.
     */
    @Override
    public boolean agregar(AutorDTO t) {
        int r = 0;
        try {
            ps = conexion.conectar().prepareStatement(
                "INSERT INTO autor(nombre, apellidos, sexo, nacionalidad) VALUES(?,?,?,?)"
            );
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getApellidos());
            ps.setString(3, t.getSexo());
            ps.setString(4, t.getNacionalidad());
            r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Actualiza los datos de un autor existente en la base de datos.
     *
     * @param t Objeto AutorDTO con los nuevos datos del autor.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    @Override
    public boolean actualizar(AutorDTO t) {
        int r = 0;
        try {
            ps = conexion.conectar().prepareStatement(
                "UPDATE autor SET nombre=?, apellidos=?, sexo=?, nacionalidad=? WHERE id=?"
            );
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getApellidos());
            ps.setString(3, t.getSexo());
            ps.setString(4, t.getNacionalidad());
            ps.setInt(5, t.getIdautor());

            r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Elimina un autor de la base de datos.
     *
     * @param t Objeto AutorDTO con el ID del autor a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    @Override
    public boolean eliminar(AutorDTO t) {
        int r = 0;
        try {
            ps = conexion.conectar().prepareStatement(
                "DELETE FROM autor WHERE id=?"
            );
            ps.setInt(1, t.getIdautor());
            r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    /**
     * Busca un autor por su ID.
     *
     * @param t Objeto AutorDTO con el ID del autor.
     * @return Objeto AutorDTO con los datos encontrados o null si no existe.
     */
    public AutorDTO buscar(AutorDTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement(
                "SELECT * FROM autor WHERE id=?"
            );
            ps.setInt(1, t.getIdautor());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setIdautor(rs.getInt(1));
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
     * Obtiene el ID de un autor a partir de su nombre.
     *
     * @param t Objeto AutorDTO con el nombre del autor.
     * @return El mismo objeto con el ID asignado si se encuentra; de lo contrario, null.
     */
    public AutorDTO obtenerIdAutor(AutorDTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement(
                "SELECT id FROM autor WHERE nombre=?"
            );
            ps.setString(1, t.getNombre());
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setIdautor(rs.getInt(1));
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
     * Lista todos los autores almacenados en la base de datos.
     *
     * @return Lista de objetos AutorDTO.
     */
    public List<AutorDTO> listar() {
        List<AutorDTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM autor");
            rs = ps.executeQuery();
            while (rs.next()) {
                AutorDTO t = new AutorDTO();
                t.setIdautor(rs.getInt(1));
                t.setNombre(rs.getString(2));
                t.setApellidos(rs.getString(3));
                t.setSexo(rs.getString(4));
                t.setNacionalidad(rs.getString(5));
                lista.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }

    /**
     * Método aún no implementado para buscar autores por texto.
     *
     * @param t Texto de búsqueda.
     * @return Lista de AutorDTO (no implementado).
     */
    @Override
    public List<AutorDTO> buscar(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Busca y concatena los nombres completos de los autores de un libro específico.
     *
     * @param id_libro ID del libro.
     * @return Objeto AutorDTO con el nombre concatenado de los autores o null si no se encuentra.
     */
    public AutorDTO buscar_autores_por_libro(int id_libro) {
        boolean encontrado = false;
        AutorDTO t = new AutorDTO();
        try {
            ps = conexion.conectar().prepareStatement(
                "SELECT STRING_AGG(CONCAT(a.nombre, ' ', a.apellidos), ', ') AS Autor " +
                "FROM DETALLE_AUTOR da JOIN AUTOR a ON da.id_autor = a.id " +
                "WHERE da.id_libro = ?"
            );
            ps.setInt(1, id_libro);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setNombre(rs.getString(1));
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
