package vista;

import javax.swing.*;
import control.controlador;

/**
 * Interfaz para mostrar el reporte por magnitud
 */

public class Reporte_Magnitud extends JFrame{
    private JPanel panel;
    private JTable tablaMagnitud;
    private controlador control;

    Reporte_Magnitud(controlador control) {
        super("Reporte por Magnitud");
        setContentPane(panel);
        this.pack();
        this.control=control;
        tablaMagnitud.setModel(control.reportePorMagnitud());
        tablaMagnitud.setVisible(true);
        panel.setVisible(true);
    }
}

