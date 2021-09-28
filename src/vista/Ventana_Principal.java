package vista;

import control.Cargador;
import modelo.Sismo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import control.controlador;

public class Ventana_Principal extends JFrame {
    private JPanel principalPanel;
    private JButton móduloDeSismosButton;
    private JButton añadirPersonaParaSerButton;
    private JButton reportesDeSismosButton;
    private JButton sobreNosotrosButton;
    private static controlador control;
    //private static Cargador  cargadorInfo = new Cargador();
    private static ArrayList<Sismo> cargaSismos = new ArrayList();

    public Ventana_Principal() {
        super("Sismos En Costa Rica");
        setContentPane(principalPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        móduloDeSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelModuloSismos = new Modulo_Sismos(control);
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
    public static void main(String[] args) throws IOException, ParseException {
        control=new controlador();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Principal().setVisible(true);
            }
        });
    }
}
