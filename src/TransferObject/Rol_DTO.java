package TransferObject;

/**
 * Clase DTO (Data Transfer Object) que representa un rol dentro del sistema.
 * Un rol está compuesto por un identificador único y una descripción.
 * 
 * Se utiliza para transportar la información del rol entre las capas de la aplicación.
 */
public class Rol_DTO {

    /**
     * Identificador único del rol.
     */
    private int id;

    /**
     * Descripción del rol (por ejemplo, "Administrador", "Bibliotecario").
     */
    private String descripcion;

    /**
     * Constructor vacío.
     * Útil para crear instancias vacías que serán inicializadas posteriormente.
     */
    public Rol_DTO() {
    }

    /**
     * Constructor que inicializa el ID y la descripción del rol.
     *
     * @param id          Identificador del rol.
     * @param descripcion Descripción textual del rol.
     */
    public Rol_DTO(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Constructor que inicializa solo la descripción del rol.
     *
     * @param descripcion Descripción textual del rol.
     */
    public Rol_DTO(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Constructor que inicializa solo el ID del rol.
     *
     * @param id Identificador del rol.
     */
    public Rol_DTO(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del rol.
     *
     * @return ID del rol.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del rol.
     *
     * @param id Nuevo ID del rol.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la descripción del rol.
     *
     * @return Descripción del rol.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del rol.
     *
     * @param descripcion Nueva descripción del rol.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
