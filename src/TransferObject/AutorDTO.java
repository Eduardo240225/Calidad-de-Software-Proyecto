package TransferObject;

/**
 * Clase DTO (Data Transfer Object) que representa un autor.
 * Contiene los atributos básicos del autor y proporciona métodos
 * de acceso (getters y setters).
 * 
 * Se utiliza para transportar los datos entre capas del sistema.
 */
public class AutorDTO {

    /**
     * Identificador único del autor.
     */
    private int idautor;

    /**
     * Nombre del autor.
     */
    private String nombre;

    /**
     * Apellidos del autor.
     */
    private String apellidos;

    /**
     * Sexo del autor.
     */
    private String sexo;

    /**
     * Nacionalidad del autor.
     */
    private String nacionalidad;

    /**
     * Constructor vacío.
     * Requerido para inicializaciones sin parámetros.
     */
    public AutorDTO() {
    }

    /**
     * Constructor que inicializa los datos del autor sin ID.
     *
     * @param nombre        Nombre del autor.
     * @param apellidos     Apellidos del autor.
     * @param sexo          Sexo del autor.
     * @param nacionalidad  Nacionalidad del autor.
     */
    public AutorDTO(String nombre, String apellidos, String sexo, String nacionalidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Constructor que inicializa todos los campos del autor, incluyendo el ID.
     *
     * @param idautor       ID del autor.
     * @param nombre        Nombre del autor.
     * @param apellidos     Apellidos del autor.
     * @param sexo          Sexo del autor.
     * @param nacionalidad  Nacionalidad del autor.
     */
    public AutorDTO(int idautor, String nombre, String apellidos, String sexo, String nacionalidad) {
        this.idautor = idautor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Constructor que inicializa solo el ID del autor.
     *
     * @param idautor ID del autor.
     */
    public AutorDTO(int idautor) {
        this.idautor = idautor;
    }

    /**
     * Constructor que inicializa solo el nombre del autor.
     *
     * @param nombre Nombre del autor.
     */
    public AutorDTO(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el sexo del autor.
     *
     * @return Sexo del autor.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del autor.
     *
     * @param sexo Nuevo valor del sexo.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene la nacionalidad del autor.
     *
     * @return Nacionalidad del autor.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del autor.
     *
     * @param nacionalidad Nueva nacionalidad.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene el ID del autor.
     *
     * @return ID del autor.
     */
    public int getIdautor() {
        return idautor;
    }

    /**
     * Establece el ID del autor.
     *
     * @param idautor Nuevo ID.
     */
    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    /**
     * Obtiene el nombre del autor.
     *
     * @return Nombre del autor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del autor.
     *
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del autor.
     *
     * @return Apellidos del autor.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del autor.
     *
     * @param apellidos Nuevos apellidos.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
