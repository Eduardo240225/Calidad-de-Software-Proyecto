package BussinessObject;

import DataAccessObject.Cliente_DAO;
import TransferObject.Cliente_DTO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase Cliente (BO - Business Object) que encapsula la lógica de negocio
 * relacionada con la gestión de clientes. Interactúa con la capa DAO para
 * acceder a los datos en la base de datos.
 */
public class Cliente {

    /** Objeto de transferencia de datos (DTO) que representa un cliente. */
    private Cliente_DTO cliente_DTO;

    /** Objeto de acceso a datos (DAO) que maneja las operaciones CRUD para clientes. */
    private Cliente_DAO cliente_DAO;

    /**
     * Constructor que inicializa el DAO de cliente para realizar operaciones
     * sobre la base de datos.
     */
    public Cliente() {
        cliente_DAO = new Cliente_DAO();
    }

    /**
     * Obtiene una lista con todos los clientes registrados en la base de datos.
     *
     * @return Lista de objetos Cliente_DTO o null si no se encontraron resultados.
     */
    public List<Cliente_DTO> listar() {
        return cliente_DAO.listar() != null ? cliente_DAO.listar() : null;
    }

    /**
     * Busca clientes en la base de datos cuyo contenido coincida con el texto de búsqueda.
     *
     * @param busqueda Cadena de texto para buscar coincidencias.
     * @return Lista de coincidencias encontradas o null si no se encontraron.
     */
    public List<Cliente_DTO> buscar(String busqueda) {
        return cliente_DAO.buscar(busqueda) != null ? cliente_DAO.buscar(busqueda) : null;
    }

    /**
     * Lista los clientes filtrados por un campo específico (encabezado) y un valor de búsqueda.
     *
     * @param busqueda Texto de búsqueda a utilizar como filtro.
     * @param encabezado Campo o columna sobre la cual se filtrará (por ejemplo: "dni", "nombre").
     * @return Lista de coincidencias encontradas o null si no se encontraron resultados.
     */
    public List<Cliente_DTO> listar_encabezado(String busqueda, String encabezado) {
        return cliente_DAO.listar_encabezado(busqueda, encabezado) != null
                ? cliente_DAO.listar_encabezado(busqueda, encabezado)
                : null;
    }
    
    public Cliente_DTO buscar_por_id(int id) {
        cliente_DTO = cliente_DAO.buscar_por_id(new Cliente_DTO(id));
        if (cliente_DTO != null){
            return cliente_DTO;
        }else{
            return null;
        }
    }
}
