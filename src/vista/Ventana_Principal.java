package vista;

import control.Cargador;
import modelo.Sismo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import control.controlador;

/**
 * Interfaz principal de la aplicacion
 */

public class Ventana_Principal extends JFrame {
    private JPanel principalPanel;
    private JButton moduloDeSismosButton;
    private JButton anadirPersonaParaSerButton;
    private JButton reportesDeSismosButton;
    private JButton sobreNosotrosButton;
    private static controlador control;
    //private static Cargador  cargadorInfo = new Cargador();
    private static ArrayList<Sismo> cargaSismos = new ArrayList();

    public Ventana_Principal() {
        super("Sismos En Costa Rica");
        setContentPane(principalPanel);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        moduloDeSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelModuloSismos = new Modulo_Sismos(control);
                panelModuloSismos.setVisible(true);
            }
        });

        reportesDeSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelReporteSismos = new Reportes_Sismos(control);
                panelReporteSismos.setVisible(true);
            }
        });
        anadirPersonaParaSerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelAnadirPersona = new Anadir_Persona(control);
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
        control = new controlador();
        JFrame interfaz = new Ventana_Principal();
        interfaz.setVisible(true);
        //java.awt.EventQueue.invokeLater(new Runnable() {
          //  public void run() {
            //    new Ventana_Principal().setVisible(true);
            //}
        //});
    }
}
