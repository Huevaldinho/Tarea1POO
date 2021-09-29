package vista;

import control.controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                JFrame panelReporteProvincia = new Reporte_Provincia(control);
                panelReporteProvincia.setVisible(true);
            }
        });
        reportePorOrigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteOrigen = new Reporte_Origen(control);
                panelReporteOrigen.setVisible(true);
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
