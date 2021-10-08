package vista;

import javax.swing.*;
import control.controlador;
import java.text.ParseException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

/**
 * Interfaz para solicitar fechas para elaborar un reporte
 */

public class Solicitud_Fechas extends JFrame{
    private JTextField textoFechaInicial;
    private JTextField textoFechaFinal;
    private JButton botonAceptar;
    private JPanel panel;
    private controlador control;
    private Date fechaFuncionInicial;
    private Date fechaFuncionFinal;

    Solicitud_Fechas(controlador control) {
        super("Solictud De Fechas");
        this.control = control;
        setContentPane(panel);
        setResizable(false);
        this.pack();

        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (obtenerFechaInicial()){
                        JFrame panelReporteFechas = new Reporte_Fechas(control,fechaFuncionInicial,fechaFuncionFinal);
                        panelReporteFechas.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Ingrese una fecha válida");
                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
    private boolean obtenerFechaInicial ()throws IOException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try{
            this.fechaFuncionInicial = formatoFecha.parse(textoFechaInicial.getText());
            this.fechaFuncionFinal=formatoFecha.parse(textoFechaFinal.getText());
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }
        return control.reporteFechasValidar(fechaFuncionInicial, fechaFuncionFinal);
    }
}
