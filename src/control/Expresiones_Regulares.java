package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expresiones_Regulares {
    Expresiones_Regulares() {}

    public boolean verificarCorreoElectronico (String correoElectronico) {
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
    public boolean verificarNumeroTelefono (String numeroTelefono) {
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
