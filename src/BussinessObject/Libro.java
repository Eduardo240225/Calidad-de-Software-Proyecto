package BussinessObject;

import DataAccessObject.Libro_DAO;
import TransferObject.Libro_DTO;
import java.util.List;

/**
 * Clase de lógica de negocio que gestiona las operaciones relacionadas
 * con los libros, tales como búsqueda, listado general o filtrado por encabezados.
 */
public class Libro {

    /** Objeto DTO que representa un libro. */
    private Libro_DTO libro_DTO;

    /** Objeto DAO para acceder a la base de datos. */
    private Libro_DAO libro_DAO;

    /**
     * Constructor que inicializa el objeto DAO para comunicación con la base de datos.
     */
    public Libro() {
        libro_DAO = new Libro_DAO();
    }

    /**
     * Retorna una lista con todos los libros registrados en la base de datos.
     *
     * @return Lista de libros si existen registros, null si no hay resultados.
     */
    public List<Libro_DTO> listar() {
        List<Libro_DTO> lista = libro_DAO.listar();
        return (lista != null) ? lista : null;
    }

    /**
     * Busca libros que coincidan con un texto ingresado.
     *
     * @param busqueda Cadena de texto a buscar (título, autor, etc.).
     * @return Lista de coincidencias si existen, null en caso contrario.
     */
    public List<Libro_DTO> buscar(String busqueda) {
        List<Libro_DTO> lista = libro_DAO.buscar(busqueda);
        return (lista != null) ? lista : null;
    }

    /**
     * Lista libros aplicando un filtro según el encabezado de una columna (campo) específico.
     *
     * @param busqueda Valor que se desea buscar.
     * @param encabezado Campo o columna por el cual filtrar (ej. título, año, stock).
     * @return Lista de libros que coincidan con los criterios, null si no se encuentran coincidencias.
     */
    public List<Libro_DTO> listar_encabezado(String busqueda, String encabezado) {
        List<Libro_DTO> lista = libro_DAO.listar_encabezado(busqueda, encabezado);
        return (lista != null) ? lista : null;
    }
}
