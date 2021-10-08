package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Modelo para llevar control de expresiones regulares
 */

public class Expresiones_Regulares {
    Expresiones_Regulares() {}
    /**
     * Solicita al cargador que cree el modelo para la tabla de la interfaz grafica.
     * @param horas: Hora exacta del sismo.
     * @return boolean: True si es una hora valida, false de lo contrario.
     */
    public boolean verificarHoras (String horas) {
        Pattern patronHoras = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?");// no shive
        Matcher match = patronHoras.matcher(horas);
        boolean correcto = match.matches();
        if (correcto) {
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * Verifica el correo electronico ingresado
     * @param correoElectronico Parametro ingresado por el usuario
     * @return Retorna un booleano para verificar el correo
     */

    public static boolean verificarCorreoElectronico (String correoElectronico) {
        Pattern patronCorreoElectronico = Pattern.compile("[a-zA-Z0-9]+@{1}[a-zA-Z0-9]+(.com)");

        Matcher correoElectronicoUsuario = patronCorreoElectronico.matcher(correoElectronico);
        boolean correcto = correoElectronicoUsuario.matches();

        if (correcto){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Verifica un numero de telefono
     * @param numeroTelefono Numero de telefono ingresado por el usuario
     * @return Retorna un booleano para verificar el numero de telefono
     */

    public static boolean verificarNumeroTelefono (String numeroTelefono) {
        Pattern patronNumeroTelefono = Pattern.compile("^[0-9]{4}-{1}[0-9]{4}$");
        Matcher match = patronNumeroTelefono.matcher(numeroTelefono);
        boolean correcto = match.matches();

        if (correcto) {
            return true;
        }
        else {
            return false;
        }

    }
}
