
package Presentacion;

import BussinessObject.Inicio_Sesion;
import BussinessObject.Rol;
import BussinessObject.Usuario;
import Clases_Diseño.TEXTOFANTASMA1;
import TransferObject.Inicio_Sesion_DTO;
import TransferObject.Rol_DTO;
import TransferObject.Usuario_DTO;
import java.awt.Color;
import java.beans.PropertyVetoException;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Clase que representa el formulario interno para la gestión de usuarios del sistema.
 * 
 * <p>Permite registrar, modificar, buscar, listar y ordenar usuarios, incluyendo datos personales,
 * de contacto, rol, credenciales de inicio de sesión y sexo.
*/ 
public class Frm_Usuarios extends javax.swing.JInternalFrame {
        /** Modelo de tabla utilizado para mostrar la lista de usuarios. */
    DefaultTableModel modelo;

    /** Modelo del ComboBox utilizado para mostrar los roles disponibles. */
    DefaultComboBoxModel modelo_combo;

    /** Objeto DTO que representa un rol seleccionado. */
    Rol_DTO rol_DTO;

    /** Objeto de negocio para la gestión de roles. */
    Rol rol;

    /** Objeto DTO que representa un usuario. */
    Usuario_DTO usuario_DTO;

    /** Objeto de negocio para la gestión de usuarios. */
    Usuario usuario;

    /** Objeto DTO para gestionar las credenciales de inicio de sesión. */
    Inicio_Sesion_DTO inicio_sesion_DTO;

    /** Objeto de negocio para gestionar el inicio de sesión. */
    Inicio_Sesion inicio_sesion;

    /** Agrupador de botones de selección de sexo (Masculino/Femenino). */
    ButtonGroup gcb_sexo = new ButtonGroup();

    /** ID del usuario que se está gestionando actualmente. */
    int id_usuario;

    /** ID de la sesión activa que creó o gestiona al usuario. */
    int id_sesion;

    
    /**
     * Constructor del formulario de gestión de usuarios.
     * 
     * <p>Inicializa todos los componentes gráficos, configura el formulario como ventana interna,
     * y prepara el entorno visual para registrar y gestionar usuarios.
    */ 
    public Frm_Usuarios() throws PropertyVetoException {
        /* Inicializa todos los elementos de la interfaz gráfica*/        
        initComponents();
         /*Posiciona el formulario en la esquina superior izquierda*/
        this.setLocation(0, 0);
        /* Maximiza el formulario dentro del escritorio principal*/
        this.setMaximum(true);
        /* Cierra solo esta ventana sin afectar otras*/
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        /* Agrega texto de ayuda o estilo a los campos de entrada*/
        texto_campos();
        rol_DTO = new Rol_DTO();
        rol = new Rol();
        usuario_DTO = new Usuario_DTO();
        usuario = new Usuario();
        inicio_sesion_DTO = new Inicio_Sesion_DTO();
        inicio_sesion = new Inicio_Sesion();
        /* Muestra todos los usuarios en la tabla*/
        listar_usuarios();
        /* Llena el comboBox con roles disponibles*/
        listar_roles();
        /* Agrupa los radio buttons de sexo*/
        gcb_sexo.add(rb_m);
        gcb_sexo.add(rb_f);
        /* Personaliza el estilo de los encabezados de la tabla*/
        ordenar_encabezado();
        
    }
    
    /**
    * Agrega placeholders o texto guía a los campos de entrada del formulario.
    * 
    * <p>Estos textos se muestran dentro de los campos para orientar al usuario 
    * sobre qué debe ingresar, y desaparecen cuando el campo recibe el foco.
    */
    private void texto_campos(){
        /* Agrega un texto fantasma al campo "Nombre"*/
        TEXTOFANTASMA1 nombre = new TEXTOFANTASMA1("Ingrese Nombre",txt_nombres);
         /* Agrega un texto fantasma al campo "Apellidos"*/
        TEXTOFANTASMA1 apellidos = new TEXTOFANTASMA1("Ingrese Apellidos",txt_apellidos);
        /* Agrega un texto fantasma al campo "DNI"*/
        TEXTOFANTASMA1 dni = new TEXTOFANTASMA1("Ingrese D.N.I",txt_dni);
        /* Agrega un texto fantasma al campo "Teléfono"*/
        TEXTOFANTASMA1 telefono = new TEXTOFANTASMA1("Ingrese Teléfono",txt_telefono);
        /* Agrega un texto fantasma al campo "Correo Electrónico"*/
        TEXTOFANTASMA1 correo = new TEXTOFANTASMA1("Ingrese Correo Electrónico",txt_correo);
        /* Agrega un texto fantasma al campo "Usuario"*/
        TEXTOFANTASMA1 usuario = new TEXTOFANTASMA1("Ingrese Usuario",txt_usuario);
        /*Agrega un texto fantasma al campo "Contraseña"*/
        TEXTOFANTASMA1 contraseña = new TEXTOFANTASMA1("Ingrese Contraseña",txt_contraseña);
        /*Agrega un texto fantasma al campo "buscar"*/
        TEXTOFANTASMA1 buscar = new TEXTOFANTASMA1 ("Busque por DNI, Nombres o Apellidos",txt_buscar);
    }
    
    /**
    * Llena el ComboBox de roles con la lista obtenida desde la capa de negocio.
    * 
    * <p>Cada rol es agregado por su descripción para que pueda ser seleccionado 
    * por el usuario al registrar o modificar datos.
    */
    private void listar_roles(){
        try{
            /* Obtiene el modelo actual del comboBox de roles*/
            modelo_combo = (DefaultComboBoxModel)cb_rol.getModel();
            /* Recorre la lista de roles y agrega su descripción al comboBox*/
            for (Rol_DTO r : rol.listar()){
                modelo_combo.addElement(r.getDescripcion());
            }
            /* Establece el modelo actualizado en el comboBox*/
            cb_rol.setModel(modelo_combo);
        /* Captura cualquier error (aunque no se maneja aquí)*/   
        }catch (Exception e){
        
        }
    }
    
