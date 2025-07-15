package TransferObject;

/**
 * Clase DTO (Data Transfer Object) que representa un usuario del sistema.
 * Contiene los datos personales y de sesión asociados a un usuario.
 * 
 * Se utiliza para transportar datos entre capas, especialmente en procesos
 * de autenticación, registro y gestión de usuarios.
 */
public class Usuario_DTO {

    /**
     * Identificador único del usuario.
     */
    private int id;

    /**
     * Identificador de la sesión de inicio asociada al usuario.
     */
    private int id_sesion;

    /**
     * Documento Nacional de Identidad del usuario.
     */
    private String dni;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellidos del usuario.
     */
    private String apellidos;

    /**
     * Sexo del usuario. Ejemplo: 'M' o 'F'.
     */
    private char sexo;

    /**
     * Número telefónico del usuario.
     */
    private String telefono;

    /**
     * Correo electrónico del usuario.
     */
    private String correo;

    /**
     * Constructor vacío.
     * Útil para instanciación por defecto o asignación posterior de datos.
     */
    public Usuario_DTO() {
    }

    /**
     * Constructor que inicializa todos los atributos del usuario.
     *
     * @param id         Identificador único del usuario.
     * @param id_sesion  ID de la sesión de inicio de sesión asociada.
     * @param dni        Documento de identidad.
     * @param nombre     Nombre del usuario.
     * @param apellidos  Apellidos del usuario.
     * @param sexo       Sexo del usuario.
     * @param telefono   Número telefónico.
     * @param correo     Correo electrónico.
     */
    public Usuario_DTO(int id, int id_sesion, String dni, String nombre, String apellidos, char sexo, String telefono, String correo) {
        this.id = id;
        this.id_sesion = id_sesion;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Constructor que inicializa solo el ID de sesión y también lo asigna como ID del usuario.
     *
     * @param id_sesion ID de sesión del usuario.
     */
    public Usuario_DTO(int id_sesion) {
        this.id_sesion = id_sesion;
        this.id = id_sesion;
    }

    /**
     * Constructor que inicializa todos los atributos excepto el ID.
     *
     * @param id_sesion  ID de la sesión de inicio de sesión asociada.
     * @param dni        Documento de identidad.
     * @param nombre     Nombre del usuario.
     * @param apellidos  Apellidos del usuario.
     * @param sexo       Sexo del usuario.
     * @param telefono   Número telefónico.
     * @param correo     Correo electrónico.
     */
    public Usuario_DTO(int id_sesion, String dni, String nombre, String apellidos, char sexo, String telefono, String correo) {
        this.id_sesion = id_sesion;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id Nuevo ID del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID de sesión del usuario.
     *
     * @return ID de sesión.
     */
    public int getId_sesion() {
        return id_sesion;
    }

    /**
     * Establece el ID de sesión del usuario.
     *
     * @param id_sesion Nuevo ID de sesión.
     */
    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    /**
     * Obtiene el DNI del usuario.
     *
     * @return DNI del usuario.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del usuario.
     *
     * @param dni Nuevo DNI.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return Apellidos del usuario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del usuario.
     *
     * @param apellidos Nuevos apellidos.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el sexo del usuario.
     *
     * @return Sexo del usuario.
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del usuario.
     *
     * @param sexo Nuevo sexo.
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene el teléfono del usuario.
     *
     * @return Teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del usuario.
     *
     * @param telefono Nuevo número telefónico.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo Nuevo correo.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
