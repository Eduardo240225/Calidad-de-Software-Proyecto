package Presentacion;

import BussinessObject.Inicio_Sesion;
import BussinessObject.Rol;
import BussinessObject.Usuario;
import Clases_Diseño.TEXTOFANTASMA1;
import TransferObject.Inicio_Sesion_DTO;
import TransferObject.Rol_DTO;
import TransferObject.Usuario_DTO;
import javax.swing.JOptionPane;

/**
 * Clase que representa el formulario de inicio de sesión para el sistema de biblioteca.
 * Hereda de JDialog para mostrar una ventana modal que permite a los usuarios autenticarse.
 * Se conecta con la capa lógica (BO) y la capa de datos (DTO) para validar las credenciales.
 */
public class Frm_Inicio_Sesion extends javax.swing.JDialog {
    
    /** Objeto DTO que almacena los datos de inicio de sesión */
    Inicio_Sesion_DTO inicio_sesion_DTO;
    /** Objeto BO que contiene la lógica del inicio de sesión */
    Inicio_Sesion inicio_sesion;
    /** Objeto DTO que representa los datos del rol del usuario */
    Rol_DTO rol_DTO;
    /** Objeto BO que maneja la lógica relacionada al rol */
    Rol rol;
    /** Objeto DTO que representa los datos del usuario */
    Usuario_DTO usuario_DTO;
    /** Objeto BO que gestiona la lógica del usuario */
    Usuario usuario;
    
