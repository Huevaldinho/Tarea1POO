package vista;

import javax.swing.*;

/**
 * Interfaz que muestra los datos personales de los creadores
 */

public class Sobre_Nosotros extends JFrame {
    private JPanel sobreNosotrosPanel;

    Sobre_Nosotros() {
        super("Sobre Nosotros");
        setContentPane(sobreNosotrosPanel);
        setResizable(false);
        this.pack();
    }
}
