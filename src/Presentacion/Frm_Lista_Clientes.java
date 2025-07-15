

package Presentacion;

import BussinessObject.Cliente;
import Clases_Diseño.TEXTOFANTASMA1;
import TransferObject.Cliente_DTO;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


/**
 * Clase Frm_Lista_Clientes
 * Interfaz gráfica que muestra una lista de clientes en una tabla.
 * Permite buscar por nombre, apellido o DNI, ordenar los resultados por columnas,
 * y seleccionar un cliente para transferir los datos al formulario de préstamo.
 */
public class Frm_Lista_Clientes extends javax.swing.JFrame {

    DefaultTableModel modelo;
    private Cliente_DTO cliente_DTO;
    private Cliente cliente;
    private Frm_Prestamo frmPrestamo;
    
    /**
    * Constructor que recibe una instancia de Frm_Prestamo.
    * Se encarga de inicializar los componentes, centrar la ventana,
    * configurar el placeholder del buscador y cargar la lista de clientes.
    * 
    * @param frmPrestamo Referencia al formulario de préstamos para retornar los datos del cliente seleccionado.
    */
    public Frm_Lista_Clientes(Frm_Prestamo frmPrestamo) {
        initComponents(); // Inicializa todos los componentes visuales generados por NetBeans

        setLocationRelativeTo(this); // Centra la ventana en la pantalla

        this.frmPrestamo = frmPrestamo;
        /* Agrega texto guía al campo de búsqueda */
        TEXTOFANTASMA1 buscar = new TEXTOFANTASMA1("Ingrese el DNI, Nombre o Apellido del cliente", txt_buscar);

        /* Instancia las clases necesarias */
        cliente_DTO = new Cliente_DTO();
        cliente = new Cliente();

        Limpiar_Tabla(); // Limpia la tabla antes de llenarla
        Listar_Clientes(); // Muestra todos los clientes al iniciar
        Ordenar_Encabezado(); // Permite ordenamiento al hacer clic en encabezados de columna
    }