    /**
     * Constructor del formulario de inicio de sesión.
     * 
     * @param parent Ventana padre (comúnmente un JFrame).
     * @param modal  Define si la ventana es modal.
     */
    public Frm_Inicio_Sesion(java.awt.Frame parent, boolean modal) {
        /* Llama al constructor de la superclase JDialog para configurar la ventana como modal*/
        super(parent, modal);
        /* Inicializa todos los componentes visuales (botones, etiquetas, campos de texto, etc.)*/
        initComponents();
        /* Centra la ventana en la pantalla*/
        this.setLocationRelativeTo(this);
        /* Crea un efecto de texto fantasma para el campo de usuario con el mensaje "Ingresar usuario"*/
        TEXTOFANTASMA1 user = new TEXTOFANTASMA1("Ingresar usuario", txtusuario);
        /*Crea un efecto de texto fantasma para el campo de contraseña con el mensaje "Ingrese contraseña"*/
        TEXTOFANTASMA1 passw = new TEXTOFANTASMA1("Ingrese contraseña", pass);
        /* Instancia el objeto DTO (Transfer Object) para el inicio de sesión*/
        inicio_sesion_DTO = new Inicio_Sesion_DTO();
        /* Instancia la clase que contiene la lógica del inicio de sesión*/
        inicio_sesion = new Inicio_Sesion();
        /* Instancia el objeto DTO del rol del usuario*/
        rol_DTO = new Rol_DTO();
        /* Instancia la clase que maneja la lógica relacionada al rol*/
        rol = new Rol();
        /* Instancia el objeto DTO que contiene los datos del usuario*/
        usuario_DTO = new Usuario_DTO();
        /* Instancia la clase que maneja la lógica de usuario*/
        usuario = new Usuario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        panel2 = new org.edisoncor.gui.panel.Panel();
        panel3 = new org.edisoncor.gui.panel.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnlogin = new javax.swing.JButton();
        panel5 = new org.edisoncor.gui.panel.Panel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel6 = new org.edisoncor.gui.panel.Panel();
        jLabel4 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        panel7 = new org.edisoncor.gui.panel.Panel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        pass = new javax.swing.JPasswordField();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de sesión - Biblioteca");
        setUndecorated(true);

        panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        panel1.setColorPrimario(java.awt.Color.orange);
        panel1.setColorSecundario(new java.awt.Color(255, 204, 0));
        panel1.setGradiente(org.edisoncor.gui.panel.Panel.Gradiente.ESQUINA_2);

        panel2.setColorPrimario(new java.awt.Color(27, 32, 64));
        panel2.setColorSecundario(new java.awt.Color(27, 32, 64));
        panel2.setGradiente(org.edisoncor.gui.panel.Panel.Gradiente.CIRCULAR);

        panel3.setColorPrimario(new java.awt.Color(255, 204, 0));
        panel3.setColorSecundario(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_book_and_pencil_120px.png"))); // NOI18N

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_w_cute_50px.png"))); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_e_cute_50px.png"))); // NOI18N

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_l_cute_50px.png"))); // NOI18N

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_m_cute_50px.png"))); // NOI18N

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_o_cute_50px.png"))); // NOI18N

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_c_cute_50px.png"))); // NOI18N

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_e_cute_50px.png"))); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_filled_circle_25px.png"))); // NOI18N
        jLabel13.setToolTipText("Salir");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        btnlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/BOTONINICIO_2.png"))); // NOI18N
        btnlogin.setBorder(null);
        btnlogin.setBorderPainted(false);
        btnlogin.setContentAreaFilled(false);
        btnlogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnlogin.setFocusable(false);
        btnlogin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/BOTONINICIO_1.png"))); // NOI18N
        btnlogin.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/BOTONINICIO_2.png"))); // NOI18N
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        panel5.setColorPrimario(new java.awt.Color(255, 204, 0));
        panel5.setColorSecundario(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña:");

        panel6.setColorPrimario(new java.awt.Color(35, 41, 74));
        panel6.setColorSecundario(new java.awt.Color(35, 41, 74));

        jLabel4.setBackground(new java.awt.Color(35, 41, 74));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/User_2.png"))); // NOI18N
        jLabel4.setOpaque(true);

        txtusuario.setBackground(new java.awt.Color(35, 41, 74));
        txtusuario.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtusuario.setForeground(new java.awt.Color(255, 255, 255));
        txtusuario.setBorder(null);
        txtusuario.setCaretColor(new java.awt.Color(255, 255, 255));
        txtusuario.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtusuario.setSelectionColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel7.setColorPrimario(new java.awt.Color(35, 41, 74));
        panel7.setColorSecundario(new java.awt.Color(35, 41, 74));

        jLabel16.setBackground(new java.awt.Color(35, 41, 74));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Password.png"))); // NOI18N
        jLabel16.setOpaque(true);

        pass.setBackground(new java.awt.Color(35, 41, 74));
        pass.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setBorder(null);
        pass.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pass))
            .addGroup(panel7Layout.createSequentialGroup()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btnlogin))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel13)
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnlogin)
                .addGap(24, 24, 24)
                .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OJ91BM0.jpg"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
     * Evento que se ejecuta al hacer clic sobre el ícono de salida (círculo negro).
     * Cierra completamente la aplicación.
     * 
     * @param evt Evento del mouse.
     */
    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel13MouseClicked

    /**
     * Evento que se ejecuta al hacer clic en el botón "Iniciar sesión".
     * Valida las credenciales ingresadas y permite el acceso al menú principal si son correctas.
     * 
     * @param evt Evento del botón.
     */
    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        
        /* Verifica si el campo de usuario está vacío (ignorando mayúsculas/minúsculas)*/
        if (txtusuario.getText().equalsIgnoreCase("")) {
            /* Muestra un mensaje de advertencia si el usuario no ingresó su nombre de usuario*/
            JOptionPane.showMessageDialog(this, "Ingrese usuario", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
            /* Coloca el foco en el campo de texto del usuario*/
            txtusuario.requestFocus();
        /* Verifica si el campo de contraseña está vacío (ignorando mayúsculas/minúsculas)*/
        } else { if (pass.getText().equalsIgnoreCase("")) {
                /* Muestra un mensaje de advertencia si no se ingresó la contraseña*/
                JOptionPane.showMessageDialog(this, "Ingrese contraseña", "Error de Ingreso", JOptionPane.WARNING_MESSAGE);
                /* Coloca el foco en el campo de texto de la contraseña*/
                pass.requestFocus();
        }else {
                /* Llama al método para iniciar sesión, pasándole usuario y contraseña; devuelve un DTO si las credenciales son correctas*/
                inicio_sesion_DTO = inicio_sesion.iniciar_sesion(txtusuario.getText(), pass.getText());
                if (inicio_sesion_DTO != null) {
                    /* Muestra mensaje si el acceso fue exitoso*/
                    JOptionPane.showMessageDialog(this, "Acceso Exitoso");
                    /* Busca los datos del rol según el ID de rol del usuario que inició sesión*/
                    rol_DTO = rol.buscar_por_id(inicio_sesion_DTO.getId_rol());
                    /* Busca los datos del usuario según el ID de sesión*/
                    usuario_DTO = usuario.buscar_sesion(inicio_sesion_DTO.getId());
                    /* Crea y muestra la ventana principal del sistema, pasando el rol y el ID de sesión*/
                    Frm_Menu_Principal p = new Frm_Menu_Principal(rol_DTO.getDescripcion(), inicio_sesion_DTO.getId());
                    p.setVisible(true);
                    /* Establece el texto de bienvenida en la ventana principal*/
                    p.lbl_rol.setText("Bienvenido " + rol_DTO.getDescripcion() + ": "+ usuario_DTO.getNombre()+" "+usuario_DTO.getApellidos());
                    /* Cierra la ventana actual de inicio de sesión*/
                    dispose();
                } else {
                    /* Muestra un mensaje si las credenciales son incorrectas*/
                    JOptionPane.showMessageDialog(this, "Acceso Fallido", "ERROR DE INGRESO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnloginActionPerformed

    /**
     * Método principal que inicia el formulario de inicio de sesión.
     * Ejecuta el formulario como una ventana modal.
     * 
     * @param args Argumentos desde la línea de comandos (no se utilizan).
     */
    public static void main(String args[]) {
        
        /* Ejecuta el siguiente bloque en el hilo de eventos de la interfaz gráfica (para garantizar que el GUI se maneje correctamente)*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            
                /* Método run que contiene lo que se va a ejecutar*/
            public void run() {
                
                /* Crea una instancia del formulario de inicio de sesión, pasándole un JFrame como ventana padre y configurándola como modal*/
                Frm_Inicio_Sesion dialog = new Frm_Inicio_Sesion(new javax.swing.JFrame(), true);
                
                /* Agrega un listener para manejar el evento de cierre de la ventana*/
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                     /* Sobrescribe el método windowClosing para definir qué pasa al cerrar la ventana*/
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                        /* Finaliza completamente la aplicación al cerrar la ventana*/
                        System.exit(0);
                    }
                });
                /* Muestra la ventana de inicio de sesión en pantalla*/
                dialog.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel2;
    private org.edisoncor.gui.panel.Panel panel3;
    private org.edisoncor.gui.panel.Panel panel5;
    private org.edisoncor.gui.panel.Panel panel6;
    private org.edisoncor.gui.panel.Panel panel7;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
