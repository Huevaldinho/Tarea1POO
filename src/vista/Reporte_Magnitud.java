package vista;

import control.controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.text.ParseException;

public class Reporte_Magnitud extends JFrame {
    private JPanel panel;
    private JTable tablaSismos;

    private JScrollBar scrollBar;
    private JScrollBar scrollBarTabla;

    private  controlador  control;

    Reporte_Magnitud(){}
    Reporte_Magnitud(controlador control) {
        super("Reporte Sismos por Magnitud");
        setContentPane(panel);
        this.pack();
        this.control=control;
        try {
            System.out.println("\n\nENTRO AL TRY AAAAAA\n\n");
            tablaSismos.setModel(control.reportePorMagnitud(7.0));//DICE QUE ES NULO POR QUEEEEE JUEPUTA
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tablaSismos.setVisible(true);
        panel.setVisible(true);
    }
}
