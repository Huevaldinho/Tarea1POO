package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_Principal extends JFrame {
    private JPanel principalPanel;
    private JButton móduloDeSismosButton;
    private JButton añadirPersonaParaSerButton;
    private JButton reportesDeSismosButton;
    private JButton sobreNosotrosButton;

    public Ventana_Principal() {
        super("Sismos En Costa Rica");
        setContentPane(principalPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        móduloDeSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelModuloSismos = new Modulo_Sismos();
                panelModuloSismos.setVisible(true);
            }
        });

        reportesDeSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteSismos = new Reportes_Sismos();
                panelReporteSismos.setVisible(true);
            }
        });
        añadirPersonaParaSerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelAnadirPersona = new Anadir_Persona();
                panelAnadirPersona.setSize(400, 300);
                panelAnadirPersona.setVisible(true);
            }
        });
        sobreNosotrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelSobreNosotros = new Sobre_Nosotros();
                panelSobreNosotros.setVisible(true);
            }
        });
    }
}
