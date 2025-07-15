package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Categoria_DTO;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase DAO (Data Access Object) para la entidad Categoría.
 * Proporciona métodos para acceder y manipular los datos de categorías
 * en la base de datos.
 */
public class Categoria_DAO implements CRUD<Categoria_DTO> {

    /**
     * Objeto para ejecutar sentencias SQL (INSERT, UPDATE, DELETE).
     */
    PreparedStatement ps;

    /**
     * Objeto para almacenar resultados de consultas SQL (SELECT).
     */
    ResultSet rs;

    /**
     * Objeto que gestiona la conexión con la base de datos.
     */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     */
    public Categoria_DAO() {
        conexion = new Conexion();
    }

    /**
     * Agrega una nueva categoría a la base de datos.
     * 
     * @param t Objeto Categoria_DTO con los datos a insertar.
     * @return true si se insertó correctamente, false en caso contrario.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public boolean agregar(Categoria_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Actualiza una categoría existente en la base de datos.
     * 
     * @param t Objeto Categoria_DTO con los nuevos datos.
     * @return true si se actualizó correctamente, false en caso contrario.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public boolean actualizar(Categoria_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Elimina una categoría de la base de datos.
     * 
     * @param t Objeto Categoria_DTO con el ID de la categoría a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public boolean eliminar(Categoria_DTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Busca categorías en la base de datos según un texto dado.
     * 
     * @param t Texto de búsqueda.
     * @return Lista de objetos Categoria_DTO que coincidan con el criterio.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public List<Categoria_DTO> buscar(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Lista todas las categorías registradas en la base de datos.
     * 
     * @return Lista de objetos Categoria_DTO.
     * @throws UnsupportedOperationException Método aún no implementado.
     */
    @Override
    public List<Categoria_DTO> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Busca una categoría por su ID.
     * 
     * @param t Objeto Categoria_DTO que contiene el ID de la categoría.
     * @return El mismo objeto con los datos encontrados o null si no existe.
     */
    public Categoria_DTO buscar_por_id(Categoria_DTO t) {
        boolean encontrado = false;
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM CATEGORIA WHERE id=?");
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
}
