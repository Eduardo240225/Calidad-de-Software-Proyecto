
package Presentacion;

import java.beans.PropertyVetoException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Clase que representa la ventana principal del sistema de biblioteca.
 * Esta clase gestiona el acceso a las funcionalidades del sistema según el rol del usuario (Administrador o Bibliotecario). 
 */
public class Frm_Menu_Principal extends javax.swing.JFrame {   
    /** Almacena el rol del usuario autenticado (por ejemplo: "Administrador" o "Bibliotecario"). */
    private String rol;
    /** ID de la sesión del usuario actualmente logueado. */
    private int id_sesion;

    /**
     * Constructor que inicializa el formulario principal del sistema.
     *
     * @param rol        El rol del usuario autenticado.
     * @param id_sesion  El ID de la sesión activa del usuario.
     */
    public Frm_Menu_Principal(String rol, int id_sesion) {
        /* Asigna el rol recibido al atributo de la clase*/
        this.rol=rol;
        /* Asigna el ID de sesión recibido al atributo correspondiente*/
        this.id_sesion=id_sesion;
        /* Inicializa todos los componentes gráficos de la interfaz*/
        initComponents();
        /* Establece la ventana en modo maximizado (ocupa toda la pantalla)*/
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        /* Llama al método que valida o ajusta las opciones según el rol del usuario*/
        Verificar_rol();
        btn_cambiar_password.setText("<html><center>Cambiar<br>Contraseña</center></html>");
        
    }
    
