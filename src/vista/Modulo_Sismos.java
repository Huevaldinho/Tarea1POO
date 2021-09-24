package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modulo_Sismos extends JFrame {
    private JPanel sismosPanel;
    private JButton mostrarSismosButton;
    private JButton anadirSismosButton;
    private JButton modificarSismoButton;

    public Modulo_Sismos() {
        super("Modulo de Sismos");
        setContentPane(sismosPanel);
        this.pack();
        mostrarSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelMostrarSismos = new Mostrar_Sismos();
                panelMostrarSismos.setVisible(true);
            }
        });
        anadirSismosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelAnadirSismo = new Anadir_Sismo();
                panelAnadirSismo.setVisible(true);
            }
        });
        modificarSismoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame panelModificarSismo = new Modificar_Sismo();
                panelModificarSismo.setVisible(true);
            }
        });
    }
}
