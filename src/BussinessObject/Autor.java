package BussinessObject;

import DataAccessObject.AutorDAO;
import TransferObject.AutorDTO;
import java.util.List;

/**
 * Clase Autor (BO - Business Object) que representa la lógica de negocio para gestionar autores.
 * Utiliza un objeto AutorDAO para interactuar con la base de datos y aplicar reglas de negocio.
 */
public class Autor {

    /** Objeto de transferencia de datos del autor */
    private AutorDTO autorDTO;

    /** Objeto de acceso a datos para operaciones con la tabla AUTOR */
    private AutorDAO autorDAO;

    /**
     * Constructor que inicializa el DAO de autor.
     */
    public Autor() {
        autorDAO = new AutorDAO();
    }

    /**
     * Agrega un nuevo autor a la base de datos.
     *
     * @param nombre Nombre del autor.
     * @param apellidos Apellidos del autor.
     * @param sexo Sexo del autor (M/F).
     * @param nacionalidad Nacionalidad del autor.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String agregar(String nombre, String apellidos, String sexo, String nacionalidad) {
        String mensaje;
        autorDTO = new AutorDTO(nombre, apellidos, sexo, nacionalidad);
        if (autorDAO.agregar(autorDTO)) {
            mensaje = "El Autor se registró exitosamente";
        } else {
            mensaje = "Error, el Autor no se pudo registrar";
        }
        return mensaje;
    }

    /**
     * Actualiza los datos de un autor existente.
     *
     * @param idautor ID del autor.
     * @param nombres Nombres del autor.
     * @param apellidos Apellidos del autor.
     * @param sexo Sexo del autor.
     * @param nacionalidad Nacionalidad del autor.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String actualizar(int idautor, String nombres, String apellidos, String sexo, String nacionalidad) {
        String mensaje;
        autorDTO = new AutorDTO(idautor, nombres, apellidos, sexo, nacionalidad);
        if (autorDAO.actualizar(autorDTO)) {
            mensaje = "El Autor se actualizó exitosamente";
        } else {
            mensaje = "Error, el Autor no se pudo actualizar";
        }
        return mensaje;
    }

    /**
     * Elimina un autor por su ID.
     *
     * @param idautor ID del autor a eliminar.
     * @return Mensaje indicando si se eliminó correctamente.
     */
    public String eliminar(int idautor) {
        String mensaje;
        autorDTO = new AutorDTO(idautor);
        if (autorDAO.eliminar(autorDTO)) {
            mensaje = "El Autor se eliminó exitosamente";
        } else {
            mensaje = "Problemas para eliminar el Autor";
        }
        return mensaje;
    }

    /**
     * Busca un autor por su ID.
     *
     * @param idautor ID del autor a buscar.
     * @return Objeto AutorDTO si se encuentra, null en caso contrario.
     */
    public AutorDTO buscar(int idautor) {
        autorDTO = autorDAO.buscar(new AutorDTO(idautor));
        return autorDTO != null ? autorDTO : null;
    }

    /**
     * Obtiene el ID de un autor a partir de su nombre.
     *
     * @param nombre Nombre del autor.
     * @return Objeto AutorDTO con el ID, o null si no se encuentra.
     */
    public AutorDTO obtenerIdAutor(String nombre) {
        autorDTO = autorDAO.obtenerIdAutor(new AutorDTO(nombre));
        return autorDTO != null ? autorDTO : null;
    }

    /**
     * Lista todos los autores registrados.
     *
     * @return Lista de objetos AutorDTO o null si no hay datos.
     */
    public List<AutorDTO> listar() {
        List<AutorDTO> lista = autorDAO.listar();
        return lista != null ? lista : null;
    }

    /**
     * Busca el autor asociado a un libro específico por su ID.
     *
     * @param id_libro ID del libro.
     * @return Objeto AutorDTO con la información del autor, o null si no se encuentra.
     */
    public AutorDTO buscar_autores_por_libro(int id_libro) {
        autorDTO = autorDAO.buscar_autores_por_libro(id_libro);
        return autorDTO != null ? autorDTO : null;
    }
}