    /**
     * Método que ajusta la interfaz de acuerdo al rol del usuario.
     * <p>Por ejemplo, oculta el botón "Usuarios" para los roles distintos de "Administrador".</p>
     */
    private void Verificar_rol(){
        /* Si el rol del usuario es "Administrador", se muestra el botón de usuarios*/
        if (rol.equals("Administrador")){
            btn_usuarios.setVisible(true);
        /* Si no, se oculta el botón para restringir el acceso*/
        }else{
            btn_usuarios.setVisible(false);
        }
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        lbl_rol = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btn_cerrar_sesion = new javax.swing.JButton();
        btn_usuarios = new javax.swing.JButton();
        btn_cambiar_password = new javax.swing.JButton();
        btn_prestamos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pagina Principal");
        setUndecorated(true);

        panelImage1.setBackground(new java.awt.Color(255, 204, 153));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/formas-papel-psicodelico-espacio-copia.jpg"))); // NOI18N

        lbl_rol.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        lbl_rol.setText("Bienvenido bibliotecario: ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/avatar.png"))); // NOI18N

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        btn_cerrar_sesion.setBackground(new java.awt.Color(255, 255, 153));
        btn_cerrar_sesion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/salida.png"))); // NOI18N
        btn_cerrar_sesion.setText("Cerrar Sesión");
        btn_cerrar_sesion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51), 5));
        btn_cerrar_sesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar_sesion.setFocusable(false);
        btn_cerrar_sesion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cerrar_sesion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrar_sesionActionPerformed(evt);
            }
        });

        btn_usuarios.setBackground(new java.awt.Color(255, 255, 153));
        btn_usuarios.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Online Community.png"))); // NOI18N
        btn_usuarios.setText("Usuarios");
        btn_usuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51), 5));
        btn_usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_usuarios.setFocusable(false);
        btn_usuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuariosActionPerformed(evt);
            }
        });

        btn_cambiar_password.setBackground(new java.awt.Color(255, 255, 153));
        btn_cambiar_password.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_cambiar_password.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cifrado.png"))); // NOI18N
        btn_cambiar_password.setText("Cambiar \nContraseña");
        btn_cambiar_password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51), 5));
        btn_cambiar_password.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cambiar_password.setFocusable(false);
        btn_cambiar_password.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cambiar_password.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cambiar_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiar_passwordActionPerformed(evt);
            }
        });

        btn_prestamos.setBackground(new java.awt.Color(255, 255, 153));
        btn_prestamos.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_prestamos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/i-Gestion-Prestamos_Libros.png"))); // NOI18N
        btn_prestamos.setText("Préstamos");
        btn_prestamos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51), 5));
        btn_prestamos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_prestamos.setFocusable(false);
        btn_prestamos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_prestamos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_prestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prestamosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 1205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_rol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_prestamos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cerrar_sesion, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(btn_cambiar_password, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addComponent(btn_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_rol)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cerrar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_cambiar_password, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_prestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        escritorio.setLayer(panelImage1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(escritorio)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento que se ejecuta cuando el usuario hace clic en el botón "Cerrar Sesión".
     * <p>Se solicita confirmación y, si se acepta, se cierra esta ventana y se muestra el formulario de inicio de sesión.</p>
     * 
     * @param evt Evento generado por el botón.
     */
    private void btn_cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrar_sesionActionPerformed
        /* Muestra un cuadro de confirmación para preguntar al usuario si desea cerrar sesión*/
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
        /* Si el usuario confirma (elige "Sí")*/
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Cerrar ventana actual (dispose en lugar de setVisible(false))
            Frm_Menu_Principal.this.dispose();

            /* Crea una nueva instancia del formulario de inicio de sesión (sin ventana padre)*/
            Frm_Inicio_Sesion inicio_sesion = new Frm_Inicio_Sesion(null, true);
            /* Centra la ventana de inicio de sesión en la pantalla*/
            inicio_sesion.setLocationRelativeTo(null);
            /* Muestra la ventana de inicio de sesión*/
            inicio_sesion.setVisible(true);
        }

    }//GEN-LAST:event_btn_cerrar_sesionActionPerformed

    /**
     * Evento que abre la ventana de gestión de usuarios, si el usuario tiene permisos.
     *
     * @param evt Evento generado al hacer clic en el botón "Usuarios".
     */
    private void btn_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuariosActionPerformed
        /* Declara una variable para la ventana interna de gestión de usuarios*/
        Frm_Usuarios frmu = null;
        try {
            /* Intenta crear una nueva instancia del formulario de usuarios*/
            frmu = new Frm_Usuarios();
        /* Captura la excepción en caso de error al crear la ventana (por restricciones de ventanas internas)*/
        } catch (PropertyVetoException ex) {
            /* Muestra el error en consola*/
            ex.printStackTrace();
        }
        /* Agrega la ventana de usuarios al panel principal (escritorio)*/
        escritorio.add(frmu);
        /* Muestra la ventana de usuarios*/
        frmu.show();

    }//GEN-LAST:event_btn_usuariosActionPerformed

    /**
     * Evento que permite al usuario cambiar su contraseña.
     *
     * @param evt Evento generado al hacer clic en el botón "Cambiar Contraseña".
     */
    private void btn_cambiar_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiar_passwordActionPerformed
        /* Crea una nueva ventana para cambiar la contraseña, pasando el ID de sesión actual*/
        Frm_Cambio_Password cp = new Frm_Cambio_Password(id_sesion);
        /* Muestra la ventana de cambio de contraseña*/
        cp.setVisible(true);
    }//GEN-LAST:event_btn_cambiar_passwordActionPerformed

    /**
     * Evento que abre el formulario de gestión de préstamos.
     *
     * @param evt Evento generado al hacer clic en el botón "Préstamos".
     */
    private void btn_prestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prestamosActionPerformed
        /* Crea una nueva ventana para el formulario de préstamos, pasando el ID de sesión actual*/
        Frm_Prestamo frmp = new Frm_Prestamo(id_sesion);
        /* Muestra la ventana de formulario de préstamos*/
        frmp.setVisible(true);
    }//GEN-LAST:event_btn_prestamosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiar_password;
    private javax.swing.JButton btn_cerrar_sesion;
    private javax.swing.JButton btn_prestamos;
    private javax.swing.JButton btn_usuarios;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JLabel lbl_rol;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}
