package vista;

import control.controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Mostrar_Sismos extends JFrame {
    private JPanel panel;

    Mostrar_Sismos(controlador control) {
        super("Mostrar Sismos");
        setContentPane(panel);
        this.pack();
    }

}
