package DataAccessObject;

import java.util.List;

/**
 * Interfaz genérica para definir operaciones CRUD (Crear, Leer, Actualizar, Eliminar).
 * 
 * @param <T> Tipo de objeto sobre el que se aplicarán las operaciones.
 */
public interface CRUD<T> {

    /**
     * Agrega un nuevo registro a la base de datos.
     *
     * @param t Objeto a agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean agregar(T t);

    /**
     * Actualiza un registro existente en la base de datos.
     *
     * @param t Objeto con los datos actualizados.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean actualizar(T t);

    /**
     * Elimina un registro de la base de datos.
     *
     * @param t Objeto que contiene el identificador del registro a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean eliminar(T t);

    /**
     * Busca registros que coincidan con un criterio de búsqueda.
     *
     * @param t Texto o palabra clave para buscar coincidencias.
     * @return Lista de objetos que cumplen con el criterio de búsqueda.
     */
    public List<T> buscar(String t);

    /**
     * Lista todos los registros disponibles en la base de datos.
     *
     * @return Lista de todos los objetos encontrados.
     */
    public List<T> listar();
}
