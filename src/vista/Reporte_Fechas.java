package vista;

import javax.swing.*;
import control.controlador;

public class Reporte_Fechas extends JFrame{
    private JPanel panel;
    private controlador control;

    Reporte_Fechas(controlador control) {
        super("Reporte por Fechas");
        this.control = control;
        setContentPane(panel);
        this.pack();
    }
}
