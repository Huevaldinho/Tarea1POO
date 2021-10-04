package vista;

import javax.swing.*;
import control.controlador;

public class Reporte_Magnitud extends JFrame{
    private JPanel panel;
    private JTable tablaMagnitud;
    private controlador control;

    Reporte_Magnitud(controlador control) {
        super("Reporte por Mes");
        this.control = control;
        setContentPane(panel);
        this.pack();
    }
}

