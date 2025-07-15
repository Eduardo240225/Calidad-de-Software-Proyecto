
package Presentacion;

import BussinessObject.Cliente;
import BussinessObject.Detalle_Prestamo;
import BussinessObject.Prestamo;
import BussinessObject.Usuario;
import Clases_Diseño.TEXTOFANTASMA1;
import TransferObject.Cliente_DTO;
import TransferObject.Detalle_Prestamo_DTO;
import TransferObject.Prestamo_DTO;
import TransferObject.Usuario_DTO;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 * Formulario que representa la ventana principal para la gestión de préstamos
 * dentro del sistema de biblioteca.
 * <p>
 * Permite seleccionar cliente y libro, especificar cantidad, y asignar al bibliotecario
 * que realiza el préstamo. La interfaz también muestra texto de ayuda en los campos de entrada.
 * </p>
 * 
 */
public class Frm_Prestamo extends javax.swing.JFrame {
    /** Identificador del cliente seleccionado para el préstamo */
    private int id_cliente;
    /** Identificador del libro seleccionado para el préstamo */
    private int id_libro;
    /** Identificador de la sesión actual iniciada */
    private int id_sesion;
    /** Identificador del bibliotecario que realiza el préstamo */
    private int id_bibliotecario;
    /** Objeto de lógica de negocio para gestión de usuarios */
    private Usuario usuario;
    /** Objeto de transferencia con los datos del usuario */
    private Usuario_DTO usuario_DTO;
    /** Modelo de tabla utilizado para mostrar la lista de Detalle Préstamo. */
    DefaultTableModel modelo;
    /** Modelo de tabla utilizado para mostrar la lista de Préstamos. */
    DefaultTableModel modelo_prestamo;
    
    Prestamo_DTO prestamo_DTO;
    
    Prestamo prestamo;
    
    Cliente_DTO cliente_DTO;
    
    Cliente cliente;
    
    Detalle_Prestamo_DTO detalle_prestamo_DTO;
    
    Detalle_Prestamo detalle_prestamo;
    
    /**
     * Constructor del formulario de préstamo.
     * <p>
     * Recibe el ID de sesión activa, inicializa los componentes,
     * configura el estado de la ventana, carga datos del bibliotecario
     * asociado a la sesión e inserta texto de ayuda en los campos.
     * </p>
     *
     * @param id_sesion ID de la sesión iniciada por el usuario (bibliotecario)
     */
    public Frm_Prestamo(int id_sesion) {
        initComponents();
        this.id_sesion = id_sesion;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        agregar_texto_fantasma_txt();
        sp_cantidad.getEditor().getComponent(0).setBackground(new Color(255,153,0));
        usuario = new Usuario();
        usuario_DTO = new Usuario_DTO();
        usuario_DTO = usuario.buscar_sesion(id_sesion);
        txt_bibliotecario.setText(usuario_DTO.getNombre()+" "+usuario_DTO.getApellidos());
        this.id_bibliotecario = usuario_DTO.getId();
        SpinnerNumberModel modelo = new SpinnerNumberModel(0, 0, 5, 1);
        sp_cantidad.setModel(modelo);
        prestamo_DTO = new Prestamo_DTO();
        prestamo = new Prestamo();
        cliente = new Cliente();
        cliente_DTO = new Cliente_DTO();
        detalle_prestamo_DTO = new Detalle_Prestamo_DTO();
        detalle_prestamo = new Detalle_Prestamo();
        listar_prestamos();
    }
           
    
    /**
     * Asigna texto fantasma (placeholders) a los campos de texto de la interfaz
     * para guiar al usuario en la introducción de datos.
     */
    private void agregar_texto_fantasma_txt(){
        TEXTOFANTASMA1 cliente = new TEXTOFANTASMA1("Seleccione un Cliente",txt_cliente);
        TEXTOFANTASMA1 dni_cliente = new TEXTOFANTASMA1("Seleccione un Cliente",txt_dni_cliente);
        TEXTOFANTASMA1 libro = new TEXTOFANTASMA1("Seleccione un Libro",txt_libro);
        TEXTOFANTASMA1 categoria = new TEXTOFANTASMA1("Seleccione un Libro",txt_categoria);
        TEXTOFANTASMA1 buscar = new TEXTOFANTASMA1 ("Busque por ID, Cliente, Fecha o Estado",txt_buscar);
    }

