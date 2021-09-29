package vista;

import javax.swing.*;
import control.controlador;

public class Reporte_Mes extends JFrame{
    private JPanel panel;
    private controlador control;

    Reporte_Mes(controlador control) {
        super("Reporte por Mes");
        this.control = control;
        setContentPane(panel);
        this.pack();
    }
}
