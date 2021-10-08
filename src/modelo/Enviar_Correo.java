package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Esta clase funciona para enviar correos electronicos, en este caso usando el servidor de Gmail
 */

public class Enviar_Correo {
    private static final String from = "plataformasismospoo"; // establecer de donde se va a enviar (debe ser gmail)
    private static final String contrasena = "contrasenaCorreo"; // establecer contrasena del correo from

    public Enviar_Correo() {}

    /**
     * @param listaPersonas personas registradas en la app
     * @param sismo nuevo sismo que se registro
     */

    public static void enviarCorreo(ArrayList<Persona> listaPersonas, Sismo sismo) {
        // se establece el smtp de gmail
        Properties propiedades = System.getProperties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        Session sesion = Session.getDefaultInstance(propiedades);

        try{
            // composicion de mensaje
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(from));

            // anade las personas interesadas para ser notificadas
            boolean bandera = false;
            for (Persona i : listaPersonas) {
                for (NProvincia j : i.getProvinciasInteres()) {
                    int temp;
                    switch (j) {
                        case SanJose -> temp = 1;
                        case Alajuela -> temp = 2;
                        case Cartago -> temp = 3;
                        case Heredia -> temp = 4;
                        case Guancaste -> temp = 5;
                        case Puntarenas -> temp = 6;
                        default -> temp = 7;
                    }
                    if (sismo.getProvincia() == temp) {
                        mensaje.addRecipient(Message.RecipientType.CC, new InternetAddress(i.getCorreoElectronico()));
                        bandera = true;
                        break;
                    }
                }
            }
            if (!bandera) {
                return;
            }
            // prepara enums en Strings
            String lugarOrigen = sismo.convertirLugarOrigen(sismo);
            String origen = sismo.convertirOrigen(sismo);
            String provincia = sismo.convertirProvincia(sismo);

            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

            // prepara mensaje a enviar
            mensaje.setSubject("Nuevo Sismo En " + provincia);
            mensaje.setText("Fecha: " + formatoFecha.format(sismo.getFecha()) + "\n"
                    + "Instante Exacto: " + formatoHora.format(sismo.getInstanteExacto()) + "\n"
                    + "Provincia: " + provincia + "\n"
                    + "Latitud: " + sismo.getLocalizacionLatitud() + "\n"
                    + "Longitud: " + sismo.getLocalizacionLongitud() + "\n"
                    + "Localización: " + sismo.getLocalizacionDescripcion() + "\n"
                    + "Magnitud: " + sismo.getMagnitud() + "\n"
                    + "Profundidad Del Sismo: " + sismo.getProfundidad() + " km\n"
                    + "Origen: " + origen + "\n"
                    + "Lugar De Origen: " + lugarOrigen);

            // preparacion y envio de mensaje
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(from, contrasena);
            transporte.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.CC));
            transporte.close();
            System.out.println("El correo se envio");
        } catch (MessagingException mex) {mex.printStackTrace();}
    }
}





