package vista;

import javax.swing.*;
import control.controlador;

public class Reporte_Origen extends JFrame{
    private JPanel panel;
    private controlador control;

    Reporte_Origen(controlador control) {
        super("Reporte por Origen");
        this.control = control;
        setContentPane(panel);
        this.pack();
    }
}
