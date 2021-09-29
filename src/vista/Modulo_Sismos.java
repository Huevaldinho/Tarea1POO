package vista;

import control.controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modulo_Sismos extends JFrame {
    private JPanel sismosPanel;
    private JButton mostrarSismosButton;
    private JButton anadirSismosButton;
    private JButton modificarSismoButton;
    private static controlador control;

    public Modulo_Sismos(controlador control) {
        super("Modulo de Sismos");
        setContentPane(sismosPanel);
        setResizable(false);
        this.pack();
        mostrarSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelMostrarSismos = new Mostrar_Sismos(control);
                panelMostrarSismos.setVisible(true);
            }
        });
        anadirSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelAnadirSismo = new Anadir_Sismo(control);
                panelAnadirSismo.setVisible(true);
            }
        });
        modificarSismoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelModificarSismo = new Modificar_Sismo(control);
                panelModificarSismo.setVisible(true);
            }
        });
    }
}
