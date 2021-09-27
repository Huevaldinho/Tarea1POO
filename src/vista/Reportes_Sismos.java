package vista;

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

    Reportes_Sismos() {
        super("Reportes de Sismos");
        setContentPane(reporteSismosPanel);
        this.pack();
        reportePorProvinciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteProvincia = new Reporte_Provincia();
                panelReporteProvincia.setVisible(true);
            }
        });
        reportePorOrigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteOrigen = new Reporte_Origen();
                panelReporteOrigen.setVisible(true);
            }
        });
        reportePorFechasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteFechas = new Reporte_Fechas();
                panelReporteFechas.setVisible(true);
            }
        });
        reportePorMesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteMes = new Reporte_Mes();
                panelReporteMes.setVisible(true);
            }
        });
        reportePorMagnitudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteMagnitud = new Reporte_Magnitud();
                panelReporteMagnitud.setVisible(true);
            }
        });
    }
}