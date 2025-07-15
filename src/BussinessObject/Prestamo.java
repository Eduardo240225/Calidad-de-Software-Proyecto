
package BussinessObject;

import DataAccessObject.Prestamo_DAO;
import TransferObject.Prestamo_DTO;
import java.util.List;
import javax.swing.JOptionPane;


public class Prestamo {
    Prestamo_DTO prestamo_DTO;
    Prestamo_DAO prestamo_DAO;
    
    public Prestamo(){
        prestamo_DAO = new Prestamo_DAO();
    }
    
    
    public List<Prestamo_DTO> listar(){
        if (prestamo_DAO.listar() != null) {
            return prestamo_DAO.listar(); // llamada duplicada
        } else {
            return null;
        }
    }
    
    public int agregar(int id_bibliotecario, int id_cliente, String fecha_prestamo, String estado, String fecha_max_devolucion) {
        prestamo_DTO = new Prestamo_DTO(id_bibliotecario,id_cliente,fecha_prestamo,estado,fecha_max_devolucion);
        return prestamo_DAO.agregar_prestamo(prestamo_DTO);
    }
    
    public List<Prestamo_DTO> buscar(String busqueda) {
        return (prestamo_DAO.buscar(busqueda) != null) ? prestamo_DAO.buscar(busqueda) : null;
    }

    /**
     * Lista usuarios filtrados por un campo específico (encabezado) y un texto de búsqueda.
     *
     * @param busqueda Texto de búsqueda.
     * @param encabezado Campo por el cual se filtra (por ejemplo, "nombre", "dni").
     * @return Lista de coincidencias filtradas, o {@code null} si no se encontraron resultados.
     */
//    public List<Usuario_DTO> listar_encabezado(String busqueda, String encabezado) {
//        return (usuario_DAO.listar_encabezado(busqueda, encabezado) != null)
//                ? usuario_DAO.listar_encabezado(busqueda, encabezado)
//                : null;
//    }
    
}
