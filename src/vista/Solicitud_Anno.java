package vista;

import javax.swing.*;
import control.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Interfaz para solicitar un anno para hacer un reporte
 */

public class Solicitud_Anno extends JFrame {
    private JTextField textoAnno;
    private JButton botonAceptar;
    private JPanel panel;
    private controlador control;
    private Date annoConvertido;

    Solicitud_Anno(controlador control) {
        super("Solicitud De Año");
        this.control = control;
        setContentPane(panel);
        setResizable(false);
        this.pack();

        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (obtenerAnno()){
                    JFrame panelReporteFechas = new Reporte_Mes(control,annoConvertido);
                    panelReporteFechas.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Ingrese un año válida");
                }
            }
        });
    }
    private boolean obtenerAnno(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy", Locale.getDefault());
        try{
            this.annoConvertido=formatoFecha.parse(textoAnno.getText());
            return true;
        }catch (ParseException e){
            System.out.println("ERROR EN EL PARSE");
            e.printStackTrace();
            return false;
        }
    }
}
