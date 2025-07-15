package BussinessObject;

import DataAccessObject.Inicio_Sesion_DAO;
import TransferObject.Inicio_Sesion_DTO;
import javax.swing.JOptionPane;

/**
 * Clase de lógica de negocio que gestiona las operaciones relacionadas con el
 * inicio de sesión de usuarios en el sistema, como autenticación, cambio de
 * contraseña y mantenimiento de credenciales.
 */
public class Inicio_Sesion {

    /** Objeto DTO que representa los datos del inicio de sesión. */
    private Inicio_Sesion_DTO inicio_sesion_DTO;

    /** Objeto DAO para interactuar con la base de datos. */
    private Inicio_Sesion_DAO inicio_sesion_DAO;

    /** Constructor que inicializa la instancia DAO. */
    public Inicio_Sesion() {
        inicio_sesion_DAO = new Inicio_Sesion_DAO();
    }

    /**
     * Verifica las credenciales ingresadas por el usuario.
     *
     * @param usuario Nombre de usuario.
     * @param contraseña Contraseña del usuario.
     * @return Objeto DTO con los datos del usuario si las credenciales son válidas, de lo contrario null.
     */
    public Inicio_Sesion_DTO iniciar_sesion(String usuario, String contraseña) {
        inicio_sesion_DTO = inicio_sesion_DAO.iniciar_sesion(new Inicio_Sesion_DTO(usuario, contraseña));
        return inicio_sesion_DTO != null ? inicio_sesion_DTO : null;
    }

    /**
     * Verifica si la contraseña ingresada corresponde a la contraseña actual del usuario.
     *
     * @param id ID del usuario.
     * @param contraseña Contraseña actual a verificar.
     * @return true si coincide, false en caso contrario.
     */
    public boolean verificar_contraseña_actual(int id, String contraseña) {
        return inicio_sesion_DAO.verificar_contraseña_actual(new Inicio_Sesion_DTO(id, contraseña));
    }

    /**
     * Actualiza la contraseña del usuario validando que cumpla con los requisitos de seguridad
     * y que no sea igual a la anterior.
     *
     * @param id ID del usuario.
     * @param contraseña Nueva contraseña.
     * @return true si la contraseña fue actualizada correctamente, false si hubo errores o no cumple con los requisitos.
     */
    public boolean actualizar_contraseña(int id, String contraseña) {
        if (contraseña_segura(contraseña)) {
            if (inicio_sesion_DAO.verificar_contraseña_actual(new Inicio_Sesion_DTO(id, contraseña))) {
                JOptionPane.showMessageDialog(null,
                        "¡La nueva contraseña no puede ser igual a la anterior!",
                        "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                if (inicio_sesion_DAO.actualizar_contraseña(new Inicio_Sesion_DTO(id, contraseña))) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "¡ERROR AL ACTUALIZAR CONTRASEÑA!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    """
                    La nueva contraseña no cumple con los requisitos:
                        - Mínimo 8 caracteres
                        - Incluir letras, números y símbolos
                    """,
                    "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Verifica si una contraseña es segura (mínimo 8 caracteres, contiene letras, números y símbolos).
     *
     * @param password Cadena de texto de la contraseña.
     * @return true si cumple con los requisitos, false de lo contrario.
     */
    private boolean contraseña_segura(String password) {
        if (password.length() < 8) return false;
        boolean tieneLetra = password.matches(".*[a-zA-Z].*");
        boolean tieneNumero = password.matches(".*\\d.*");
        boolean tieneSimbolo = password.matches(".*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|].*");
        return tieneLetra && tieneNumero && tieneSimbolo;
    }

    /**
     * Busca un inicio de sesión a partir de su ID.
     *
     * @param id ID del usuario.
     * @return Objeto DTO con los datos del inicio de sesión si fue encontrado, null si no existe.
     */
    public Inicio_Sesion_DTO buscar_por_id(int id) {
        inicio_sesion_DTO = inicio_sesion_DAO.buscar_por_id(new Inicio_Sesion_DTO(id));
        return inicio_sesion_DTO != null ? inicio_sesion_DTO : null;
    }

    /**
     * Agrega un nuevo registro de inicio de sesión.
     *
     * @param id_rol ID del rol asociado.
     * @param usuario Nombre de usuario.
     * @param contraseña Contraseña.
     * @return true si fue agregado correctamente, false si hubo error.
     */
    public boolean agregar(int id_rol, String usuario, String contraseña) {
        inicio_sesion_DTO = new Inicio_Sesion_DTO(id_rol, usuario, contraseña);
        if (inicio_sesion_DAO.agregar(inicio_sesion_DTO)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al añadir Inicio de Sesión",
                    "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Actualiza los datos de un inicio de sesión existente.
     *
     * @param id ID del inicio de sesión.
     * @param id_rol ID del rol.
     * @param usuario Nuevo nombre de usuario.
     * @param contraseña Nueva contraseña.
     * @return true si fue actualizado correctamente, false si hubo error.
     */
    public boolean actualizar(int id, int id_rol, String usuario, String contraseña) {
        inicio_sesion_DTO = new Inicio_Sesion_DTO(id, id_rol, usuario, contraseña);
        if (inicio_sesion_DAO.actualizar(inicio_sesion_DTO)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar Inicio de Sesión",
                    "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Elimina un inicio de sesión por su ID.
     *
     * @param id ID del inicio de sesión.
     * @return true si fue eliminado correctamente, false si no se pudo eliminar.
     */
    public boolean eliminar(int id) {
        inicio_sesion_DTO = new Inicio_Sesion_DTO(id);
        if (inicio_sesion_DAO.eliminar(inicio_sesion_DTO)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Este Inicio Sesión no se puede eliminar",
                    "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
