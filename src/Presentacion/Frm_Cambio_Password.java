
package Presentacion;

import BussinessObject.Inicio_Sesion;
import Clases_Diseño.TEXTOFANTASMA1;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Clase que representa el formulario de cambio de contraseña para el sistema de biblioteca.
 * Hereda de JDialog para mostrar una ventana modal que permite a los usuarios cambiar su contraseña.
 * Se conecta con la capa lógica (BO) y la capa de datos (DTO) para validar las credenciales.
 */
public class Frm_Cambio_Password extends javax.swing.JFrame {

    private int id_sesion;
    private Inicio_Sesion inicio_sesion;
    

    /**
    * Crea una nueva instancia del formulario para cambiar la contraseña.
    *
    * @param id_sesion El identificador del usuario con sesión iniciada.
    */
    public Frm_Cambio_Password(int id_sesion) {
        /* Inicializa los componentes gráficos de la ventana (botones, campos, etc.)*/
        initComponents();
        /* Guarda el ID de sesión recibido como parámetro en el atributo de la clase*/
        this.id_sesion = id_sesion;
        /* Centra el formulario en la pantalla*/
        setLocationRelativeTo(this);
        /* Aplica texto guía (placeholder) al campo de la contraseña actual*/
        TEXTOFANTASMA1 contraseña_actual = new TEXTOFANTASMA1("Ingrese Contraseña Actual", txt_password_actual);
        /* Aplica texto guía (placeholder) al campo de la nueva contraseña*/
        TEXTOFANTASMA1 contraseña_nueva = new TEXTOFANTASMA1("Ingrese Contraseña Nueva", txt_nuevo_password);
        /* Crea una instancia del objeto que maneja la lógica de sesión*/
        inicio_sesion = new Inicio_Sesion();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        panel2 = new org.edisoncor.gui.panel.Panel();
        jLabel3 = new javax.swing.JLabel();
        panel6 = new org.edisoncor.gui.panel.Panel();
        jLabel4 = new javax.swing.JLabel();
        txt_password_actual = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        panel7 = new org.edisoncor.gui.panel.Panel();
        jLabel6 = new javax.swing.JLabel();
        txt_nuevo_password = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btn_cambiar = new javax.swing.JButton();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        lblExit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setUndecorated(true);

        panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        panel1.setColorPrimario(new java.awt.Color(255, 204, 0));
        panel1.setColorSecundario(new java.awt.Color(255, 204, 0));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel2.setText("CAMBIO DE CONTRASEÑA");

        panel2.setColorPrimario(new java.awt.Color(27, 32, 64));
        panel2.setColorSecundario(new java.awt.Color(27, 32, 64));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña actual:");

        panel6.setColorPrimario(new java.awt.Color(35, 41, 74));
        panel6.setColorSecundario(new java.awt.Color(35, 41, 74));

        jLabel4.setBackground(new java.awt.Color(35, 41, 74));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/clave.png"))); // NOI18N
        jLabel4.setOpaque(true);

        txt_password_actual.setBackground(new java.awt.Color(35, 41, 74));
        txt_password_actual.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_password_actual.setForeground(new java.awt.Color(255, 255, 255));
        txt_password_actual.setBorder(null);
        txt_password_actual.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_password_actual.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_password_actual.setSelectionColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_password_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_password_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contraseña nueva:");

        panel7.setColorPrimario(new java.awt.Color(35, 41, 74));
        panel7.setColorSecundario(new java.awt.Color(35, 41, 74));

        jLabel6.setBackground(new java.awt.Color(35, 41, 74));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seguro.png"))); // NOI18N
        jLabel6.setOpaque(true);

        txt_nuevo_password.setBackground(new java.awt.Color(35, 41, 74));
        txt_nuevo_password.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nuevo_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_nuevo_password.setBorder(null);
        txt_nuevo_password.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_nuevo_password.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_nuevo_password.setSelectionColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_nuevo_password, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nuevo_password, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btn_cambiar.setBackground(new java.awt.Color(204, 0, 0));
        btn_cambiar.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        btn_cambiar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cambiar.setText("CAMBIAR");
        btn_cambiar.setBorder(null);
        btn_cambiar.setBorderPainted(false);
        btn_cambiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cambiar.setName("btn_cambiar"); // NOI18N
        btn_cambiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cambiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cambiarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_cambiarMouseReleased(evt);
            }
        });
        btn_cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cambiar.png"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        lblExit.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setText("X");
        lblExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblExitMouseReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/restablecer-la-contrasena (1).png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)))
                .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 3, Short.MAX_VALUE))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
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

    private void lblExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseReleased
        
        
    }//GEN-LAST:event_lblExitMouseReleased

    /**
    * Evento al salir el mouse de la etiqueta de cierre (X).
    * Restaura la apariencia original (fondo transparente y texto negro).
    *
    * @param evt Evento del mouse
    */
    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        lblExit.setOpaque(false);
        lblExit.setForeground(Color.black);
    }//GEN-LAST:event_lblExitMouseExited

    /**
    * Evento al hacer clic sobre la etiqueta de cierre (X).
    * Cierra u oculta el formulario actual.
    *
    * @param evt Evento del mouse
    */
    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        setVisible(false);
        
    }//GEN-LAST:event_lblExitMouseClicked

    /**
    * Evento al entrar el mouse sobre la etiqueta de cierre (X).
    * Cambia el fondo a rojo y el texto a blanco para dar feedback visual.
    *
    * @param evt Evento del mouse
    */
    private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseEntered
        lblExit.setOpaque(true);
        lblExit.setBackground(Color.red);
        lblExit.setForeground(Color.white);
    }//GEN-LAST:event_lblExitMouseEntered

    /**
     * Acción al presionar el botón "Cambiar".
     * Valida los campos ingresados, verifica la contraseña actual y actualiza la contraseña si es correcta.
     *
     * @param evt Evento de acción generado por el botón
     */
    private void btn_cambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarActionPerformed
    /* Establece el color de fondo del botón "btn_cambiar" a rojo */
    btn_cambiar.setBackground(Color.red);

    /* Verifica si el campo de texto para la contraseña actual está vacío */
    if (txt_password_actual.getText().isEmpty()){
        /* Muestra un mensaje de advertencia indicando que debe ingresar la contraseña actual */
        JOptionPane.showMessageDialog(null, "¡Por favor, ingrese su contraseña actual!", "¡ERROR!" , JOptionPane.WARNING_MESSAGE);
        /* Coloca el cursor en el campo de la contraseña actual */
        txt_password_actual.requestFocus();
    }
    /* Si el campo de la nueva contraseña está vacío */
    else if (txt_nuevo_password.getText().isEmpty()){
        /* Muestra un mensaje de advertencia indicando que debe ingresar una nueva contraseña */
        JOptionPane.showMessageDialog(null, "¡Por favor, ingrese su nueva contraseña!", "¡ERROR!" , JOptionPane.WARNING_MESSAGE);
        /* Coloca el cursor en el campo de la nueva contraseña */
        txt_nuevo_password.requestFocus();
    }
    else{
        /* Verifica si la contraseña actual ingresada es correcta, llamando al método correspondiente */
        if (inicio_sesion.verificar_contraseña_actual(id_sesion, txt_password_actual.getText())){

            /* Si la contraseña es correcta, intenta actualizarla con la nueva ingresada */
            if (inicio_sesion.actualizar_contraseña(id_sesion, txt_nuevo_password.getText())){
                /* Muestra un mensaje de confirmación de que la contraseña fue actualizada exitosamente */
                JOptionPane.showMessageDialog(null, "¡CONTRASEÑA ACTUALIZADA CON ÉXITO!", "CONTRASEÑA CAMBIADA", JOptionPane.INFORMATION_MESSAGE);
                /* Limpia los campos de texto de las contraseñas */
                txt_password_actual.setText("");
                txt_nuevo_password.setText("");
                /* Coloca el cursor nuevamente en el campo de la contraseña actual */
                txt_password_actual.requestFocus();
            }

        }
        else{
            /* Si la contraseña actual es incorrecta, muestra un mensaje de error */
            JOptionPane.showMessageDialog(null, "¡La contraseña actual ingresada no es correcta!", "CONTRASEÑA ERRÓNEA", JOptionPane.ERROR_MESSAGE);
        }
    }

    }//GEN-LAST:event_btn_cambiarActionPerformed

    private void btn_cambiarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cambiarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cambiarMouseReleased

    /**
    * Evento al pasar el mouse sobre el botón "Cambiar".
    * Cambia el color de fondo del botón para resaltar el hover.
    *
    * @param evt Evento del mouse
    */
    private void btn_cambiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cambiarMouseEntered
        btn_cambiar.setBackground(Color.red);
    }//GEN-LAST:event_btn_cambiarMouseEntered

    /**
    * Evento al quitar el mouse del botón "Cambiar".
    * Restaura el color original del botón.
    *
    * @param evt Evento del mouse
    */
    private void btn_cambiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cambiarMouseExited
        btn_cambiar.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btn_cambiarMouseExited

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblExit;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel2;
    private org.edisoncor.gui.panel.Panel panel6;
    private org.edisoncor.gui.panel.Panel panel7;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public javax.swing.JTextField txt_nuevo_password;
    public javax.swing.JTextField txt_password_actual;
    // End of variables declaration//GEN-END:variables
}
