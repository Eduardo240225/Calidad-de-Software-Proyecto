package BussinessObject;

import DataAccessObject.Categoria_DAO;
import TransferObject.Categoria_DTO;

/**
 * Clase Categoria (BO - Business Object) que gestiona la lógica de negocio relacionada con las categorías de libros.
 * Se encarga de interactuar con la capa DAO para realizar operaciones sobre objetos Categoria_DTO.
 */
public class Categoria {

    /** Objeto de transferencia de datos para categoría */
    Categoria_DTO categoria_DTO;

    /** Objeto de acceso a datos para categoría */
    Categoria_DAO categoria_DAO;

    /**
     * Constructor que inicializa la instancia del DAO de categoría.
     */
    public Categoria() {
        categoria_DAO = new Categoria_DAO();
    }

    /**
     * Busca una categoría en la base de datos por su ID.
     *
     * @param id ID de la categoría a buscar.
     * @return Objeto Categoria_DTO con los datos encontrados o null si no existe.
     */
    public Categoria_DTO buscar_por_id(int id) {
        categoria_DTO = categoria_DAO.buscar_por_id(new Categoria_DTO(id));
        return categoria_DTO != null ? categoria_DTO : null;
    }
}
