package Presentacion;

import BussinessObject.Autor;
import Clases_Diseño.TEXTOFANTASMA1;
import TransferObject.AutorDTO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase AutorFrame.
 * Ventana para gestionar autores: permite registrar, modificar, eliminar y buscar autores.
 * Esta clase utiliza la lógica del negocio a través de la clase {@link Autor}
 * y presenta los datos en una interfaz basada en Swing.
 */
public class AutorFrame extends javax.swing.JDialog {

    /** Modelo de datos para la tabla de autores */
    DefaultTableModel modelo;

    /** Objeto de lógica de negocio para autores */
    Autor autor;

    /** Objeto de transferencia de datos del autor */
    AutorDTO autorDTO;

    /** ID del autor seleccionado en la tabla */
    int idAutor;

    /**
     * Constructor del formulario AutorFrame.
     * Inicializa componentes y carga la lista de autores.
     *
     * @param parent Ventana principal (padre)
     * @param modal  Define si la ventana es modal o no
     */
    public AutorFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        textofantasma();
        this.setLocationRelativeTo(this);
        activar();
        modelo = new DefaultTableModel();
        autorDTO = new AutorDTO();
        autor = new Autor();
        listar();
    }

    /**
     * Asigna texto fantasma (placeholders) a los campos del formulario.
     * Mejora la experiencia del usuario indicando qué debe ingresar en cada campo.
     */
    private void textofantasma() {
        new TEXTOFANTASMA1("Ingresar nombre", txtnombre);
        new TEXTOFANTASMA1("Ingresar apellido", txtapellidos);
        new TEXTOFANTASMA1("Ingresar nacionalidad", txtnacionalidad);
        new TEXTOFANTASMA1("Search", txtbuscar);
    }

    /**
     * Limpia todas las filas de la tabla de autores antes de una nueva carga de datos.
     */
    private void limpiarTabla() {
        modelo = (DefaultTableModel) tbAutores.getModel();
        modelo.getDataVector().removeAllElements();
        tbAutores.removeAll();
    }

    /**
     * Limpia todos los campos de texto del formulario y enfoca el cursor en el campo nombre.
     */
    private void limpiarTexto() {
        txtnombre.setText("");
        txtapellidos.setText("");
        txtnacionalidad.setText("");
        txtbuscar.setText("");
        txtnombre.requestFocus();
    }

    /**
     * Lista todos los autores registrados en la base de datos y los muestra en la tabla.
     */
    public void listar() {
        try {
            modelo = (DefaultTableModel) tbAutores.getModel();
            Object[] ob = new Object[5];
            for (AutorDTO p : autor.listar()) {
                ob[0] = p.getIdautor();
                ob[1] = p.getNombre();
                ob[2] = p.getApellidos();
                ob[3] = p.getSexo();
                ob[4] = p.getNacionalidad();
                modelo.addRow(ob);
            }
            tbAutores.setModel(modelo);
        } catch (Exception e) {
            // Error al listar autores
        }
    }

    /**
     * Activa botones y funciones necesarias al iniciar el formulario.
     * Prepara la interfaz para registrar un nuevo autor.
     */
    private void activar() {
        btnnuevo.setEnabled(true);
        btnguardar.setEnabled(true);
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnlimpiar.setEnabled(true);
    }

    /**
     * Ajusta los botones para edición o eliminación de un autor existente.
     * Se llama cuando se selecciona un autor de la tabla.
     */
    private void desactivar() {
        btnnuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btnmodificar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnlimpiar.setEnabled(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel5 = new org.edisoncor.gui.panel.Panel();
        panel6 = new org.edisoncor.gui.panel.Panel();
        panel8 = new org.edisoncor.gui.panel.Panel();
        panel9 = new org.edisoncor.gui.panel.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        panel16 = new org.edisoncor.gui.panel.Panel();
        panel11 = new org.edisoncor.gui.panel.Panel();
        jLabel6 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        panel12 = new org.edisoncor.gui.panel.Panel();
        jLabel7 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        panel17 = new org.edisoncor.gui.panel.Panel();
        jLabel11 = new javax.swing.JLabel();
        txtnacionalidad = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAutores = new javax.swing.JTable();
        panel15 = new org.edisoncor.gui.panel.Panel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtbuscar = new javax.swing.JTextField();
        btnagregar = new javax.swing.JLabel();
        rbnombres = new javax.swing.JRadioButton();
        rbid = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panel13 = new org.edisoncor.gui.panel.Panel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        rbm = new javax.swing.JRadioButton();
        rbf = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Autores");

        panel5.setColorPrimario(new java.awt.Color(255, 204, 0));
        panel5.setColorSecundario(new java.awt.Color(255, 204, 0));
        panel5.setGradiente(org.edisoncor.gui.panel.Panel.Gradiente.ESQUINA_4);

        panel6.setColorPrimario(new java.awt.Color(20, 25, 53));
        panel6.setColorSecundario(new java.awt.Color(20, 25, 53));

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panel8.setColorPrimario(new java.awt.Color(20, 25, 53));
        panel8.setColorSecundario(new java.awt.Color(20, 25, 53));

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        panel9.setColorPrimario(new java.awt.Color(20, 25, 53));
        panel9.setColorSecundario(new java.awt.Color(20, 25, 53));

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 734, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel1.setText("Registro de Autores");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        btnnuevo.setBackground(new java.awt.Color(20, 25, 53));
        btnnuevo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnnuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnnuevo.setText("Nuevo");
        btnnuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btnnuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnuevo.setFocusable(false);
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setBackground(new java.awt.Color(20, 25, 53));
        btnguardar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setText("Guardar");
        btnguardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnguardar.setFocusable(false);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnmodificar.setBackground(new java.awt.Color(20, 25, 53));
        btnmodificar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnmodificar.setForeground(new java.awt.Color(255, 255, 255));
        btnmodificar.setText("Modificar");
        btnmodificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodificar.setFocusable(false);
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(20, 25, 53));
        btneliminar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(255, 255, 255));
        btneliminar.setText("Eliminar");
        btneliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btneliminar.setFocusable(false);
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnlimpiar.setBackground(new java.awt.Color(20, 25, 53));
        btnlimpiar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnlimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnlimpiar.setText("Limpiar");
        btnlimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 25, 53)));
        btnlimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnlimpiar.setFocusable(false);
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        panel16.setColorPrimario(new java.awt.Color(20, 25, 53));
        panel16.setColorSecundario(new java.awt.Color(20, 25, 53));

        javax.swing.GroupLayout panel16Layout = new javax.swing.GroupLayout(panel16);
        panel16.setLayout(panel16Layout);
        panel16Layout.setHorizontalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        panel16Layout.setVerticalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );

        panel11.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel11.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel6.setBackground(new java.awt.Color(255, 153, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Bookshop_2.png"))); // NOI18N
        jLabel6.setOpaque(true);

        txtnombre.setBackground(new java.awt.Color(255, 153, 0));
        txtnombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnombre.setBorder(null);
        txtnombre.setCaretColor(new java.awt.Color(255, 255, 255));
        txtnombre.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtnombre.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombres:");

        panel12.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel12.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel7.setBackground(new java.awt.Color(255, 153, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Identity Theft.png"))); // NOI18N
        jLabel7.setOpaque(true);

        txtapellidos.setBackground(new java.awt.Color(255, 153, 0));
        txtapellidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtapellidos.setBorder(null);
        txtapellidos.setCaretColor(new java.awt.Color(255, 255, 255));
        txtapellidos.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtapellidos.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addGroup(panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel12Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addGroup(panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Apellidos:");

        panel17.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel17.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel11.setBackground(new java.awt.Color(255, 153, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Location.png"))); // NOI18N
        jLabel11.setOpaque(true);

        txtnacionalidad.setBackground(new java.awt.Color(255, 153, 0));
        txtnacionalidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnacionalidad.setBorder(null);
        txtnacionalidad.setCaretColor(new java.awt.Color(255, 255, 255));
        txtnacionalidad.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtnacionalidad.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel17Layout = new javax.swing.GroupLayout(panel17);
        panel17.setLayout(panel17Layout);
        panel17Layout.setHorizontalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel17Layout.createSequentialGroup()
                .addGroup(panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel17Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtnacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel17Layout.setVerticalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel17Layout.createSequentialGroup()
                .addGroup(panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Nacionalidad:");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombres", "Apellidos", "Sexo", "Nacionalidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAutores.setFocusable(false);
        tbAutores.setGridColor(new java.awt.Color(255, 255, 255));
        tbAutores.setRowHeight(30);
        tbAutores.setSelectionBackground(new java.awt.Color(20, 25, 53));
        tbAutores.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbAutores.getTableHeader().setResizingAllowed(false);
        tbAutores.getTableHeader().setReorderingAllowed(false);
        tbAutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbAutoresMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbAutores);

        panel15.setBackground(new java.awt.Color(255, 153, 0));
        panel15.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel15.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel19.setBackground(new java.awt.Color(255, 153, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Search.png"))); // NOI18N
        jLabel19.setOpaque(true);

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        txtbuscar.setBackground(new java.awt.Color(255, 153, 0));
        txtbuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtbuscar.setBorder(null);
        txtbuscar.setCaretColor(new java.awt.Color(255, 255, 255));
        txtbuscar.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtbuscar.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
            }
        });

        btnagregar.setBackground(new java.awt.Color(255, 153, 0));
        btnagregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Add.png"))); // NOI18N
        btnagregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnagregar.setOpaque(true);
        btnagregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnagregarMouseClicked(evt);
            }
        });

        rbnombres.setBackground(new java.awt.Color(255, 153, 0));
        rbnombres.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rbnombres.setText("Nombres");
        rbnombres.setFocusable(false);

        rbid.setBackground(new java.awt.Color(255, 153, 0));
        rbid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rbid.setText("Id");
        rbid.setFocusable(false);

        javax.swing.GroupLayout panel15Layout = new javax.swing.GroupLayout(panel15);
        panel15.setLayout(panel15Layout);
        panel15Layout.setHorizontalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel15Layout.createSequentialGroup()
                .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel15Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbnombres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator11))
                .addGap(0, 0, 0))
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel15Layout.createSequentialGroup()
                .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Buscar Autor:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Sexo:");

        panel13.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel13.setColorSecundario(new java.awt.Color(255, 153, 0));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setBackground(new java.awt.Color(255, 153, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Gender.png"))); // NOI18N
        jLabel16.setOpaque(true);

        rbm.setBackground(new java.awt.Color(255, 153, 0));
        buttonGroup1.add(rbm);
        rbm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbm.setText("M");
        rbm.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rbm.setFocusable(false);

        rbf.setBackground(new java.awt.Color(255, 153, 0));
        buttonGroup1.add(rbf);
        rbf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbf.setText("F");
        rbf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rbf.setFocusable(false);

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13Layout.createSequentialGroup()
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel13Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rbm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbf)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13Layout.createSequentialGroup()
                .addGroup(panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbm, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbf, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel5Layout.createSequentialGroup()
                .addComponent(panel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(0, 48, Short.MAX_VALUE)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(panel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(63, 63, 63))
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(463, 463, 463)))))))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, 0)
                                        .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, 0)
                                        .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(0, 0, 0)
                                        .addComponent(panel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(0, 0, 0)
                                        .addComponent(panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)
                                .addGap(0, 0, 0)
                                .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(panel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * Acción del botón "Nuevo".
    * Activa el formulario y limpia los campos de entrada.
    *
    * @param evt Evento de acción del botón
    */
    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        activar();
        limpiarTexto();
    }//GEN-LAST:event_btnnuevoActionPerformed

    /**
    * Acción del botón "Guardar".
    * Valida los campos y guarda un nuevo autor si la validación es exitosa.
    *
    * @param evt Evento de acción del botón
    */
    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        /* 
        * Verifica si el campo de nombre está vacío.
        * Si lo está, muestra un mensaje de advertencia y enfoca el cursor en el campo.
        */
       if (txtnombre.getText().equalsIgnoreCase("")) {
           JOptionPane.showMessageDialog(this, "Ingrese Nombres de la Editorial", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
           txtnombre.requestFocus();
       } else {
           /* 
            * Verifica si el campo de apellidos está vacío.
            * Si lo está, muestra un mensaje de advertencia y enfoca el cursor.
            */
           if (txtapellidos.getText().equalsIgnoreCase("")) {
               JOptionPane.showMessageDialog(this, "Ingrese Dirrección de la Editorial", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
               txtapellidos.requestFocus();
           } else {
               /* 
                * Verifica si el campo de nacionalidad (posiblemente correo) está vacío.
                * Si lo está, muestra un mensaje de advertencia y enfoca el cursor.
                */
               if (txtnacionalidad.getText().equalsIgnoreCase("")) {
                   JOptionPane.showMessageDialog(this, "Ingrese Email de la Editorial", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
                   txtnacionalidad.requestFocus();
               } else {
                   /* Determina el sexo según el botón de radio seleccionado */
                   String sexo = "";
                   if (rbf.isSelected()) {
                       sexo = "F"; // Femenino
                   } else {
                       sexo = "M"; // Masculino
                   }

                   /* 
                    * Muestra un cuadro de confirmación para guardar los datos.
                    * resp == 0 indica que el usuario hizo clic en "Sí".
                    */
                   int resp = JOptionPane.showConfirmDialog(this, "¿Desea Guardar Editorial?", "Pregunta", JOptionPane.INFORMATION_MESSAGE);
                   if (resp == 0) {
                       /* 
                        * Llama al método agregar() de la clase Autor para registrar los datos.
                        * Luego limpia la tabla, actualiza la lista y borra los campos.
                        */
                       String mensaje = autor.agregar(txtnombre.getText(), txtapellidos.getText(), sexo, txtnacionalidad.getText());
                       limpiarTabla();   // Limpia la tabla actual
                       listar();         // Muestra la lista actualizada
                       limpiarTexto();   // Limpia los campos de entrada
                       JOptionPane.showMessageDialog(this, mensaje, "¡Guardado Exitoso!", JOptionPane.INFORMATION_MESSAGE);
                   }
               }
           }
       }

    }//GEN-LAST:event_btnguardarActionPerformed

    /**
    * Acción del botón "Modificar".
    * Valida los campos y actualiza los datos del autor seleccionado.
    *
    * @param evt Evento de acción del botón
    */
    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        /* 
        * Verifica si el campo nombre está vacío. Si es así, muestra una advertencia y enfoca el campo.
        */
       if (txtnombre.getText().equalsIgnoreCase("")) {
           JOptionPane.showMessageDialog(this, "Ingrese Nombres de la Editorial", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
           txtnombre.requestFocus();
       } else {
           /* 
            * Verifica si el campo apellidos (dirección) está vacío. 
            * Si lo está, muestra un mensaje y enfoca el campo.
            */
           if (txtapellidos.getText().equalsIgnoreCase("")) {
               JOptionPane.showMessageDialog(this, "Ingrese Dirrección de la Editorial", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
               txtapellidos.requestFocus();
           } else {
               /* 
                * Verifica si el campo nacionalidad (email) está vacío. 
                * Si lo está, muestra un mensaje y enfoca el campo.
                */
               if (txtnacionalidad.getText().equalsIgnoreCase("")) {
                   JOptionPane.showMessageDialog(this, "Ingrese Email de la Editorial", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
                   txtnacionalidad.requestFocus();
               } else {
                   /* 
                    * Muestra un cuadro de confirmación antes de proceder con la actualización.
                    * resp == 0 significa que el usuario aceptó.
                    */
                   int resp = JOptionPane.showConfirmDialog(this, "¿Desea Modificar Autor?", "Pregunta", JOptionPane.INFORMATION_MESSAGE);
                   if (resp == 0) {
                       String mensaje = "";

                       /* Obtiene el índice de la fila seleccionada en la tabla */
                       int fila = tbAutores.getSelectedRow();

                       /* Verifica si hay una fila seleccionada */
                       if (fila == -1) {
                           JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
                       } else {
                           /* Determina el sexo del autor según el radio button seleccionado */
                           String sexo = "";
                           if (rbf.isSelected()) {
                               sexo = "F"; // Femenino
                           } else {
                               sexo = "M"; // Masculino
                           }

                           /* Llama al método actualizar de la clase Autor para modificar los datos */
                           mensaje = autor.actualizar(idAutor, txtnombre.getText(), txtapellidos.getText(), sexo, txtnacionalidad.getText());
                       }

                       /* Refresca la interfaz: limpia tabla, vuelve a listar, borra campos y activa estado inicial */
                       limpiarTabla();
                       listar();
                       limpiarTexto();
                       activar();

                       /* Muestra mensaje de éxito */
                       JOptionPane.showMessageDialog(this, mensaje, "¡Actualización Exitosa!", JOptionPane.INFORMATION_MESSAGE);
                   }
               }
           }
       }


    }//GEN-LAST:event_btnmodificarActionPerformed

    /**
    * Acción del botón "Eliminar".
    * Elimina el autor actualmente seleccionado en la tabla.
    *
    * @param evt Evento de acción del botón
    */
    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        /* 
        * Bloque que permite eliminar un autor seleccionado desde la tabla `tbAutores`.
        */
       String mensaje = "";  // Variable para almacenar el mensaje de confirmación o error

       /* Obtiene la fila seleccionada de la tabla */
       int fila = tbAutores.getSelectedRow();

       /* Verifica si se ha seleccionado alguna fila */
       if (fila == -1) {
           // Si no se ha seleccionado, muestra un mensaje de advertencia
           JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
       } else {
           // Si hay una fila seleccionada, se procede a eliminar el autor usando su ID
           mensaje = autor.eliminar(idAutor);
       }

       /* Limpia los datos de la tabla para evitar duplicados */
       limpiarTabla();

       /* Llama al método listar() para volver a cargar los autores actualizados */
       listar();

       /* Muestra el mensaje recibido tras la eliminación */
       JOptionPane.showMessageDialog(this, mensaje, "¡Eliminado Exitoso!", JOptionPane.INFORMATION_MESSAGE);

       /* Ejecuta la acción del botón "Nuevo" para restablecer el formulario */
       btnnuevoActionPerformed(evt);

    }//GEN-LAST:event_btneliminarActionPerformed

    /**
    * Acción del botón "Limpiar".
    * Equivale a hacer clic en "Nuevo": limpia campos y activa controles.
    *
    * @param evt Evento de acción del botón
    */
    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        btnnuevoActionPerformed(evt);
    }//GEN-LAST:event_btnlimpiarActionPerformed

    /**
    * Acción al presionar una fila de la tabla de autores.
    * Extrae los datos del autor seleccionado y los carga en los campos.
    *
    * @param evt Evento de mouse sobre la tabla
    */
    private void tbAutoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAutoresMousePressed
        /* 
        * Obtiene el índice de la fila seleccionada en la tabla de autores.
        */
       int fila = tbAutores.getSelectedRow();

       /* 
        * Verifica si no se ha seleccionado ninguna fila.
        * Si es así, muestra un mensaje de advertencia.
        */
       if (fila == -1) {
           JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
       } else {
           /*
            * Si hay una fila seleccionada, extrae el ID del autor de la columna 0
            * y lo convierte a entero para almacenarlo en la variable `idAutor`.
            */
           idAutor = Integer.parseInt(tbAutores.getValueAt(fila, 0).toString());

           /* Llena los campos del formulario con los datos del autor seleccionado */
           txtnombre.setText(tbAutores.getValueAt(fila, 1).toString());         // Nombre del autor
           txtapellidos.setText(tbAutores.getValueAt(fila, 2).toString());      // Apellidos del autor
           txtnacionalidad.setText(tbAutores.getValueAt(fila, 4).toString());   // Nacionalidad del autor

           /* Cambia el estado de los botones: activa "Modificar" y "Eliminar", desactiva "Guardar" */
           desactivar();
       }

    }//GEN-LAST:event_tbAutoresMousePressed
    
    /**
     * Evento que se dispara al soltar una tecla en el campo de búsqueda.
     * Permite buscar por ID si está seleccionada la opción correspondiente.
     *
     * @param evt Evento de tecla liberada
     */
    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        /* 
        * Verifica si el radio button "rbid" (buscar por ID) está seleccionado.
        */
       if (rbid.isSelected()) {
           char verificar = evt.getKeyChar();  // Obtiene el carácter presionado por el usuario

           /* 
            * Valida si el carácter ingresado es una letra.
            * Si es así, se reproduce un sonido de advertencia, se cancela la entrada,
            * se muestra un mensaje, y se limpia el campo de búsqueda.
            */
           if (Character.isLetter(verificar)) {
               getToolkit().beep();  // Reproduce sonido
               evt.consume();        // Cancela la escritura del carácter
               JOptionPane.showMessageDialog(this, "¡Ingrese solo números!", "Advertencia", JOptionPane.WARNING_MESSAGE);
               txtbuscar.setText("");  // Limpia el campo de búsqueda
           } else {
               /*
                * Si el texto no está vacío y es numérico, se procede a buscar el autor por su ID.
                */
               if (!txtbuscar.getText().isEmpty()) {
                   idAutor = Integer.parseInt(txtbuscar.getText());  // Convierte el texto a entero
                   autorDTO = autor.buscar(idAutor);                 // Busca el autor con ese ID

                   if (autorDTO != null) {
                       limpiarTabla();  // Limpia la tabla antes de mostrar resultados

                       // Configura el modelo de la tabla y agrega una fila con los datos encontrados
                       modelo = (DefaultTableModel) tbAutores.getModel();
                       Object[] ob = new Object[5];
                       ob[0] = autorDTO.getIdautor();
                       ob[1] = autorDTO.getNombre();
                       ob[2] = autorDTO.getApellidos();
                       ob[3] = autorDTO.getSexo();
                       ob[4] = autorDTO.getNacionalidad();
                       modelo.addRow(ob);
                       tbAutores.setModel(modelo);

                       // Muestra mensaje de éxito
                       JOptionPane.showMessageDialog(this, "Autor encontrado", "¡Busqueda Exitosa!", JOptionPane.INFORMATION_MESSAGE);
                   } else {
                       // Si no se encontró el autor
                       JOptionPane.showMessageDialog(this, "Autor no encontrado", "¡Busqueda Fallida!", JOptionPane.INFORMATION_MESSAGE);
                       limpiarTabla();
                   }
               } else {
                   // Si el campo de búsqueda está vacío, se lista todo nuevamente
                   limpiarTabla();
                   listar();
               }
           }
       }

       /* 
        * Si el radio button "rbnombres" está seleccionado (buscar por nombre), el código aún no está implementado.
        */
       if (rbnombres.isSelected()) {
           //
       }

    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void btnagregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnagregarMouseClicked
       //boton_agregar_pagina_libro
    }//GEN-LAST:event_btnagregarMouseClicked

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AutorFrame dialog = new AutorFrame(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnagregar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private org.edisoncor.gui.panel.Panel panel11;
    private org.edisoncor.gui.panel.Panel panel12;
    private org.edisoncor.gui.panel.Panel panel13;
    private org.edisoncor.gui.panel.Panel panel15;
    private org.edisoncor.gui.panel.Panel panel16;
    private org.edisoncor.gui.panel.Panel panel17;
    private org.edisoncor.gui.panel.Panel panel5;
    private org.edisoncor.gui.panel.Panel panel6;
    private org.edisoncor.gui.panel.Panel panel8;
    private org.edisoncor.gui.panel.Panel panel9;
    private javax.swing.JRadioButton rbf;
    private javax.swing.JRadioButton rbid;
    private javax.swing.JRadioButton rbm;
    private javax.swing.JRadioButton rbnombres;
    private javax.swing.JTable tbAutores;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtnacionalidad;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
