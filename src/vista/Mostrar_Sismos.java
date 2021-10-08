package vista;

import control.controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz grafica para mostrar los sismos registrados.
 */

public class Mostrar_Sismos extends JFrame {
    private JPanel panel;
    private JTable tablaSismos;

    private JScrollBar scrollBar;
    private JScrollBar scrollBarTabla;

    private  controlador  control;

    Mostrar_Sismos() {    }
    Mostrar_Sismos(controlador control) {
        super("Mostrar Sismos");
        setContentPane(panel);
        this.pack();
        this.control=control;
        tablaSismos.setModel(control.cargarSismosTabla());//Por que marca error?
        tablaSismos.setVisible(true);
        panel.setVisible(true);
    }
}