    /**
    * Lista todos los usuarios registrados en el sistema.
    * 
    * <p>Obtiene la información desde la capa de negocio, construye las filas de 
    * la tabla e incluye datos como nombre, apellidos, sexo, rol, y credenciales.
    * 
    * <p>Este método también convierte los valores de sexo (M/F) a formato legible.
    */
    private void listar_usuarios(){
        try{
            /* Obtiene el modelo actual de la tabla de usuarios*/
            modelo = (DefaultTableModel) tb_usuarios.getModel();
            /* Crea un arreglo de objetos con 10 columnas (campos del usuario)*/
            Object[] ob = new Object[10];
            /* Recorre todos los usuarios listados*/
            for (Usuario_DTO u : usuario.listar()){
                ob[0] = u.getId();
                ob[1] = rol.buscar_por_id(inicio_sesion.buscar_por_id(u.getId_sesion()).getId_rol()).getDescripcion();
                ob[2] = u.getDni();
                ob[3] = u.getNombre();
                ob[4] = u.getApellidos();
                /* Convierte el sexo de 'M' o 'F' a texto completo*/
                String sexo = null;
                if (String.valueOf(u.getSexo()).equals("M")){
                    sexo="Masculino";
                }else if (String.valueOf(u.getSexo()).equals("F")){
                    sexo="Femenino";
                }
                ob[5] = sexo;
                ob[6] = u.getTelefono();
                ob[7] = u.getCorreo();
                ob[8] = inicio_sesion.buscar_por_id(u.getId_sesion()).getUsuario();
                ob[9] = inicio_sesion.buscar_por_id(u.getId_sesion()).getContraseña();
                
                modelo.addRow(ob);
            }
                /* Establece el modelo actualizado en la tabla*/
                tb_usuarios.setModel(modelo);
            }catch (Exception e){
                /* Imprime errores en consola */
                System.out.println(e.getMessage());
            }
    }
    
    /**
    * Limpia todos los campos del formulario de usuarios.
    * 
    * <p>Se utiliza después de registrar, modificar o cancelar una operación 
    * para restablecer el formulario a su estado inicial.
    */
    private void limpiar_campos(){
        txt_nombres.setText("");
        txt_apellidos.setText("");
        txt_dni.setText("");
        txt_telefono.setText("");
        gcb_sexo.clearSelection();
        txt_correo.setText("");
        cb_rol.setSelectedIndex(0);
        txt_usuario.setText("");
        txt_contraseña.setText("");
        txt_buscar.setText("");
        txt_nombres.requestFocus();
    }
    
    /**
    * Verifica si el correo electrónico ingresado tiene un formato válido.
    * 
    * @return true si contiene "@" y un punto después del arroba; false en caso contrario.
    */
    private boolean verificar_correo() {
        /* Verifica que el correo contenga "@" y que el "." venga después del "@"*/
        return txt_correo.getText().contains("@") && txt_correo.getText().lastIndexOf(".") > txt_correo.getText().indexOf("@"); 
    }
    
    /**
    * Verifica si la contraseña ingresada cumple con los requisitos de seguridad.
    * 
    * @return true si tiene al menos 8 caracteres, una letra, un número y un símbolo especial.
    */
    private boolean verificar_contraseña() {
        /* Si la longitud es menor a 8, no es válida*/
        if (txt_contraseña.getText().length() < 8) return false;
         /* Verifica si contiene al menos una letra*/
        boolean tieneLetra = txt_contraseña.getText().matches(".*[a-zA-Z].*");
        /* Verifica si contiene al menos un número*/
        boolean tieneNumero = txt_contraseña.getText().matches(".*\\d.*");
         /* Verifica si contiene al menos un símbolo especial*/
        boolean tieneSimbolo = txt_contraseña.getText().matches(".*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|].*");
        /* Retorna true solo si cumple con todas las condiciones*/
        return tieneLetra && tieneNumero && tieneSimbolo;
    }
    
    /**
    * Elimina todas las filas actuales de la tabla de usuarios.
    * 
    * <p>Este método se utiliza previo a una nueva carga de datos para evitar duplicados.
    */
    private void limpiar_tabla(){
        /* Obtiene el modelo de la tabla*/
        modelo = (DefaultTableModel) tb_usuarios.getModel();
         /* Elimina todas las filas*/
        modelo.getDataVector().removeAllElements();
         /* Limpia visualmente la tabla*/
        tb_usuarios.removeAll();
    }
   
