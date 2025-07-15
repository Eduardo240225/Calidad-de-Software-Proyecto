package TransferObject;

/**
 * Clase DTO (Data Transfer Object) que representa los datos de inicio de sesión
 * de un usuario del sistema, incluyendo identificador, rol, nombre de usuario y contraseña.
 * 
 * Se utiliza para transportar los datos entre la capa de presentación, lógica y persistencia.
 */
public class Inicio_Sesion_DTO {

    /**
     * Identificador único de la cuenta del Usuario.
     */
    private int id;

    /**
     * Identificador del rol asignado al usuario.
     */
    private int id_rol;

    /**
     * Nombre de usuario (login).
     */
    private String usuario;

    /**
     * Contraseña del usuario.
     */
    private String contraseña;

    /**
     * Constructor vacío.
     * Útil para crear objetos vacíos y luego asignar los valores.
     */
    public Inicio_Sesion_DTO() {
    }

    /**
     * Constructor que inicializa todos los atributos.
     *
     * @param id         Identificador del usuario.
     * @param id_rol     Identificador del rol.
     * @param usuario    Nombre de usuario.
     * @param contraseña Contraseña del usuario.
     */
    public Inicio_Sesion_DTO(int id, int id_rol, String usuario, String contraseña) {
        this.id = id;
        this.id_rol = id_rol;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    /**
     * Constructor que inicializa id_rol, usuario y contraseña.
     *
     * @param id_rol     Identificador del rol.
     * @param usuario    Nombre de usuario.
     * @param contraseña Contraseña del usuario.
     */
    public Inicio_Sesion_DTO(int id_rol, String usuario, String contraseña) {
        this.id_rol = id_rol;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    /**
     * Constructor que inicializa usuario y contraseña.
     *
     * @param usuario    Nombre de usuario.
     * @param contraseña Contraseña del usuario.
     */
    public Inicio_Sesion_DTO(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    /**
     * Constructor que inicializa id y contraseña.
     *
     * @param id         Identificador del usuario.
     * @param contraseña Contraseña del usuario.
     */
    public Inicio_Sesion_DTO(int id, String contraseña) {
        this.id = id;
        this.contraseña = contraseña;
    }

    /**
     * Constructor que inicializa solo el id del usuario.
     *
     * @param id Identificador del usuario.
     */
    public Inicio_Sesion_DTO(int id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id Nuevo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del rol asignado al usuario.
     *
     * @return ID del rol.
     */
    public int getId_rol() {
        return id_rol;
    }

    /**
     * Establece el identificador del rol asignado al usuario.
     *
     * @param id_rol Nuevo ID del rol.
     */
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario Nuevo nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contraseña Nueva contraseña.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
