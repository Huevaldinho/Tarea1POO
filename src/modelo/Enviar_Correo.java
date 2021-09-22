package modelo;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Enviar_Correo {
    private static String to;
    private static final String from = "ejemplo@gmail.com"; // establecer de donde se va a enviar (debe ser gmail)
    private static final String contrasena = "contrasena"; // establecer contrasena del correo from

    public Enviar_Correo(){}

    public String getCorreoElectronicoTo() {
        return this.to;
    }
    public void setCorreoElectronicoTo(String correoElectronico) {
        this.to = correoElectronico;
    }

    public static void enviarCorreo() {
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
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mensaje.setSubject("Prueba");
            mensaje.setText("Esto es una prueba desde Java");

            // preparacion y envio de mensaje
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(from, contrasena);
            transporte.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            transporte.close();
            System.out.println("El correo se envio");
        } catch (MessagingException mex) {mex.printStackTrace();}
    }
}





