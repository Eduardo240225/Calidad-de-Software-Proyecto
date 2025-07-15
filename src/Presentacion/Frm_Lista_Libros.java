
package Presentacion;

import BussinessObject.Autor;
import BussinessObject.Categoria;
import BussinessObject.Libro;
import Clases_Diseño.TEXTOFANTASMA1;
import TransferObject.Libro_DTO;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Clase Frm_Lista_Libros
 * ----------------------
 * Interfaz gráfica que permite mostrar y buscar libros disponibles en el sistema.
 * Muestra en una tabla todos los libros con sus datos (ID, título, categoría, autores, año, stock).
 * Permite:
 * - Buscar por título, categoría o autor(es)
 * - Ordenar resultados haciendo clic en los encabezados
 * - Seleccionar un libro y retornar los datos al formulario de préstamos
 * 
 * Utiliza objetos de lógica de negocio como:
 * - Libro
 * - Autor
 * - Categoria
 * 
 * Además, interactúa con el formulario Frm_Prestamo para enviar los datos del libro seleccionado.
 */

public class Frm_Lista_Libros extends javax.swing.JFrame {

        /* Modelo de datos para la tabla de libros */
        DefaultTableModel modelo;

        /* Objeto de transferencia que representa un libro */
        Libro_DTO libro_DTO;

        /* Objeto de lógica de negocio para acceder a funciones de libros */
        Libro libro;

        /* Lógica de negocio para acceder a funciones de categorías */
        Categoria categoria;

        /* Lógica de negocio para acceder a funciones de autores */
        Autor autor;

        /* Referencia al formulario Frm_Prestamo donde se enviarán los datos del libro seleccionado */
        private Frm_Prestamo frmPrestamo;

    /**
     * Constructor del formulario Frm_Lista_Libros.
     * Inicializa componentes, objetos de negocio, carga la lista de libros
     * y configura búsqueda, ordenamiento y centrado de la ventana.
     *
     * @param frmPrestamo Instancia del formulario de préstamo para enviar datos del libro seleccionado.
     */
    public Frm_Lista_Libros(Frm_Prestamo frmPrestamo) {
        initComponents(); // Inicializa los componentes visuales de la interfaz (generado por NetBeans)

        setLocationRelativeTo(this); // Centra la ventana en la pantalla
        this.frmPrestamo = frmPrestamo;
        /* Asigna texto fantasma (placeholder) al campo de búsqueda */
        TEXTOFANTASMA1 buscar = new TEXTOFANTASMA1("Ingrese el Libro, Categoría o Autor(es)", txt_buscar);

        /* Inicializa los objetos necesarios para la lógica del formulario */
        libro_DTO = new Libro_DTO(); // Objeto de transferencia de datos
        libro = new Libro();         // Clase para obtener la lista de libros
        categoria = new Categoria(); // Clase para gestionar categorías
        autor = new Autor();         // Clase para gestionar autores

        Limpiar_Tabla(); // Limpia cualquier dato anterior en la tabla de libros
        Listar_libros(); // Carga y muestra la lista de libros en la tabla
        Ordenar_Encabezado(); // Permite ordenar por columnas haciendo clic en encabezados
    }


