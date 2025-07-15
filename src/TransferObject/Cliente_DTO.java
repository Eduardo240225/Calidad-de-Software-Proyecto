package TransferObject;

/**
 * Clase DTO (Data Transfer Object) que representa un cliente.
 * Contiene los atributos personales y de contacto del cliente,
 * y se utiliza para transportar datos entre capas de la aplicación.
 */
public class Cliente_DTO {

    /**
     * Identificador único del cliente.
     */
    private int id;

    /**
     * Documento Nacional de Identidad del cliente.
     */
    private String dni;

    /**
     * Nombre del cliente.
     */
    private String nombre;

    /**
     * Apellidos del cliente.
     */
    private String apellidos;

    /**
     * Sexo del cliente. Ejemplo: 'M' o 'F'.
     */
    private char sexo;

    /**
     * Número de teléfono del cliente.
     */
    private String telefono;

    /**
     * Correo electrónico del cliente.
     */
    private String correo;

    /**
     * Dirección del cliente.
     */
    private String direccion;

    /**
     * Constructor por defecto.
     * Requerido para crear instancias vacías del cliente.
     */
    public Cliente_DTO() {
        
    }

    /**
     * Constructor que inicializa todos los atributos del cliente.
     *
     * @param id        Identificador único del cliente.
     * @param dni       Documento Nacional de Identidad.
     * @param nombre    Nombre del cliente.
     * @param apellidos Apellidos del cliente.
     * @param sexo      Sexo del cliente ('M' o 'F').
     * @param telefono  Número de teléfono.
     * @param correo    Correo electrónico.
     * @param direccion Dirección física.
     */
    public Cliente_DTO(int id, String dni, String nombre, String apellidos, char sexo, String telefono, String correo, String direccion) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    /**
     * Constructor que inicializa solo el id del cliente.
     *
     * @param id        Identificador único del cliente.
     */ 
    public Cliente_DTO(int id) {
        this.id = id;
    }
    
    
    /**
     * Obtiene el ID del cliente.
     *
     * @return ID del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id Nuevo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el DNI del cliente.
     *
     * @return DNI del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del cliente.
     *
     * @param dni Nuevo DNI.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del cliente.
     *
     * @return Apellidos del cliente.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del cliente.
     *
     * @param apellidos Nuevos apellidos.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el sexo del cliente.
     *
     * @return Sexo del cliente.
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del cliente.
     *
     * @param sexo Nuevo sexo.
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene el teléfono del cliente.
     *
     * @return Teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     *
     * @param telefono Nuevo número telefónico.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return Correo del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param correo Nuevo correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la dirección del cliente.
     *
     * @return Dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     *
     * @param direccion Nueva dirección.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
