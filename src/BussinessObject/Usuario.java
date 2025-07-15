package BussinessObject;

import DataAccessObject.Usuario_DAO;
import TransferObject.Usuario_DTO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase de lógica de negocio que gestiona las operaciones relacionadas con los usuarios del sistema.
 */
public class Usuario {

    /** Objeto DTO que representa un usuario. */
    private Usuario_DTO usuario_DTO;

    /** Objeto DAO que permite el acceso a los datos de los usuarios. */
    private Usuario_DAO usuario_DAO;

    /**
     * Constructor que inicializa el objeto DAO para operar con los datos de usuario.
     */
    public Usuario() {
        usuario_DAO = new Usuario_DAO();
    }

    /**
     * Busca un usuario según su ID de sesión.
     *
     * @param id_sesion ID de sesión del usuario.
     * @return Un objeto {@code Usuario_DTO} si se encuentra, o {@code null} si no existe.
     */
    public Usuario_DTO buscar_sesion(int id_sesion) {
        usuario_DTO = usuario_DAO.buscar_sesion(new Usuario_DTO(id_sesion));
        return (usuario_DTO != null) ? usuario_DTO : null;
    }

    /**
     * Retorna una lista con todos los usuarios registrados en el sistema.
     *
     * @return Lista de objetos {@code Usuario_DTO}, o {@code null} si no se encontraron registros.
     */
    public List<Usuario_DTO> listar() {
        return (usuario_DAO.listar() != null) ? usuario_DAO.listar() : null;
    }

    /**
     * Agrega un nuevo usuario con los datos proporcionados.
     *
     * @param id_sesion ID de sesión asociado.
     * @param dni Documento de identidad.
     * @param nombre Nombres del usuario.
     * @param apellidos Apellidos del usuario.
     * @param sexo Género del usuario.
     * @param telefono Número telefónico.
     * @param correo Correo electrónico.
     * @return {@code true} si el usuario se registró correctamente, {@code false} en caso de error.
     */
    public boolean agregar(int id_sesion, String dni, String nombre, String apellidos, char sexo, String telefono, String correo) {
        usuario_DTO = new Usuario_DTO(id_sesion, dni, nombre, apellidos, sexo, telefono, correo);
        if (usuario_DAO.agregar(usuario_DTO)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al añadir Usuario", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Busca un usuario por su ID único.
     *
     * @param id Identificador del usuario.
     * @return Un objeto {@code Usuario_DTO} si se encuentra, {@code null} si no existe.
     */
    public Usuario_DTO buscar_por_id(int id) {
        usuario_DTO = usuario_DAO.buscar_por_id(new Usuario_DTO(id));
        return (usuario_DTO != null) ? usuario_DTO : null;
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id ID del usuario.
     * @param id_sesion ID de sesión asociado.
     * @param dni Documento de identidad.
     * @param nombre Nombre del usuario.
     * @param apellidos Apellidos del usuario.
     * @param sexo Género del usuario.
     * @param telefono Teléfono del usuario.
     * @param correo Correo electrónico.
     * @return {@code true} si se actualizó correctamente, {@code false} si ocurrió un error.
     */
    public boolean actualizar(int id, int id_sesion, String dni, String nombre, String apellidos, char sexo, String telefono, String correo) {
        usuario_DTO = new Usuario_DTO(id, id_sesion, dni, nombre, apellidos, sexo, telefono, correo);
        if (usuario_DAO.actualizar(usuario_DTO)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar Usuario", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Elimina un usuario según su ID.
     *
     * @param id ID del usuario.
     * @return {@code true} si el usuario fue eliminado correctamente, {@code false} si no fue posible.
     */
    public boolean eliminar(int id) {
        usuario_DTO = new Usuario_DTO(id);
        if (usuario_DAO.eliminar(usuario_DTO)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Este Usuario no se puede eliminar", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Busca usuarios cuyo contenido coincida con el texto proporcionado.
     *
     * @param busqueda Texto de búsqueda (puede ser nombre, DNI, etc.).
     * @return Lista de coincidencias, o {@code null} si no se encontraron resultados.
     */
    public List<Usuario_DTO> buscar(String busqueda) {
        return (usuario_DAO.buscar(busqueda) != null) ? usuario_DAO.buscar(busqueda) : null;
    }

    /**
     * Lista usuarios filtrados por un campo específico (encabezado) y un texto de búsqueda.
     *
     * @param busqueda Texto de búsqueda.
     * @param encabezado Campo por el cual se filtra (por ejemplo, "nombre", "dni").
     * @return Lista de coincidencias filtradas, o {@code null} si no se encontraron resultados.
     */
    public List<Usuario_DTO> listar_encabezado(String busqueda, String encabezado) {
        return (usuario_DAO.listar_encabezado(busqueda, encabezado) != null)
                ? usuario_DAO.listar_encabezado(busqueda, encabezado)
                : null;
    }
}
