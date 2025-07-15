
package BussinessObject;

import DataAccessObject.Detalle_Prestamo_DAO;
import TransferObject.Detalle_Prestamo_DTO;
import javax.swing.JOptionPane;


public class Detalle_Prestamo {
    Detalle_Prestamo_DTO detalle_prestamo_DTO;
    Detalle_Prestamo_DAO detalle_prestamo_DAO;
    
    public Detalle_Prestamo(){
        detalle_prestamo_DAO = new Detalle_Prestamo_DAO();
    }
    
    public int contar_total_libros(int id){
        return detalle_prestamo_DAO.contar_total_libros(id);
    }
    
    public boolean agregar(int id_prestamo, int id_libro, int cantidad){
        detalle_prestamo_DTO = new Detalle_Prestamo_DTO(id_prestamo,id_libro,cantidad);
        if (detalle_prestamo_DAO.agregar(detalle_prestamo_DTO)){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Error al añadir Detalle Préstamo", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
