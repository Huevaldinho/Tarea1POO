package vista;

import javax.swing.*;
import control.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Solicitud_Fechas extends JFrame{
    private JTextField textoFechaInicial;
    private JTextField textoFechaFinal;
    private JButton botonAceptar;
    private JPanel panel;
    private controlador control;

    Solicitud_Fechas(controlador control) {
        super("Solictud De Fechas");
        this.control = control;
        setContentPane(panel);
        setResizable(false);
        this.pack();

        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteFechas = new Reporte_Fechas(control);
                panelReporteFechas.setVisible(true);
            }
        });
    }
}