    /**
    * Llena la tabla con todos los clientes registrados en la base de datos.
    * Usa el modelo DefaultTableModel para agregar cada cliente como una fila.
    */
    private void Listar_Clientes() {
        try {
            modelo = (DefaultTableModel) tb_clientes.getModel(); // Obtiene el modelo actual de la tabla
            Object[] ob = new Object[5]; // Arreglo para almacenar datos de cada fila

            /* Recorre la lista de clientes y los agrega al modelo */
            for (Cliente_DTO u : cliente.listar()) {
                ob[0] = u.getId();
                ob[1] = u.getNombre();
                ob[2] = u.getApellidos();
                ob[3] = u.getDni();
                ob[4] = u.getTelefono();
                modelo.addRow(ob); // Agrega la fila al modelo
            }
            tb_clientes.setModel(modelo); // Asigna el modelo actualizado a la tabla
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Muestra errores en consola
        }
    }

    
    /**
    * Limpia todas las filas existentes de la tabla para evitar duplicaciones.
    * Este método es útil antes de recargar los datos o realizar una nueva búsqueda.
    */
    private void Limpiar_Tabla() {
        modelo = (DefaultTableModel) tb_clientes.getModel(); // Obtiene el modelo
        modelo.getDataVector().removeAllElements(); // Elimina los datos
        tb_clientes.removeAll(); // Limpia visualmente la tabla
    }

    
    /**
    * Realiza una búsqueda de clientes en la base de datos según el texto ingresado.
    * Muestra solo los clientes cuyo nombre, apellido o DNI coinciden con el texto.
    */
    private void Listar_Busqueda_Clientes() {
        try {
            modelo = (DefaultTableModel) tb_clientes.getModel();
            Object[] ob = new Object[5];

            for (Cliente_DTO u : cliente.buscar(txt_buscar.getText())) {
                ob[0] = u.getId();
                ob[1] = u.getNombre();
                ob[2] = u.getApellidos();
                ob[3] = u.getDni();
                ob[4] = u.getTelefono();
                modelo.addRow(ob);
            }
            tb_clientes.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    /**
    * Muestra un mensaje vacío en la tabla si no se encuentran resultados.
    * Este método se invoca cuando la búsqueda no arroja coincidencias.
    */
    private void Mostrar_Mensaje_Tabla_Vacia() {
        modelo = (DefaultTableModel) tb_clientes.getModel();
        modelo.setRowCount(0); // Borra todas las filas
        tb_clientes.setModel(modelo); // Forzar actualización del modelo en la tabla
    }



    
    /**
    * Lista los clientes ordenados por la columna seleccionada (ID, Nombre, etc.).
    * Se utiliza al hacer clic en los encabezados de la tabla para ordenar dinámicamente.
    * 
    * @param encabezado Nombre de la columna por la cual se desea ordenar los resultados.
    */
    private void listar_encabezado_usuarios(String encabezado) {
        try {
            modelo = (DefaultTableModel) tb_clientes.getModel();
            Object[] ob = new Object[5];

            for (Cliente_DTO u : cliente.listar_encabezado(txt_buscar.getText(), encabezado)) {
                ob[0] = u.getId();
                ob[1] = u.getNombre();
                ob[2] = u.getApellidos();
                ob[3] = u.getDni();
                ob[4] = u.getTelefono();
                modelo.addRow(ob);
            }
            tb_clientes.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    /**
    * Agrega un listener a los encabezados de la tabla para habilitar el ordenamiento.
    * Al hacer clic en un encabezado, se ordena la lista de clientes según esa columna.
    */
    private void Ordenar_Encabezado() {
        JTableHeader header = tb_clientes.getTableHeader();

        header.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int columna = header.columnAtPoint(evt.getPoint()); // Obtiene la columna clickeada
                String nombre_columna = tb_clientes.getColumnName(columna); // Obtiene el nombre del encabezado

                switch (nombre_columna) {
                    case "ID": Limpiar_Tabla(); listar_encabezado_usuarios("id"); break;
                    case "Nombre": Limpiar_Tabla(); listar_encabezado_usuarios("nombre"); break;
                    case "Apellidos": Limpiar_Tabla(); listar_encabezado_usuarios("apellidos"); break;
                    case "DNI": Limpiar_Tabla(); listar_encabezado_usuarios("dni"); break;
                    case "Telefono": Limpiar_Tabla(); listar_encabezado_usuarios("telefono"); break;
                    default: Limpiar_Tabla(); Listar_Clientes(); break; // Carga todos si no se reconoce la columna
                }
            }
        });
    }
    
    /**
    * Obtiene la fila seleccionada de la tabla y transfiere los datos del cliente
    * al formulario de préstamo (Frm_Prestamo). También cierra la ventana actual.
    */
    private void Seleccionar_Cliente(){
        int fila=tb_clientes.getSelectedRow();   
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {     
                frmPrestamo.txt_cliente.setText(tb_clientes.getValueAt(fila,1).toString() + ' ' + tb_clientes.getValueAt(fila,2).toString());
                frmPrestamo.txt_dni_cliente.setText(tb_clientes.getValueAt(fila,3).toString());
                frmPrestamo.setId_cliente((int) tb_clientes.getValueAt(fila, 0));
                this.dispose();
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al seleccionar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel5 = new org.edisoncor.gui.panel.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_clientes = new javax.swing.JTable();
        panel15 = new org.edisoncor.gui.panel.Panel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txt_buscar = new javax.swing.JTextField();
        btnagregar = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lb_miniminzar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53), 10));
        panel5.setColorPrimario(new java.awt.Color(255, 204, 0));
        panel5.setColorSecundario(new java.awt.Color(255, 204, 0));
        panel5.setGradiente(org.edisoncor.gui.panel.Panel.Gradiente.ESQUINA_4);

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel1.setText("Lista de Clientes");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/People.png"))); // NOI18N

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tb_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellidos", "DNI", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_clientes.setFocusable(false);
        tb_clientes.setGridColor(new java.awt.Color(255, 255, 255));
        tb_clientes.setRowHeight(60);
        tb_clientes.setSelectionBackground(new java.awt.Color(20, 25, 53));
        tb_clientes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tb_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_clientesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_clientesMouseReleased(evt);
            }
        });
        tb_clientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_clientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_clientes);

        panel15.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel15.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel19.setBackground(new java.awt.Color(255, 153, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/User_2.png"))); // NOI18N
        jLabel19.setOpaque(true);

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

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

        btnagregar.setBackground(new java.awt.Color(255, 153, 0));
        btnagregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Add.png"))); // NOI18N
        btnagregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnagregar.setOpaque(true);
        btnagregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnagregarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel15Layout = new javax.swing.GroupLayout(panel15);
        panel15.setLayout(panel15Layout);
        panel15Layout.setHorizontalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel15Layout.createSequentialGroup()
                .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel15Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator11))
                .addGap(0, 0, 0))
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel15Layout.createSequentialGroup()
                .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Buscar Nombres y/o Apellidos:");

        lb_miniminzar.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lb_miniminzar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_miniminzar.setText("-");
        lb_miniminzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_miniminzar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lb_miniminzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_miniminzarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_miniminzarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_miniminzarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(155, 155, 155)
                .addComponent(lb_miniminzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(lb_miniminzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addGap(0, 0, 0)
                .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_clientesMousePressed

    }//GEN-LAST:event_tb_clientesMousePressed

    private void txt_buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyTyped
        
    }//GEN-LAST:event_txt_buscarKeyTyped

    /**
    * Evento que se ejecuta al hacer clic en el botón agregar.
    * Invoca el método para seleccionar cliente y transferir datos.
    */
    private void btnagregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnagregarMouseClicked
        // Validar doble clic y fila seleccionada
        Seleccionar_Cliente();
    }//GEN-LAST:event_btnagregarMouseClicked

    /**
    * Oculta la ventana al hacer clic en el icono de minimizar.
    */
    private void lb_miniminzarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_miniminzarMouseClicked
        setVisible(false);
    }//GEN-LAST:event_lb_miniminzarMouseClicked

    /**
    * Cambia el color del botón de minimizar al pasar el cursor por encima (efecto hover).
    */
    private void lb_miniminzarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_miniminzarMouseEntered
        lb_miniminzar.setOpaque(true);
        lb_miniminzar.setBackground(Color.BLUE);
        lb_miniminzar.setForeground(Color.white);
    }//GEN-LAST:event_lb_miniminzarMouseEntered

    /**
    * Restaura el color original del botón de minimizar cuando se quita el cursor.
    */
    private void lb_miniminzarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_miniminzarMouseExited
        lb_miniminzar.setOpaque(false);
        lb_miniminzar.setForeground(Color.black);
    }//GEN-LAST:event_lb_miniminzarMouseExited

    private void tb_clientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_clientesKeyReleased
        
    }//GEN-LAST:event_tb_clientesKeyReleased

    /**
    * Evento que se dispara al liberar una tecla en el campo de búsqueda.
    * Si hay texto, filtra los clientes; si está vacío, lista todos nuevamente.
    */
    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        if (!txt_buscar.getText().isEmpty()) {
            if (!cliente.buscar(txt_buscar.getText()).isEmpty()) {
                Limpiar_Tabla();
                Listar_Busqueda_Clientes(); /* Muestra resultados coincidentes */
            } else {
                getToolkit().beep(); // Sonido si no hay resultados
                Mostrar_Mensaje_Tabla_Vacia();
                JOptionPane.showMessageDialog(null, "¡Cliente no encontrado!", "Búsqueda Inválida", JOptionPane.WARNING_MESSAGE);
                
            }
        } else {
            Limpiar_Tabla(); // Si el campo está vacío, muestra todo
            Listar_Clientes();
        }
    }//GEN-LAST:event_txt_buscarKeyReleased

    /**
    * Evento que se activa al soltar el clic del mouse sobre la tabla.
    * Si se detecta un doble clic sobre una fila, se selecciona al cliente.
    */
    private void tb_clientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_clientesMouseReleased
        // Validar doble clic y fila seleccionada
        if (evt.getClickCount() == 2) {
            Seleccionar_Cliente();
        }
    }//GEN-LAST:event_tb_clientesMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnagregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lb_miniminzar;
    private org.edisoncor.gui.panel.Panel panel15;
    private org.edisoncor.gui.panel.Panel panel5;
    private javax.swing.JTable tb_clientes;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables

}
