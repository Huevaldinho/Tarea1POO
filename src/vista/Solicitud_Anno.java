package vista;

import javax.swing.*;
import control.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Solicitud_Anno extends JFrame {
    private JTextField textoAnno;
    private JButton botonAceptar;
    private JPanel panel;
    private controlador control;

    Solicitud_Anno(controlador control) {
        super("Solicitud De Año");
        this.control = control;
        setContentPane(panel);
        setResizable(false);
        this.pack();

        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteMes = new Reporte_Mes(control);
                panelReporteMes.setVisible(true);
            }
        });
    }
}
