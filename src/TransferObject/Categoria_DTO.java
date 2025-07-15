package TransferObject;

/**
 * Clase DTO (Data Transfer Object) que representa una categoría de libros u objetos.
 * Contiene los atributos esenciales para identificar y describir una categoría,
 * y se utiliza para transferir datos entre capas del sistema.
 */
public class Categoria_DTO {

    /**
     * Identificador único de la categoría.
     */
    private int id;

    /**
     * Descripción textual de la categoría.
     */
    private String descripcion;

    /**
     * Constructor que inicializa solo el ID de la categoría.
     *
     * @param id Identificador único de la categoría.
     */
    public Categoria_DTO(int id) {
        this.id = id;
    }

    /**
     * Constructor que inicializa el ID y la descripción de la categoría.
     *
     * @param id          Identificador único de la categoría.
     * @param descripcion Descripción textual de la categoría.
     */
    public Categoria_DTO(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el ID de la categoría.
     *
     * @return ID de la categoría.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la categoría.
     *
     * @param id Nuevo ID de la categoría.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la descripción de la categoría.
     *
     * @return Descripción de la categoría.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la categoría.
     *
     * @param descripcion Nueva descripción de la categoría.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
