
package TransferObject;


public class Prestamo_DTO {
    private int id;
    private int id_bibliotecario;
    private int id_cliente;
    private String fecha_prestamo;
    private String estado;
    private String fecha_max_devolucion;

    public Prestamo_DTO(){
        
    }
    
    public Prestamo_DTO(int id, int id_bibliotecario, int id_cliente, String fecha_prestamo, String estado, String fecha_max_devolucion) {
        this.id = id;
        this.id_bibliotecario = id_bibliotecario;
        this.id_cliente = id_cliente;
        this.fecha_prestamo = fecha_prestamo;
        this.estado = estado;
        this.fecha_max_devolucion = fecha_max_devolucion;
    }

    public Prestamo_DTO(int id_bibliotecario, int id_cliente, String fecha_prestamo, String estado, String fecha_max_devolucion) {
        this.id_bibliotecario = id_bibliotecario;
        this.id_cliente = id_cliente;
        this.fecha_prestamo = fecha_prestamo;
        this.estado = estado;
        this.fecha_max_devolucion = fecha_max_devolucion;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_bibliotecario() {
        return id_bibliotecario;
    }

    public void setId_bibliotecario(int id_bibliotecario) {
        this.id_bibliotecario = id_bibliotecario;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_max_devolucion() {
        return fecha_max_devolucion;
    }

    public void setFecha_max_devolucion(String fecha_max_devolucion) {
        this.fecha_max_devolucion = fecha_max_devolucion;
    }
    
    
}
