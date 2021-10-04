package vista;

import javax.swing.*;
import control.controlador;
import java.util.Date;

public class Reporte_Fechas extends JFrame {
    private JPanel panel;
    private JTable tablaSismosFecha;

    private JScrollBar scrollBar;
    private JScrollBar scrollBarTabla;

    private  controlador  control;

    Reporte_Fechas(){ }
    Reporte_Fechas(controlador control,Date fechaInicial,Date fechaFinal) {
        super("Reporte por Fechas");
        setContentPane(panel);
        this.pack();
        this.control=control;
        if (control.reporteFechas(fechaInicial,fechaFinal)==null)
            System.out.println("FFFFFFFF");
        //JTable tablaSismosFecha = new JTable();
        tablaSismosFecha.setModel(control.reporteFechas(fechaInicial,fechaFinal));
        tablaSismosFecha.setVisible(true);
        panel.setVisible(true);
    }
}
