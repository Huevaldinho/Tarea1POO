package vista;

import control.controlador;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.text.ParseException;

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
                JFrame panelSolicitudFechas = new Solicitud_Fechas(control);
                panelSolicitudFechas.setVisible(true);
            }
        });
        reportePorMesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelSolicitarAnno = new Solicitud_Anno(control);
                panelSolicitarAnno.setVisible(true);
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