    /**
    * Verifica que todos los campos obligatorios del formulario hayan sido completados correctamente.
    * 
    * <p>Incluye validaciones para DNI, teléfono, correo, rol, sexo, usuario y contraseña.
    * 
    * @return true si todos los campos están correctamente validados; false si hay errores.
    */
    private boolean verificar_campos(){
        boolean verificado=false;
         /* Verifica si el campo de nombres está vacío*/
        if (txt_nombres.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "¡Por favor ingrese un nombre!", "CAMPO VACÍO", JOptionPane.WARNING_MESSAGE);
            txt_nombres.requestFocus();
            
             /* Verifica si el campo de apellidos está vacío*/
        } else if (txt_apellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Por favor ingrese apellidos!", "CAMPO VACÍO", JOptionPane.WARNING_MESSAGE);
            txt_apellidos.requestFocus();
            
            /* Verifica si el DNI tiene menos de 8 caracteres*/
        } else if (txt_dni.getText().length()<8) {
            JOptionPane.showMessageDialog(null, "¡Por favor ingrese un número de DNI válido!", "DNI INVÁLIDO", JOptionPane.WARNING_MESSAGE);
            txt_dni.requestFocus();
            
             /* Verifica si el teléfono tiene menos de 9 caracteres*/
        } else if (txt_telefono.getText().length()<9) {
            JOptionPane.showMessageDialog(null, "¡Por favor ingrese un número de teléfono válido!", "TELÉFONO INVÁLIDO", JOptionPane.WARNING_MESSAGE);
            txt_telefono.requestFocus();
            
            /* Verifica que el correo electrónico sea válido*/
        } else if (!verificar_correo()) {
            JOptionPane.showMessageDialog(null, "¡Por favor ingrese una dirección de correo electrónico válida!", "CORREO INVÁLIDO", JOptionPane.WARNING_MESSAGE);
            txt_correo.requestFocus();
            
            /* Verifica si el usuario está vacío*/
        } else if (txt_usuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Por favor ingrese un usuario!", "CAMPO VACÍO", JOptionPane.WARNING_MESSAGE);
            txt_usuario.requestFocus();
            
               /* Verifica si la contraseña cumple requisitos*/
        } else if (!verificar_contraseña()) {
            JOptionPane.showMessageDialog(null, """
                                                La contraseña no cumple con los requisitos:
                                                        - Mínimo 8 caracteres
                                                        - Incluir letras, números y símbolos
                                                       ""","CONTRASEÑA INVÁLIDA", JOptionPane.WARNING_MESSAGE);
            txt_contraseña.requestFocus();
            
             /* Verifica si se ha seleccionado un rol*/
        } else if (cb_rol.getSelectedIndex()==0) {
            JOptionPane.showMessageDialog(null, "¡Por favor seleccione un rol!", "SELECCIÓN VACÍA", JOptionPane.WARNING_MESSAGE);
            cb_rol.requestFocus();
            
            /* Verifica si se ha seleccionado un sexo*/
        } else if (gcb_sexo.getSelection()==null){
            JOptionPane.showMessageDialog(null, "¡Por favor seleccione un sexo!", "SELECCIÓN VACÍA", JOptionPane.WARNING_MESSAGE);
        }else{
            /* Todos los campos están verificados*/
            return verificado=true;
        }
        /* Retorna false si falla alguna validación*/
        return verificado;
    }
    
    /**
    * Realiza una búsqueda de usuarios según el texto ingresado en el campo `txt_buscar`.
    * 
    * <p>Filtra por nombre, apellido o DNI, y muestra los resultados en la tabla.
    */
   private void listar_busqueda_usuarios() {
       try {
           // Se obtiene el modelo de la tabla para manipular sus filas
           modelo = (DefaultTableModel) tb_usuarios.getModel();

           // Se declara un array de objetos con 10 columnas (coincidiendo con las columnas de la tabla)
           Object[] ob = new Object[10];

           // Se recorre la lista de usuarios que coinciden con el texto ingresado
           for (Usuario_DTO u : usuario.buscar(txt_buscar.getText())) {
               ob[0] = u.getId(); // ID del usuario

               // Se obtiene la descripción del rol a través del ID de sesión → ID de rol
               ob[1] = rol.buscar_por_id(
                           inicio_sesion.buscar_por_id(u.getId_sesion()).getId_rol()
                      ).getDescripcion();

               ob[2] = u.getDni();        // DNI del usuario
               ob[3] = u.getNombre();     // Nombre
               ob[4] = u.getApellidos();  // Apellidos

               // Conversión de código de sexo a texto legible
               String sexo = null;
               if (String.valueOf(u.getSexo()).equals("M")) {
                   sexo = "Masculino";
               } else if (String.valueOf(u.getSexo()).equals("F")) {
                   sexo = "Femenino";
               }
               ob[5] = sexo;

               ob[6] = u.getTelefono();   // Teléfono
               ob[7] = u.getCorreo();     // Correo electrónico

               // Se obtienen los datos de inicio de sesión (usuario y contraseña) a partir del ID de sesión
               ob[8] = inicio_sesion.buscar_por_id(u.getId_sesion()).getUsuario();       // Nombre de usuario
               ob[9] = inicio_sesion.buscar_por_id(u.getId_sesion()).getContraseña();    // Contraseña (idealmente no debe mostrarse)

               // Se agrega la fila al modelo de la tabla
               modelo.addRow(ob);
           }

           // Se actualiza la tabla con el modelo cargado
           tb_usuarios.setModel(modelo);

       } catch (Exception e) {
           // En caso de error, se imprime el mensaje en consola
           System.out.println(e.getMessage());
       }
   }

    
    /**
    * Muestra un mensaje dentro de la tabla indicando que no se encontraron resultados.
    * 
    * <p>Este método se utiliza cuando una búsqueda no retorna coincidencias.
    */
   private void mostrar_mensaje_tabla_vacia() {
       // Se obtiene el modelo de la tabla de usuarios
       modelo = (DefaultTableModel) tb_usuarios.getModel();

       modelo.setRowCount(0); // Asegura que el modelo esté completamente vacío

       tb_usuarios.setModel(modelo);
   }

    
    /**
    * Lista los usuarios ordenados por el encabezado de columna seleccionado.
    * 
    * @param encabezado El nombre del campo de la base de datos por el cual ordenar (ej. "nombre", "dni").
    */
   private void listar_encabezado_usuarios(String encabezado) {
       try {
           // Se obtiene el modelo de la tabla de usuarios para poder añadir nuevas filas
           modelo = (DefaultTableModel) tb_usuarios.getModel();

           // Arreglo temporal para almacenar los valores de cada fila (10 columnas)
           Object[] ob = new Object[10];

           /*
            * Se itera sobre los usuarios devueltos por el método listar_encabezado()
            * que filtra y ordena según el texto ingresado y el encabezado especificado.
            */
           for (Usuario_DTO u : usuario.listar_encabezado(txt_buscar.getText(), encabezado)) {
               ob[0] = u.getId();  // ID del usuario

               // Se obtiene la descripción del rol del usuario a partir de su sesión
               ob[1] = rol.buscar_por_id(
                           inicio_sesion.buscar_por_id(u.getId_sesion()).getId_rol()
                      ).getDescripcion();

               ob[2] = u.getDni();         // DNI del usuario
               ob[3] = u.getNombre();      // Nombre del usuario
               ob[4] = u.getApellidos();   // Apellidos del usuario

               // Conversión del valor del sexo ('M' o 'F') a un formato legible
               String sexo = null;
               if (String.valueOf(u.getSexo()).equals("M")) {
                   sexo = "Masculino";
               } else if (String.valueOf(u.getSexo()).equals("F")) {
                   sexo = "Femenino";
               }
               ob[5] = sexo;

               ob[6] = u.getTelefono();    // Teléfono del usuario
               ob[7] = u.getCorreo();      // Correo electrónico

               // Se obtienen las credenciales de sesión del usuario
               ob[8] = inicio_sesion.buscar_por_id(u.getId_sesion()).getUsuario();       // Nombre de usuario
               ob[9] = inicio_sesion.buscar_por_id(u.getId_sesion()).getContraseña();    // Contraseña (por seguridad no debería mostrarse)

               // Se agrega la fila al modelo
               modelo.addRow(ob);
           }

           // Se actualiza la tabla con los datos ordenados
           tb_usuarios.setModel(modelo);

       } catch (Exception e) {
           // En caso de error, se imprime el mensaje en consola
           System.out.println(e.getMessage());
       }
   }

    
    /**
    * Habilita la funcionalidad de ordenar la tabla de usuarios al hacer clic en los encabezados.
    * 
    * <p>Dependiendo del encabezado seleccionado, se invoca el ordenamiento correspondiente.
    */
   private void ordenar_encabezado() {
       // Se obtiene el encabezado (header) de la tabla
       JTableHeader header = tb_usuarios.getTableHeader();

       /*
        * Se agrega un evento de clic al encabezado para detectar qué columna fue seleccionada
        */
       header.addMouseListener(new java.awt.event.MouseAdapter() {
           @Override
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               // Se obtiene el índice de la columna donde se hizo clic
               int columna = header.columnAtPoint(evt.getPoint());

               // Se obtiene el nombre (título) de dicha columna
               String nombre_columna = tb_usuarios.getColumnName(columna);

               // Según el encabezado seleccionado, se ejecuta el ordenamiento correspondiente
               switch (nombre_columna) {
                   case "Id":
                       limpiar_tabla();
                       listar_encabezado_usuarios("u.id");
                       break;
                   case "Rol":
                       limpiar_tabla();
                       listar_encabezado_usuarios("descripcion");
                       break;
                   case "DNI":
                       limpiar_tabla();
                       listar_encabezado_usuarios("dni");
                       break;
                   case "Nombre":
                       limpiar_tabla();
                       listar_encabezado_usuarios("nombre");
                       break;
                   case "Apellidos":
                       limpiar_tabla();
                       listar_encabezado_usuarios("apellidos");
                       break;
                   case "Sexo":
                       limpiar_tabla();
                       listar_encabezado_usuarios("sexo");
                       break;
                   case "Teléfono":
                       limpiar_tabla();
                       listar_encabezado_usuarios("telefono");
                       break;
                   case "Correo":
                       limpiar_tabla();
                       listar_encabezado_usuarios("correo");
                       break;
                   case "Usuario":
                       limpiar_tabla();
                       listar_encabezado_usuarios("usuario");
                       break;
                   case "Contraseña":
                       limpiar_tabla();
                       listar_encabezado_usuarios("contraseña");
                       break;

                   // En caso de que el encabezado no coincida con ninguno de los anteriores
                   default:
                       limpiar_tabla();
                       listar_usuarios(); // Lista completa sin orden específico
                       break;
               }

           }
       });
   }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        panel2 = new org.edisoncor.gui.panel.Panel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panel8 = new org.edisoncor.gui.panel.Panel();
        jLabel6 = new javax.swing.JLabel();
        txt_nombres = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        panel6 = new org.edisoncor.gui.panel.Panel();
        jLabel4 = new javax.swing.JLabel();
        txt_apellidos = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        panel7 = new org.edisoncor.gui.panel.Panel();
        jLabel8 = new javax.swing.JLabel();
        txt_dni = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        panel9 = new org.edisoncor.gui.panel.Panel();
        jLabel10 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        panel11 = new org.edisoncor.gui.panel.Panel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        rb_m = new javax.swing.JRadioButton();
        rb_f = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panel14 = new org.edisoncor.gui.panel.Panel();
        jLabel18 = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        panel12 = new org.edisoncor.gui.panel.Panel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        cb_rol = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        panel10 = new org.edisoncor.gui.panel.Panel();
        jLabel19 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        panel13 = new org.edisoncor.gui.panel.Panel();
        jLabel20 = new javax.swing.JLabel();
        txt_contraseña = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        btn_guardar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_usuarios = new javax.swing.JTable();
        panel16 = new org.edisoncor.gui.panel.Panel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        txt_buscar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("REGISTRO DE USUARIOS");
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(900, 762));

        panel1.setColorPrimario(new java.awt.Color(20, 25, 53));
        panel1.setColorSecundario(new java.awt.Color(20, 25, 53));
        panel1.setPreferredSize(new java.awt.Dimension(1418, 726));

        panelImage1.setBackground(new java.awt.Color(255, 255, 0));
        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/73Z_2108.w023.n001.896B.p1.896.jpg"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel2.setColorPrimario(new java.awt.Color(255, 204, 51));
        panel2.setColorSecundario(new java.awt.Color(255, 204, 0));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Community.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel1.setText("Registro de Usuarios");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Apellidos:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombres:");

        panel8.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel8.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel6.setBackground(new java.awt.Color(255, 153, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/User_2.png"))); // NOI18N
        jLabel6.setOpaque(true);

        txt_nombres.setBackground(new java.awt.Color(255, 153, 0));
        txt_nombres.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_nombres.setBorder(null);
        txt_nombres.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_nombres.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_nombres.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_nombres.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator3.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel8Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombres)))
                .addGap(68, 68, 68))
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel6.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel6.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel4.setBackground(new java.awt.Color(255, 153, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/User_2.png"))); // NOI18N
        jLabel4.setOpaque(true);

        txt_apellidos.setBackground(new java.awt.Color(255, 153, 0));
        txt_apellidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_apellidos.setBorder(null);
        txt_apellidos.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_apellidos.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_apellidos.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator2.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_apellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("D.N.I:");

        panel7.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel7.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel8.setBackground(new java.awt.Color(255, 153, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Identity Theft.png"))); // NOI18N
        jLabel8.setOpaque(true);

        txt_dni.setBackground(new java.awt.Color(255, 153, 0));
        txt_dni.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_dni.setBorder(null);
        txt_dni.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_dni.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_dni.setSelectionColor(new java.awt.Color(255, 255, 255));
        txt_dni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_dniKeyTyped(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(panel7Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dni, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Teléfono:");

        panel9.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel9.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel10.setBackground(new java.awt.Color(255, 153, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Phone.png"))); // NOI18N
        jLabel10.setOpaque(true);

        txt_telefono.setBackground(new java.awt.Color(255, 153, 0));
        txt_telefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_telefono.setBorder(null);
        txt_telefono.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_telefono.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_telefono.setSelectionColor(new java.awt.Color(255, 255, 255));
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(panel9Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_telefono))
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel11.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel11.setColorSecundario(new java.awt.Color(255, 153, 0));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setBackground(new java.awt.Color(255, 153, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Gender.png"))); // NOI18N
        jLabel11.setOpaque(true);

        rb_m.setBackground(new java.awt.Color(255, 153, 0));
        rb_m.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_m.setText("M");
        rb_m.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_m.setFocusable(false);
        rb_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_mActionPerformed(evt);
            }
        });

        rb_f.setBackground(new java.awt.Color(255, 153, 0));
        rb_f.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_f.setText("F");
        rb_f.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_f.setFocusable(false);
        rb_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_fActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel11Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rb_m)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_f)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_m, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_f, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Sexo:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Correo Electronico:");

        panel14.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel14.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel18.setBackground(new java.awt.Color(255, 153, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Mail.png"))); // NOI18N
        jLabel18.setOpaque(true);

        txt_correo.setBackground(new java.awt.Color(255, 153, 0));
        txt_correo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_correo.setBorder(null);
        txt_correo.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_correo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_correo.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator10.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel14Layout = new javax.swing.GroupLayout(panel14);
        panel14.setLayout(panel14Layout);
        panel14Layout.setHorizontalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panel14Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator10)
        );
        panel14Layout.setVerticalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel14Layout.createSequentialGroup()
                .addGroup(panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Rol:");

        panel12.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel12.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel14.setBackground(new java.awt.Color(255, 153, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/i-rol-usuario.png"))); // NOI18N
        jLabel14.setOpaque(true);

        jSeparator8.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        cb_rol.setBackground(new java.awt.Color(255, 153, 0));
        cb_rol.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cb_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccionar Rol>" }));
        cb_rol.setToolTipText("");
        cb_rol.setBorder(null);
        cb_rol.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cb_rol.setFocusable(false);
        cb_rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_rolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addGroup(panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel12Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addGroup(panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Contraseña:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Usuario:");

        panel10.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel10.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel19.setBackground(new java.awt.Color(255, 153, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/User_2.png"))); // NOI18N
        jLabel19.setOpaque(true);

        txt_usuario.setBackground(new java.awt.Color(255, 153, 0));
        txt_usuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_usuario.setBorder(null);
        txt_usuario.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_usuario.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_usuario.setSelectionColor(new java.awt.Color(255, 255, 255));

        jSeparator9.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel10Layout.createSequentialGroup()
                .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel10Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_usuario)))
                .addGap(68, 68, 68))
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel10Layout.createSequentialGroup()
                .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel13.setColorPrimario(new java.awt.Color(255, 153, 0));
        panel13.setColorSecundario(new java.awt.Color(255, 153, 0));

        jLabel20.setBackground(new java.awt.Color(255, 153, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Password.png"))); // NOI18N
        jLabel20.setOpaque(true);

        txt_contraseña.setBackground(new java.awt.Color(255, 153, 0));
        txt_contraseña.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_contraseña.setBorder(null);
        txt_contraseña.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_contraseña.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txt_contraseña.setSelectionColor(new java.awt.Color(255, 255, 255));
        txt_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contraseñaActionPerformed(evt);
            }
        });

        jSeparator11.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator11)
            .addGroup(panel13Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13Layout.createSequentialGroup()
                .addGroup(panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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
        btn_eliminar.setName(""); // NOI18N
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

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tb_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Rol", "DNI", "Nombre", "Apellidos", "Sexo", "Teléfono", "Correo", "Usuario", "Contraseña"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_usuarios.setFocusable(false);
        tb_usuarios.setGridColor(new java.awt.Color(255, 255, 255));
        tb_usuarios.setRowHeight(50);
        tb_usuarios.setSelectionBackground(new java.awt.Color(20, 25, 53));
        tb_usuarios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tb_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_usuariosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_usuariosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_usuarios);

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
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
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

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addGap(33, 33, 33))))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(panel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(panel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(panel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(20, 20, 20)
                                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(20, 20, 20)
                                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(panel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(panel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(20, 20, 20)
                                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(154, 154, 154)
                                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)))
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 714, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelImage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1424, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_rolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_rolActionPerformed

    }//GEN-LAST:event_cb_rolActionPerformed

    /**
     * Acción que se ejecuta al presionar el botón "Guardar".
     * <p>Este método valida todos los campos del formulario. Si los datos son válidos, solicita confirmación al usuario 
     * y luego registra tanto el inicio de sesión como la información del nuevo usuario en la base de datos.</p>
     * <p>Si ambas operaciones son exitosas, actualiza la tabla de usuarios, limpia los campos del formulario 
     * y muestra un mensaje de éxito.</p>
     *
     * @param evt Evento generado por el clic del botón
     */
    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        /*
        * Si los campos del formulario han sido validados correctamente...
        */
       if (verificar_campos()) {

           /*
            * Se muestra un cuadro de confirmación al usuario para saber si desea guardar el nuevo usuario.
            * Si responde "Sí" (índice 0), se continúa con el registro.
            */
           if (JOptionPane.showConfirmDialog(this, "¿Desea guardar este usuario?", "Pregunta", JOptionPane.INFORMATION_MESSAGE) == 0) {

               char sexo;

               /*
                * Se determina el sexo seleccionado en el grupo de botones de radio (rb_m para masculino).
                * Se obtiene el botón seleccionado del ButtonGroup `gcb_sexo`.
                */
               if (gcb_sexo.getSelection() == rb_m.getModel()) {
                   sexo = 'M'; // Masculino
               } else {
                   sexo = 'F'; // Femenino
               }

               boolean agregar_inicio_sesion = inicio_sesion.agregar(
                   rol.buscar_por_descripcion(cb_rol.getSelectedItem().toString()).getId(),
                   txt_usuario.getText(),
                   txt_contraseña.getText()
               );

               boolean agregar_usuario = usuario.agregar(
                   inicio_sesion.iniciar_sesion(txt_usuario.getText(), txt_contraseña.getText()).getId(),
                   txt_dni.getText(),
                   txt_nombres.getText(),
                   txt_apellidos.getText(),
                   sexo,
                   txt_telefono.getText(),
                   txt_correo.getText()
               );

               if (agregar_inicio_sesion && agregar_usuario) {
                   limpiar_tabla();
                   listar_usuarios();
                   limpiar_campos();
                   JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente", "¡Guardado Exitoso!", JOptionPane.INFORMATION_MESSAGE);
               }
           }
       }

        
    }//GEN-LAST:event_btn_guardarActionPerformed

    /**
     * Acción que se ejecuta al presionar el botón "Modificar".
     * <p>Este método permite actualizar los datos de un usuario previamente seleccionado en la tabla.</p>
     * <p>Primero verifica si se ha seleccionado una fila. Luego valida los campos del formulario.
     * Si los datos son válidos y el usuario confirma la acción, se actualiza tanto la información
     * de inicio de sesión como los datos personales del usuario en la base de datos.</p>
     * <p>Al finalizar, se actualiza la tabla, se limpian los campos y se muestra un mensaje
     * de éxito si la modificación fue exitosa.</p>
     *
     * @param evt Evento generado por el clic del botón
     */
    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        /*
        * Se obtiene la fila seleccionada de la tabla de usuarios.
        * Si no hay ninguna fila seleccionada, el valor será -1.
        */
       int fila = tb_usuarios.getSelectedRow();

       if(fila != -1) { // Si se ha seleccionado una fila en la tabla
           if (verificar_campos()) {

               if (JOptionPane.showConfirmDialog(this, "¿Desea modificar este usuario?", "Pregunta", JOptionPane.INFORMATION_MESSAGE) == 0) {

                   char sexo;

                   if (gcb_sexo.getSelection() == rb_m.getModel()) {
                       sexo = 'M';
                   } else {
                       sexo = 'F';
                   }

                   boolean actualizar_inicio_sesion = inicio_sesion.actualizar(
                       id_sesion,
                       rol.buscar_por_descripcion(cb_rol.getSelectedItem().toString()).getId(),
                       txt_usuario.getText(),
                       txt_contraseña.getText()
                   );

                   
                   boolean actualizar_usuario = usuario.actualizar(
                       id_usuario,
                       id_sesion,
                       txt_dni.getText(),
                       txt_nombres.getText(),
                       txt_apellidos.getText(),
                       sexo,
                       txt_telefono.getText(),
                       txt_correo.getText()
                   );

                   
                   if (actualizar_inicio_sesion && actualizar_usuario) {
                       limpiar_tabla();
                       listar_usuarios();
                       limpiar_campos();
                       JOptionPane.showMessageDialog(this, "Usuario modificado exitosamente", "¡Modificación Exitosa!", JOptionPane.INFORMATION_MESSAGE);
                   }
               }
           }
       } else {
           JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla", "¡FILA NO SELECCIONADA!", JOptionPane.WARNING_MESSAGE);
       }

        
    }//GEN-LAST:event_btn_modificarActionPerformed

    /**
     * Acción que se ejecuta al presionar el botón "Eliminar".
     * <p>Este método permite eliminar un usuario seleccionado en la tabla, junto con su cuenta de inicio de sesión asociada.</p>
     * <p>Primero verifica si se ha seleccionado una fila en la tabla. Si es así, solicita confirmación al usuario.
     * En caso afirmativo, procede a eliminar tanto el usuario como su sesión. Si ambas eliminaciones son exitosas,
     * actualiza la tabla y limpia el formulario.</p>
     *
     * @param evt Evento generado por el clic del botón
     */
    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        
       int fila = tb_usuarios.getSelectedRow();
       
       if (fila != -1) {

           if (JOptionPane.showConfirmDialog(this, "¿Desea eliminar este usuario?", "Pregunta", JOptionPane.INFORMATION_MESSAGE) == 0) {

               boolean eliminar_usuario = usuario.eliminar(id_usuario);
               boolean eliminar_inicio_sesion = inicio_sesion.eliminar(id_sesion);

               if (eliminar_usuario && eliminar_inicio_sesion) {
                   limpiar_tabla();
                   listar_usuarios();
                   limpiar_campos();
                   JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente", "¡Eliminación Exitosa!", JOptionPane.INFORMATION_MESSAGE);
               }
           }

       } else {
           
           JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla", "¡FILA NO SELECCIONADA!", JOptionPane.WARNING_MESSAGE);
       }

    }//GEN-LAST:event_btn_eliminarActionPerformed

    /**
     * Acción que se ejecuta al presionar el botón "Limpiar".
     * <p>Este método reinicia completamente el formulario de usuarios. Borra todos los datos visuales
     * mostrados en la tabla, recarga la lista completa de usuarios desde la base de datos, 
     * y limpia todos los campos de entrada del formulario.</p>
     * <p>Es útil para reiniciar el estado de la interfaz luego de una operación de registro,
     * edición o eliminación.</p>
     *
     * @param evt Evento generado por el clic del botón
     */
    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        
       limpiar_tabla();
       
       listar_usuarios();
       
       limpiar_campos();

    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void tb_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_usuariosMouseClicked

    }//GEN-LAST:event_tb_usuariosMouseClicked

    /**
     * Evento que se ejecuta al presionar una fila de la tabla de usuarios.
     * <p>Este método permite cargar los datos del usuario seleccionado desde la tabla
     * hacia los campos de entrada del formulario, facilitando la edición o eliminación del registro.</p>
     * <p>Además, guarda el <code>id_usuario</code> y <code>id_sesion</code> asociados para
     * usarlos en operaciones posteriores (modificar o eliminar).</p>
     *
     * @param evt Evento generado al presionar con el mouse sobre una fila de la tabla.
     */
    private void tb_usuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_usuariosMousePressed
        
       int fila = tb_usuarios.getSelectedRow();

       if (fila != -1) {

           id_usuario = Integer.parseInt(tb_usuarios.getValueAt(fila, 0).toString());

           id_sesion = usuario.buscar_por_id(id_usuario).getId_sesion();

           cb_rol.setSelectedItem(tb_usuarios.getValueAt(fila, 1));
           txt_dni.setText(tb_usuarios.getValueAt(fila, 2).toString());
           txt_nombres.setText(tb_usuarios.getValueAt(fila, 3).toString());
           txt_apellidos.setText(tb_usuarios.getValueAt(fila, 4).toString());

           if (tb_usuarios.getValueAt(fila, 5).toString().equals("Masculino")) {
               rb_m.setSelected(true);
               rb_f.setSelected(false);
           } else {
               rb_m.setSelected(false);
               rb_f.setSelected(true);
           }

           txt_telefono.setText(tb_usuarios.getValueAt(fila, 6).toString());
           txt_correo.setText(tb_usuarios.getValueAt(fila, 7).toString());
           txt_usuario.setText(tb_usuarios.getValueAt(fila, 8).toString());
           txt_contraseña.setText(tb_usuarios.getValueAt(fila, 9).toString());
       }

    }//GEN-LAST:event_tb_usuariosMousePressed

    private void txt_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contraseñaActionPerformed

    /**
     * Evento que se dispara al presionar una tecla dentro del campo "Teléfono".
     * <p>Este método valida en tiempo real que solo se ingresen números
     * y que el número no exceda los 9 dígitos permitidos.</p>
     * <ul>
     *   <li>Si se intenta ingresar una letra, el evento se bloquea y se muestra una advertencia.</li>
     *   <li>Si ya hay 9 dígitos en el campo, se impide seguir escribiendo y se muestra una advertencia.</li>
     * </ul>
     *
     * @param evt Evento generado por la tecla presionada en el campo de texto.
     */
    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
        
       if (Character.isLetter(evt.getKeyChar())) {
           getToolkit().beep(); // Sonido de advertencia
           evt.consume(); // Cancelar el ingreso del carácter
           JOptionPane.showMessageDialog(this, "¡Ingrese solo números!", "Advertencia", JOptionPane.WARNING_MESSAGE);
           return; // Salir de la función
       }

       if (txt_telefono.getText().length() >= 9) {
           getToolkit().beep(); // Sonido de advertencia
           evt.consume(); // Cancelar el ingreso del carácter
           JOptionPane.showMessageDialog(this, "¡El número no puede tener más de 9 dígitos!", "Advertencia", JOptionPane.WARNING_MESSAGE);
       }

    }//GEN-LAST:event_txt_telefonoKeyTyped

    /**
     * Evento que se ejecuta cuando el usuario presiona una tecla en el campo "DNI".
     * <p>Este método restringe la entrada para que solo se puedan ingresar números
     * y un máximo de 8 dígitos, que es la longitud estándar del DNI peruano.</p>
     * <ul>
     *   <li>Si el usuario intenta ingresar una letra, se bloquea y se muestra una advertencia.</li>
     *   <li>Si se intenta ingresar más de 8 caracteres, también se bloquea y se notifica al usuario.</li>
     * </ul>
     *
     * @param evt Evento generado por la tecla presionada en el campo de texto del DNI.
     */
    private void txt_dniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniKeyTyped
        
       if (Character.isLetter(evt.getKeyChar())) {
           getToolkit().beep(); // Sonido de advertencia
           evt.consume(); // Evita que el carácter se ingrese
           JOptionPane.showMessageDialog(this, "¡Ingrese solo números!", "Advertencia", JOptionPane.WARNING_MESSAGE);
           return; // Salida inmediata para no continuar con la siguiente validación
       }

       if (txt_dni.getText().length() >= 8) {
           getToolkit().beep(); // Sonido de advertencia
           evt.consume(); // Bloquear el carácter adicional
           JOptionPane.showMessageDialog(this, "¡El DNI no puede tener más de 8 dígitos!", "Advertencia", JOptionPane.WARNING_MESSAGE);
       }

    }//GEN-LAST:event_txt_dniKeyTyped

    private void rb_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_mActionPerformed
        
    }//GEN-LAST:event_rb_mActionPerformed

    private void rb_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_fActionPerformed
        
    }//GEN-LAST:event_rb_fActionPerformed

    private void txt_buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyTyped

    }//GEN-LAST:event_txt_buscarKeyTyped

    /**
     * Evento que se ejecuta cuando el usuario suelta una tecla en el campo de búsqueda (`txt_buscar`).
     * <p>Este método permite realizar una búsqueda dinámica y en tiempo real mientras se escribe.
     * Filtra y muestra en la tabla `tb_usuarios` solo los registros que coinciden con el texto ingresado.</p>
     *
     * <ul>
     *   <li>Si el campo no está vacío y hay resultados, se listan solo los usuarios coincidentes.</li>
     *   <li>Si no hay coincidencias, se muestra un mensaje en la tabla indicando que no se encontraron resultados.</li>
     *   <li>Si el campo de búsqueda está vacío, se restaura la lista completa de usuarios.</li>
     * </ul>
     *
     * @param evt Evento generado al soltar una tecla en el campo de texto `txt_buscar`.
     */
    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        
       if (!txt_buscar.getText().isEmpty()) {

           if (!usuario.buscar(txt_buscar.getText()).isEmpty()) {
               limpiar_tabla();              // Borra las filas anteriores
               listar_busqueda_usuarios();   // Muestra solo los usuarios filtrados por el texto ingresado
           } else {
               getToolkit().beep();
               mostrar_mensaje_tabla_vacia();
               JOptionPane.showMessageDialog(null, "¡Cliente no encontrado!", "Búsqueda Inválida", JOptionPane.WARNING_MESSAGE);
           }

       } else {
           limpiar_tabla();
           listar_usuarios();
       }

        
    }//GEN-LAST:event_txt_buscarKeyReleased

    /**
     * Evento que se ejecuta cuando el puntero del mouse entra en el área del botón "Guardar".
     * <p>Cambia el color de fondo del botón para proporcionar una retroalimentación visual al usuario.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_guardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarMouseEntered
        btn_guardar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_guardarMouseEntered

    /**
     * Evento que se ejecuta cuando el puntero del mouse sale del área del botón "Guardar".
     * <p>Restaura el color original del botón.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_guardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarMouseExited
        btn_guardar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_guardarMouseExited

    /**
     * Evento que se ejecuta cuando el puntero del mouse entra en el área del botón "Modificar".
     * <p>Cambia el color de fondo del botón como indicación visual de enfoque.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_modificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificarMouseEntered
        btn_modificar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_modificarMouseEntered

    /**
     * Evento que se ejecuta cuando el puntero del mouse sale del área del botón "Modificar".
     * <p>Restaura el color original del botón.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_modificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificarMouseExited
        btn_modificar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_modificarMouseExited

    /**
     * Evento que se ejecuta cuando el puntero del mouse entra en el área del botón "Eliminar".
     * <p>Aplica un color más oscuro al botón como efecto visual.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_eliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarMouseEntered
        btn_eliminar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_eliminarMouseEntered

    /**
     * Evento que se ejecuta cuando el puntero del mouse sale del área del botón "Eliminar".
     * <p>Devuelve el botón a su color original.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_eliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarMouseExited
        btn_eliminar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_eliminarMouseExited

    /**
     * Evento que se ejecuta cuando el puntero del mouse entra en el área del botón "Limpiar".
     * <p>Oscurece el color del botón temporalmente para indicar foco.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_limpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiarMouseEntered
        btn_limpiar.setBackground(new Color(0,0,51));
    }//GEN-LAST:event_btn_limpiarMouseEntered
    
    /**
     * Evento que se ejecuta cuando el puntero del mouse sale del área del botón "Limpiar".
     * <p>Reestablece el color original del botón.</p>
     *
     * @param evt Evento generado por el movimiento del mouse.
     */
    private void btn_limpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiarMouseExited
        btn_limpiar.setBackground(new Color(0,25,53));
    }//GEN-LAST:event_btn_limpiarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JComboBox<String> cb_rol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel10;
    private org.edisoncor.gui.panel.Panel panel11;
    private org.edisoncor.gui.panel.Panel panel12;
    private org.edisoncor.gui.panel.Panel panel13;
    private org.edisoncor.gui.panel.Panel panel14;
    private org.edisoncor.gui.panel.Panel panel16;
    private org.edisoncor.gui.panel.Panel panel2;
    private org.edisoncor.gui.panel.Panel panel6;
    private org.edisoncor.gui.panel.Panel panel7;
    private org.edisoncor.gui.panel.Panel panel8;
    private org.edisoncor.gui.panel.Panel panel9;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JRadioButton rb_f;
    private javax.swing.JRadioButton rb_m;
    private javax.swing.JTable tb_usuarios;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_contraseña;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_dni;
    private javax.swing.JTextField txt_nombres;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
