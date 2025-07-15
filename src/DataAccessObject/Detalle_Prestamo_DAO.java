/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Detalle_Prestamo_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author JHOEL
 */
public class Detalle_Prestamo_DAO implements CRUD<Detalle_Prestamo_DTO>{

    /** Permite ejecutar instrucciones SQL (INSERT, UPDATE, DELETE). */
    PreparedStatement ps;

    /** Almacena los resultados de consultas (SELECT). */
    ResultSet rs;

    /** Objeto de conexión a la base de datos. */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión con la base de datos.
     */
    public Detalle_Prestamo_DAO(){
        conexion = new Conexion();
    }
    
    
    @Override
    public boolean agregar(Detalle_Prestamo_DTO t) {
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Agregar_Detalle_Prestamo ?, ?, ?;");
            ps.setInt(1, t.getId_prestamo());
            ps.setInt(2, t.getId_libro());
            ps.setInt(3, t.getCantidad());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public boolean actualizar(Detalle_Prestamo_DTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Detalle_Prestamo_DTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Detalle_Prestamo_DTO> buscar(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Detalle_Prestamo_DTO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int contar_total_libros(int id){
        int total = 0;
        try {
            ps = conexion.conectar().prepareStatement("SELECT COUNT(*) AS Total_Libros_Prestados FROM DETALLE_PRESTAMO WHERE id_prestamo=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        finally {
            conexion.desconectar();
        }
        return total;
    }
    
}
