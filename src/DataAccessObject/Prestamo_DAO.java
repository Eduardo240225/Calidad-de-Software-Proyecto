/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessObject;

import DataSource.Conexion;
import TransferObject.Prestamo_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author JHOEL
 */
public class Prestamo_DAO implements CRUD<Prestamo_DTO> {

    /** Permite ejecutar instrucciones SQL (INSERT, UPDATE, DELETE). */
    PreparedStatement ps;

    /** Almacena los resultados de consultas (SELECT). */
    ResultSet rs;

    /** Objeto de conexión a la base de datos. */
    Conexion conexion;

    /**
     * Constructor que inicializa la conexión con la base de datos.
     */
    public Prestamo_DAO(){
        conexion = new Conexion();
    }
    
    @Override
    public boolean agregar(Prestamo_DTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int agregar_prestamo(Prestamo_DTO t){
        int id_generado;
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Agregar_Prestamo ?, ?, ?, ?, ?;");
            ps.setInt(1, t.getId_bibliotecario());
            ps.setInt(2, t.getId_cliente());
            ps.setString(3, t.getFecha_prestamo());
            ps.setString(4, t.getEstado());
            ps.setString(5, t.getFecha_max_devolucion());
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
               return id_generado = rs.getInt(1);
            }
            
            return id_generado=0;
            
        } catch (SQLException ex) {
            System.out.println("Error al agregar préstamo: " + ex.getMessage());
            return id_generado=0;
        } finally {
            conexion.desconectar();
        }
    }
    
    @Override
    public boolean actualizar(Prestamo_DTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Prestamo_DTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Prestamo_DTO> buscar(String t) {
        List<Prestamo_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("EXEC Proc_Buscar_Prestamos ?;");
            ps.setString(1, t);
            rs = ps.executeQuery();
            while (rs.next()) {
                Prestamo_DTO u = new Prestamo_DTO();
                u.setId(rs.getInt(1));
                u.setId_bibliotecario(rs.getInt(2));
                u.setId_cliente(rs.getInt(3));
                u.setFecha_prestamo(rs.getString(4));
                u.setEstado(rs.getString(5));
                u.setFecha_max_devolucion(rs.getString(6));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }

    @Override
    public List<Prestamo_DTO> listar() {
        List<Prestamo_DTO> lista = new ArrayList<>();
        try {
            ps = conexion.conectar().prepareStatement("SELECT * FROM Vista_Lista_Prestamos;");
            rs = ps.executeQuery();
            while (rs.next()) {
                Prestamo_DTO u = new Prestamo_DTO();
                u.setId(rs.getInt(1));
                u.setId_bibliotecario(rs.getInt(2));
                u.setId_cliente(rs.getInt(3));
                u.setFecha_prestamo(rs.getString(4));
                u.setEstado(rs.getString(5));
                u.setFecha_max_devolucion(rs.getString(6));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }
    
}
