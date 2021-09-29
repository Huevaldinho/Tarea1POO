package vista;

import javax.swing.*;
import control.controlador;

public class Reporte_Magnitud extends JFrame{
    private JPanel panel;
    private controlador control;

    Reporte_Magnitud(controlador control) {
        super("Reporte por Magnitud");
        this.control = control;
        setContentPane(panel);
        this.pack();
    }
}
