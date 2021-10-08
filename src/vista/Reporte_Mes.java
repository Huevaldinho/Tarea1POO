package vista;

import javax.swing.*;
import control.controlador;
import org.jfree.ui.RefineryUtilities;

import java.util.Date;

/**
 * Interfaz para mostrar el reporte por mes
 */

public class Reporte_Mes extends JFrame{
    private JPanel panel;
    private controlador control;

    Reporte_Mes(controlador control, Date annoConvertido) {
        double[] info=control.reportesPorMesEnUnAnno(annoConvertido);
        GraficoBarras chart = new GraficoBarras("Reporte de Sismos por Mes",
                "Sismos por mes en un año",annoConvertido,info);
        chart.pack( );
        chart.setSize(700,500);
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
