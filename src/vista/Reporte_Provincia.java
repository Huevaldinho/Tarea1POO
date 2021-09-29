package vista;

import javax.swing.*;
import control.controlador;

public class Reporte_Provincia extends JFrame{
    private JPanel panel;
    private controlador control;

    Reporte_Provincia(controlador control) {
        super("Reporte por Provincia");
        this.control = control;
        setContentPane(panel);
        this.pack();
    }
}