    /**
     * Devuelve el ID del cliente asociado al préstamo.
     * 
     * @return ID del cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * Asigna el ID del cliente al préstamo.
     * 
     * @param id_cliente ID del cliente a establecer
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Devuelve el ID del libro asociado al préstamo.
     * 
     * @return ID del libro
     */
    public int getId_libro() {
        return id_libro;
    }

    /**
     * Asigna el ID del libro al préstamo.
     * 
     * @param id_libro ID del libro a establecer
     */
    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }
    
    /**
     * Realiza la limpieza de los TextField, estableciendo valores vacíos.
     * 
     */
    private void Limpiar_Entradas(){
        txt_cliente.setText("");
        txt_dni_cliente.setText("");
        txt_libro.setText("");
        txt_categoria.setText("");
        sp_cantidad.setValue(0);
        txt_buscar.setText("");
        
        modelo.getDataVector().removeAllElements();
        modelo.fireTableDataChanged();
        
    }
    
    private boolean verificar_libro_duplicado(){
        boolean duplicado = false;
        for (int i = 0;i < modelo.getRowCount();i++){
            if (id_libro == Integer.parseInt(tb_detalle.getValueAt(i, 0).toString())){
                JOptionPane.showMessageDialog(null, "¡Ya has agregado este libro en este préstamo!","Libro Duplicado",JOptionPane.WARNING_MESSAGE);
                duplicado = true;
            }
        }
        return duplicado;
    }
    
    private boolean verificar_campos(){
        boolean verificado = false;
        if (txt_cliente.getText().isEmpty() || txt_dni_cliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "¡Por favor, seleccione un cliente","CAMPO VACÍO",JOptionPane.WARNING_MESSAGE);
        }else if (txt_libro.getText().isEmpty() || txt_categoria.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "¡Por favor, seleccione un libro","CAMPO VACÍO",JOptionPane.WARNING_MESSAGE);
        }else if (sp_cantidad.getValue().equals(0)){            
            JOptionPane.showMessageDialog(null, "¡Por favor, escoga una cantidad válida!","CAMPO VACÍO",JOptionPane.WARNING_MESSAGE);
        }else{
            verificado = true;
        }
        return verificado;
    }
    
    private void limpiar_entradas_libro(){
        txt_libro.setText("");
        txt_categoria.setText("");
        sp_cantidad.setValue(0);
    }
    
    private void limpiar_tabla(){
        /* Obtiene el modelo de la tabla*/
        modelo_prestamo = (DefaultTableModel) tb_prestamos.getModel();
         /* Elimina todas las filas*/
        modelo_prestamo.getDataVector().removeAllElements();
         /* Limpia visualmente la tabla*/
        tb_prestamos.removeAll();
    }
    
    private void listar_prestamos(){
        try{
            /* Obtiene el modelo actual de la tabla de usuarios*/
            modelo_prestamo = (DefaultTableModel) tb_prestamos.getModel();
            /* Crea un arreglo de objetos con 10 columnas (campos del usuario)*/
            Object[] ob = new Object[5];
            /* Recorre todos los usuarios listados*/
            for (Prestamo_DTO u : prestamo.listar()){
                ob[0] = u.getId();
                Cliente_DTO c = cliente.buscar_por_id(u.getId_cliente());
                ob[1] = c.getNombre()+" "+c.getApellidos();
                ob[2] = detalle_prestamo.contar_total_libros(u.getId()); //CONSULTA PARA CONTAR EL TOTAL DE FILAS EN DETALLE_PRESTAMO
                ob[3] = u.getFecha_prestamo();
                ob[4] = u.getEstado();
                
                modelo_prestamo.addRow(ob);
            }
                /* Establece el modelo actualizado en la tabla*/
                tb_prestamos.setModel(modelo_prestamo);
            }catch (Exception e){
                /* Imprime errores en consola */
                System.out.println(e.getMessage());
            }
    }
         
    private void listar_busqueda_prestamo(){
        try{
            /* Obtiene el modelo actual de la tabla de usuarios*/
            modelo_prestamo = (DefaultTableModel) tb_prestamos.getModel();
            /* Crea un arreglo de objetos con 10 columnas (campos del usuario)*/
            Object[] ob = new Object[5];
            /* Recorre todos los usuarios listados*/
            for (Prestamo_DTO u : prestamo.buscar(txt_buscar.getText())){
                ob[0] = u.getId();
                Cliente_DTO c = cliente.buscar_por_id(u.getId_cliente());
                ob[1] = c.getNombre()+" "+c.getApellidos();
                ob[2] = detalle_prestamo.contar_total_libros(u.getId()); //CONSULTA PARA CONTAR EL TOTAL DE FILAS EN DETALLE_PRESTAMO
                ob[3] = u.getFecha_prestamo();
                ob[4] = u.getEstado();
                
                modelo_prestamo.addRow(ob);
            }
                /* Establece el modelo actualizado en la tabla*/
                tb_prestamos.setModel(modelo_prestamo);
            }catch (Exception e){
                /* Imprime errores en consola */
                System.out.println(e.getMessage());
            }
    }
    
    private void mostrar_mensaje_tabla_vacia() {
       // Se obtiene el modelo de la tabla de usuarios
       modelo_prestamo = (DefaultTableModel) tb_prestamos.getModel();

       modelo_prestamo.setRowCount(0); // Asegura que el modelo esté completamente vacío

       tb_prestamos.setModel(modelo_prestamo);
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        panel2 = new org.edisoncor.gui.panel.Panel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        panel10 = new org.edisoncor.gui.panel.Panel();
        jLabel8 = new javax.swing.JLabel();
        txt_cliente = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lb_seleccionar_cliente = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panel11 = new org.edisoncor.gui.panel.Panel();
        jLabel9 = new javax.swing.JLabel();
        txt_dni_cliente = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        panel12 = new org.edisoncor.gui.panel.Panel();
        jLabel10 = new javax.swing.JLabel();
        txt_bibliotecario = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        panel13 = new org.edisoncor.gui.panel.Panel();
        jLabel12 = new javax.swing.JLabel();
        txt_libro = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        lb_seleccionar_libro = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panel14 = new org.edisoncor.gui.panel.Panel();
        jLabel14 = new javax.swing.JLabel();
        txt_categoria = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        panel15 = new org.edisoncor.gui.panel.Panel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        sp_cantidad = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        btn_agregar_libro = new javax.swing.JButton();
        btn_modificar_cantidad = new javax.swing.JButton();
        btn_eliminar_seleccion = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_detalle = new javax.swing.JTable();
        btn_guardar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        panel3 = new org.edisoncor.gui.panel.Panel();
        panel16 = new org.edisoncor.gui.panel.Panel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        txt_buscar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_prestamos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        lb_exit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(20, 25, 53));
        setUndecorated(true);

        panel1.setColorPrimario(new java.awt.Color(20, 25, 53));
        panel1.setColorSecundario(new java.awt.Color(20, 18, 36));

        panel2.setColorPrimario(java.awt.Color.orange);
        panel2.setColorSecundario(new java.awt.Color(255, 204, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Books.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel1.setText("Registro de Prestamos");

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Cliente:");

        panel10.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel10.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel8.setBackground(new java.awt.Color(255, 153, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/User_2.png"))); // NOI18N
        jLabel8.setOpaque(true);

        txt_cliente.setBackground(new java.awt.Color(255, 153, 0));
        txt_cliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_cliente.setBorder(null);
        txt_cliente.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_cliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_cliente.setEnabled(false);
        txt_cliente.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_cliente.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator6.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        lb_seleccionar_cliente.setBackground(new java.awt.Color(255, 153, 0));
        lb_seleccionar_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_seleccionar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Add.png"))); // NOI18N
        lb_seleccionar_cliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_seleccionar_cliente.setOpaque(true);
        lb_seleccionar_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_seleccionar_clienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(panel10Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_seleccionar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel10Layout.createSequentialGroup()
                .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_seleccionar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("D.N.I Cliente:");

        panel11.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel11.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel9.setBackground(new java.awt.Color(255, 153, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Identity Theft.png"))); // NOI18N
        jLabel9.setOpaque(true);

        txt_dni_cliente.setBackground(new java.awt.Color(255, 153, 0));
        txt_dni_cliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_dni_cliente.setBorder(null);
        txt_dni_cliente.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_dni_cliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_dni_cliente.setEnabled(false);
        txt_dni_cliente.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_dni_cliente.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator7.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dni_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator7)
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dni_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Bibliotecario:");

        panel12.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel12.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel10.setBackground(new java.awt.Color(255, 153, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/i-rol-usuario.png"))); // NOI18N
        jLabel10.setOpaque(true);

        txt_bibliotecario.setBackground(new java.awt.Color(255, 153, 0));
        txt_bibliotecario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_bibliotecario.setBorder(null);
        txt_bibliotecario.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_bibliotecario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_bibliotecario.setEnabled(false);
        txt_bibliotecario.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_bibliotecario.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator8.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(panel12Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_bibliotecario))
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addGroup(panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bibliotecario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Libro:");

        panel13.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel13.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel12.setBackground(new java.awt.Color(255, 153, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/libro.png"))); // NOI18N
        jLabel12.setOpaque(true);

        txt_libro.setBackground(new java.awt.Color(255, 153, 0));
        txt_libro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_libro.setBorder(null);
        txt_libro.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_libro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_libro.setEnabled(false);
        txt_libro.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_libro.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator9.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        lb_seleccionar_libro.setBackground(new java.awt.Color(255, 153, 0));
        lb_seleccionar_libro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_seleccionar_libro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Add Bookmark.png"))); // NOI18N
        lb_seleccionar_libro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_seleccionar_libro.setOpaque(true);
        lb_seleccionar_libro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_seleccionar_libroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_libro, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_seleccionar_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator9)
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13Layout.createSequentialGroup()
                .addGroup(panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_seleccionar_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Categoría de libro:");

        panel14.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel14.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel14.setBackground(new java.awt.Color(255, 153, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/i-Categoria-libro.png"))); // NOI18N
        jLabel14.setOpaque(true);

        txt_categoria.setBackground(new java.awt.Color(255, 153, 0));
        txt_categoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_categoria.setBorder(null);
        txt_categoria.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_categoria.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_categoria.setEnabled(false);
        txt_categoria.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_categoria.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator10.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel14Layout = new javax.swing.GroupLayout(panel14);
        panel14.setLayout(panel14Layout);
        panel14Layout.setHorizontalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel14Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_categoria))
            .addComponent(jSeparator10)
        );
        panel14Layout.setVerticalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel14Layout.createSequentialGroup()
                .addGroup(panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Cantidad:");

        panel15.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel15.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel16.setBackground(new java.awt.Color(255, 153, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Note.png"))); // NOI18N
        jLabel16.setOpaque(true);

        jSeparator11.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        sp_cantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sp_cantidad.setBorder(null);
        sp_cantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout panel15Layout = new javax.swing.GroupLayout(panel15);
        panel15.setLayout(panel15Layout);
        panel15Layout.setHorizontalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator11)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel15Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel15Layout.createSequentialGroup()
                .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel3.setText("Detalle de préstamo");

        btn_agregar_libro.setBackground(new java.awt.Color(204, 0, 0));
        btn_agregar_libro.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        btn_agregar_libro.setForeground(new java.awt.Color(255, 255, 255));
        btn_agregar_libro.setText("AGREGAR LIBRO");
        btn_agregar_libro.setBorder(null);
        btn_agregar_libro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agregar_libro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_agregar_libroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_agregar_libroMouseExited(evt);
            }
        });
        btn_agregar_libro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_libroActionPerformed(evt);
            }
        });

        btn_modificar_cantidad.setBackground(new java.awt.Color(204, 0, 0));
        btn_modificar_cantidad.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        btn_modificar_cantidad.setForeground(new java.awt.Color(255, 255, 255));
        btn_modificar_cantidad.setText("MODIFICAR CANTIDAD");
        btn_modificar_cantidad.setBorder(null);
        btn_modificar_cantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_modificar_cantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_modificar_cantidadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_modificar_cantidadMouseExited(evt);
            }
        });
        btn_modificar_cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificar_cantidadActionPerformed(evt);
            }
        });

        btn_eliminar_seleccion.setBackground(new java.awt.Color(204, 0, 0));
        btn_eliminar_seleccion.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        btn_eliminar_seleccion.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar_seleccion.setText("ELIMINAR SELECCIÓN");
        btn_eliminar_seleccion.setBorder(null);
        btn_eliminar_seleccion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eliminar_seleccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_eliminar_seleccionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_eliminar_seleccionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_eliminar_seleccionMousePressed(evt);
            }
        });
        btn_eliminar_seleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_seleccionActionPerformed(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        tb_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Libro", "Libro", "Categoría", "Cantidad", "Fecha Préstamo"
            }
        ));
        tb_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_detalleMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_detalle);

        btn_guardar.setBackground(new java.awt.Color(20, 25, 53));
        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardar.setText("Guardar");
        btn_guardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btn_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_guardar.setFocusable(false);
        btn_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_guardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_guardarMouseExited(evt);
            }
        });
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(20, 25, 53));
        btn_modificar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_modificar.setForeground(new java.awt.Color(255, 255, 255));
        btn_modificar.setText("Modificar");
        btn_modificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btn_modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_modificar.setFocusable(false);
        btn_modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_modificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_modificarMouseExited(evt);
            }
        });
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(20, 25, 53));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btn_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eliminar.setFocusable(false);
        btn_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_eliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_eliminarMouseExited(evt);
            }
        });
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_limpiar.setBackground(new java.awt.Color(20, 25, 53));
        btn_limpiar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_limpiar.setForeground(new java.awt.Color(255, 255, 255));
        btn_limpiar.setText("Limpiar");
        btn_limpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btn_limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_limpiar.setFocusable(false);
        btn_limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_limpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_limpiarMouseExited(evt);
            }
        });
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(158, 158, 158))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btn_agregar_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(btn_modificar_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btn_eliminar_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(5, 5, 5)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, 0)
                        .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, 0)
                        .addComponent(panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, 0)
                        .addComponent(panel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, 0)
                        .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, 0)
                        .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_modificar_cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_agregar_libro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_eliminar_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panel3.setColorPrimario(java.awt.Color.orange);
        panel3.setColorSecundario(new java.awt.Color(255, 204, 0));

        panel16.setBackground(new java.awt.Color(255, 153, 0));
        panel16.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel16.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel22.setBackground(new java.awt.Color(255, 153, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Search.png"))); // NOI18N
        jLabel22.setOpaque(true);

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));

        txt_buscar.setBackground(new java.awt.Color(255, 153, 0));
        txt_buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_buscar.setBorder(null);
        txt_buscar.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_buscar.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_buscar.setSelectionColor(new java.awt.Color(255, 255, 255));
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panel16Layout = new javax.swing.GroupLayout(panel16);
        panel16.setLayout(panel16Layout);
        panel16Layout.setHorizontalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel16Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panel16Layout.setVerticalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel16Layout.createSequentialGroup()
                .addGroup(panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tb_prestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Cantidad Libros", "Fecha Préstamo", "Estado"
            }
        ));
        jScrollPane3.setViewportView(tb_prestamos);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Prestamo Libro.png"))); // NOI18N

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel17.setText("Lista de Prestamos");

        lb_exit.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lb_exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_exit.setText("X");
        lb_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_exitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_exitMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addGap(0, 141, Short.MAX_VALUE)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addComponent(panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17)
                                        .addGap(20, 20, 20)))
                                .addGap(40, 40, 40)
                                .addComponent(lb_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel17)))
                    .addComponent(lb_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * Evento al hacer clic sobre la etiqueta de selección de cliente.
    * <p>
    * Abre el formulario {@code Frm_Lista_Clientes} para seleccionar un cliente
    * y pasa la referencia del formulario actual para comunicar la selección.
    * </p>
    */
    private void lb_seleccionar_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_seleccionar_clienteMouseClicked
        Frm_Lista_Clientes listaClientes = new Frm_Lista_Clientes(this); // pasas la referencia
        listaClientes.setVisible(true);
    }//GEN-LAST:event_lb_seleccionar_clienteMouseClicked

    /**
    * Evento al hacer clic sobre la etiqueta de selección de libro.
    * <p>
    * Abre el formulario {@code Frm_Lista_Libros} para seleccionar un libro,
    * pasando también la referencia del formulario actual.
    * </p>
    */
    private void lb_seleccionar_libroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_seleccionar_libroMouseClicked
        Frm_Lista_Libros frm_lis_libros = new Frm_Lista_Libros(this);
        frm_lis_libros.setVisible(true);
    }//GEN-LAST:event_lb_seleccionar_libroMouseClicked

    /** Cambia el color del botón "Agregar libro" al pasar el mouse por encima */
    private void btn_agregar_libroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregar_libroMouseEntered
        btn_agregar_libro.setBackground(Color.RED);
    }//GEN-LAST:event_btn_agregar_libroMouseEntered

    /** Restaura el color original del botón "Agregar libro" al salir el mouse */
    private void btn_agregar_libroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregar_libroMouseExited
        btn_agregar_libro.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btn_agregar_libroMouseExited

    /** Acción del botón "Agregar libro" (por implementar) */
    private void btn_agregar_libroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_libroActionPerformed
        modelo = (DefaultTableModel) tb_detalle.getModel();
       
        if (verificar_campos() && !verificar_libro_duplicado()){
            if (modelo.getRowCount()>=5){
                JOptionPane.showMessageDialog(null, "Ya ingresaste el límite máximo de libros en un préstamo", "Límite de Libros", JOptionPane.WARNING_MESSAGE);
            }else{
                Object datos[] = new Object[5];
                datos[0] = id_libro;
                datos[1] = txt_libro.getText();
                datos[2] = txt_categoria.getText();
                datos[3] = sp_cantidad.getValue();
                datos[4] = LocalDate.now();

                modelo.addRow(datos);
                limpiar_entradas_libro();
            }
            
        }
    }//GEN-LAST:event_btn_agregar_libroActionPerformed

    /** Cambia el color del botón "Modificar cantidad" al pasar el mouse */
    private void btn_modificar_cantidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificar_cantidadMouseEntered
        btn_modificar_cantidad.setBackground(Color.RED);
    }//GEN-LAST:event_btn_modificar_cantidadMouseEntered

    /** Restaura el color del botón "Modificar cantidad" al salir el mouse */
    private void btn_modificar_cantidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificar_cantidadMouseExited
        btn_modificar_cantidad.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btn_modificar_cantidadMouseExited

    /** Cambia el color del botón "Eliminar selección" al pasar el mouse */
    private void btn_eliminar_seleccionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_seleccionMouseEntered
        btn_eliminar_seleccion.setBackground(Color.RED);
    }//GEN-LAST:event_btn_eliminar_seleccionMouseEntered

    /** Restaura el color del botón "Eliminar selección" al salir el mouse */
    private void btn_eliminar_seleccionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_seleccionMouseExited
        btn_eliminar_seleccion.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btn_eliminar_seleccionMouseExited

    private void btn_eliminar_seleccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_seleccionMousePressed

    }//GEN-LAST:event_btn_eliminar_seleccionMousePressed

    /** Cambia el color del botón "Guardar" al pasar el mouse */
    private void btn_guardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarMouseEntered
        btn_guardar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_guardarMouseEntered

    /** Restaura el color del botón "Guardar" al salir el mouse */
    private void btn_guardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarMouseExited
        btn_guardar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_guardarMouseExited

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        
        if (txt_cliente.getText().isEmpty() || txt_dni_cliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "¡Error! Seleccione un cliente","Campos Vacíos",JOptionPane.WARNING_MESSAGE);
        }else if (tb_detalle.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "¡Error! Seleccione algún libro","Libros sin agregar",JOptionPane.WARNING_MESSAGE);
        }else{
            int id_agregar_prestamo = prestamo.agregar(id_bibliotecario, id_cliente, String.valueOf(LocalDate.now()), "Prestado", String.valueOf(LocalDate.now().plusDays(10)));
            if (id_agregar_prestamo == 0){
                System.out.println(id_agregar_prestamo);
                JOptionPane.showMessageDialog(null, "¡Error! El préstamo no se pudo registrar", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }else{
                for (int i = 0;i < modelo.getRowCount();i++){
                    detalle_prestamo.agregar(id_agregar_prestamo,(int) modelo.getValueAt(i, 0),(int) modelo.getValueAt(i, 3));
                }
                limpiar_tabla();
                listar_prestamos();
                Limpiar_Entradas();
            }
            
            
        }
        
    }//GEN-LAST:event_btn_guardarActionPerformed

    /** Cambia el color del botón "Modificar" al pasar el mouse */
    private void btn_modificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificarMouseEntered
        btn_modificar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_modificarMouseEntered

    /** Restaura el color del botón "Modificar" al salir el mouse */
    private void btn_modificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificarMouseExited
        btn_modificar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_modificarMouseExited

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    /** Cambia el color del botón "Eliminar" al pasar el mouse */
    private void btn_eliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarMouseEntered
        btn_eliminar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_eliminarMouseEntered

    /** Restaura el color del botón "Eliminar" al salir el mouse */
    private void btn_eliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarMouseExited
        btn_eliminar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_eliminarMouseExited

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed

    /** Cambia el color del botón "Limpiar" al pasar el mouse */
    private void btn_limpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiarMouseEntered
        btn_limpiar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_limpiarMouseEntered

    /** Restaura el color del botón "Limpiar" al salir el mouse */
    private void btn_limpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiarMouseExited
        btn_limpiar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_limpiarMouseExited

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        Limpiar_Entradas();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        if (!txt_buscar.getText().isEmpty()) {

           if (!prestamo.buscar(txt_buscar.getText()).isEmpty()) {
               limpiar_tabla();              // Borra las filas anteriores
               listar_busqueda_prestamo();   // Muestra solo los prestamos filtrados por el texto ingresado
           } else {
               getToolkit().beep();
               mostrar_mensaje_tabla_vacia();
               JOptionPane.showMessageDialog(null, "¡Préstamo no encontrado!", "Búsqueda Inválida", JOptionPane.WARNING_MESSAGE);
           }

       } else {
           limpiar_tabla();
           listar_prestamos();
       }
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void txt_buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyTyped

    }//GEN-LAST:event_txt_buscarKeyTyped

    /**
    * Acción al hacer clic en el botón (etiqueta) de cerrar.
    * <p>Cierra la ventana del formulario de préstamo.</p>
    */
    private void lb_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_exitMouseClicked
        setVisible(false);
    }//GEN-LAST:event_lb_exitMouseClicked

    /**
    * Acción al entrar el mouse en el botón de cerrar (X).
    * <p>
    * Cambia el fondo y color del texto para indicar que es interactivo.
    * </p>
    */
    private void lb_exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_exitMouseEntered
        lb_exit.setOpaque(true);
        lb_exit.setBackground(Color.red);
        lb_exit.setForeground(Color.white);
    }//GEN-LAST:event_lb_exitMouseEntered

    /**
    * Acción al salir el mouse del botón de cerrar (X).
    * <p>Restaura los estilos por defecto del componente.</p>
    */
    private void lb_exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_exitMouseExited
        lb_exit.setOpaque(false);
        lb_exit.setForeground(Color.black);
    }//GEN-LAST:event_lb_exitMouseExited

    private void btn_modificar_cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificar_cantidadActionPerformed
        int fila = tb_detalle.getSelectedRow();
        
        if (fila >= 0 && verificar_campos()){
            for (int i = 0;i < modelo.getRowCount();i++){
                if (id_libro == Integer.parseInt(tb_detalle.getValueAt(i, 0).toString())){
                    tb_detalle.setValueAt(sp_cantidad.getValue(), i, 3);
                    JOptionPane.showMessageDialog(null, "¡Se modificó la cantidad correctamente!","Detalle Modificado",JOptionPane.INFORMATION_MESSAGE);
                    limpiar_entradas_libro();
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "¡Seleccione una fila válida para modificar!", "Fila Inválida", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_modificar_cantidadActionPerformed

    private void btn_eliminar_seleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_seleccionActionPerformed
        int fila = tb_detalle.getSelectedRow();
        
        if (fila >= 0){
            modelo.removeRow(fila);
            JOptionPane.showMessageDialog(null, "¡Se eliminó el libro seleccionado!","Detalle Eliminado",JOptionPane.WARNING_MESSAGE);
            limpiar_entradas_libro();
        }else{
            JOptionPane.showMessageDialog(null, "¡Seleccione una fila válida para eliminar!", "Fila Inválida", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_eliminar_seleccionActionPerformed

    private void tb_detalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_detalleMousePressed
        int fila = tb_detalle.getSelectedRow();
        
        if (fila >= 0){
            id_libro = Integer.parseInt(tb_detalle.getValueAt(fila, 0).toString());
            txt_libro.setText(tb_detalle.getValueAt(fila, 1).toString());
            txt_categoria.setText(tb_detalle.getValueAt(fila, 2).toString());
            sp_cantidad.setValue(Integer.valueOf(tb_detalle.getValueAt(fila, 3).toString()));
        }
        
    }//GEN-LAST:event_tb_detalleMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar_libro;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_eliminar_seleccion;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_modificar_cantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lb_exit;
    private javax.swing.JLabel lb_seleccionar_cliente;
    private javax.swing.JLabel lb_seleccionar_libro;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel10;
    private org.edisoncor.gui.panel.Panel panel11;
    private org.edisoncor.gui.panel.Panel panel12;
    private org.edisoncor.gui.panel.Panel panel13;
    private org.edisoncor.gui.panel.Panel panel14;
    private org.edisoncor.gui.panel.Panel panel15;
    private org.edisoncor.gui.panel.Panel panel16;
    private org.edisoncor.gui.panel.Panel panel2;
    private org.edisoncor.gui.panel.Panel panel3;
    private javax.swing.JSpinner sp_cantidad;
    private javax.swing.JTable tb_detalle;
    private javax.swing.JTable tb_prestamos;
    private javax.swing.JTextField txt_bibliotecario;
    private javax.swing.JTextField txt_buscar;
    public javax.swing.JTextField txt_categoria;
    public javax.swing.JTextField txt_cliente;
    public javax.swing.JTextField txt_dni_cliente;
    public javax.swing.JTextField txt_libro;
    // End of variables declaration//GEN-END:variables

}



