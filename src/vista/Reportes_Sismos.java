package vista;

import control.controlador;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Reportes_Sismos extends JFrame {
    private JButton reportePorProvinciaButton;
    private JButton reportePorMagnitudButton;
    private JButton reportePorMesButton;
    private JButton reportePorFechasButton;
    private JButton reportePorOrigenButton;
    private JPanel reporteSismosPanel;
    private controlador control;

    Reportes_Sismos(controlador control) {
        super("Reportes de Sismos");
        this.control = control;
        setContentPane(reporteSismosPanel);
        setResizable(false);
        this.pack();

        reportePorProvinciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Histograma histo = new Histograma("Reporte de Sismos por Provincias",control);
                histo.pack();
                histo.setSize(315,575);
                RefineryUtilities.centerFrameOnScreen(histo);
                histo.setVisible(true);
            }
        });
        reportePorOrigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                PieChart demo = new PieChart( "Reporte de Sismos por Origen",control );
                demo.setSize( 560 , 367 );
                RefineryUtilities.centerFrameOnScreen( demo );
                demo.setVisible( true );
            }
        });
        reportePorFechasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteFechas = new Reporte_Fechas(control);
                panelReporteFechas.setVisible(true);
            }
        });
        reportePorMesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteMes = new Reporte_Mes(control);
                panelReporteMes.setVisible(true);
            }
        });
        reportePorMagnitudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteMagnitud = new Reporte_Magnitud(control);
                panelReporteMagnitud.setVisible(true);
            }
        });
    }
}
