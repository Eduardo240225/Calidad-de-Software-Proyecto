package BussinessObject;

import DataAccessObject.Rol_DAO;
import TransferObject.Rol_DTO;
import java.util.List;

/**
 * Clase de lógica de negocio encargada de gestionar las operaciones
 * relacionadas con los roles de usuario en el sistema.
 */
public class Rol {

    /** Objeto DTO que representa un rol. */
    private Rol_DTO rol_DTO;

    /** Objeto DAO que proporciona acceso a los datos de roles. */
    private Rol_DAO rol_DAO;

    /**
     * Constructor que inicializa el DAO para acceder a los datos de roles.
     */
    public Rol() {
        rol_DAO = new Rol_DAO();
    }

    /**
     * Busca un rol en la base de datos utilizando su ID.
     *
     * @param id Identificador único del rol.
     * @return Un objeto {@code Rol_DTO} si se encuentra el rol, {@code null} si no existe.
     */
    public Rol_DTO buscar_por_id(int id) {
        rol_DTO = rol_DAO.buscar_por_id(new Rol_DTO(id));
        return (rol_DTO != null) ? rol_DTO : null;
    }

    /**
     * Retorna una lista con todos los roles registrados en la base de datos.
     *
     * @return Lista de objetos {@code Rol_DTO}, o {@code null} si no se encontraron registros.
     */
    public List<Rol_DTO> listar() {
        List<Rol_DTO> lista = rol_DAO.listar();
        return (lista != null) ? lista : null;
    }

    /**
     * Busca un rol por su descripción en la base de datos.
     *
     * @param descripcion Descripción textual del rol (ej. "Administrador").
     * @return Objeto {@code Rol_DTO} si se encuentra el rol, {@code null} si no se encuentra.
     */
    public Rol_DTO buscar_por_descripcion(String descripcion) {
        rol_DTO = rol_DAO.buscar_por_descripcion(new Rol_DTO(descripcion));
        return (rol_DTO != null) ? rol_DTO : null;
    }
}