    /**
    * Método que carga y muestra en la tabla todos los libros registrados.
    * Obtiene la lista desde la capa de lógica de negocio y llena la tabla.
    */
    private void Listar_libros() {
        try {
            /* Obtiene el modelo actual de la tabla de libros */
            modelo = (DefaultTableModel) tb_libros.getModel();

            /* Arreglo temporal para almacenar los datos de cada fila */
            Object[] ob = new Object[6];

            /* Recorre la lista de libros obtenida desde la clase lógica 'libro' */
            for (Libro_DTO u : libro.listar()) {

                /* Coloca en cada posición del arreglo los datos necesarios */
                ob[0] = u.getId(); // ID del libro
                ob[1] = u.getTitulo(); // Título del libro

                /* Busca y obtiene la descripción de la categoría mediante su ID */
                ob[2] = categoria.buscar_por_id(u.getId_categoria()).getDescripcion();

                /* Busca y obtiene el nombre del(los) autor(es) del libro */
                ob[3] = autor.buscar_autores_por_libro(u.getId()).getNombre();

                /* Año de publicación del libro */
                ob[4] = u.getAño_publicacion();

                /* Stock disponible del libro */
                ob[5] = u.getStock();

                /* Agrega la fila con los datos al modelo de la tabla */
                modelo.addRow(ob);
            }

            /* Asigna el modelo con los nuevos datos a la tabla */
            tb_libros.setModel(modelo);

        } catch (Exception e) {
            /* Muestra en consola el mensaje de error si ocurre una excepción */
            System.out.println(e.getMessage());
        }
    }

    
    /**
     * Limpia por completo la tabla antes de cargar nuevos datos.
     * Elimina todas las filas del modelo asociado a la tabla.
     */
    private void Limpiar_Tabla() {
        /* Obtiene el modelo actual asociado a la tabla de libros */
        modelo = (DefaultTableModel) tb_libros.getModel();

        /* Elimina todos los elementos del modelo (todas las filas) */
        modelo.getDataVector().removeAllElements();

        /* Limpia visualmente la tabla para reflejar los cambios */
        tb_libros.removeAll();
    }

    
    /* 
    /**
    * Busca libros que coincidan con el texto ingresado en el campo de búsqueda.
    * Puede filtrar por título, categoría o autor. Los resultados se muestran en la tabla.
    */

   private void Listar_Busqueda_Libros() {
       try {
           /* Obtiene el modelo actual de la tabla de libros */
           modelo = (DefaultTableModel) tb_libros.getModel();

           /* Arreglo temporal para almacenar los datos de cada libro */
           Object[] ob = new Object[6];

           /* Recorre los resultados de búsqueda según el texto ingresado */
           for (Libro_DTO u : libro.buscar(txt_buscar.getText())) {
               ob[0] = u.getId(); // ID del libro
               ob[1] = u.getTitulo(); // Título del libro

               /* Obtiene la descripción de la categoría asociada al libro */
               ob[2] = categoria.buscar_por_id(u.getId_categoria()).getDescripcion();

               /* Obtiene el nombre del autor o autores asociados al libro */
               ob[3] = autor.buscar_autores_por_libro(u.getId()).getNombre();

               ob[4] = u.getAño_publicacion(); // Año de publicación
               ob[5] = u.getStock(); // Cantidad de libros disponibles

               /* Agrega los datos del libro como una nueva fila en el modelo */
               modelo.addRow(ob);
           }

           /* Asigna el modelo actualizado a la tabla para mostrar resultados */
           tb_libros.setModel(modelo);

       } catch (Exception e) {
           /* Muestra cualquier error en la consola */
           System.out.println(e.getMessage());
       }
   }

    
    /**
    * Limpia la tabla y la deja vacía visualmente cuando no se encuentran resultados
    * en la búsqueda. Se utiliza como feedback para el usuario.
    */
   private void Mostrar_Mensaje_Tabla_Vacia() {

       /* Obtiene el modelo de la tabla */
       modelo = (DefaultTableModel) tb_libros.getModel();

       modelo.setRowCount(0); // Asegura que no haya filas

       /* Agrega el modelo para mostrar la tabla vacía */
       tb_libros.setModel(modelo);
   }

    
    /**
    * Muestra los libros ordenados por una columna específica (ID, Título, Categoría, Autor, etc.).
    * Se invoca cuando se hace clic sobre los encabezados de la tabla.
    * 
    * @param encabezado Campo por el cual se desea ordenar los datos.
    */
   private void listar_encabezado_usuarios(String encabezado) {
       try {
           /* Obtiene el modelo actual de la tabla de libros */
           modelo = (DefaultTableModel) tb_libros.getModel();

           /* Arreglo para almacenar temporalmente los datos de cada fila */
           Object[] ob = new Object[6];

           /* Obtiene y recorre la lista de libros ordenados según el encabezado proporcionado */
           for (Libro_DTO u : libro.listar_encabezado(txt_buscar.getText(), encabezado)) {
               ob[0] = u.getId(); // ID del libro
               ob[1] = u.getTitulo(); // Título del libro

               /* Busca la descripción de la categoría usando su ID */
               ob[2] = categoria.buscar_por_id(u.getId_categoria()).getDescripcion();

               /* Busca el nombre del autor (o autores) usando el ID del libro */
               ob[3] = autor.buscar_autores_por_libro(u.getId()).getNombre();

               ob[4] = u.getAño_publicacion(); // Año de publicación
               ob[5] = u.getStock(); // Stock disponible

               /* Agrega los datos como una fila en la tabla */
               modelo.addRow(ob);
           }

           /* Asigna el modelo actualizado a la tabla para mostrar los resultados ordenados */
           tb_libros.setModel(modelo);

       } catch (Exception e) {
           /* Muestra el error en la consola si ocurre alguna excepción */
           System.out.println(e.getMessage());
       }
   }

    
     /**
    * Activa la funcionalidad de ordenamiento en la tabla al hacer clic sobre los encabezados.
    * Determina qué columna fue seleccionada y ordena la lista de libros en consecuencia.
    */
private void Ordenar_Encabezado() {
    /* Obtiene el encabezado de la tabla */
    JTableHeader header = tb_libros.getTableHeader();

    /* Agrega un listener para detectar clics del mouse sobre los encabezados */
    header.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            /* Obtiene el índice de la columna donde se hizo clic */
            int columna = header.columnAtPoint(evt.getPoint());

            /* Obtiene el nombre del encabezado de esa columna */
            String nombre_columna = tb_libros.getColumnName(columna);

            /* Evalúa qué columna fue seleccionada y llama al método correspondiente */
            switch (nombre_columna) {
                case "ID":
                    Limpiar_Tabla(); // Limpia la tabla
                    listar_encabezado_usuarios("L.id"); // Ordena por ID de libro
                    break;

                case "Libro":
                    Limpiar_Tabla();
                    listar_encabezado_usuarios("L.titulo"); // Ordena por título
                    break;

                case "Categoria":
                    Limpiar_Tabla();
                    listar_encabezado_usuarios("C.descripcion"); // Ordena por categoría
                    break;

                case "Autor(es)":
                    Limpiar_Tabla();
                    listar_encabezado_usuarios("autores"); // Ordena por autores
                    break;

                case "Año de Lanzamiento":
                    Limpiar_Tabla();
                    listar_encabezado_usuarios("L.año_publicacion"); // Ordena por año
                    break;

                case "Stock":
                    Limpiar_Tabla();
                    listar_encabezado_usuarios("L.stock"); // Ordena por cantidad de stock
                    break;

                default:
                    Limpiar_Tabla();
                    Listar_libros(); // Si no se reconoce la columna, lista todos sin orden
                    break;
            }
        }
    });
}

    /**
    * Obtiene el libro seleccionado en la tabla y transfiere sus datos
    * al formulario Frm_Prestamo. Si no se selecciona ninguna fila, muestra un mensaje.
    */
    private void Seleccionar_Libro(){
        int fila=tb_libros.getSelectedRow();   
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {     
                frmPrestamo.txt_libro.setText(tb_libros.getValueAt(fila,1).toString());
                frmPrestamo.txt_categoria.setText(tb_libros.getValueAt(fila,2).toString());
                frmPrestamo.setId_libro((int) tb_libros.getValueAt(fila, 0));
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
        tb_libros = new javax.swing.JTable();
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
        jLabel1.setText("Lista de Libros");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/lista-libros.png"))); // NOI18N

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tb_libros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Libro", "Categoría", "Autor(es)", "Año de Lanzamiento", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_libros.setFocusable(false);
        tb_libros.setGridColor(new java.awt.Color(255, 255, 255));
        tb_libros.setRowHeight(60);
        tb_libros.setSelectionBackground(new java.awt.Color(20, 25, 53));
        tb_libros.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tb_libros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_librosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_librosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_libros);

        panel15.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel15.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel19.setBackground(new java.awt.Color(255, 153, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/libro.png"))); // NOI18N
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnagregar)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(jSeparator11))
                .addGap(0, 0, 0))
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel15Layout.createSequentialGroup()
                .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnagregar))
                .addGap(0, 0, 0)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Buscar Libro o Categoría:");

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
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(138, 138, 138)
                .addComponent(lb_miniminzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jLabel1))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2)))
                    .addComponent(lb_miniminzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addGap(0, 0, 0)
                .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tb_librosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_librosMousePressed

    }//GEN-LAST:event_tb_librosMousePressed

    
    private void txt_buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyTyped

    }//GEN-LAST:event_txt_buscarKeyTyped
    /**
    * Evento que cambia el aspecto visual del botón de minimizar al pasar el cursor encima.
    * Sirve para dar una mejor experiencia visual (hover).
    */
    private void lb_miniminzarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_miniminzarMouseEntered
        lb_miniminzar.setOpaque(true); // Hace que el fondo sea visible
        lb_miniminzar.setBackground(Color.BLUE); // Establece el color de fondo
        lb_miniminzar.setForeground(Color.white); // Cambia el color del texto a blanco
    }//GEN-LAST:event_lb_miniminzarMouseEntered

    /* 
    * Evento que se activa cuando el mouse sale del ícono de minimizar.
    * Restaura el estado visual original (sin color de fondo y texto negro).
    */
    private void lb_miniminzarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_miniminzarMouseExited
        lb_miniminzar.setOpaque(false); // Elimina el fondo opaco
        lb_miniminzar.setForeground(Color.black); // Restaura el color original del texto
    }//GEN-LAST:event_lb_miniminzarMouseExited

    /**
    * Evento que minimiza (oculta) la ventana cuando el usuario hace clic en el botón de minimizar.
    */
    private void lb_miniminzarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_miniminzarMouseClicked
        setVisible(false); // Oculta la ventana (minimiza visualmente)
    }//GEN-LAST:event_lb_miniminzarMouseClicked

    /**
    * Evento que se dispara al soltar una tecla en el campo de búsqueda.
    * Permite búsqueda dinámica de libros a medida que el usuario escribe.
    * Si no hay coincidencias, muestra un mensaje informativo.
    */
    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        if (!txt_buscar.getText().isEmpty()) {
            /* Verifica si hay coincidencias en la búsqueda */
            if (!libro.buscar(txt_buscar.getText()).isEmpty()) {
                Limpiar_Tabla(); // Limpia la tabla antes de mostrar nuevos datos
                Listar_Busqueda_Libros(); // Muestra los libros que coinciden con la búsqueda
            } else {
                getToolkit().beep(); // Reproduce un sonido si no se encuentra nada
                Mostrar_Mensaje_Tabla_Vacia(); // Informa que no hay resultados
                JOptionPane.showMessageDialog(null, "¡Libro no encontrado!", "Búsqueda Inválida", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            /* Si no hay texto en el campo, se muestran todos los libros */
            Limpiar_Tabla();
            Listar_libros();
        }
    }//GEN-LAST:event_txt_buscarKeyReleased

    /**
    * Evento que se dispara al soltar el clic del mouse sobre la tabla.
    * Si se hace doble clic, se selecciona el libro y se envía al formulario de préstamo.
    */

    private void tb_librosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_librosMouseReleased
        // Validar doble clic
        if (evt.getClickCount() == 2) {
            Seleccionar_Libro();
        }
    }//GEN-LAST:event_tb_librosMouseReleased

    /**
    * Evento que se activa al hacer clic en el botón "agregar".
    * Llama al método para seleccionar un libro desde la tabla.
    */
    private void btnagregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnagregarMouseClicked
        Seleccionar_Libro();
    }//GEN-LAST:event_btnagregarMouseClicked

    

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
    private javax.swing.JTable tb_libros;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
